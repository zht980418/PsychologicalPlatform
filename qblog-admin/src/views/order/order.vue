查看/添加日程表信息，只对日历数据操作
<template>
  <div class="app-container">
    <el-row>
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
          <br>
        </el-card>
      </el-col>
      <el-col :span="18">
        <el-card class="demo-app">
          <el-col
            :xs="{span:24,offset:0}"
            :sm="{span:24,offset:0}"
            :md="{span:24,offset:0}"
            :lg="{span:18,offset:3}"
            :xl="{span:16,offset:4}"
          >
            <FullCalendar :options="calendarOptions">
              <template v-slot:eventContent="arg">
                <b>{{ arg.timeText }}</b>
                <br>
                <i>{{ arg.event.title }}</i>
              </template>
            </FullCalendar>
          </el-col>
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
import '@fullcalendar/core/locales/zh-cn'
import { defaultConstraint, createEventId } from '@/utils/event-utils'
import { getDoctorConstraintById, getDoctorCalendarById } from '@/api/order'
import { mapGetters } from 'vuex'

export default {
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  data() {
    return {
      Init: true,
      Update: false,
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
        editable: false, // 拖动并选择多个时段
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
      },
      currentEvents: [],
    }
  },
  computed: {
    ...mapGetters({
      doctorId: 'id'
    })
  },
  created() {
    // 获取时间限制
    this.calendarOptions.businessHours = defaultConstraint()
    this.calendarOptions.selectConstraint = defaultConstraint()
    getDoctorConstraintById(this.doctorId).then((res) => {
      if (res.code === 0) {
        this.calendarOptions.selectConstraint = res.data // 传入限制时间数组
        // TODO 有一个问题，就是获取的必须是当前周的事件，现在获取的还是所有事件
        this.calendarOptions.businessHours = res.data
      }
      // 获取日程
      getDoctorCalendarById(this.doctorId).then((res) => {
        if (res.code === 0) {
          this.calendarOptions.events = res.data // 传入预约信息
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
    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
    },

    // 新建预约
    handleDateSelect(selectInfo) {
      this.$router.push({ name: 'OrderPage', params: { editType: this.Init, selectInfo: selectInfo, orderId: createEventId() } })
    },

    // 点击已有预约
    handleEventClick(clickInfo) {
      console.log(clickInfo);
      this.$router.push({ name: 'OrderPage', params: { editType: this.Update, selectInfo: clickInfo, orderId: clickInfo.event.id } })
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
