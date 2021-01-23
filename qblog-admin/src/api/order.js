import request from '@/utils/request'

export function getConstraint(params) {
  return request({
    url: '/vue-admin-template/order/list',
    method: 'get',
    params
  })
}

export function postOrder(params) {
  return request({
    url: '/vue-admin-template/order/list',
    method: 'post',
    params
  })
}
