import instance from '@/http/request'
import urls from "@/http/urls"
import { transDocList } from '@/utils/doctor-utils'
import { transOrderList } from '@/utils/event-utils'

export {
    getDoctorList, getConstraintById, getCalendarById, postOrder, getOrderById, deleteOrderById, updateOrderById, getOrderHistoryById
}

/**
 * 获取医生列表
 * @param {doctorId:number} doctorId 
 * @returns {doctorName:String,doctorId:number,avatar}
 */
function getDoctorList() {
    return instance.get(urls.doctorList).then(res => {
        transDocList(res.data.data)
        return res.data
    })
}

/**
 * 获取医生预约限制条件
 * @param {doctorId:number} doctorId 医生id
 */
function getConstraintById(doctorId) {
    return instance.get(urls.doctordate + '/' + doctorId).then(res => res.data)
}

/**
 * 获取医生预约日程表
 * 返回 orderid title【name】 start end uid
 * @param {doctorId:number} doctorId 医生id
 */
function getCalendarById(doctorId) {
    return instance.get(urls.doctorCalendar + '/' + doctorId).then(res => res.data)
}

/**
 * 添加预约
 * @param {*} form 预约信息
 */
function postOrder(form) {
    console.log(form)
    return instance.post(urls.order, form).then(res => res.data)
}

/**
 * 获取预约信息【form】
 * @param {orderId:number} orderId 预约id
 */
function getOrderById(orderId) {
    return instance.get(urls.order + '/' + orderId).then(res => res.data)
}

/**
 * 删除预约信息【form】
 * @param {orderId:number} orderId 预约id
 */
function deleteOrderById(orderId) {
    return instance.delete(urls.order + '/' + orderId).then(res => res.data)
}

/**
 * 修改预约信息
 * @param {orderId:number} orderId 预约id
 */
function updateOrderById(orderId, form) {
    return instance.put(urls.order + '/' + orderId, form).then(res => res.data)
}

/**
 * 获取用户预约列表【根据uid在form表查找】
 * @param {uid:number} uid
 * @returns {orderId,doctorName,status,start,end} 
 */
function getOrderHistoryById(uid) {
    return instance.get(urls.orderHistory + '/' + uid).then(res => {
        transOrderList(res.data.data)
        console.log(res.data.data);
        return res.data
    })
}