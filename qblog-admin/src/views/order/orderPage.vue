修改预约具体信息，对用户数据做操作，日程表信息返回上一个页面
<template>
  <div class="app-container">
    <el-row>
      <el-col>
        <el-form
          ref="form"
          :model="form"
        >
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
            <el-row>
              <el-col :span="6">
                <el-date-picker
                  v-model="form.ordertime"
                  type="datetime"
                  value-format="timestamp"
                  start-placeholder="开始时间"
                  @change="test"
                />
              </el-col>
              <el-col
                :span="1"
                :offset="2"
              >
                <i
                  class="el-icon-warning-outline"
                  @mouseover="alertConsultDuration =true"
                  @mouseleave="alertConsultDuration =false"
                />
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <el-alert
                  v-show="alertConsultDuration"
                  title="预约时长：1小时"
                  type="info"
                  style="height:40px;"
                  :closable="false"
                  center
                  show-icon
                />
              </el-col>
            </el-row>
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
              <el-col :span="22">
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
              <el-col :span="22">
                <el-alert
                  v-show="alertQuestionVisible"
                  title="你困惑或期望解决的问题是什么?"
                  type="info"
                  :closable="false"
                  show-icon
                />
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item
            label="家庭情况"
            :label-width="formLabelWidth"
          >
            <el-row>
              <el-col :span="22">
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
              <el-col :span="22">
                <el-alert
                  v-show="alertFamilyVisible"
                  title="你过去是否有重大躯体疾病史、或重大成长事件影响到现在困惑的你?"
                  type="info"
                  :closable="false"
                  show-icon
                />
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item
            label="咨询期望"
            :label-width="formLabelWidth"
          >
            <el-row>
              <el-col :span="22">
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
              <el-col :span="22">
                <el-alert
                  v-show="alertExpectationVisible"
                  title="你期待通过咨询达到什么样的目标?"
                  type="info"
                  :closable="false"
                  show-icon
                />
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item
            label="咨询历史"
            :label-width="formLabelWidth"
          >
            <el-row>
              <el-col :span="22">
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
              <el-col :span="22">
                <el-alert
                  v-show="alertHistoryVisible"
                  title="以前有没有做过咨询，得到什么结果?"
                  type="info"
                  :closable="false"
                  show-icon
                />
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item
            label="心理测试"
            :label-width="formLabelWidth"
          >
            <el-row>
              <el-col :span="22">
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
              <el-col :span="22">
                <el-alert
                  v-show="alertTestVisible"
                  title="以前有没有做过心理测试，得到什么结果?"
                  type="info"
                  :closable="false"
                  show-icon
                />
              </el-col>
            </el-row>
          </el-form-item>
          <el-col
            :span="22"
            :offset="1"
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
            <br>
            <el-form-item
              v-show="form.type==='offline'"
              label="咨询室"
            >
              <el-select
                v-model="form.room"
                placeholder="请选择咨询室"
                clearable
                @change="handleCalendarChange"
              >
                <el-option
                  label="咨询室1"
                  value="咨询室1"
                />
                <el-option
                  label="咨询室2"
                  value="咨询室2"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-form>
      </el-col>
    </el-row>
    <el-row v-if="!(form.room=='')">
      <el-col
        :span="20"
        :offset="2"
      >
        <h1 style="text-align:center;">{{ form.room }}使用情况</h1>
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
    </el-row>
    <br>
    <el-row>
      <el-col
        :span="20"
        :offset="2"
      >
        <el-alert
          type="warning"
          :closable="false"
        >
          <h3><b>我们郑重承诺：尊重来访者个人隐私，对咨询内容予以严格保密（但如涉及到来访者本人或他人的生命安全除外）。</b></h3>
        </el-alert>
      </el-col>
    </el-row>
    <br>
    <el-row>
      <el-col :offset="8">
        <div v-if="initVisible">
          <el-button
            type="info"
            icon="el-icon-close"
            plain
            @click="$router.back(-1)"
          >取 消</el-button>
          <el-button
            type="primary"
            icon="el-icon-check"
            plain
            @click="handleAddOrder"
          >确 定</el-button>
        </div>
        <div v-if="!initVisible">
          <el-button
            type="info"
            icon="el-icon-close"
            plain
            @click="$router.back(-1)"
          >取 消</el-button>
          <el-button
            type="success"
            icon="el-icon-edit"
            plain
            @click="handleUpdateOrder"
          >修 改</el-button>
          <el-button
            type="danger"
            icon="el-icon-delete"
            plain
            @click="handleDeleteOrder"
          >删 除</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import { INITIAL_EVENTS, createEventId, defaultConstraint } from '@/utils/event-utils'
