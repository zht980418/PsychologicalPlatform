<template>
  <div class="app-container">
    <el-col :span="6">
      <el-card class="demo-app">
        <br>
        <h2>功能介绍</h2>
        <ul>
          <li>咨询师日程表</li>
          <li>点击时段即可添加预约信息</li>
          <li>再次点击预约即可删除</li>
        </ul>
        <br>
        <h2>事件说明：</h2>
        <ul>
          <li> <span style="color:#E6A23C;">黄色事件：未确认预约</span> </li>
          <li> <span style="color:#67C23A;">绿色事件：已确认预约</span> </li>
          <li> <span style="color:#F56C6C;">红色事件：已拒绝预约</span> </li>
        </ul>
        <br>
        <h2>时段说明：</h2>
        <ul>
          <li><span>白色时段：未预约时段</span></li>
          <li><span>灰色时段：非工作时段</span></li>
          <li><span>黄色时段：本日时段</span></li>
        </ul>
        <br>
      </el-card>
    </el-col>
    <el-col :span="18">
      <el-card class="demo-app">
        <el-col
          :span="22"
          :offset="1"
        >
          <FullCalendar :options="roomConfig">
            <template v-slot:eventContent="arg">
              <b>{{ arg.timeText }}</b>
              <br>
              <i>{{ arg.event.title }}</i>
            </template>
          </FullCalendar>
        </el-col>
      </el-card>
    </el-col>
  </div>
</template>

<script>
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import '@fullcalendar/core/locales/zh-cn'
import { createEventId } from '@/utils/event-utils'
import { getSchedule, postApplication, deleteApplicationById } from '@/api/schedule'
import { mapGetters } from 'vuex'

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
            endTime: '18:00'
          }
        ],
        initialView: 'timeGridWeek',
        events: '',
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
    }
  },
  computed: {
    ...mapGetters({
      doctorId: 'id',
      doctorName: 'name'
    })
  },
  created() {
    getSchedule().then((res) => {
      if (res.code === 0) {
        this.roomConfig.events = res.data
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
    handleWeekendsToggle() {
      this.roomConfig.weekends = !this.roomConfig.weekends // update a property
    },

    // 新增预约
    handleDateSelect(selectInfo) {
      if (confirm('你确定要申请该时段排班吗？')) {
        // 存储
        const appId = createEventId()
        let params = {
          appId: appId,
          doctorId: this.doctorId,
          doctorName: this.doctorName,
          start: selectInfo.startStr.split('T')[1].split('+')[0],
          end: selectInfo.endStr.split('T')[1].split('+')[0],
          daysOfWeek: selectInfo.start.getDay()
        }
        postApplication(params).then((res) => {
          if (res.code === 0) {
            this.$notify.success({
              title: '提示',
              message: '排班申请成功！',
            })
            getSchedule().then((res) => {
              if (res.code === 0) {
                this.roomConfig.events = res.data
              }
            }).catch((err) => {
              console.log(err)
              this.$notify.error({
                title: '提示',
                message: '网络忙，排班表获取失败',
              })
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
      console.log(clickInfo.event)
      if (clickInfo.event.groupId === this.doctorId) {
        if (confirm(`你确定要取消该排班申请吗？ '${clickInfo.event.title}'`)) {
          deleteApplicationById(clickInfo.event.id).then((res) => {
            if (res.code === 0) {
              clickInfo.event.remove()
              this.$notify.success({
                title: '提示',
                message: '排班删除成功',
              })
            }
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
          // 存储
          const params = {
            appId: appId,
            doctorId: this.doctorId,
            doctorName: this.doctorName,
            start: clickInfo.event.startStr.split('T')[1].split('+')[0],
            end: clickInfo.event.endStr.split('T')[1].split('+')[0],
            daysOfWeek: clickInfo.event.start.getDay()
          }
          postApplication(params).then((res) => {
            if (res.code === 0) {
              getSchedule().then((res) => {
                if (res.code === 0) {
                  this.roomConfig.events = res.data
                }
              }).catch((err) => {
                console.log(err)
                this.$notify.error({
                  title: '提示',
                  message: '网络忙，排班表获取失败',
                })
              })
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
