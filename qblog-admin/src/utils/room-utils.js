/* eslint-disable */
function transRoom(room) {
    room['roomId'] = room.roomid
    delete (room['roomid'])
}

export function transRoomList(list) {
    for (var i = 0; i < list.length; i++) {
        transRoom(list[i])
    }
}
