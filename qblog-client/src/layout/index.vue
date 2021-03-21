<template>
  <div>
    <el-row>
      <el-col
        :xs="{span:24,offset:0}"
        :sm="{span:24,offset:0}"
        :md="{span:22,offset:1}"
        :lg="{span:20,offset:2}"
        :xl="{span:18,offset:3}"
      >
        <Header />
        <NavMenu></NavMenu>
        <AppMain />
      </el-col>
    </el-row>
    <Footer />
  </div>
</template>

<script>

export default {
  name: 'Layout',
  components: {
    Header: () => import("@/components/Header"),
    Footer: () => import("@/components/Footer"),
    NavMenu: () => import("@/layout/components/NavMenu/index"),
    AppMain: () => import("@/layout/components/AppMain")
  },
  data() {
    return {
      img_SCU: require('@/assets/SCU_LOGO.png')
    }
  },
  methods: {
  }
}
</script>

<style lang="scss" scoped>
@import "~@/styles/mixin.scss";
@import "~@/styles/variables.scss";
</style>