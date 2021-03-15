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

export default instance;

