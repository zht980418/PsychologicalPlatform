import request from '@/utils/request'

export function login(data) {
  console.log(data)
  return request({
    url: '/logintest',
    method: 'post',
    data
  })
}

export function regist(data) {
  return request({
    url: '/register',
    method: 'post',
    data
  })
}

export function getUserById(id){
  return request({
    url: '/logintest/' + id,
    method: 'get',
    data: {
      id: id
    }
  })
}

// export function getUsers(){
//   return request({
//     url: '/logintest',
//     method: 'get'
//   })
// }

// export function addUser(data){
//   return request({
//     url: '/',
//     method: 'post',
//     data
//   })
// }

export function logout() {
  return request({
    url: '/auth/logout',
    method: 'post'
  })
}
