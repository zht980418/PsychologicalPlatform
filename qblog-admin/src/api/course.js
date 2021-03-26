import urls from '@/api/urls'
import request from '@/utils/request'
import { transCourseList, transCourse, RetransCourse } from '@/utils/course-utils'
import { createEventId } from '@/utils/event-utils'


/**
 * // 获取课程列表
 * @params 
 * @returns {CourseId:number,CourseTitle:String}
 */

export function getCourseList() {
    return request({
        url: urls.CourseList,
        method: 'get'
    }).then((res) => {
        transCourseList(res.data)
        return res
    })
}

/**
 * // 获取单个课程信息
 * @param {courseId:number} courseId 
 * @returns {CourseId:number,CourseTitle:String,CourseDescription:String,CourseLink:String }
 */
export function getCourseById(courseId) {
    return request({
        url: urls.Course + '/' + courseId,
        method: 'get'
    }).then((res) => {
        transCourse(res.data)
        return res
    })
}

/**
 * 添加课程
 * @param {title,description,link} params 
 * @returns 
 */

export function postCourse(params) {
    params.courseId = createEventId()
    return request({
        url: urls.Course,
        method: 'post',
        data: RetransCourse(params)
    })
}

/**
 * 修改课程信息
 * @param {id:number} id 
 * @param {id,title,description,link} params 
 * @returns 
 */

export function updateCourseById(id, params) {
    return request({
        url: urls.Course + '/' + id,
        method: 'put',
        data: RetransCourse(params)
    })
}

export function deleteCourseById(id) {
    return request({
        url: urls.Course + '/' + id,
        method: 'delete'
    })
}