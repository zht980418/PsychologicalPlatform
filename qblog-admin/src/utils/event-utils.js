
// let eventGuid = 0
const todayStr = new Date().toISOString().replace(/T.*$/, '') // YYYY-MM-DD of today

export const INITIAL_EVENTS = [
  {
    id: 202102071320,
    title: '一个预约',
    start: todayStr + 'T10:00:00',
    end: todayStr + 'T11:00:00'
  },
  // {
  //   id: createEventId(),
  //   title: '过时预约',
  //   start: todayStr + 'T14:00:00'
  //   end: todayStr + 'T15:00:00'
  // }
]

export const INITIAL_SCHEDULE = [
  {
    id: createEventId(),
    title: '李医生',
    start: todayStr + 'T09:00:00',
    end: todayStr + 'T13:00:00'
  },
  {
    id: createEventId(),
    title: '刘医生',
    start: todayStr + 'T09:00:00',
    end: todayStr + 'T13:00:00'
  },
  {
    id: createEventId(),
    title: '胡医生',
    start: todayStr + 'T09:00:00',
    end: todayStr + 'T13:00:00'
  },
  {
    id: createEventId(),
    title: '胡医生',
    start: todayStr + 'T13:00:00',
    end: todayStr + 'T18:00:00'
  },
]

// 生成日程id
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
  const list = []
  for (let i = 0; i < event.length; i++) {
    list.push({ id: event[i].orderid, title: event[i].name, start: event[i].start, end: event[i].end })
  }
  console.log(list)
  return list
}
