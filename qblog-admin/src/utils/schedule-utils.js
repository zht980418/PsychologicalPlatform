/* eslint-disable */
function getTime(n) {
    var now = new Date()
    var year = now.getFullYear()
    //因为月份是从0开始的,所以获取这个月的月份数要加1才行
    var month = now.getMonth() + 1
    var date = now.getDate()
    var day = now.getDay()
    //判断是否为周日,如果不是的话,就让今天的day-1(例如星期二就是2-1)
    if (day !== 0) {
        n = n + (day - 1)
    }
    else {
        n = n + day
    }
    if (day) {
        //这个判断是为了解决跨年的问题
        if (month > 1) {
            month = month
        }
        //这个判断是为了解决跨年的问题,月份是从0开始的
        else {
            year = year - 1
            month = 12
        }
    }
    now.setDate(now.getDate() - n)
    year = now.getFullYear()
    month = now.getMonth() + 1
    date = now.getDate()
    let s = year + '-' + (month < 10 ? ('0' + month) : month) + '-' + (date < 10 ? ('0' + date) : date)
    return s
}

function transSchedule(schedule) {
    schedule['id'] = schedule.appid
    schedule['title'] = schedule.doctorid
    getDate(schedule.daysofweek, schedule.start, schedule.end)
    delete (schedule['appid'])
    delete (schedule['doctorid'])
    delete (schedule['daysofweek'])
}

export function transScheduleList(list) {
    for (var i = 0; i < list.length; i++) {
        transSchedule(list[i])
    }
    console.log(list)
}

function getDate(dayOfWeek, start, end) {
    const today = new Date()
    let day = today.getDay()
    // console.log(getTime(1 - dayOfWeek))
    start = start.split('T')
    start = getTime(1 - dayOfWeek) + 'T' + start[1]
    end = end.split('T')
    end = getTime(1 - dayOfWeek) + 'T' + end[1]
    // //上周的开始时间
    // console.log(getTime(7))
    // //上周的结束时间
    // console.log(getTime(1))
    // //本周的开始时间
    // console.log(getTime(0))
    // //本周的结束时间
    // console.log(getTime(-6))
}

