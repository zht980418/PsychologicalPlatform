import request from '@/utils/request'
import urls from './urls'
import { transUserList } from '@/utils/user-utils'

export function login(data) {
  console.log(data)
  return request({
    url: '/logintest',
    method: 'post',
    data
  })
}

export function register(data) {
  return request({
    url: '/register',
    method: 'post',
    data
  })
}

export function getUserById(id) {
  return request({
    url: '/logintest/' + id,
    method: 'get',
    data: {
      id: id
    }
  })
}

/**
 * 通过姓名搜索用户
 * @param {name:String} name
 * @returns {uid:String,name:String,phone:Number}
 */
export function getUserByName(name) {
  return request({
    url: urls.User + '/' + name,
    method: 'get',
  }).then(res => {
    console.log(res)
    transUserList(res.data)
    return res
  })
}

export function getUsers() {
  return request({
    url: '/logintestall',
    method: 'get'
  })
}

export function modifyUser(id, data) {
  return request({
    url: '/register/' + id,
    method: 'put',
    data
  })
}

export function deleteUser(id) {
  return request({
    url: '/register/' + id,
    method: 'delete'
  })
}

export function logout() {
  return request({
    url: '/auth/logout',
    method: 'post'
  })
}
