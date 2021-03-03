<template>
  <div>
    <h1 align="center">排班管理</h1>
    <el-col
      :span="22"
      :offset="1"
    >
      <FullCalendar :options="scheduleConfig">
        <template v-slot:eventContent="arg">
          <!-- <b>{{ arg.timeText }}</b> -->
          <i>{{ arg.event.title }}</i>
        </template>
      </FullCalendar>
    </el-col>
    <el-dialog
      :visible.sync="dialogEditVisible"
      title="分配咨询室"
    >
      <el-select
        v-model="roomSelection"
        placeholder="请选择咨询室"
        clearable
      >
        <el-option
          v-for="item in room"
          :key="item.roomId"
          :value="item.roomId"
          :label="item.name"
        />
      </el-select>
      <FullCalendar :options="roomConfig">
        <template v-slot:eventContent="arg">
          <!-- <b>{{ arg.timeText }}</b> -->
          <i>{{ arg.event.title }}</i>
        </template>
      </FullCalendar>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button @click="dialogEditVisible=false">取消修改</el-button>
        <el-button
          type="primary"
          @click="roomEdit"
        >确认修改</el-button>
      </div>

    </el-dialog>
  </div>
</template>

<script>
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import { getSchedule, getRoomScheduleById, postRoomSchedule } from '@/api/schedule'
import { getroom } from '@/api/room'
import { INITIAL_SCHEDULE } from '@/utils/event-utils'
import { transScheduleList } from '@/utils/schedule-utils'
import '@fullcalendar/core/locales/zh-cn'

export default {
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  data() {
    return {
      dialogEditVisible: false,
      roomSelection: '',
      clickapp: '',
      room: [],
      scheduleConfig: {
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
        events: '',
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
          },
          // {
          //   daysOfWeek: [1, 2, 3, 4, 5], // Monday, Tuesday, Wednesday
          //   startTime: '14:00',
          //   endTime: '18:00'
          // },
        ],
        initialView: 'timeGridWeek',
        // initialEvents: INITIAL_EVENTS, // alternatively, use the `events` setting to fetch from a feed
        editable: true, // 拖动并选择多个时段
        selectConstraint: [ // specify an array instead
          {
            daysOfWeek: [1, 2, 3, 4, 5], // Monday, Tuesday, Wednesday
            startTime: '09:00',
            endTime: '18:00'
          },
          // {
          //   daysOfWeek: [1, 2, 3, 4, 5], // Monday, Tuesday, Wednesday
          //   startTime: '14:00', // 8am
          //   endTime: '18:00' // 6pm
          // }
        ],
        selectable: false,
        selectMirror: true,
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
    }
  },
  watch: {
    roomSelection: function (val) {
      getRoomScheduleById(val).then((res) => {
        if (res.code === 0) {
          console.log(res.data)
          this.roomConfig.events
        }
      }).catch((err) => {
        console.log(err)
        this.$notify.error({
          title: '提示',
          message: '网络忙，咨询室排班信息获取失败',
        })
      })
    }
  },
  created() {
    getroom().then((res) => {
      if (res.code === 0) {
        console.log(res)
        this.room = res.data // 传入咨询室列表
        transroom(this.room)
        this.roomSelection = this.room[0].roomId
      }
    }).catch((err) => {
      console.log(err)
      this.$notify.error({
        title: '提示',
        message: '网络忙，咨询室信息获取失败',
      })
    })
    getSchedule().then((res) => {
      if (res.code === 0) {
        console.log(res)
        this.scheduleConfig.events = res.data
        transScheduleList(this.roomConfig.events)
      }
    }).catch((err) => {
      console.log(err)
      this.$notify.error({
        title: '提示',
        message: '网络忙，排班表获取失败',
      })
    })
  },
  methods: {
    // 点击已有预约
    handleEventClick(clickInfo) {
      this.dialogEditVisible = true
      this.clickapp = clickInfo
      console.log(clickInfo)
    },

    // 分配咨询室
    roomEdit() {
      postRoomSchedule(this.clickapp.event.id, this.roomSelection).then((res) => {
        if (res.code === 0) {
          this.$notify.success({
            title: '提示',
            message: '咨询室分配成功！'
          })
        }
      }).catch((err) => {
        console.log(err)
        this.$notify.error({
          title: '提示',
          message: '网络忙，咨询室分配失败',
        })
      })
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
