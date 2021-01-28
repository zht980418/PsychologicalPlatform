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
        <el-card class="order-box">
          <div class="demo-app-main">
            <FullCalendar
              id="orderCalendar"
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
      :inline="true"
      center
    >
      <el-row>
        <el-col>
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
              label="预约时间"
              :label-width="formLabelWidth"
            >
              <el-date-picker
                v-model="form.ordertime"
                type="datetimerange"
                range-separator="至"
                start-placeholder="开始时间"
                end-placeholder="结束时间"
                :editable="false"
              />
            </el-form-item>
            <el-row>
              <el-col :span="12">
                <el-form-item
                  label="姓名"
                  :label-width="formLabelWidth"
                >
                  <el-input v-model="form.name" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item
                  label="性别"
                  :label-width="formLabelWidth"
                >
                  <el-input v-model="form.gender" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item
                  label="出生年月"
                  :label-width="formLabelWidth"
                >
                  <el-date-picker
                    v-model="form.birth"
                    type="date"
                    placeholder="选择日期"
                  />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item
                  label="职业"
                  :label-width="formLabelWidth"
                >
                  <el-input v-model="form.occupation" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-form-item
                label="联系方式"
                :label-width="formLabelWidth"
              >
                <el-input v-model="form.phone" />
              </el-form-item>
            </el-row>
            <el-row>
              <el-form-item
                label="家庭住址"
                :label-width="formLabelWidth"
              >
                <el-input v-model="form.address" />
              </el-form-item>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item
                  label="紧急联系人"
                  :label-width="formLabelWidth"
                >
                  <el-input v-model="form.emergency" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item
                  label="紧急联系人电话"
                  :label-width="formLabelWidth"
                >
                  <el-input v-model="form.emergencyphone" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-form-item
              label="来询问题"
              :label-width="formLabelWidth"
            >
              <el-row>
                <el-col :span="21">
                  <el-input
                    v-model="form.question"
                    type="textarea"
                  />
                </el-col>
                <el-col
                  :offset="1"
                  :span="1"
                >
                  <i
                    class="el-icon-warning-outline"
                    @mouseover="alertQuestionVisible =true"
                    @mouseleave="alertQuestionVisible =false"
                  />
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="21">
                  <el-alert
                    v-show="alertQuestionVisible"
                    title="你困惑或期望解决的问题是什么?"
                    type="info"
                    :closable="false"
                  />
                </el-col>
              </el-row>
            </el-form-item>
            <el-form-item
              label="家庭情况"
              :label-width="formLabelWidth"
            >
              <el-row>
                <el-col :span="21">
                  <el-input
                    v-model="form.family"
                    type="textarea"
                  />
                </el-col>
                <el-col
                  :offset="1"
                  :span="1"
                >
                  <i
                    class="el-icon-warning-outline"
                    @mouseover="alertFamilyVisible =true"
                    @mouseleave="alertFamilyVisible =false"
                  />
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="21">
                  <el-alert
                    v-show="alertFamilyVisible"
                    title="你过去是否有重大躯体疾病史、或重大成长事件影响到现在困惑的你?"
                    type="info"
                    :closable="false"
                  />
                </el-col>
              </el-row>
            </el-form-item>
            <el-form-item
              label="咨询期望"
              :label-width="formLabelWidth"
            >
              <el-row>
                <el-col :span="21">
                  <el-input
                    v-model="form.expectation"
                    type="textarea"
                  />
                </el-col>
                <el-col
                  :offset="1"
                  :span="1"
                >
                  <i
                    class="el-icon-warning-outline"
                    @mouseover="alertExpectationVisible =true"
                    @mouseleave="alertExpectationVisible =false"
                  />
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="21">
                  <el-alert
                    v-show="alertExpectationVisible"
                    title="你期待通过咨询达到什么样的目标?"
                    type="info"
                    :closable="false"
                  />
                </el-col>
              </el-row>
            </el-form-item>
            <el-form-item
              label="咨询历史"
              :label-width="formLabelWidth"
            >
              <el-row>
                <el-col :span="21">
                  <el-input
                    v-model="form.history"
                    type="textarea"
                  />
                </el-col>
                <el-col
                  :offset="1"
                  :span="1"
                >
                  <i
                    class="el-icon-warning-outline"
                    @mouseover="alertHistoryVisible =true"
                    @mouseleave="alertHistoryVisible =false"
                  />
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="21">
                  <el-alert
                    v-show="alertHistoryVisible"
                    title="以前有没有做过咨询，得到什么结果?"
                    type="info"
                    :closable="false"
                  />
                </el-col>
              </el-row>
            </el-form-item>
            <el-form-item
              label="心理测试"
              :label-width="formLabelWidth"
            >
              <el-row>
                <el-col :span="21">
                  <el-input
                    v-model="form.test"
                    type="textarea"
                  />
                </el-col>
                <el-col
                  :offset="1"
                  :span="1"
                >
                  <i
                    class="el-icon-warning-outline"
                    @mouseover="alertTestVisible =true"
                    @mouseleave="alertTestVisible =false"
                  />
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="21">
                  <el-alert
                    v-show="alertTestVisible"
                    title="以前有没有做过心理测试，得到什么结果?"
                    type="info"
                    :closable="false"
                  />
                </el-col>
              </el-row>
            </el-form-item>
            <el-col
              :span="22"
              :offset="2"
            >
              <el-form-item label="在过去一个月里，睡眠状况如何？">
                <el-radio-group v-model="form.sleep">
                  <el-radio label="很差" />
                  <el-radio label="不满意" />
                  <el-radio label="正常" />
                  <el-radio label="良好" />
                  <el-radio label="很好" />
                </el-radio-group>
              </el-form-item>
              <el-form-item label="在过去一个月里，人际关系如何？">
                <el-radio-group v-model="form.relationship">
                  <el-radio label="很差" />
                  <el-radio label="不满意" />
                  <el-radio label="正常" />
                  <el-radio label="良好" />
                  <el-radio label="很好" />
                </el-radio-group>
              </el-form-item>
              <el-form-item label="在过去一个月里，压力水平如何？">
                <el-radio-group v-model="form.stress">
                  <el-radio label="很高" />
                  <el-radio label="高" />
                  <el-radio label="中等" />
                  <el-radio label="还好" />
                  <el-radio label="很好" />
                </el-radio-group>
              </el-form-item>
              <el-form-item label="在过去一个月里，心情如何？">
                <el-radio-group v-model="form.mood">
                  <el-radio label="很低落" />
                  <el-radio label="低落" />
                  <el-radio label="一般" />
                  <el-radio label="心情不错" />
                  <el-radio label="心情特别好" />
                </el-radio-group>
              </el-form-item>
              <el-form-item label="是否有过自伤行为？">
                <el-radio-group v-model="form.hurt">
                  <el-radio label="无" />
                  <el-radio label="曾经有" />
                  <el-radio label="现在有" />
                </el-radio-group>
              </el-form-item>
              <el-form-item label="是否有过自杀的想法或行为？">
                <el-radio-group v-model="form.suicide">
                  <el-radio label="无" />
                  <el-radio label="曾经有" />
                  <el-radio label="现在有" />
                </el-radio-group>
              </el-form-item>
              <el-form-item label="咨询室">
                <el-select
                  v-model="form.office"
                  placeholder="请选择咨询室"
                >
                  <el-option
                    label="咨询室1"
                    value="office1"
                  />
                  <el-option
                    label="咨询室2"
                    value="office2"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-form>
          <el-row>
            <el-col>
              <FullCalendar
                id="roomCalendar"
                class="demo-app-calendar"
                :options="roomConfig"
              />
              <template v-slot:eventContent="arg1">
                <b>{{ arg1.timeText }}</b>
                <i>{{ arg1.event.title }}</i>
              </template>
            </el-col>
          </el-row>
        </el-col>
      </el-row>
      <el-row>
        <el-col
          :span="22"
          :offset="1"
        >
          <el-alert
            type="warning"
            :closable="false"
          >
            <h3><b>我们郑重承诺：尊重来访者个人隐私，对咨询内容予以严格保密（但如涉及到来访者本人或他人的生命安全除外）。</b></h3>
          </el-alert>
        </el-col>
      </el-row>
      <div
        v-if="!dialogEditVisible"
        slot="footer"
        class="dialog-footer"
      >
        <el-button @click="dialogFormVisible=false">取 消</el-button>
        <el-button
          type="primary"
          @click="addEvent(form,selection)"
        >确 定</el-button>
      </div>
      <div
        v-if="dialogEditVisible"
        slot="footer"
        class="dialog-footer"
      >
        <el-button @click="(dialogFormVisible=false)&(dialogEditVisible=false)">取 消</el-button>
        <el-button
          type="success"
          icon="el-icon-edit"
          @click="handleEventEdit(form,selection)"
        >修 改</el-button>
        <el-button
          type="danger"
          icon="el-icon-delete"
          @click="handleEventDelete(form,selection)"
        >删 除</el-button>
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
import { getConstraint, postOrder } from '@/api/order'

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
        select: this.handleRoomSelect,
        eventClick: this.handleRoomDelete,
        eventsSet: this.handleRoomEvents,
        /* you can update a remote database when these fire:
        eventAdd:
        eventChange:
        eventRemove:
        */
      },
      currentEvents: [],
      currentRoomEvents: [],
      selection: '', // 暂存selectInfo
      dialogFormVisible: false,
      dialogEditVisible: false,
      alertQuestionVisible: false,
      alertFamilyVisible: false,
      alertExpectationVisible: false,
      alertHistoryVisible: false,
      alertTestVisible: false,
      form: {
        type: '',
        ordertime: '',
        name: '',
        gender: '',
        birth: '',
        occupation: '',
        phone: '',
        address: '',
        emergency: '',
        emergencyphone: '',
        question: '',
        family: '',
        expectation: '',
        history: '',
        test: '',
        room: ''
      },
      defaultForm: {
        type: '',
        ordertime: '',
        name: '',
        gender: '',
        birth: '',
        occupation: '',
        phone: '',
        address: '',
        emergency: '',
        emergencyphone: '',
        question: '',
        family: '',
        expectation: '',
        history: '',
        test: '',
        room: ''
      },
      formLabelWidth: '120px',
    }
  },
  created() {
    this.calendarOptions.businessHours = this.orderConstraint()
    this.calendarOptions.selectConstraint = this.orderConstraint()
    getConstraint(this.doctorname).then((res) => {
      // this.calendarOptions.selectConstraint = res // 传入限制时间数组
      // this.calendarOptions.selectConstraint = this.orderConstraint()
    }).catch((err) => {
      console.log(err)
      this.$notify.error({
        title: '提示',
        message: '网络忙，预约日程表获取失败',
      })
    })
  },
  methods: {
    // 预约日程表
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
        case 5: day = [0]
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
      this.dialogFormVisible = true // 展示信息填写form
      this.selection = selectInfo
    },
    // 添加新的预约
    addEvent(form, selectInfo) {
      console.log(selectInfo)
      this.dialogFormVisible = false // 关闭登记表
      const calendarApi = selectInfo.view.calendar
      calendarApi.unselect() // clear date selection
      if (form.name) {
        calendarApi.addEvent({
          id: createEventId(),
          title: form.name,
          start: selectInfo.startStr,
          end: selectInfo.endStr,
          allDay: selectInfo.allDay
        })
        // 存储数据
        const params = {
          doctorname: this.doctorname,
          type: this.form.type,
          name: this.form.name,
          ordertime: this.form.ordertime,
          gender: this.form.gender,
          birth: this.form.birth,
          occupation: this.form.occupation,
          phone: this.form.phone.phone,
          address: this.form.address,
          emergency: this.form.emergency,
          emergencyphone: this.form.emergencyphone,
          question: this.form.question,
          family: this.form.family,
          expectation: this.form.expectation,
          history: this.form.history,
          test: this.form.test,
          room: this.form.room,
          start: selectInfo.startStr,
          end: selectInfo.endStr,
          allDay: selectInfo.allDay,
        }
        // 存储
        postOrder(params)
        // 清空表单
        this.form = JSON.parse(JSON.stringify(this.defaultForm))
      }
    },
    // 点击已有预约
    handleEventClick(clickInfo) {
      console.log(clickInfo)
      // TODO 获取预约信息
      // 打开预约信息表格
      this.dialogEditVisible = true
      this.dialogFormVisible = true
      this.selection = clickInfo
    },

    handleEventDelete(form, clickInfo) {
      // TODO 删数据
      clickInfo.event.remove()
      // 关表格
      this.dialogEditVisible = false
      this.dialogFormVisible = false
    },

    handleEventEdit(form, clickInfo) {
      this.handleEventDelete(form, clickInfo) // 删除
      this.handleModify(form, clickInfo)
      // 关表格
      this.dialogFormVisible = false
      this.dialogEditVisible = false
    },
    // 重新添加预约信息
    handleModify(form, selectInfo) {
      const calendarApi = selectInfo.view.calendar
      calendarApi.unselect() // clear date selection
      if (form.name) {
        calendarApi.addEvent({
          id: createEventId(),
          title: form.name,
          start: selectInfo.event.startStr,
          end: selectInfo.event.endStr,
          allDay: selectInfo.event.allDay
        })
        // const params = { doctorname: this.doctorname, startStr: selectInfo.startStr }
        // postOrder(params)
        // 清空表单
        this.form = JSON.parse(JSON.stringify(this.defaultForm))
      }
    },

    handleEvents(events) {
      this.currentEvents = events
    },

    // 咨询室日程表
    handleRoomSelect(form, selectInfo) {
      console.log(selectInfo)
      const calendarApi = selectInfo.view.calendar
      calendarApi.unselect() // clear date selection
      if (form.name) {
        calendarApi.addEvent({
          id: createEventId(),
          title: form.name,
          start: selectInfo.startStr,
          end: selectInfo.endStr,
          allDay: selectInfo.allDay
        })
      }
    },

    hendleRoomDelete(clickInfo) {
      if (confirm(`你确定要取消该预约吗？ '${clickInfo.event.title}'`)) {
        clickInfo.event.remove()
        this.form.room = ''
        // TODO 删除预约
      }
    },

    handleRoomEvents(events) {
      this.currentRoomEvents = events
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
