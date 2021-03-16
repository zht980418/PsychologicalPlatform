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