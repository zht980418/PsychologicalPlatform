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
// 预约信息，
// [
//  {
//   id: ,
//   title: ,
//   start: ,
//   end:'
//  },
// ]

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
// 返回from表格内容，和下面一样的

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
// 把这些信息存在form中，
// 根据type类型判断线上线下，线下的话还要存储一下room信息，就是把这个预约也添加到咨询室的日程表中

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
// 删预约
