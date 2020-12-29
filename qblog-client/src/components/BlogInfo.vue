<template>
  <el-card class="main-con">
    <el-avatar :size="96" :src="blogInfo.avatar" fit="cover"></el-avatar>
    <div class="nickname">{{blogInfo.nickname}}</div>
    <div class="info-box">
      <div class="text-center">
        <div class="info-count">{{blogInfo.articleCount}}</div>
        <div class="info-desc">文章数</div>
      </div>
      <div class="text-center">
        <div class="info-count">{{blogInfo.totalViews}}</div>
        <div class="info-desc">浏览量</div>
      </div>
    </div>
    <el-divider></el-divider>
    <i class="el-icon-menu">文章分类</i>
    <router-link
      :to="'/category/' + category.name"
      class="category-item"
      v-for="(category,index) in categories"
      :key="index"
    >
      {{category.name}}
      <el-tag type="success" size="small" effect="dark">{{category.count}}</el-tag>
    </router-link>
    <el-divider></el-divider>
    <i class="el-icon-price-tag">文章标签</i>
    <div class="tag-box">
      <router-link :to="'/tag/' + tag" v-for="(tag,index) in tags" :key="index">
        <el-tag effect="plain" size="small">{{tag}}</el-tag>
      </router-link>
    </div>
  </el-card>
</template>

<script>
import request from "@/http/request";
import { mapState } from "vuex";

export default {
  name: "BlogInfo",
  data() {
    return {
      categories: [],
      tags: [],
    };
  },
  computed: mapState(["blogInfo"]),
  created() {
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
    request
      .getAllCategories()
      .then((res) => {
        if (res.code === 0) {
          this.categories = res.data;
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
          message: "网络忙，文章分类获取失败",
        });
      });
  },
};
</script>

<style scoped>
.main-con {
  margin-top: 20px;
}
.el-avatar {
  display: block;
  margin: 0 auto;
}
.nickname {
  margin-top: 5px;
  color: #303133;
  font-size: 16px;
  text-align: center;
}
.info-box {
  margin-top: 10px;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.info-count {
  color: #303133;
  font-weight: bold;
  font-size: 20px;
}
.info-desc {
  color: #909399;
  font-size: 12px;
}
.category-item {
  display: block;
  text-decoration: none;
  font-size: 14px;
  color: #606266;
  margin-top: 10px;
  padding: 8px;
  transition: all 0.5s;
  border-radius: 5px;
  border: 1px solid #ebeef5;
}
.category-item:hover {
  box-shadow: rgba(0, 0, 0, 0.1) 0px 1px 8px 0px;
  cursor: pointer;
  transform: scale(1.03);
  color: #303133;
}
.category-item .el-tag {
  float: right;
}
.tag-box {
  margin-top: 10px;
}
.tag-box .el-tag {
  margin: 0 2px;
}
.tag-box .el-tag:hover {
  background-color: #409eff;
  color: #ffffff;
  cursor: pointer;
}
</style>