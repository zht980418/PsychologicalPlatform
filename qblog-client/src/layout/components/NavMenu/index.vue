<template>
  <el-row>
    <el-col
      :xs="{span:16,offset:4}"
      :sm="{span:16,offset:4}"
      :md="{span:18,offset:4}"
      :lg="{span:18,offset:5}"
      :xl="{span:18,offset:5}"
    >
      <el-menu
        mode="horizontal"
        menu-trigger="hover"
        default-active="/home"
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
        <!-- <el-menu-item
          v-for="route in routes"
          :key="route.path"
          :index="route.path"
          :base-path="route.path"
        >
          {{ route.meta.title }}
        </el-menu-item> -->
        <!-- <navItem
          v-for="route in routes"
          :key="route.path"
          :item="route"
          :base-path="route.path"
        ></navItem> -->
      </el-menu>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "NavMenu",
  components: {
    // navItem: () => import('./components/index')
  },
  data() {
    return {
      route: []
    }
  },
  computed: {
    routes() {
      console.log(this.$router.options.routes)
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
