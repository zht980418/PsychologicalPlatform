/* eslint-disable */
export function transRoom(room) {
    room['roomId'] = room.roomid
    delete (room['roomid'])
    console.log(room)
}

export function transRoomList(list) {
    for (var i = 0; i < list.length; i++) {
        transRoom(list[i])
    }
    console.log(list)
}