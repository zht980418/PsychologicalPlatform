function transDoctor(doc) {
    doc['doctorId'] = doc.userid
    doc['doctorName'] = doc.nickname
    delete (doc.userid)
    delete (doc.nickname)
}

export function transDocList(list) {
    for (let i = 0; i < list.length; i++) {
        transDoctor(list[i])
    }
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