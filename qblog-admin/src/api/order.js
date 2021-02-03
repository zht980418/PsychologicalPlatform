import request from '@/utils/request'

// 获取医生日程限制信息
export function getDoctorConstraint(params) {
  return request({
    url: '/vue-admin-template/order/order',
    method: 'get',
    data: {
      doctorId: params
    }
  })
}

// 获取医生预约日程表
export function getDoctorCalendar(params) {
  return request({
    url: '/vue-admin-template/order/order',
    method: 'post',
    data: {
      doctorId: params
    }
  })
}

// 获取单个预约信息
export function getOrder(params) {
  return request({
    url: '/vue-admin-template/order/order',
    method: 'get',
    data: {
      doctorId: params
    }
  })
}

// 存储预约信息
export function postOrder(params) {
  return request({
    url: '/vue-admin-template/order/orderPage',
    method: 'post',
    data: {
      doctorId: params.doctorId,
      OrderId: params.OrderId,
      uid: params.uid,
      type: params.type,
      name: params.name,
      ordertime: params.ordertime,
      gender: params.gender,
      birth: params.birth,
      occupation: params.occupation,
      phone: params.phone.phone,
      address: params.address,
      emergency: params.emergency,
      emergencyphone: params.emergencyphone,
      question: params.question,
      family: params.family,
      expectation: params.expectation,
      history: params.history,
      test: params.test,
      room: params.room,
      start: params.start,
      end: params.end
    }
  })
}

// 删除预约信息
export function deleteOrder(params) {
  return request({
    url: '/vue-admin-template/order/orderPage',
    method: 'post',
    data: {
      orderId: params
    }
  })
}
