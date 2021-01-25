<template>
  <el-carousel
    indicator-position="outside"
    height="100vh"
    :autoplay="false"
  >
    <el-carousel-item
      v-for="(item,index) in itemlist"
      :key="index"
    >
      <el-row :gutter="20">
        <el-col :span="6">
          <el-row
            type="flex"
            class="row-bg"
            justify="space-between"
          >
            <div class="demo-type">
              <el-avatar
                shape="square"
                :fit=item.img
                :size="180"
                :src=item.img
                @error="errorHandler"
              ></el-avatar>
            </div>
          </el-row>
          <el-row
            type="flex"
            class="row-bg"
            justify="space-between"
          ><i
              class="el-icon-user"
              style="font-size: 20px; color: #606266"
            >{{item.name}}</i></el-row>
          <el-row
            type="flex"
            class="row-bg"
            justify="space-between"
          ><i
              class="el-icon-phone"
              style="font-size: 20px; color: #606266"
            >{{item.phonenumber}}</i></el-row>
        </el-col>
        <el-col :span="18">
          <el-row
            type="flex"
            class="row-bg"
            justify="space-around"
          ><i
              class="el-icon-date"
              style="font-size: 20px; color: #606266"
            >预约日程表</i></el-row>
          <!-- 日程表 -->
          <div class='demo-app'>
            <div class='demo-app-sidebar'>
              <div class='demo-app-sidebar-section'>
                <h2>Instructions</h2>
                <ul>
                  <li>Select dates and you will be prompted to create a new event</li>
                  <li>Drag, drop, and resize events</li>
                  <li>Click an event to delete it</li>
                </ul>
              </div>
              <div class='demo-app-sidebar-section'>
                <label>
                  <input
                    type='checkbox'
                    :checked='calendarOptions.weekends'
                    @change='handleWeekendsToggle'
                  />
                  toggle weekends
                </label>
              </div>
              <div class='demo-app-sidebar-section'>
                <h2>All Events ({{ currentEvents.length }})</h2>
                <ul>
                  <li
                    v-for='event in currentEvents'
                    :key='event.id'
                  >
                    <b>{{ event.startStr }}</b>
                    <i>{{ event.title }}</i>
                  </li>
                </ul>
              </div>
            </div>
            <div class='demo-app-main'>
              <FullCalendar
                class='demo-app-calendar'
                :options='calendarOptions'
                :businessHours="{
                  startTime: '07:00',
                  endTime:'18:00',
                  daysOfWeek: [ 1, 2, 3, 4, 5, 6, 0 ]
                }"
              >
                <template v-slot:eventContent='arg'>
                  <b>{{ arg.timeText }}</b>
                  <i>{{ arg.event.title }}</i>
                </template>
              </FullCalendar>
            </div>
          </div>
        </el-col>
      </el-row>
    </el-carousel-item>
  </el-carousel>
</template>


<script>
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import { INITIAL_EVENTS, createEventId } from './event-utils'
import '@fullcalendar/core/locales/zh-cn'

export default {
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  data() {
    return {
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

        initialView: 'timeGridWeek',
        initialEvents: INITIAL_EVENTS, // alternatively, use the `events` setting to fetch from a feed
        editable: true,
        selectable: true,
        selectMirror: true,
        dayMaxEvents: true,
        weekends: true,
        allDaySlot: false,
        slotMinTime: "09:00:00",
        slotMaxTime: "18:00:00",
        slotDuration: "01:00:00",
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

      itemlist: [
        { name: "张三", phonenumber: "123", img: "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3649178992,1821853682&fm=26&gp=0.jpg" },
        { name: "李四", phonenumber: "456", img: "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3649178992,1821853682&fm=26&gp=0.jpg" },
        { name: "王五", phonenumber: "789", img: "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3649178992,1821853682&fm=26&gp=0.jpg" },
      ],
    }
  },
  methods: {
    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
    },

    handleDateSelect(selectInfo) {
      let title = prompt('Please enter a new title for your event')
      let calendarApi = selectInfo.view.calendar

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
    },

    handleEventClick(clickInfo) {
      if (confirm(`Are you sure you want to delete the event '${clickInfo.event.title}'`)) {
        clickInfo.event.remove()
      }
    },

    handleEvents(events) {
      this.currentEvents = events
    },
    errorHandler() {
      return true
    },
    handleConfirm() {
      this.dialogFormVisible = false
      this.gridData.some((item, i) => {
        if (item.name == this.form.startTime) {
          this.gridData.splice(i, 1)
          // 在 数组的 some 方法中，如果 return true，就会立即终止这个数组的后续循环
          return true;
        }
      })
    }
  }
}
</script>

<style lang='scss'>
h2 {
  margin: 0;
  font-size: 16px;
}

ul {
  margin: 0;
  padding: 0 0 0 1.5em;
}

li {
  margin: 1.5em 0;
  padding: 0;
}

b {
  /* used for event dates/times */
  margin-right: 3px;
}

.demo-app {
  display: flex;
  min-height: 100%;
  font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
  font-size: 14px;
}

.demo-app-sidebar {
  width: 300px;
  line-height: 1.5;
  background: #eaf9ff;
  border-right: 1px solid #d3e2e8;
}

.demo-app-sidebar-section {
  padding: 2em;
}

.demo-app-main {
  flex-grow: 1;
  padding: 3em;
}

.fc {
  /* the calendar root */
  max-width: 1100px;
  margin: 0 auto;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: EEF1F6;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: EEF1F6;
}
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}
.row-bg {
  padding: 10px 0;
  background-color: EEF1F6;
}
</style>