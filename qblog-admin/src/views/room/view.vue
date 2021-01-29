<template>
  <el-row>
    <el-col
      :span="20"
      :offset="2"
    >
      <h1 style="text-align:center;">咨询室使用情况</h1>
      <FullCalendar
        class="demo-app-calendar"
        :options="roomConfig"
      >
        <template v-slot:eventContent="arg">
          <b>{{ arg.timeText }}</b>
          <i>{{ arg.event.title }}</i>
        </template>
      </FullCalendar>
    </el-col>
  </el-row>
</template>
<script>
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
// import { INITIAL_EVENTS, createEventId } from './event-utils'
import '@fullcalendar/core/locales/zh-cn'
// import { getConstraint, postOrder } from '@/api/order'

export default {
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  data() {
    return {
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
            endTime: '12:00'
          },
          {
            daysOfWeek: [1, 2, 3, 4, 5], // Monday, Tuesday, Wednesday
            startTime: '14:00',
            endTime: '18:00'
          },
        ],
        initialView: 'timeGridWeek',
        // initialEvents: INITIAL_EVENTS, // alternatively, use the `events` setting to fetch from a feed
        editable: true, // 拖动并选择多个时段
        selectConstraint: [ // specify an array instead
          {
            daysOfWeek: [1, 2, 3, 4, 5], // Monday, Tuesday, Wednesday
            startTime: '09:00',
            endTime: '12:00'
          },
          {
            daysOfWeek: [1, 2, 3, 4, 5], // Monday, Tuesday, Wednesday
            startTime: '14:00', // 8am
            endTime: '18:00' // 6pm
          }
        ],
        selectable: true,
        selectMirror: true,
        dayMaxEvents: true,
        weekends: true,
        allDaySlot: false,
        slotMinTime: '09:00:00',
        slotMaxTime: '18:00:00',
        slotDuration: '01:00:00',
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
    }
  },
  created() {
    // 获取查看/编辑状态
    this.roomConfig.selectable = Boolean(this.$route.params.op)
    // 获取限制信息
    this.roomConfig.businessHours = this.orderConstraint()
    this.roomConfig.selectConstraint = this.orderConstraint()
    // TODO  获取数据
  },
  methods: {
    orderConstraint() {
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
    },

    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
    },

    handleDateSelect(selectInfo) {
      const calendarApi = selectInfo.view.calendar
      calendarApi.unselect() // clear date selection
      if (selectInfo.startStr) {
        calendarApi.addEvent({
          //   id: createEventId(),
          title: 'this.form.name',
          start: selectInfo.startStr,
          end: selectInfo.endStr,
          allDay: selectInfo.allDay
        })
      }
    },

    handleEventClick(clickInfo) {

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

<style>
</style>
