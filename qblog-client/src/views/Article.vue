<template>
  <el-row>
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
          <el-row>
            <div v-if="this.videoAddress" class="video" :src = "videoAdress" style="margin:auto;width : 1200px; height: 800px;">
              <iframe style="width: 100%; height:100%" :src="videoAddress"></iframe>
            </div>
          </el-row>
      </el-card>
    </el-col>
    </el-row>
    <el-backtop></el-backtop>
  </el-row>
</template>

<script>
import { getArticleByID } from "@/api/article"

import showdownHighlight from "showdown-highlight";

function getVideoAddress(content){
  const videoAddress = content.split('](').pop().slice(0,-1)
  return videoAddress === null ? 'https://www.bilibili.com/video/BV1Fy4y1m7T8' : videoAddress
}
export default {
  name: "Article",
  data() {
    return {
      article: { content: "" },
      extensions: [showdownHighlight],
      videoAddress : '',
    };
  },
  created() {
    getArticleByID(this.$route.params.id)
      .then(res => {
        if (res.code === 0) {
          this.article = res.data
          console.log(this.article)
          this.videoAddress = getVideoAddress(res.data.content)
          console.log(this.videoAddress)
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
