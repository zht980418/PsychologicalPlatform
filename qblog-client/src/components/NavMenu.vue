<template>

  <el-menu
    mode="horizontal"
    menu-trigger="hover"
    router
  >
    <el-menu-item index="/home">首页</el-menu-item>
    <el-submenu index="2">
      <template slot="title">心理教育</template>
      <el-menu-item index="/category/all">分类浏览</el-menu-item>
      <el-menu-item index="/tag/all">标签浏览</el-menu-item>
    </el-submenu>
    <el-menu-item index="/quiz">心理自测</el-menu-item>
    <el-menu-item index="/order">咨询预约</el-menu-item>
    <el-menu-item index="/info">个人中心</el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "NavMenu",
  data() {
    return {
      route: []
    }
  },
  computed: {
    routes() {
      return this.$router.options.routes
    },
  }

};

</script>

<style>
.el-menu-item {
  padding: 0.2rem 1rem;
  font-size: 18px;
}

.el-submenu__title {
  padding: 0.2rem 1rem;
  font-size: 18px;
}
</style>
