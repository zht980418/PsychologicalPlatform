<template>
  <el-row :gutter="0">
    <el-col :span="14" :offset="5">
      <div class="tag-box">
        <router-link :to="'/tag/' + tag" v-for="(tag,index) in tags" :key="index">
          <el-tag
            :class="tag === $route.params.name ? 'tag-selected' : ''"
            effect="plain"
            size="small"
          >{{tag}}</el-tag>
        </router-link>
      </div>
      <article-item v-for="article in pageInfo.records" :key="article.id" :article="article"></article-item>
      <el-pagination
        background
        @current-change="handleCurrentChange"
        :current-page.sync="pageInfo.current"
        :page-size="pageInfo.size"
        layout="prev, pager, next, jumper"
        :total="pageInfo.total"
        :hide-on-single-page="true"
      ></el-pagination>
    </el-col>
  </el-row>
</template>

<script>
import request from "@/http/request";

export default {
  name: "Tag",
  data() {
    return {
      pageInfo: [],
      tags: [],
    };
  },
  components: {
    ArticleItem: () => import("@/components/ArticleItem.vue"),
  },
  methods: {
    getArticlesInTagView(page, limit) {
      const tag = this.$route.params.name;
      if (tag === "all") {
        request
          .getArticles(page, limit)
          .then((res) => {
            if (res.code === 0) {
              this.pageInfo = res.data;
            } else {
              this.$notify.error({
                title: "提示",
                message: res.msg,
              });
            }
          })
          .catch((err) => {
            console.log(err);
            this.$notify.error({
              title: "提示",
              message: "网络忙，文章获取失败",
            });
          });
      } else {
        request
          .getArticlesByTag(tag, page, limit)
          .then((res) => {
            if (res.code === 0) {
              this.pageInfo = res.data;
            } else {
              this.$notify.error({
                title: "提示",
                message: res.msg,
              });
            }
          })
          .catch((err) => {
            console.log(err);
            this.$notify.error({
              title: "提示",
              message: "网络忙，文章获取失败",
            });
          });
      }
    },
    loadData() {
      request
        .getAllTags()
        .then((res) => {
          if (res.code === 0) {
            this.tags = res.data;
          } else {
            this.$notify.error({
              title: "提示",
              message: res.msg,
            });
          }
        })
        .catch((err) => {
          console.log(err);
          this.$notify.error({
            title: "提示",
            message: "网络忙，标签获取失败",
          });
        });
      this.getArticlesInTagView(1, 5);
    },
    handleCurrentChange(page) {
      this.getArticlesInTagView(page, 5);
    },
  },
  beforeRouteUpdate(to, from, next) {
    next();
    console.log(to);
    console.log(from);
    this.loadData();
  },
  created() {
    this.loadData();
  },
};
</script>

<style scoped>
.tag-box {
  margin: 10px 0;
}
.tag-box .el-tag {
  margin: 0 2px;
}
.tag-box .el-tag:hover {
  background-color: #409eff;
  color: #ffffff;
  cursor: pointer;
}
.tag-selected {
  background-color: #409eff;
  color: #ffffff;
  cursor: pointer;
}
.el-pagination {
  margin: 20px 0;
  text-align: center;
}
</style>