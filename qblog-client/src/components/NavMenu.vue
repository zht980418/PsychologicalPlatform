<template>
  <el-menu default-active="/" mode="horizontal" router>
    <el-menu-item index="/">首页</el-menu-item>
    <el-submenu index="2">
      <template slot="title">心理教育</template>
      <el-menu-item index="/category/all">分类浏览</el-menu-item>
      <el-menu-item index="/tag/all">标签浏览</el-menu-item>
      <!-- <el-menu-item index="/timeline">时间线</el-menu-item> -->
    </el-submenu>
    <!--<el-menu-item index="/message">留言板</el-menu-item>-->
    <el-menu-item index="/quiz">心理测量问卷</el-menu-item>
    <el-menu-item index="/order">心理咨询预约</el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "NavMenu"
};
</script>

<style scoped>
</style>