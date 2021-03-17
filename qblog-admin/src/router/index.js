import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRouterMap = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
]

export const asyncRouterMap = [
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '仪表盘', icon: 'dashboard' }
    }]
  },

  {
    path: '/example',
    component: Layout,
    redirect: '/example/table',
    name: 'Example',
    meta: { title: '我的文章', icon: 'el-icon-document', role: ['管理员', '编辑'] },
    children: [
      {
        path: 'table',
        name: 'Table',
        component: () => import('@/views/table/index'),
        meta: { title: '文章管理', icon: 'table', role: ['管理员', '编辑'] }
      },
      {
        path: 'editor',
        name: 'Editor',
        component: () => import('@/views/editor/index'),
        meta: { title: '写文章', icon: 'el-icon-edit', role: ['管理员', '编辑'] }
      }
    ]
  },
  {
    path: '/courseManager',
    component: Layout,
    meta: { title: '课程管理', icon: 'el-icon-collection', role: ['管理员', '编辑'] },
    children: [
      {
        path: 'index',
        name: 'CourseManager',
        component: () => import('@/views/courseManage/courseList'),
        meta: { title: '课程列表', icon: 'el-icon-collection', role: ['管理员', '编辑'] }
      },
      {
        path: 'courseAddPage',
        name: 'CourseAddPage',
        component: () => import('@/views/courseManage/addPage'),
        meta: { title: '添加课程', noCache: true, icon: 'el-icon-circle-plus-outline', role: ['管理员', '编辑'] },
        // hidden: true
      },
    ]
  },
  {
    path: '/message',
    component: Layout,
    redirect: '/message/list',
    name: 'Message',
    meta: { title: '我的留言', icon: 'el-icon-message', role: ['管理员', '咨询师'] },
    children: [
      {
        path: 'list',
        name: 'List',
        component: () => import('@/views/message/index'),
        meta: { title: '留言列表', icon: 'el-icon-s-comment', role: ['管理员', '咨询师'] }
      }
    ]
  },
  {
    path: '/order',
    component: Layout,
    redirect: '/order/orderCalendar',
    name: 'Order',
    meta: { title: '日程管理', icon: 'el-icon-date', role: ['管理员', '咨询师'] },
    children: [
      {
        path: 'orderCalendar',
        name: 'OrderCalendar',
        component: () => import('@/views/order/order'),
        meta: { title: '我的日程', icon: 'el-icon-date', role: ['管理员', '咨询师'] }
      },
      {
        path: 'orderPage',
        name: 'OrderPage',
        component: () => import('@/views/order/orderPage'),
        meta: { title: '预约详情', icon: 'el-icon-date', role: ['管理员', '咨询师'] },
        hidden: true
      },
      {
        path: 'apply',
        name: 'Apply',
        component: () => import('@/views/order/apply'),
        meta: { title: '排班申请', icon: 'el-icon-s-order', role: ['管理员', '咨询师'] }
      }
    ]
  },
  {
    path: '/info',
    component: Layout,
    redirect: '/info',
    name: 'Info',
    children: [
      {
        path: 'info',
        name: 'info',
        component: () => import('@/views/info/index'),
        meta: { title: '个人中心', icon: 'el-icon-user', role: ['管理员', '编辑', '咨询师'] },
      }
    ]
  },
  {
    path: '/record',
    component: Layout,
    redirect: '/record',
    name: 'Record',
    meta: { title: '咨询管理', icon: 'el-icon-notebook-1', role: ['管理员', '咨询师'] },
    children: [
      {
        path: 'recordByUser',
        name: 'recordByUser',
        component: () => import('@/views/record/index'),
        meta: { title: '用户查询', icon: 'el-icon-search', role: ['管理员', '咨询师'] },
      },
      {
        path: 'viewRecord',
        name: 'viewRecord',
        component: () => import('@/views/record/viewRecord'),
        hidden: true,
        meta: { title: '查看记录', icon: 'el-icon-edit-outline', role: ['管理员', '咨询师'] },
      },
      {
        path: 'addRecord',
        name: 'addRecord',
        component: () => import('@/views/record/record'),
        hidden: true,
        meta: { title: '表格填写', icon: 'el-icon-edit-outline', role: ['管理员', '咨询师'] },
      },
      {
        path: 'modifyRecordTable',
        name: 'modifyRecordTable',
        component: () => import('@/views/record/modifyRecordTable'),
        meta: { title: '表格编辑', icon: 'el-icon-edit', role: ['管理员', '咨询师'] },
      },
    ]
  },
  {
    path: '/userManage',
    component: Layout,
    redirect: '/userManage/userManage',
    name: 'userManage',
    meta: { title: '用户管理', icon: 'el-icon-s-data', role: ['管理员'] },
    children: [
      {
        path: 'userManage',
        component: () => import('@/views/userManage/index'),
        meta: { title: '用户查询', icon: 'el-icon-user', role: ['管理员'] },
      },
      {
        path: 'modifyUser',
        name: 'modifyUser',
        component: () => import('@/views/userManage/modifyUser'),
        meta: { title: '用户编辑', icon: 'el-icon-user', role: ['管理员'] },
        hidden: true,
      },
      {
        path: 'addUser',
        name: 'addUser',
        component: () => import('@/views/userManage/addUser'),
        meta: { title: '添加用户', icon: 'el-icon-user', role: ['管理员'] },
      },

    ]
  },
  {
    path: '/room',
    component: Layout,
    redirect: '/room/list',
    name: 'Room',
    meta: { role: ['管理员'] },
    children: [
      {
        path: 'list',
        name: 'room',
        component: () => import('@/views/room/index'),
        meta: { title: '咨询室管理', icon: 'el-icon-s-home', role: ['管理员'] },
      },
      {
        path: 'view',
        name: 'View',
        component: () => import('@/views/room/view'),
        meta: { title: '咨询室管理', icon: 'el-icon-s-home', role: ['管理员'] },
        hidden: true
      }
    ]
  },
  {
    path: '/schedule',
    component: Layout,
    name: 'Schedule',
    redirect: '/schedule/list',
    meta: { role: ['管理员'] },
    children: [
      {
        path: '/list',
        name: 'schedule',
        component: () => import('@/views/schedule/index'),
        meta: { title: '排班管理', icon: 'el-icon-set-up', role: ['管理员'] },
      },
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
