function transCourse(course) {
    course['description'] = course.coursedescription
    course['courseId'] = course.courseid
    course['link'] = course.courselink
    course['title'] = course.coursetitle
    delete (course.coursedescription)
    delete (course.courseid)
    delete (course.courselink)
    delete (course.coursetitle)
}

export function transCourseList(list) {
    for (let i = 0; i < list.length; i++) {
        transCourse(list[i])
    }
}