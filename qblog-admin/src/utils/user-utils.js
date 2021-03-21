function transUser(user) {
    user.name = user.nickname
    user.phone = user.phonenumber
    user.uid = user.userid
    delete (user.nickname)
    delete (user.phonenumber)
    delete (user.userid)
}

export function transUserList(list) {
    for (let i = 0; i < list.length; i++) {
        transUser(list[i])
    }
    return list
}