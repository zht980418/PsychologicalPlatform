import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken } from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login'] // no redirect whitelist

router.beforeEach(async (to, from, next) => {
  // start progress bar
  NProgress.start()
  // set page title
  document.title = getPageTitle(to.meta.title)
  // determine whether the user has logged in
  const hasToken = getToken()

  if (hasToken) {
    if (to.path === '/login') {
      // if is logged in, redirect to the home page
      next({ path: '/' })
      NProgress.done()
    } else {
      const hasGetUserInfo = store.getters.role
      console.log('路由守卫，检查是否成功拉取信息', hasGetUserInfo)
      if (hasGetUserInfo[0]) {
        console.log('检测到用户信息已获取，继续执行')
        next()
      } else {
        try {
          if (sessionStorage.getItem('state')) {
            store.replaceState(Object.assign({}, store.state,
              JSON.parse(sessionStorage.getItem('state'))))
          }
          // get user info
          console.log('没有检测到用户信息，开始拉取用户信息')
          await store.dispatch('user/getInfo')
          const roles = store.getters.role
          console.log('用户权限为:', roles)
          store.dispatch('GenerateRoutes', { roles }).then(() => { // 生成可访问的路由表
            console.log('开始根据权限生成路由表')
            router.addRoutes(store.getters.addRouters) // 动态添加可访问路由表
            next('/')
          })
        } catch (error) {
          // remove token and go to login page to re-login
          await store.dispatch('user/resetToken')
          Message.error(error || '出现错误，请重新登录')
          next(`/login?redirect=${to.path}`)
          NProgress.done()
        }
      }
    }
  } else {
    /* has no token*/

    if (whiteList.indexOf(to.path) !== -1) {
      // in the free login whitelist, go directly
      next()
    } else {
      // other pages that do not have permission to access are redirected to the login page.
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})
