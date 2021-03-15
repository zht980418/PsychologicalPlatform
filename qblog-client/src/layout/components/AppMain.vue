<template>
  <div>
    <el-row>
      <router-view :key="key" />
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'AppMain',
  computed: {
    key() {
      return this.$route.path
    }
  }
}
</script>

<style>
</style>