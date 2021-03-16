import urls from '@/api/urls'
import request from '@/utils/request'

/**
 * // TODO 获取课程列表
 * @params 
 * @returns {CourseId:number,CourseTitle:String}
 */

export function getCourseList() {
    return request({
        url: urls.CourseList,
        method: 'get'
    })
}

/**
 * // TODO 获取单个课程信息
 * @param {courseId:number} courseId 
 * @returns {CourseId:number,CourseTitle:String,CourseDescription:String,CourseLink:String }
 */
export function getCourse() {
    return request({
        url: urls.Course,
        method: 'get'
    })
}