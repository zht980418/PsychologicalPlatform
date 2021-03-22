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

export function transConstraintList(list) {
    for (let i = 0; i < list.length; i++) {
        list[i].startTime = list[i].start
        list[i].endTime = list[i].end
        list[i].daysOfWeek = list[i].daysofweek
        delete (list[i].start)
        delete (list[i].end)
        delete (list[i].daysofweek)
    }
    return list
}