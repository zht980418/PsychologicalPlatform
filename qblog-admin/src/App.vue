<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import store from "@/store";

export default {
  name: 'App',
  created(){
    window.addEventListener('beforeunload', () => {
      sessionStorage.setItem('state', JSON.stringify(store.state))
    })
  }

}
</script>
