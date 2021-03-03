<template>
  <div class="app-main-container">
    <el-row>
      <el-col
        :span="7"
        :offset="10"
      >
        <h1
          v-once
          @mouseover="alertVisible=true"
          @mouseleave="alertVisible=false"
        >{{ room.name }}使用情况</h1>
      </el-col>
    </el-row>
    <el-row>
      <el-col
        :span="13"
        :offset="6"
      >
        <el-form
          :model="room"
          :inline="true"
          :disabled="true"
        >
          <el-form-item
            label="咨询室名："
            :label-width="formLabelWidth"
          >
            <el-input v-model="room.name" />
          </el-form-item>
          <el-form-item
            label="咨询室地址："
            :label-width="formLabelWidth"
          >
            <el-input v-model="room.address" />
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <el-row>
      <el-col
        :span="20"
        :offset="2"
      >
        <el-card>
          <FullCalendar
            class="demo-app-calendar"
            :options="roomConfig"
          >
            <template v-slot:eventContent="arg">
              <b>{{ arg.timeText }}</b>
              <i>{{ arg.event.title }}</i>
            </template>
          </FullCalendar>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import { INITIAL_EVENTS, defaultConstraint } from '@/utils/event-utils'
import { getRoomCalendarById } from '@/api/room'
import '@fullcalendar/core/locales/zh-cn'

export default {
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  data() {
    return {
      formLabelWidth: '120px',
      room: {
        roomId: 'newId',
        name: 'newRoom',
        address: 'newAddress',
      },
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
        initialEvents: INITIAL_EVENTS, // alternatively, use the `events` setting to fetch from a feed
        events: '',
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
        selectable: false,
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
    // 获取room信息
    this.room.roomId = this.$route.params.roomId
    this.room.name = this.$route.params.name
    this.room.address = this.$route.params.address
    // 获取限制信息
    this.roomConfig.businessHours = defaultConstraint()
    this.roomConfig.selectConstraint = defaultConstraint()
    // TODO 获取日程表数据
    getRoomCalendarById(this.room.roomId).then((res) => {
      if (res.code === 0) {
        console.log(res)
        this.roomConfig.events = res.data // 传入咨询室日程
      }
    }).catch((err) => {
      console.log(err)
      this.$notify.error({
        title: '提示',
        message: '网络忙，咨询室日程获取失败',
      })
    })
  },
  methods: {
    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
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
