<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import store from "@/store";
export const storeName = store.state.userid === undefined ? 'store': store.user.userid;

export default {
  name: 'App',
  created() {
    window.addEventListener('beforeunload', () => {
      sessionStorage.setItem(storeName, JSON.stringify(store.state))
    })
  }

}
</script>
