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
 * @param {appId:number,doctorId:number,start:Date,end:Date,daysOfWeek:number} params 排班时间参数
 */
export function postApplication(params) {
    return request({
        url: urls.Schedule,
        method: 'post',
        data: {
            appId: params.appId,
            doctorId: parmas.doctorId,
            start: params.start,
            end: params.end,
            daysOfWeek: params.daysOfWeek
        }
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
