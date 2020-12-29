<template>
  <el-row>
    <el-col :span="20" :offset="2">
      <el-card class="markdown-body">
        <h1>{{article.title}}</h1>
        <blockquote>{{'作者：' + article.author + ' | 创建时间：' + article.gmtCreate + ' | 浏览数：' + article.views + ' | 分类：' + article.category + ' | 标签：' + article.tags.join("、")}}</blockquote>
        <VueShowdown
          :markdown="article.content"
          flavor="vanilla"
          :options="{ emoji: true, tables: true }"
          :extensions="extensions"
        />
      </el-card>
    </el-col>
    <el-backtop></el-backtop>
  </el-row>
</template>

<script>
import request from "@/http/request";
import showdownHighlight from "showdown-highlight";

export default {
  name: "Article",
  data() {
    return {
      article: { content: "" },
      extensions: [showdownHighlight]
    };
  },
  created() {
    request
      .getArticleByID(this.$route.params.id)
      .then(res => {
        if (res.code === 0) {
          this.article = res.data;
        } else {
          this.$notify.error({
            title: "提示",
            message: res.msg
          });
        }
      })
      .catch(err => {
        console.log(err);
        this.$notify.error({
          title: "提示",
          message: "网络忙，文章详情获取失败"
        });
      });
  }
};
</script>

<style scoped>
.el-card {
  margin-top: 15px;
  padding: 20px;
}
</style>