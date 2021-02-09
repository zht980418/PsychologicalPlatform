查看/添加日程表信息，只对日历数据操作
<template>
  <div class="app-container">
    <el-row>
      <el-col :span="6">
        <el-card class="demo-app-sidebar">
          <div class="demo-app-sidebar-section">
            <h2>{{ timelist }}</h2>
            <h2>功能介绍</h2>
            <br>
            <ul>
              <li>咨询师日程表</li>
              <li>点击时段即可添加预约信息</li>
              <li>再次点击预约即可删除</li>
            </ul>
            <br>
          </div>
          <div class="demo-app-sidebar-section">
            <label>
              <input
                type="checkbox"
                :checked="calendarOptions.weekends"
                @change="handleWeekendsToggle"
              >
              显示周末
            </label>
          </div>
          <br>
          <div class="demo-app-sidebar-section">
            <h2>所有预约 ({{ currentEvents.length }})</h2>
            <ul>
              <li
                v-for="event in currentEvents"
                :key="event.id"
              >
                <b>{{ event.startStr }}</b>
                <i>{{ event.title }}</i>
              </li>
            </ul>
          </div>
        </el-card>
      </el-col>
      <el-col :span="18">
        <el-card class="demo-app-sidebar">
          <div class="demo-app-main">
            <FullCalendar
              class="demo-app-calendar"
              :options="calendarOptions"
            >
              <template v-slot:eventContent="arg">
                <b>{{ arg.timeText }}</b>
                <i>{{ arg.event.title }}</i>
              </template>
            </FullCalendar>
          </div>
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
import { INITIAL_EVENTS, defaultConstraint, createEventId, transEvent } from '@/utils/event-utils'
import { getDoctorConstraintById, getDoctorCalendarById } from '@/api/order'
import '@fullcalendar/core/locales/zh-cn'

export default {
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  data() {
    return {
      Init: true,
      Update: false,
      // doctorId: this.$route.params.doctorId,
      doctorId: 'lisi123',
      timelist: this.$route.params.timelist,
      formLabelWidth: '120px',
      // 日历参数
      calendarOptions: {
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
        selectMirror: false,
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
        events: '',
        /* you can update a remote database when these fire:
        eventAdd:
        eventChange:
        eventRemove:
        */
        eventMouseover: this.test,
      },
      currentEvents: [],
    }
  },
  created() {
    // 获取时间限制
    this.calendarOptions.businessHours = defaultConstraint()
    this.calendarOptions.selectConstraint = defaultConstraint()
    getDoctorConstraintById(this.doctorId).then((res) => {
      if (res.code === 0) {
        this.calendarOptions.selectConstraint = res.data // 传入限制时间数组
      }
      // 获取日程
      getDoctorCalendarById(this.doctorId).then((res) => {
        if (res.code === 0) {
          console.log(res.data)
          console.log(transEvent(res.data[0]))
          this.calendarOptions.events = transEvent(res.data[0])// 传入预约信息
        } else {
          this.$notify.error({
            title: '提示',
            message: '预约日程表获取失败',
          })
        }
      }).catch((err) => {
        console.log(err)
        this.$notify.error({
          title: '提示',
          message: '网络忙，预约日程表获取失败',
        })
      })
    }).catch((err) => {
      console.log(err)
      this.$notify.error({
        title: '提示',
        message: '网络忙，日程表限制信息获取失败',
      })
    })
  },
  methods: {
    test() {
      this.alert('title', '<h2>Title</h2>')
      // .attr("data-container", "body")
      // .attr("data-toggle", "popover")
      // .attr("data-content", "<h4>Popover 中的一些内容 —— options 方法</h4>")
      // .attr("data-html", "true")
      // .attr("data-trigger", "hover")
      // .attr("data-placement", "bottom")
      // $("[data-toggle='popover']").popover()
    },

    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
    },

    // 新建预约
    handleDateSelect(selectInfo) {
      this.$router.push({ name: 'OrderPage', params: { doctorId: this.doctorId, editType: this.Init, selectInfo: selectInfo, orderId: createEventId() } })
    },

    // 点击已有预约
    handleEventClick(clickInfo) {
      // console.log(clickInfo)
      this.$router.push({ name: 'OrderPage', params: { doctorId: this.doctorId, editType: this.Update, selectInfo: clickInfo, orderId: clickInfo.event.id } })
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

<style lang='scss' scoped>
.demo-app-sidebar {
  height: 100vh;
  padding: 3vh;
}
</style>
