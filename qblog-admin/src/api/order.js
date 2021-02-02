import request from '@/utils/request'

// 获取限制信息
export function getConstraint(params) {
  return request({
    url: '/vue-admin-template/order/list',
    method: 'get',
    data: {
      doctorId: params
    }
  })
}

// 获取预约信息
export function getOrder(params) {
  return request({
    url: '/vue-admin-template/order/list',
    method: 'get',
    data: {
      doctorId: params
    }
  })
}

// 存储预约信息
export function postOrder(params) {
  return request({
    url: '/vue-admin-template/order/list',
    method: 'post',
    params
  })
}
