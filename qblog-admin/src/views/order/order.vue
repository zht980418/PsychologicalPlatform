<template>
  <div class="app-container">
    <el-row>
      <el-col span="6">
        <el-card class="demo-app-sidebar">
          <div class="demo-app-sidebar-section">
            <h2>{{ timelist }}</h2>
            <h2>功能介绍</h2>
            <br>
            <ul>
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
      <el-col span="18">
        <el-card class="order-box">
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
    <el-dialog
      title="咨询预约"
      :visible.sync="dialogFormVisible"
    >
      <el-form>
        <el-form-item
          label="咨询方式"
          :label-width="formLabelWidth"
        >
          <el-select
            v-model="form.type"
            placeholder="请选择咨询方式"
          >
            <el-option
              label="线上"
              value="online"
            />
            <el-option
              label="线下"
              value="offline"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          label="咨询主题"
          :label-width="formLabelWidth"
        >
          <el-input v-model="form.theme" />
        </el-form-item>
        <el-form-item
          label="姓名"
          :label-width="formLabelWidth"
        >
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item
          label="联系方式"
          :label-width="formLabelWidth"
        >
          <el-input v-model="form.phone" />
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button @click="addEvent(form.theme,selection)">取 消</el-button>
        <el-button
          type="primary"
          @click="addEvent(form.theme,selection)"
        >确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import { INITIAL_EVENTS, createEventId } from './event-utils'
import '@fullcalendar/core/locales/zh-cn'
import { getConstraint } from '@/api/order'

export default {
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  data() {
    return {
      doctorname: this.$route.params.doctorname,
      timelist: this.$route.params.timelist,
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
            startTime: '08:00', // 8am
            endTime: '18:00' // 6pm
          }
        ],
        initialView: 'timeGridWeek',
        initialEvents: INITIAL_EVENTS, // alternatively, use the `events` setting to fetch from a feed
        editable: true,
        // 拖动并选择多个时段
        selectConstraint: [ // specify an array instead
          {
            daysOfWeek: [1, 2, 3, 4, 5], // Monday, Tuesday, Wednesday
            startTime: '08:00', // 8am
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
        eventsSet: this.handleEvents
        /* you can update a remote database when these fire:
        eventAdd:
        eventChange:
        eventRemove:
        */
      },
      currentEvents: [],
      selection: '',
      dialogFormVisible: false,
      form: {
        type: '',
        theme: '',
        name: '',
        phone: ''
      },
      formLabelWidth: '120px',
    }
  },
  created() {
    getConstraint(this.doctorname).then((res) => {
      this.calendarOptions.selectConstraint = res // 传入限制时间数组
    }).catch((err) => {
      console.log(err)
      this.$notify.error({
        title: '提示',
        message: '网络忙，预约日程表获取失败',
      })
    })
  },
  methods: {
    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
    },
    handleDateSelect(selectInfo) {
      this.dialogFormVisible = true // 展示信息填写form
      this.selection = selectInfo
    },

    addEvent(title, selectInfo) {
      this.dialogFormVisible = false

      const calendarApi = selectInfo.view.calendar

      calendarApi.unselect() // clear date selection

      if (title) {
        calendarApi.addEvent({
          id: createEventId(),
          title,
          start: selectInfo.startStr,
          end: selectInfo.endStr,
          allDay: selectInfo.allDay
        })
      }
      this.form.type = ''
      this.form.theme = ''
      this.form.name = ''
      this.form.phone = ''
    },

    handleEventClick(clickInfo) {
      if (confirm(`你确定要取消该预约吗？ '${clickInfo.event.title}'`)) {
        clickInfo.event.remove()
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

<style lang='scss' scoped>
.demo-app-sidebar {
  height: 105vh;
  padding: 3vh;
}
</style>
