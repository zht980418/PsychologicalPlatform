<template>
  <div>
    <h1 align="center">排班申请</h1>
    <el-col
      :span="22"
      :offset="1"
    >
      <FullCalendar :options="roomConfig">
        <template v-slot:eventContent="arg">
          <!-- <b>{{ arg.timeText }}</b> -->
          <i>{{ arg.event.title }}</i>
        </template>
      </FullCalendar>
    </el-col>
  </div>
</template>

<script>
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import { INITIAL_SCHEDULE, createEventId } from '@/utils/event-utils'
import { getSchedule, postApplication, deleteApplicationById } from '@/api/schedule'
import '@fullcalendar/core/locales/zh-cn'

export default {
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  data() {
    return {
      // doctorId: this.$route.params.doctorId,
      doctorId: '李医生',
      roomConfig: {
        plugins: [
          dayGridPlugin,
          timeGridPlugin,
          interactionPlugin // needed for dateClick
        ],
        headerToolbar: {
          left: '',
          center: 'title',
          right: ''
        },
        businessHours: [ // specify an array instead
          {
            daysOfWeek: [1, 2, 3, 4, 5], // Monday, Tuesday, Wednesday
            startTime: '09:00',
            endTime: '18:00'
          }
        ],
        initialView: 'timeGridWeek',
        initialEvents: INITIAL_SCHEDULE, // alternatively, use the `events` setting to fetch from a feed
        editable: true, // 拖动并选择多个时段
        selectConstraint: [ // specify an array instead
          {
            daysOfWeek: [1, 2, 3, 4, 5], // Monday, Tuesday, Wednesday
            startTime: '09:00',
            endTime: '18:00'
          },
        ],
        dayMaxEventRows: true, // for all non-TimeGrid views
        views: {
          timeGrid: {
            dayMaxEventRows: 3 // adjust to 6 only for timeGridWeek/timeGridDay
          }
        },
        selectable: true,
        selectMirror: false,
        // dayMaxEvents: true,
        weekends: true,
        allDaySlot: false,
        slotMinTime: '09:00:00',
        slotMaxTime: '17:00:00',
        slotDuration: '04:00:00',
        expandRows: true,
        locale: 'zh-cn',
        select: this.handleDateSelect,
        eventClick: this.handleEventClick,
        eventsSet: this.handleEvents,
        /* you can update a remote database when these fire:
        eventAdd:
        eventChange:
        eventRemove:
        */
      },
      currentEvents: [],
      timelist: []
    }
  },
  created() {
    // 获取排班表
    getSchedule().then((res) => {
      this.roomConfig.initialEvents = res
      console.log('获取成功')
    }).catch((err) => {
      console.log(err)
      this.$notify.error({
        title: '提示',
        message: '网络忙，排班表获取失败',
      })
    })
  },
  methods: {
    handleWeekendsToggle() {
      this.roomConfig.weekends = !this.roomConfig.weekends // update a property
    },

    // 新增预约
    handleDateSelect(selectInfo) {
      if (confirm('你确定要申请该时段排班吗？')) {
        // 存储
        const appId = createEventId()
        const params = { appId: appId, doctorId: this.doctorId, start: selectInfo.startStr, end: selectInfo.endStr, daysOfWeek: selectInfo.start.getDay() }
        postApplication(params).then((res) => {
          if (res.data === 0) {
            this.$notify.success({
              title: '提示',
              message: '排班申请成功',
            })
            const calendarApi = selectInfo.view.calendar
            calendarApi.unselect() // clear date selection
            calendarApi.addEvent({
              id: appId,
              title: this.doctorId,
              start: selectInfo.startStr,
              end: selectInfo.endStr,
            })
          }
        }).catch((err) => {
          console.log(err)
          this.$notify.error({
            title: '提示',
            message: '网络忙，排班申请失败',
          })
        })
      }
    },
    // 点击已有预约
    handleEventClick(clickInfo) {
      if (clickInfo.event.title === this.doctorId) {
        if (confirm(`你确定要取消该排班申请吗？ '${clickInfo.event.title}'`)) {
          deleteApplicationById(clickInfo.event.id).then((res) => {
            clickInfo.event.remove()
            this.$notify.success({
              title: '提示',
              message: '排班删除成功',
            })
          }).catch((err) => {
            console.log(err)
            this.$notify.error({
              title: '提示',
              message: '网络忙，排班删除失败',
            })
          })
        }
      } else {
        if (confirm('你确定要申请该时段排班吗？')) {
          const calendarApi = clickInfo.view.calendar
          calendarApi.unselect() // clear date selection
          const appId = createEventId()
          calendarApi.addEvent({
            id: appId,
            title: this.doctorId,
            start: clickInfo.event.startStr,
            end: clickInfo.event.endStr
          })
          // 存储
          const params = {
            appId: appId,
            doctorId: this.doctorId,
            start: clickInfo.event.startStr,
            end: clickInfo.event.endStr,
            daysOfWeek: clickInfo.event.start.getDay()
          }
          postApplication(params).then((res) => {
            if (res === true) {
              this.$notify.success({
                title: '提示',
                message: '排班申请成功',
              })
            }
          }).catch((err) => {
            console.log(err)
            this.$notify.error({
              title: '提示',
              message: '网络忙，排班申请失败',
            })
          })
        }
      }
    },

    handleEvents(events) {
      this.currentEvents = events
    },

    errorHandler() {
      return true
    },
  }
}
</script>
