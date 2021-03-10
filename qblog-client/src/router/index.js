import VueRouter from "vue-router";
import Vue from 'vue';

Vue.use(VueRouter);

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: "/",
    name: "Home",
    component: () => import("@/views/Home.vue"),
    meta: {
      title: "心理健康中心",
    },
  },
  {
    path: "/post/:id",
    name: "Article",
    component: () => import("@/views/Article.vue"),
    meta: {
      title: "文章详情",
    },
  },
  {
    path: "/Login",
    name: "Login",
    component: () => import("@/views/Login.vue"),
    meta: {
      title: "登录注册",
    },
  },
  {
    path: "/category/:name",
    name: "Category",
    component: () => import("@/views/Category.vue"),
    meta: {
      title: "文章分类",
    },
  },
  {
    path: "/tag/:name",
    name: "Tag",
    component: () => import("@/views/Tag.vue"),
    meta: {
      title: "标签",
    },
  },
  {
    path: "/timeline",
    name: "TimeLine",
    component: () => import("@/views/TimeLine.vue"),
    meta: {
      title: "时间线",
    },
  },
  {
    path: "/message",
    name: "Message",
    component: () => import("@/views/Message.vue"),
    meta: {
      title: "留言板",
    },
  },
  {
    path: "/quiz",
    name: "Quiz",
    component: () => import("@/views/Quiz.vue"),
    // redirect:"/quiz/quiz1",
    meta: {
      title: "心理测量问卷",
    },
    children: [
      { path: "quiz1", component: () => import("@/views/quiz/Quiz1.vue") },
      { path: "quiz2", component: () => import("@/views/quiz/Quiz2.vue") },
      { path: "quiz3", component: () => import("@/views/quiz/Quiz3.vue") },
      { path: "quiz4", component: () => import("@/views/quiz/Quiz4.vue") },
    ]
  },
  {
    path: "/order",
    name: "Order",
    component: () => import("@/views/Order.vue"),
    meta: {
      title: "心理咨询预约",
    },
    children: [
      { path: "doctorlist", component: () => import("@/views/order/DoctorList.vue") },
      { path: "orderform", component: () => import("@/views/order/OrderForm.vue") },
      { name: "ordertable", path: "ordertable", component: () => import("@/views/order/OrderTable.vue") },
    ]
  },
  {
    path: "/info",
    name: "Info",
    component: () => import("@/views/info/index.vue"),
    meta: {
      title: "个人中心"
    }
  },
  {
    path: "/test",
    // component: Layout,
    meta: {}
  }
]

const router = new VueRouter({
  mode: "history",
  routes
})

router.beforeEach((to, from, next) => {
  console.log(from);
  if (to.meta.title) {
    document.title = to.meta.title;
  }
  next();
});

export default router;
