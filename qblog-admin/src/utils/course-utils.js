export function transCourse(course) {
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

export function RetransCourse(course) {
    course['coursedescription'] = course.description
    course['courseid'] = course.courseId
    course['courselink'] = course.link
    course['coursetitle'] = course.title
    delete (course.description)
    delete (course.link)
    delete (course.title)
    delete (course.courseId)
    return course
}