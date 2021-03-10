<template>
  <section class="app-main">
    <el-col
      :xs="{span:16,offset:4}"
      :sm="{span:16,offset:4}"
      :md="{span:18,offset:4}"
      :lg="{span:18,offset:5}"
      :xl="{span:18,offset:5}"
    >
      <NavMenu></NavMenu>
    </el-col>
    <transition
      name="fade-transform"
      mode="out-in"
    >
      <router-view :key="key" />
    </transition>
  </section>
</template>

<script>
export default {
  name: 'AppMain',
  computed: {
    key() {
      return this.$route.path
    },
    title() {
      return this.$route.meta.title
    }
  }
}
</script>

<style scoped>
.app-main {
  /*50 = navbar  */
  min-height: calc(100vh - 50px);
  width: 100%;
  position: relative;
  overflow: hidden;
}
.fixed-header + .app-main {
  padding-top: 50px;
}
</style>

<style lang="scss">
// fix css style bug in open el-dialog
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 15px;
  }
}
</style>
