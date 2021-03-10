import { login, register, logout, getUserById } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    id: '',
    name: '',
    phoneNumber: '',
    avatar: '',
    role: '',
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_ID: (state, id) => {
    state.id = id
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_PHONE_NUMBER: (state, phoneNumber) => {
    state.phoneNumber = phoneNumber
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLE: (state, role) => {
    role == null ? state.role = '编辑' : state.role = role
  },

}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      // 用户名前后去掉空格
      login({ userid: username.trim(), password: password }).then(response => {
        // 获取返回值中的 data

        const { data } = response
        // 调用 mutations 中 SET_TOKEN 方法，将 token 存到 vuex 中
        commit('SET_TOKEN', data.token)
        commit('SET_ID', username)
        console.log('登录成功，state id=' + username)
        // 使用 js-cookie 插件，将 token 存入本地cookie中
        setToken(data.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  register({ commit }, userInfo) {
    const { username, password, } = userInfo
    return new Promise((resolve, reject) => {
      register({ userid: username.trim(), password: password, rolename: 'editor' }).then(response => {
        const { data } = response
        // 调用 mutations 中 SET_TOKEN 方法，将 token 存到 vuex 中
        commit('SET_TOKEN', data.token)
        // 使用 js-cookie 插件，将 token 存入本地cookie中
        setToken(data.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      console.log('当前用户id为: ' + state.id)
      getUserById(state.id).then(response => {
        const { data } = response
        console.log('拉取用户信息', data)
        if (!data) {
          return reject('认证失败，请重新登录')
        }

        const { nickname, phonenumber, rolename } = data

        commit('SET_NAME', nickname)
        commit('SET_PHONE_NUMBER', phonenumber)
        commit('SET_AVATAR', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif')
        commit('SET_ROLE', rolename.trim())
        console.log(state)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        resetRouter()
        commit('RESET_STATE')
        this.dispatch('ResetRouters')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

