/* eslint-disable */
import urls from '@/api/urls'
import request from '@/utils/request'

/**
 * 获取咨询室列表,咨询室信息【roomid name address】
 */
export function getRoomList() {
    return request({
        url: urls.Room,
        method: 'get',
    })
}

/**
 * 添加咨询室
 * @param {roomId:number,name:String,address:String} params 咨询室信息
 */
export function postRoomInfo(params) {
    return request({
        url: urls.Room,
        method: 'post',
        data: {
            roomid: params.roomId,
            name: params.name,
            address: params.address
        }
    })
}

/**
 * 删除咨询室
 * @param {roomId:number} roomId 咨询室id
 */
export function deleteRoomInfoById(roomId) {
    return request({
        url: urls.Room + '/' + roomId,
        method: 'delete',
    })
}

/**
 * 编辑咨询室信息
 * @param {roomId:number}
 * @param {roomId:number,name:String,address:String} params 咨询室信息
 */
export function updateRoomInfoById(roomId, params) {
    return request({
        url: urls.Room + '/' + roomId,
        method: 'put',
        data: {
            name: params.name,
            address: params.address
        }
    })
}

/**
 * 获取咨询室日程限制信息
 * @param {roomId:number} roomId 咨询室id
 */
export function getRoomConstraintById(roomId) {
    return request({
        url: urls.RoomConstraint + '/' + roomId,
        method: 'get',
    })
}

/**
 * 获取咨询室日程,以event的形式返回
 * @param {roomId:number} roomId 咨询室id
 */
export function getRoomCalendarById(roomId) {
    return request({
        url: urls.RoomCalendar + '/' + roomId,
        method: 'get',
    })
}