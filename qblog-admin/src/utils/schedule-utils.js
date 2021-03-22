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

function getDate(dayOfWeek, start, end) {
    const today = new Date()
    const day = today.getDay()
    if (day === 0) {
        start = start.split('T')
        start = getTime(-dayOfWeek) + 'T' + start[1]
        end = end.split('T')
        end = getTime(-dayOfWeek) + 'T' + end[1]
    }
    else {
        start = start.split('T')
        start = getTime(1 - dayOfWeek) + 'T' + start[1]
        end = end.split('T')
        end = getTime(1 - dayOfWeek) + 'T' + end[1]
    }
    return { start, end }
    // //上周的开始时间
    // console.log(getTime(7))
    // //上周的结束时间
    // console.log(getTime(1))
    // //本周的开始时间
    // console.log(getTime(0))
    // //本周的结束时间
    // console.log(getTime(-6))
}

export const green = '#67C23A'
export const yellow = '#E6A23C'
export const red = '#F56C6C'
export const Lgreen = '#E1F3D8'
export const Lyellow = '#FAECD8'
export const Lred = '#FDE2E2'

function transSchedule(schedule) {
    schedule['id'] = schedule.appid
    schedule['title'] = schedule.doctorname
    schedule['roomId'] = schedule.roomid
    schedule['backgroundColor'] = schedule.roomId == -1 ? red : schedule.roomId ? green : yellow
    schedule['borderColor'] = schedule.roomId == -1 ? Lred : schedule.roomId ? Lgreen : Lyellow
    const tmp = getDate(schedule.daysofweek, schedule.start, schedule.end)
    schedule.start = tmp.start
    schedule.end = tmp.end
    delete (schedule['roomid'])
    delete (schedule['doctorname'])
    delete (schedule['appid'])
    delete (schedule['daysofweek'])
}

export function transScheduleList(list) {
    for (var i = 0; i < list.length; i++) {
        transSchedule(list[i])
    }
    return list
}

export function RetranSchedule(schedule) {
    schedule['appid'] = schedule.appId
    schedule['doctorid'] = schedule.doctorId
    schedule['doctorname'] = schedule.doctorName
    schedule['daysofweek'] = schedule.daysOfWeek
    delete (schedule['appId'])
    delete (schedule['doctorId'])
    delete (schedule['doctorName'])
    delete (schedule['daysOfWeek'])
    return schedule
}

function transConstraint(start, end) {
    start = start.split('T')[1].split('+')[0]
    end = end.split('T')[1].split('+')[0]
    return { start, end }
}

export function transConstraintList(list) {
    for (let i = 0; i < list.length; i++) {
        let tmp = transConstraint(list[i].start, list[i].end)
        list[i].startTime = tmp.start
        list[i].endTime = tmp.end
        list[i].daysOfWeek = list[i].daysofweek
        delete (list[i].start)
        delete (list[i].end)
        delete (list[i].daysofweek)
    }
    return list
}