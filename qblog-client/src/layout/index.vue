<template>
  <div>
    <Header />
    <el-row>
      <el-col
        :xs="{span:18,offset:3}"
        :sm="{span:18,offset:3}"
        :md="{span:18,offset:3}"
        :lg="{span:18,offset:5}"
        :xl="{span:18,offset:3}"
      >
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