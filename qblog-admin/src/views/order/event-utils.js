
// let eventGuid = 0
const todayStr = new Date().toISOString().replace(/T.*$/, '') // YYYY-MM-DD of today

export const INITIAL_EVENTS = [
  {
    id: createEventId(),
    title: '一个预约',
    start: todayStr + 'T10:00:00'
  },
  // {
  //   id: createEventId(),
  //   title: '过时预约',
  //   start: todayStr + 'T14:00:00'
  // }
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