import { getConstraint, postOrder } from '@/api/order'
import '@fullcalendar/core/locales/zh-cn'

export default {
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  data() {
    return {
      doctorname: this.$route.params.doctorname,
      initVisible: this.$route.params.editType,
      orderSelectInfo: this.$route.params.selectInfo,
      calendarVisible: false,
      formLabelWidth: '120px',

      // 日历参数
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
      currentEvents: [],
      currentRoomEvents: [],
      selection: '', // 暂存selectInfo
      // 其他config
      alertConsultDuration: false,
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
        sleep: '',
        relationship: '',
        stress: '',
        mood: '',
        hurt: '',
        suicide: '',
        room: '',
      },
    }
  },
  created() {
    this.roomConfig.businessHours = defaultConstraint()
    this.roomConfig.selectConstraint = defaultConstraint()
    console.log(this.orderSelectInfo)
    this.form.ordertime = this.orderSelectInfo.event.start
    getConstraint(this.doctorname).then((res) => {
      // TODO 获取预约信息
      // this.roomConfig.selectConstraint = res // 传入限制时间数组
    }).catch((err) => {
      console.log(err)
      this.$notify.error({
        title: '提示',
        message: '网络忙，预约日程表获取失败',
      })
    })
  },
  methods: {
    test() {
      console.log(this.form.type)
      console.log(Boolean(this.form.type))
    },

    // 预约日程表
    handleWeekendsToggle() {
      this.roomConfig.weekends = !this.roomConfig.weekends // update a property
    },
    // 咨询室
    handleCalendarChange() {
      // TODO 获取room数据
      console.log(this.form.room)
    },

    // 新增预约
    handleDateSelect(selectInfo) {
      const calendarApi = selectInfo.view.calendar
      calendarApi.unselect() // clear date selection
      if (this.form.name) {
        calendarApi.addEvent({
          id: createEventId(),
          title: this.form.name,
          start: selectInfo.startStr,
          end: selectInfo.endStr,
          allDay: selectInfo.allDay
        })
        postOrder()
      }
    },
    // 点击已有预约
    handleEventClick(clickInfo) {
      if (confirm(`你确定要取消该预约吗？ '${clickInfo.event.title}'`)) {
        clickInfo.event.remove()
        this.form.room = ''
        // TODO 删除预约
      }
    },
    handleEvents(events) {
      this.currentEvents = events
    },
    errorHandler() {
      return true
    },

    // 预约信息处理
    // 添加预约
    handleAddOrder() {
      // TODO 存储
      postOrder(this.getForm())
      // 传参，日程+1
      this.$router.back(-1)
    }
  },

  // 删除预约
  handleDeleteOrder() {
    // TODO 删除
    // 根据ID 删
    this.$router.back(-1)
  },

  // 修改预约
  handleUpdateOrder() {
    // 删除
    // 根据ID 删
    // 添加
    postOrder(this.getForm())
    this.$router.back(-1)
  },

  getForm() {
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
      start: this.orderSelectInfo.startStr,
      end: this.orderSelectInfo.endStr,
      allDay: this.orderSelectInfo.allDay
    }
    return params
  }
}
</script>
