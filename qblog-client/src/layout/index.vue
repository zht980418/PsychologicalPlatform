<template>
  <div>
    <Header />
    <NavMenu></NavMenu>
    <AppMain />
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