import Vue from 'vue'
import App from './App.vue'
import store from "@/store/index"
import router from "@/router/index"
import ElementUI from 'element-ui';
import '@/styles/index.scss' // global css
import 'element-ui/lib/theme-chalk/index.css';
import 'element-ui/lib/theme-chalk/display.css';
import 'github-markdown-css/github-markdown.css';
import 'highlight.js/styles/github.css';
import { Message } from 'element-ui'
import 'default-passive-events';
import { getToken } from '@/utils/auth' // get token from cookie

import '@/icons' // icon

Vue.config.productionTip = false
Vue.use(ElementUI);

import { VueShowdown } from 'vue-showdown'
Vue.component('VueShowdown', VueShowdown)

import VideoPlayer from 'vue-video-player'
Vue.use(VideoPlayer);
require('video.js/dist/video-js.css')
require('vue-video-player/src/custom-theme.css')

new Vue({
  render: h => h(App),
  store,
  router,
}).$mount('#app')

const whiteList = ['/login', '/',] // no redirect whitelist

router.beforeEach(async (to, from, next) => {
  const hasToken = getToken()
  console.log('路由守卫')
  // let userid = sessionStorage.getItem('userid');
  if (hasToken && store.state.user.id) {
    console.log('有token')
    if (to.path === '/login') {
      /* if is logged in, redirect to the home page*/next({ path: '/' })
    } else {
      next()
    }
  } else {
    /* has no token*/
    if (whiteList.indexOf(to.path) !== -1) {
      // in the free login whitelist, go directly
      next()
    } else {
      Message.info('请先登录')
      // other pages that do not have permission to access are redirected to the login page.
      next(`/login?redirect=${to.path}`)
    }
  }
}
)

