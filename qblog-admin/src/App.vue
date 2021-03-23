<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import store from "@/store";
export const storeName = (new Date()).valueOf();

export default {
  name: 'App',
  created() {
    window.addEventListener('beforeunload', () => {
      sessionStorage.setItem(storeName, JSON.stringify(store.state))
    })
  }

}
</script>
