import { login, regist, logout, getUserById} from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    id:'',
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
    state.role = role
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
        // 使用 js-cookie 插件，将 token 存入本地cookie中
        setToken(data.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  register({commit}, userInfo){
    const { username, password, } = userInfo
    return new Promise(((resolve, reject) => {
    regist({ userid: username.trim(), password: password, rolename:'editor'}).then(response =>{
      const { data } = response
      // 调用 mutations 中 SET_TOKEN 方法，将 token 存到 vuex 中
      commit('SET_TOKEN', data.token)
      // 使用 js-cookie 插件，将 token 存入本地cookie中
      setToken(data.token)
      resolve()
    }).catch(error => {
      reject(error)
    })
    }))
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getUserById(state.id).then(response => {
        const { data } = response

        if (!data) {
          return reject('认证失败，请重新登录')
        }

        const {name, phoneNumber, avatar} = data

        commit('SET_NAME', name)
        commit('SET_PHONE_NUMBER', phoneNumber)
        commit('SET_AVATAR', avatar)
        commit('SET_ROLE', 'admin')
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

