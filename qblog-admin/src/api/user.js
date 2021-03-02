import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login',
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

export function getInfo(token) {
  return request({
    url: '/info',
    method: 'get'
  })
}

export function getUserById(id){
  return request({
    url: '/',
    method: 'post',
    id
  })
}

export function getUsers(){
  return request({
    url: '/',
    method: 'get'
  })
}

export function addUser(data){
  return request({
    url: '/',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/auth/logout',
    method: 'post'
  })
}
