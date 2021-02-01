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
import '@fullcalendar/core/locales/zh-cn'

export default {
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  data() {
    return {
      // doctorname: this.$route.params.doctorname,
      doctorname: '李医生',
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
        selectable: true,
        selectMirror: false,
        dayMaxEvents: true,
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
  },
  methods: {
    handleWeekendsToggle() {
      this.roomConfig.weekends = !this.roomConfig.weekends // update a property
    },

    // 新增预约
    handleDateSelect(selectInfo) {
      if (confirm('你确定要申请该时段排班吗？')) {
        const calendarApi = selectInfo.view.calendar
        console.log('点击')
        calendarApi.unselect() // clear date selection
        calendarApi.addEvent({
          id: createEventId(),
          title: this.doctorname,
          start: selectInfo.startStr,
          end: selectInfo.endStr,
          allDay: selectInfo.allDay
        })
        // TODO 存储
      }
    },
    // 点击已有预约
    handleEventClick(clickInfo) {
      if (clickInfo.event.title === this.doctorname) {
        if (confirm(`你确定要取消该排班申请吗？ '${clickInfo.event.title}'`)) {
          clickInfo.event.remove()
          this.form.room = ''
          // TODO 删除预约
        }
      } else {
        if (confirm('你确定要申请该时段排班吗？')) {
          const calendarApi = clickInfo.view.calendar
          console.log('点击')
          calendarApi.unselect() // clear date selection
          calendarApi.addEvent({
            id: createEventId(),
            title: this.doctorname,
            start: clickInfo.event.startStr,
            end: clickInfo.event.endStr,
            allDay: clickInfo.event.allDay
          })
          // TODO 存储
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
