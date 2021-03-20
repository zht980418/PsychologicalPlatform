// let eventGuid = 0
const todayStr = new Date().toISOString().replace(/T.*$/, '') // YYYY-MM-DD of today

export const INITIAL_EVENTS = [
  {
    id: 202102071320,
    title: '一个预约',
    start: todayStr + 'T10:00:00',
    color: 'red'
  },
  {
    id: createEventId(),
    title: '过时预约',
    start: todayStr + 'T14:00:00',
    color: 'red'
  }
]

export function createEventId() {
  const today = new Date()
  const y = today.getFullYear()
  let m = today.getMonth() + 1
  m = m < 10 ? '0' + m : m
  let d = today.getDate()
  d = d < 10 ? ('0' + d) : d
  let h = today.getHours()
  h = h < 10 ? ('0' + h) : h
  let mi = today.getMinutes()
  mi = mi < 10 ? ('0' + mi) : mi
  let s = today.getSeconds()
  s = s < 10 ? ('0' + s) : s
  let ms = today.getMilliseconds()
  ms = ms < 10 ? ('00' + ms) : (ms < 100 ? ('0' + ms) : ms)
  const id = y + m + d + h + mi + s + ms + ''
  return String(id)
}

// 生成默认选择限制
export function defaultConstraint() {
  const today = new Date()
  let day = null
  switch (today.getDay()) {
    case 1: day = [2, 3, 4, 5]
      break
    case 2: day = [3, 4, 5]
      break
    case 3: day = [4, 5]
      break
    case 4: day = [5]
      break
    case 5: return [{
      daysOfWeek: [5],
      startTime: '22:00',
      endTime: '23:00'
    }]
    default: day = [1, 2, 3, 4, 5]
  }
  return [{
    daysOfWeek: day,
    startTime: '09:00',
    endTime: '12:00'
  },
  {
    daysOfWeek: day,
    startTime: '14:00',
    endTime: '18:00'
  }]
}

export function transEvent(event) {
  // const green = '#67C23A'
  // const yellow = '#E6A23C'
  // const red = '#F56C6C'
  // const Lgreen = '#E1F3D8'
  // const Lyellow = '#FAECD8'
  // const Lred = '#FDE2E2'
  for (let i = 0; i < event.length; i++) {
    event[i].id = event[i].orderid
    event[i].title = event[i].name
    // event[i].backgroundColor = event[i].status === '' ? yellow : event[i].status === 'true' ? green : red
    // event[i].borderColor = event[i].status === '' ? Lyellow : event[i].status === 'true' ? Lgreen : Lred
  }
  return event
}

function editTime(time) {
  time = time.split('T')
  const hour = time[1].split('+')
  console.log(time)
  console.log(hour[0])
  return time[0] + ' ' + hour[0]
}

function transOrder(order) {
  order.orderId = order.orderid
  order.doctorId = order.doctorid
  order.doctorName = order.doctorname
  delete (order.orderid)
  delete (order.doctorid)
  delete (order.doctorname)
  switch (order.status) {
    case 'true': order.status = '接受'; break;
    case '': order.status = '待确认'; break;
    case 'false': order.status = '已拒绝'; break;
  }
  order.start = editTime(order.start)
  order.end = editTime(order.end)
  console.log(order.start)
  console.log(order.end)
}

export function transOrderList(list) {
  for (let i = 0; i < list.length; i++)
    transOrder(list[i])
}

