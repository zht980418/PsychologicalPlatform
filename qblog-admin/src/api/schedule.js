/* eslint-disable */
import request from '@/utils/request'

// 获取排班表
export function getSchedule() {
    return request({
        url: '/vue-admin-template/schedule/list',
        method: 'post',
    })
}
//以event形式返回吧

// 新增排班
export function addApplication(params) {
    return request({
        url: '/vue-admin-template/schedule/list',
        method: 'post',
        data: {
            Id: params.doctorId,
            start: params.start,
            end: params.end,
            daysOfWeek: params.daysOfWeek
        }
    })
}

// 删除排班
export function deleteApplication(params) {
    return request({
        url: '/vue-admin-template/schedule/list',
        method: 'post',
        data: {
            Id: params.doctorId,
            start: params.start,
            end: params.end,
            daysOfWeek: params.daysOfWeek
        }
    })
}
