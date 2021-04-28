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
          <FullCalendar :options="scheduleConfig">
            <template v-slot:eventContent="arg">
              <i>申请人：{{ arg.event.title }}</i>
              <br>
              <i>咨询室：{{arg.event.extendedProps.roomName? arg.event.extendedProps.roomName:arg.event.extendedProps.roomId==='-1'? '已拒绝该申请':'暂未分配咨询室' }}</i>
            </template>
          </FullCalendar>
        </el-col>
      </el-card>
    </el-col>
    <el-dialog
      :visible.sync="dialogEditVisible"
      title="分配咨询室"
      @close="handleClose"
    >咨询室：
      <el-select
        v-model="roomSelection"
        placeholder="请选择咨询室"
        clearable
      >
        <el-option
          value='-1'
          label='拒绝申请'
        />
        <el-option
          v-for="item in room"
          :key="item.roomId"
          :value="item.roomId"
          :label="item.name"
        />
      </el-select>
      <h1 align="center">咨询室排班表</h1>
      <FullCalendar :options="roomConfig" />
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button @click="dialogEditVisible=false">拒绝申请</el-button>
        <el-button
          type="primary"
          @click="roomEdit"
        >确认分配</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import '@fullcalendar/core/locales/zh-cn'
import { getSchedule, getRoomScheduleById, EditRoomSchedule } from '@/api/schedule'
import { getRoomList } from '@/api/room'

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
        events: [],
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
        ],
        initialView: 'timeGridWeek',
        events: [],
        editable: true, // 拖动并选择多个时段
        selectConstraint: [ // specify an array instead
          {
            daysOfWeek: [1, 2, 3, 4, 5], // Monday, Tuesday, Wednesday
            startTime: '09:00',
            endTime: '18:00'
          },
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
        // select: this.handleDateSelect,
        // eventClick: this.handleEventClick,
        eventsSet: this.handleEvents2,
        /* you can update a remote database when these fire:
        eventAdd:
        eventChange:
        eventRemove:
        */
      },
      currentEvents: [],
      currentEvents2: [],
    }
  },
  watch: {
    roomSelection: function (val) {
      if (val != '') {
        getRoomScheduleById(val).then((res) => {
          if (res.code === 0) {
            this.roomConfig.events = res.data
          }
        }).catch((err) => {
          console.log(err)
          this.$notify.error({
            title: '提示',
            message: '网络忙，咨询室排班信息获取失败',
          })
        })
      } else {
        this.roomConfig.events = []
      }
    }
  },
  created() {
    getRoomList().then((res) => {
      if (res.code === 0) {
        this.room = res.data // 传入咨询室列表
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
        console.log(res.data);
        this.scheduleConfig.events = res.data
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
      this.clickapp = clickInfo
      // 排班咨询室初始化
      // 拒绝
      if (clickInfo.event.extendedProps.roomId == -1) {
        this.roomSelection = '-1'
      } else if (clickInfo.event.extendedProps.roomId != null) {
        this.roomSelection = this.room[this.room.findIndex((item) => {
          if (item.roomId == clickInfo.event.extendedProps.roomId) { return true }
        })].roomId
      }
      this.dialogEditVisible = true
    },

    // 分配咨询室
    roomEdit() {
      EditRoomSchedule(this.clickapp.event.id, this.roomSelection).then((res) => {
        if (res.code === 0) {
          getSchedule().then((res) => {
            if (res.code === 0) {
              this.scheduleConfig.events = res.data
              this.dialogEditVisible = false
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
        }
      }).catch((err) => {
        console.log(err)
        this.$notify.error({
          title: '提示',
          message: '网络忙，咨询室分配失败',
        })
      })
    },

    handleClose() {
      this.roomSelection = ''
    },

    handleEvents(events) {
      this.currentEvents = events
    },
    handleEvents2(events2) {
      this.currentEvents2 = events2
    },

    errorHandler() {
      return true
    },
  }

}
</script>
