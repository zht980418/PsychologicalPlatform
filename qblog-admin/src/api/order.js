import request from '@/utils/request'
import urls from '@/api/urls'

/**
 * 获取医生日程限制信息
 * @param {doctorId:number} doctorId 医生id
 */
export function getDoctorConstraintById(doctorId) {
  return request({
    url: urls.DoctorConstraint + '/' + doctorId,
    method: 'get',
  })
}

/**
 * 获取医生预约日程表
 * @param {doctorId:number} doctorId 医生id
 */
export function getDoctorCalendarById(doctorId) {
  return request({
    url: urls.DoctorCalendar + '/' + doctorId,
    method: 'get',
  })
}

/**
 * 获取单个预约信息,返回from表格内容，和下面一样的
 * @param {orderId:number} orderId 预约id
 */
export function getOrderById(orderId) {
  return request({
    url: urls.Form + '/' + orderId,
    method: 'get',
  })
}

/**
 * 存储预信息
 * 信息存在form中，
 * 根据type类型判断线上线下，线下的话还要存储一下room信息，就是把这个预约也添加到咨询室的日程表中
 * @param {*} params form表
 */

export function postOrder(params) {
  return request({
    url: urls.Form,
    method: 'post',
    data: {
      doctorid: params.doctorId,
      orderid: params.orderId,
      uid: params.uid,
      type: params.type,
      name: params.name,
      ordertime: params.ordertime,
      gender: params.gender,
      birth: params.birth,
      occupation: params.occupation,
      phone: params.phone,
      address: params.address,
      emergency: params.emergency,
      emergencyphone: params.emergencyphone,
      question: params.question,
      family: params.family,
      expectation: params.expectation,
      history: params.history,
      test: params.test,
      sleep: params.sleep,
      relationship: params.relationship,
      stress: params.stress,
      mood: params.mood,
      hurt: params.hurt,
      suicide: params.suicide,
      roomid: params.roomId,
      start: params.start,
      end: params.end
    }
  })
}

/**
 * 删除预约信息
 * @param {orderId:number} orderId 预约id
 */
export function deleteOrderById(orderId) {
  return request({
    url: urls.Form + '/' + orderId,
    method: 'delete',
  })
}

/**
 * 编辑form
 * @param {id:number} orderId orderId
 * @param {*} params 预约信息form
 */
export function updateOrderById(orderId, params) {
  return request({
    url: urls.Form + '/' + orderId,
    method: 'put',
    data: {
      doctorid: params.doctorId,
      orderid: params.orderId,
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
      roomid: params.roomId,
      start: params.start,
      end: params.end
    }
  })
}
