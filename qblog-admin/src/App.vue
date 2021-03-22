<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
  created() {
    console.log('页面加载')
    if (sessionStorage.getItem('state')) {
      this.$store.replaceState(Object.assign({}, this.$store.state,
        JSON.parse(sessionStorage.getItem('state'))))
    }

    window.addEventListener('beforeunload', () => {
      sessionStorage.setItem('state', JSON.stringify(this.$store.state))
    })
  }
}
</script>
