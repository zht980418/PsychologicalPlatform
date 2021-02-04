import axios from 'axios'
import urls from "@/http/urls"
import store from "@/store/index"
import router from "@/router/index"
import { Notification } from 'element-ui'

const instance = axios.create({
  baseURL: urls.baseUrl,
  timeout: 5000,
});

instance.interceptors.request.use((config) => {
  if (store.state.token) {
    config.headers.Authorization = store.state.token;
  }
  return config;
});

instance.interceptors.response.use(
  (response) => {
    const data = response.data;
    if (data.code === 40001) {
      router.replace({
        path: "login",
        query: { redirect: router.currentRoute.fullPath }, // 将跳转的路由path作为参数，登录成功后跳转到该路由
      });
    }
    return response;
  },
  (error) => {
    //默认除了2XX之外的都是错误的，就会走这里
    Notification.error({
      title: "提示",
      message: "网络忙，请稍后再试",
    })
    return Promise.reject(error.response);
  }
);

export default {
  getArticles(page, limit) {
    return instance.get(urls.articles, {
      params: {
        page: page,
        limit: limit
      }
    }).then(res => res.data);
  },
  getArticleByID(id) {
    return instance.get(urls.article + "/" + id).then(res => res.data);
  },
  getAllTags() {
    return instance.get(urls.tags).then(res => res.data);
  },
  getArticlesByTag(tag, page, limit) {
    return instance.get(urls.tag + "/" + tag, {
      params: {
        page: page,
        limit: limit
      }
    }).then(res => res.data);
  },
  getAllCategories() {
    return instance.get(urls.categories).then(res => res.data);
  },
  getArticlesByCategory(category, page, limit) {
    return instance.get(urls.category + "/" + category, {
      params: {
        page: page,
        limit: limit
      }
    }).then(res => res.data);
  },
  getBlogInfo() {
    return instance.get(urls.info).then(res => res.data);
  },
  getTimeline() {
    return instance.get(urls.timeline).then(res => res.data);
  },
  getMessages(page, limit) {
    return instance.get(urls.messages, {
      params: {
        page: page,
        limit: limit
      }
    }).then(res => res.data);
  },
  postMessages(message) {
    return instance.post(urls.messages, message).then(res => res.data);
  },

  /**
   * 获取医生预约限制条件
   * @param {doctorId:number} doctorId 医生id
   */
  async getConstraintById(doctorId) {
    return instance.get(urls.doctordate + '/' + doctorId).then(res => res.data)
  },

  /**
   * 获取医生预约日程表
   * @param {doctorId:number} doctorId 医生id
   */
  async getCalendarById(doctorId) {
    return instance.get(urls.doctorCalendar + '/' + doctorId).then(res => res.data)
  },

  /**
   * 添加预约
   * @param {*} form 预约信息
   */
  postOrder(form) {
    console.log(form)
    return instance.post(urls.doctorCalendar, form).then(res => res.data)
  },

  /**
   * 获取预约信息【form】
   * @param {orderId:number} orderId 预约id
   */
  getOrderById(orderId) {
    return instance.get(urls.order + '/' + orderId).then(res => res.data)
  },

  /**
   * 删除预约信息【form】
   * @param {orderId:number} orderId 预约id
   */
  deleteOrderById(orderId) {
    return instance.delete(urls.order + '/' + orderId).then(res => res.data)
  },

  /**
   * 修改预约信息
   * @param {orderId:number} orderId 预约id
   */
  updateOrderById(orderId) {
    return instance.put(urls.order + '/' + orderId).then(res => res.data)
  }
}