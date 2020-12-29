<template>
  <el-card>
    <div slot="header">
      <router-link class="main-text" :to="'/post/' + article.id" v-html="article.title"></router-link>
      <div class="article-info">
        <el-tag effect="dark" size="mini">原创</el-tag>
        浏览量：{{article.views}} 分类：
        <router-link
          class="link secondary-text"
          :to="'/category/'+article.category"
        >{{article.category}}</router-link>
      </div>
    </div>
    <div class="tabloid">{{article.tabloid}}</div>
    <i class="el-icon-user-solid article-icon">{{article.author}}</i>
    <i class="el-icon-date article-icon">{{article.gmtCreate}}</i>
    <i class="el-icon-price-tag article-icon">
      <router-link
        class="tag"
        v-for="(tag,index) in article.tags"
        :key="index"
        v-text="tag"
        :to="'/tag/'+tag"
      ></router-link>
    </i>
  </el-card>
</template>

<script>
export default {
  name: "ArticleItem",
  props: ["article"],
};
</script>

<style scoped>
.el-card {
  margin-top: 20px;
}
.article-info {
  margin-top: 10px;
  color: #909399;
  font-size: 13px;
}
.article-icon,
.article-icon .tag {
  color: #909399;
  font-size: 13px;
  margin-right: 10px;
  text-decoration: none;
}
.article-icon .tag:hover {
  color: #409eff;
  cursor: pointer;
}
.tabloid {
  color: #606266;
  font-size: 14px;
  margin-bottom: 10px;
}
</style>