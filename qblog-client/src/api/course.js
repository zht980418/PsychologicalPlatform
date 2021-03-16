import instance from '@/http/request'
import urls from "@/http/urls"
import { transCourseList, transCourse } from '@/utils/course-utils'

export { getCourseList, getCourse }

/**
 * // 获取课程列表
 * @params 
 * @returns {CourseId:number,CourseTitle:String}
 */
function getCourseList() {
    return instance.get(urls.courseList).then(res => {
        transCourseList(res.data.data)
        return res.data
    })
}


/**
 * // 获取单个课程信息
 * @param {courseId:number} courseId 
 * @returns {CourseId:number,CourseTitle:String,CourseDescription:String,CourseLink:String }
 */
function getCourse(courseId) {
    return instance.get(urls.course + '/' + courseId).then(res => {
        transCourse(res.data.data)
        return res.data
    })
}
