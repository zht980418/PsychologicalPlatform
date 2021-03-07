import request from '@/utils/request'

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

export function getUsers() {
  return request({
    url: '/logintest',
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
