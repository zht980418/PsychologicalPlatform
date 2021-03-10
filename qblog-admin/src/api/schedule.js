/* eslint-disable */
import urls from '@/api/urls'
import request from '@/utils/request'

/**
 * 获取排班表，以event形式返回吧
 */
export function getSchedule() {
    return request({
        url: urls.Schedule,
        method: 'get',
    })
}

/**
 * 新增排班
 * @param {appId:number,doctorId:number,doctorName:number,start:Date,end:Date,daysOfWeek:number} params 排班时间参数
 */
export function postApplication(params) {
    console.log(params)
    return request({
        url: urls.Schedule,
        method: 'post',
        data: params
    })
}

/**
 * 删除排班
 * @param {appId:number} appId 排班事件id
 */
export function deleteApplicationById(appId) {
    return request({
        url: urls.Schedule + '/' + appId,
        method: 'delete',
    })
}

/**
 * 获取咨询室排班表event 
 * 返回数据：{appid,start,end,roomid,doctorid}名称随意，反正就这几个数据
 * @param {roomId:number} roomId
 */

export function getRoomScheduleById(roomId) {
    return request({
        url: urls.Schedule + '/' + roomId,
        method: 'get'
    })
}

/**
 * 修改咨询室排班
 * @param {appId:number} appId
 * @param {roomId:number} params 
 */
export function EditRoomSchedule(appId, params) {
    return request({
        url: urls.Schedule + '/' + appId,
        method: 'put',
        data: {
            roomid: params
        }
    })
}
