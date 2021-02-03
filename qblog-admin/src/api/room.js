/* eslint-disable */
import request from '@/utils/request'

// 获取咨询室列表
export function getRoomInfo() {
    return request({
        url: '/vue-admin-template/room/list',
        method: 'get',
    })
}
// 咨询室信息【id name address】

// 添加咨询室
export function addRoomInfo(params) {
    return request({
        url: '/vue-admin-template/room/list',
        method: 'post',
        data: {
            id: params.id,
            name: params.name,
            address: params.address
        }
    })
}

// 删除咨询室
export function deleteRoomInfo(params) {
    return request({
        url: '/vue-admin-template/room/list',
        method: 'get',
        data: {
            id: params,
        }
    })
}

// 编辑咨询室信息
export function EditRoomInfo(params) {
    return request({
        url: '/vue-admin-template/room/view',
        method: 'get',
        data: {
            id: params.id,
            name: params.name,
            address: params.address
        }
    })
}

// 获取咨询室日程限制信息
export function getRoomConstraint(params) {
    return request({
        url: '/vue-admin-template/order/orderPage',
        method: 'post',
        data: {
            room: params
        }
    })
}

// 获取咨询室日程
export function getRoomCalendar(params) {
    return request({
        url: '/vue-admin-template/order/orderPage',
        method: 'post',
        data: {
            room: params
        }
    })
}
// 以event的形式返回吧
