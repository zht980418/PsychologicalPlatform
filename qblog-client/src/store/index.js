import Vue from 'vue';
import Vuex from 'vuex';
import request from '@/http/request';
import { Notification } from 'element-ui';

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    token: window.sessionStorage.getItem("token"),
    userInfo: null,
    blogInfo: { covers: [] }
  },
  mutations: {
    setToken(state, token) {
      state.token = token;
      window.sessionStorage.setItem("token", token);
    },
    removeToken(state) {
      state.token = "";
      state.userInfo = null;
      window.sessionStorage.removeItem("token");
    },
    setUserInfo(state, userInfo) {
      state.userInfo = userInfo;
    },
    setBlogInfo(state, blogInfo) {
      state.blogInfo = blogInfo;
    },
  },
  actions: {
    getBlogInfo({ commit }) {
      request.getBlogInfo().then(res => {
        if (res.code === 0) {
          commit('setBlogInfo', res.data);
        } else {
          Notification.error({
            title: '提示',
            message: res.msg
          });
        }
      }).catch(err => {
        console.log(err);
        Notification.error({
          title: '提示',
          message: "服务器忙，获取博客信息失败"
        });
      })
    }
  }
});

export default store;