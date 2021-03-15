<template>
  <div class="app-container">
    <el-col :span="6">
      <el-card class="demo-app">
        <h2>基本信息</h2>
        <el-form
          :model="room"
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
        <h2>功能介绍</h2>
        <ul>
          <li>咨询师日程表</li>
          <li>点击时段即可添加预约信息</li>
          <li>再次点击预约即可删除</li>
        </ul>
        <h2>事件说明：</h2>
        <ul>
          <li> <span style="color:#E6A23C;">黄色事件：未确认预约</span> </li>
          <li> <span style="color:#67C23A;">绿色事件：已确认预约</span> </li>
          <li> <span style="color:#F56C6C;">红色事件：已拒绝预约</span> </li>
        </ul>
        <h2>时段说明：</h2>
        <ul>
          <li><span>白色时段：未预约时段</span></li>
          <li><span>灰色时段：非工作时段</span></li>
          <li><span>黄色时段：本日时段</span></li>
        </ul>
      </el-card>
    </el-col>
    <el-card class="demo-app">
      <h1
        v-once
        @mouseover="alertVisible=true"
        @mouseleave="alertVisible=false"
        align='center'
      >{{ room.name }}预约情况</h1>
      <el-col
        :span="19"
        :offset="3"
      >
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
    </el-card>
  </div>
</template>
<script>
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import { transEvent, defaultConstraint } from '@/utils/event-utils'
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
        this.roomConfig.events = transEvent(res.data) // 传入咨询室日程
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
