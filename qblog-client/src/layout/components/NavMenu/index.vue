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
        <el-menu-item
          v-for="route in routes"
          :key="route.path"
          :index="route.path"
        >
          {{ route.meta.title }}
        </el-menu-item>
        <el-submenu index="2">
          <template slot="title">心理教育</template>
          <el-menu-item
            v-for="route in children"
            :key="route.path"
            :index="route.path"
          >
            {{ route.meta.title }}
          </el-menu-item>
        </el-submenu>
      </el-menu>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "NavMenu",
  components: {
  },
  data() {
    return {
    }
  },
  computed: {
    routes() {
      var routes = this.$router.options.routes
      var list = []
      for (let i = 0; i < routes.length; i++) {
        if (routes[i].children && routes[i].children.length === 1 && !routes[i].hidden) {
          list.push(routes[i])
        }
      }
      console.log(list)
      return list
    },
    children() {
      var routes = this.$router.options.routes
      var list = []
      for (let i = 0; i < routes.length; i++) {
        if (routes[i].children && routes[i].children.length > 1) {
          for (let j = 0; j < routes[i].children.length; j++)
            list.push(routes[i].children[j])
        }
      }
      return list
    }
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
