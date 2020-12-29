<template>
  <div class="app-container">
    <div class="title-box">
      <input type="text" class="title" v-model="article.title" placeholder="请输入标题..." />
      <el-button type="text" @click="openDialog">提交</el-button>
    </div>
    <mavon-editor ref="md" @imgAdd="$imgAdd" v-model="article.content" class="editor" />

    <el-dialog :title="`发布文章：${article.title}`" :visible.sync="showDialog" width="30%">
      <el-form label-position="left" label-width="80px">
        <el-form-item label="标签：" style="width:fit-content">
          <el-tag
            :key="tag"
            v-for="tag in article.tags"
            closable
            :disable-transitions="false"
            @close="handleTagClose(tag)"
          >{{tag}}</el-tag>
          <el-input
            v-if="inputTagVisible"
            v-model="inputTag"
            ref="saveTagInput"
            size="small"
            @keyup.enter.native="handleTagInputConfirm"
            @blur="handleTagInputConfirm"
            class="input-new-tag"
          ></el-input>
          <el-button v-else class="button-new-tag" size="small" @click="showTagInput">+ 新标签</el-button>
        </el-form-item>
        <el-form-item label="分类：">
          <el-select v-model="article.category" placeholder="请选择">
            <el-option
              v-for="(category,index) in categories"
              :key="index"
              :label="category"
              :value="category"
            ></el-option>
          </el-select>
          <el-button type="text" @click="showAddCategoryInput = true" class="add-category-btn">自定义分类</el-button>
        </el-form-item>
        <el-collapse-transition>
          <el-form-item label="新分类：" v-show="showAddCategoryInput">
            <el-input v-model="article.newCategory" @keypress.enter.native="addCategory"></el-input>
          </el-form-item>
        </el-collapse-transition>
        <el-form-item label="摘要：">
          <el-input type="textarea" v-model="article.tabloid" rows="4"></el-input>
        </el-form-item>
        <el-form-item label="作者：">
          <el-input type="text" v-model="article.author"></el-input>
        </el-form-item>
        <el-form-item>
          <el-checkbox v-model="article.type">原创</el-checkbox>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="handleCancel">取 消</el-button>
        <el-button type="primary" @click="handleSubmit">发布</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getAllCategories } from "@/api/category";
import { getQiniuToken, uploadToQiniuCloud } from "@/api/file";
import { postArticle, getArticleById, updateArticleById } from "@/api/article";
export default {
  data() {
    return {
      showDialog: false,
      categories: [],
      inputTag: "",
      inputTagVisible: false,
      showAddCategoryInput: false,
      article: {
        title: "",
        content: "",
        category: "",
        tags: [],
        tabloid: "",
        type: true,
        author: "",
        newCategory: "",
      },
    };
  },
  created() {
    const articleId = this.$route.params.articleId;
    if (articleId) {
      getArticleById(articleId).then((res) => {
        this.article = res.data;
        this.article.type = this.article.type === "原创";
      });
    }
    getAllCategories().then((res) => {
      this.categories = res.data.map((item) => item.name);
    });
  },
  methods: {
    // 断言 target 为非空
    assertNotEmpty(target, msg) {
      if (!target) {
        this.$message({
          message: msg,
          type: "warning",
        });
        return false;
      }
      return true;
    },
    // 打开文章信息填写框
    openDialog() {
      if (
        this.assertNotEmpty(this.article.title, "请填写文章标题") &&
        this.assertNotEmpty(this.article.content, "请填写文章内容")
      ) {
        this.showDialog = true;
      }
    },
    // 关闭tag时调用
    handleTagClose(tag) {
      this.article.tags.splice(this.article.tags.indexOf(tag), 1);
    },
    showTagInput() {
      this.inputTagVisible = true;
      this.$nextTick((_) => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },
    handleTagInputConfirm() {
      let inputTag = this.inputTag;
      if (inputTag) {
        this.article.tags.push(inputTag);
      }
      this.inputTagVisible = false;
      this.inputTag = "";
    },
    addCategory() {
      this.categories.push(this.article.newCategory);
      this.showAddCategoryInput = false;
      this.article.category = this.article.newCategory;
    },
    handleCancel() {
      this.showDialog = false;
    },
    handleSubmit() {
      if (
        this.assertNotEmpty(this.article.category, "分类不能为空") &&
        this.assertNotEmpty(this.article.tags.join(), "文章标签不能为空") &&
        this.assertNotEmpty(this.article.tabloid, "文章摘要不能为空") &&
        this.assertNotEmpty(this.article.author, "文章作者不能为空")
      ) {
        this.showDialog = false;
        if (this.article.id) {
          updateArticleById(this.article.id, this.article).then((res) => {
            this.$notify({
              title: "提示",
              message: `文章《${this.article.title}》更新成功`,
              type: "success",
            });
            this.$router.push("/example/table");
          });
        } else {
          postArticle(this.article).then((res) => {
            this.$notify({
              title: "提示",
              message: `文章《${this.article.title}》发布成功`,
              type: "success",
            });
            this.$router.push("/example/table");
          });
        }
      }
    },
    // 上传图片
    $imgAdd(position, $file) {
      getQiniuToken().then((res) => {
        const uploadInfo = res.data;
        let formData = new FormData();
        formData.append("file", $file);
        formData.append("token", uploadInfo.token);
        uploadToQiniuCloud(uploadInfo.uploadUrl, formData)
          .then((res) => {
            const hash = res.hash;
            if (hash) {
              this.$refs.md.$img2Url(position, uploadInfo.imageUrl + hash);
            } else {
              this.$message({
                message: "网络忙，图片上传失败",
                type: "error",
                duration: 5 * 1000,
              });
            }
          })
          .catch((err) => {
            console.log(err);
            this.$message({
              message: "网络忙，图片上传失败",
              type: "error",
              duration: 5 * 1000,
            });
          });
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.editor {
  height: 650px;
}
.title-box {
  display: grid;
  grid-template-columns: 22fr 2fr;
}
.title {
  background: none;
  color: #303133;
  text-indent: 0px;
  outline: none;
  font-size: 16px;
  font-weight: bold;
  display: block;

  border: none;
  border-bottom: 1px solid #e0e0e0;
  border-radius: 0;
  line-height: 1;
  overflow: visible;
  margin-bottom: 15px;
  padding: 4px;
}
.title:focus {
  border-color: #409eff;
}
.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
.add-category-btn {
  margin-left: 10px;
}
</style>