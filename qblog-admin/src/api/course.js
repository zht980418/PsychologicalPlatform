import urls from '@/api/urls'
import request from '@/utils/request'
import { transCourseList, transCourse } from '@/utils/course-utils'


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
 * // TODO 获取单个课程信息
 * @param {courseId:number} courseId 
 * @returns {CourseId:number,CourseTitle:String,CourseDescription:String,CourseLink:String }
 */
export function getCourse() {
    return request({
        url: urls.Course,
        method: 'get'
    }).then((res) => {
        transCourse(res.data)
        return res
    })
}