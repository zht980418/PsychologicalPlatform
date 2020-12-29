import Vue from 'vue'
import App from './App.vue'
import store from "@/store/index"
import router from "@/router/index"
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'github-markdown-css/github-markdown.css';
import 'highlight.js/styles/github.css'

Vue.config.productionTip = false
Vue.use(ElementUI);

import { VueShowdown } from 'vue-showdown'
Vue.component('VueShowdown', VueShowdown)

new Vue({
  render: h => h(App),
  store,
  router,
  created: initBlog
}).$mount('#app')

function initBlog() {
  store.dispatch('getBlogInfo');
}