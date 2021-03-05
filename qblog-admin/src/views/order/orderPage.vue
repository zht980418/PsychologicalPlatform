
<template>
  <div class="app-container">
    <el-row>
      <el-col>
        <el-form
          ref="form"
          :model="form"
          :rules="rules"
        >
          <el-form-item
            label="咨询方式"
            :label-width="formLabelWidth"
            prop="type"
          >
            <el-col :span="7">
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
            </el-col>
            <el-col
              :span="1"
              :offset="1"
            >
              <i
                class="el-icon-warning-outline"
                @mouseover="alertConsultDuration =true"
                @mouseleave="alertConsultDuration =false"
              />
            </el-col>
            <el-col
              :span="9"
              :offset="1"
            >
              <el-alert
                v-show="alertConsultDuration"
                title="选择线下咨询可在最后预约咨询室"
                type="info"
                style="height:40px;"
                :closable="false"
                center
                show-icon
              />
            </el-col>
          </el-form-item>
          <el-form-item
            label="预约时间"
            :label-width="formLabelWidth"
            prop="ordertime"
          >
            <el-row>
              <el-col :span="5">
                <el-date-picker
                  v-model="form.ordertime"
                  type="datetime"
                  start-placeholder="开始时间"
                />
              </el-col>
              <el-col
                :span="1"
                :offset="2"
              >
                <i
                  class="el-icon-warning-outline"
                  @mouseover="alertTypeDuration =true"
                  @mouseleave="alertTypeDuration =false"
                />
              </el-col>
              <el-col
                :span="9"
                :offset="1"
              >
                <el-alert
                  v-show="alertTypeDuration"
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
                prop="name"
              >
                <el-input v-model="form.name" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item
                label="性别"
                :label-width="formLabelWidth"
                prop="gender"
              >
                <el-select
                  v-model="form.gender"
                  placeholder="请选择性别"
                >
                  <el-option
                    label="男"
                    value="男"
                  />
                  <el-option
                    label="女"
                    value="女"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item
                label="出生年月"
                :label-width="formLabelWidth"
                prop="birth"
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
                prop="occupation"
              >
                <el-input v-model="form.occupation" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item
              label="联系方式"
              :label-width="formLabelWidth"
              prop="phone"
            >
              <el-input v-model="form.phone" />
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item
              label="家庭住址"
              :label-width="formLabelWidth"
              prop="address"
            >
              <el-input v-model="form.address" />
            </el-form-item>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item
                label="紧急联系人"
                :label-width="formLabelWidth"
                prop="emergency"
              >
                <el-input v-model="form.emergency" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item
                label="紧急联系人电话"
                :label-width="formLabelWidth"
                prop="emergencyphone"
              >
                <el-input v-model="form.emergencyphone" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item
            label="来询问题"
            :label-width="formLabelWidth"
            prop="question"
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
            prop="family"
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
            prop="expectation"
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
            prop="history"
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
            prop="test"
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
            <el-form-item
              label="在过去一个月里，睡眠状况如何？"
              prop="sleep"
            >
              <el-radio-group v-model="form.sleep">
                <el-radio label="很差" />
                <el-radio label="不满意" />
                <el-radio label="正常" />
                <el-radio label="良好" />
                <el-radio label="很好" />
              </el-radio-group>
            </el-form-item>
            <el-form-item
              label="在过去一个月里，人际关系如何？"
              prop="relationship"
            >
              <el-radio-group v-model="form.relationship">
                <el-radio label="很差" />
                <el-radio label="不满意" />
                <el-radio label="正常" />
                <el-radio label="良好" />
                <el-radio label="很好" />
              </el-radio-group>
            </el-form-item>
            <el-form-item
              label="在过去一个月里，压力水平如何？"
              prop="stress"
            >
              <el-radio-group v-model="form.stress">
                <el-radio label="很高" />
                <el-radio label="高" />
                <el-radio label="中等" />
                <el-radio label="还好" />
                <el-radio label="很好" />
              </el-radio-group>
            </el-form-item>
            <el-form-item
              label="在过去一个月里，心情如何？"
              prop="mood"
            >
              <el-radio-group v-model="form.mood">
                <el-radio label="很低落" />
                <el-radio label="低落" />
                <el-radio label="一般" />
                <el-radio label="心情不错" />
                <el-radio label="心情特别好" />
              </el-radio-group>
            </el-form-item>
            <el-form-item
              label="是否有过自伤行为？"
              prop="hurt"
            >
              <el-radio-group v-model="form.hurt">
                <el-radio label="无" />
                <el-radio label="曾经有" />
                <el-radio label="现在有" />
              </el-radio-group>
            </el-form-item>
            <el-form-item
              label="是否有过自杀的想法或行为？"
              prop="suicide"
            >
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
              prop="roomId"
            >
              <el-select
                v-model="form.roomId"
                placeholder="请选择咨询室"
              >
                <el-option
                  v-for="item in room"
                  :key="item.roomId"
                  :value="item.roomId"
                  :label="item.name"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-form>
      </el-col>
    </el-row>
    <el-row v-if="(form.roomId!=='')&(form.type==='offline')">
      <el-col
        :span="20"
        :offset="2"
      >
        <h1 style="text-align:center;">咨询室使用情况</h1>
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
          >取消添加</el-button>
          <el-button
            type="primary"
            icon="el-icon-check"
            plain
            @click="handleAddOrder"
          >确定添加</el-button>
        </div>
        <div v-else>
          <el-button
            type="info"
            icon="el-icon-close"
            plain
            @click="$router.back(-1)"
          >取消查看</el-button>
          <el-button
            type="success"
            icon="el-icon-edit"
            plain
            @click="handleAddOrder"
          >接受预约</el-button>
          <el-button
            type="danger"
            icon="el-icon-delete"
            plain
            @click="handleDeleteOrder"
          >拒绝预约</el-button>
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
import { INITIAL_EVENTS, defaultConstraint } from '@/utils/event-utils'
import { transForm, newForm, RetransForm } from '@/utils/form-utils'
import { getOrderById, postOrder, updateOrderById, deleteOrderById } from '@/api/order'
import { getRoomConstraintById, getRoomCalendarById } from '@/api/room'
import '@fullcalendar/core/locales/zh-cn'

export default {
  name: 'OrderPage',
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  data() {
    return {
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
        selectable: false,
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
        /* you can update a remote database when these fire:
        eventAdd:
        eventChange:
        eventRemove:
        */
      },
      currentEvents: [],
      // 其他config
      alertTypeDuration: false,
      alertConsultDuration: false,
      alertQuestionVisible: false,
      alertFamilyVisible: false,
      alertExpectationVisible: false,
      alertHistoryVisible: false,
      alertTestVisible: false,
      form: newForm(),
      room: [{ roomId: '1', name: '咨询室1' }, { roomId: '2', name: '咨询室2' }],
      rules: {
        type: [{ required: true, message: '请选择咨询方式', trigger: 'blur' }],
        ordertime: [{ required: true, message: '请选择预约时间', trigger: 'blur' }],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }],
        gender: [{ required: true, message: '请选择性别', trigger: 'blur' }],
        birth: [{ required: true, message: '请选择日期', trigger: 'blur' }],
        phone: [{ required: true, message: '请填写联系方式', trigger: 'blur' }],
        address: [{ required: true, message: '请填写家庭住址', trigger: 'blur' }],
        emergency: [{ required: true, message: '请填写紧急联系人', trigger: 'blur' }],
        emergencyphone: [{ required: true, message: '请填写紧急联系人联系方式', trigger: 'blur' }],
        question: [{ required: true, message: '请填写来询问题', trigger: 'blur' }],
        family: [{ required: true, message: '请填写家庭情况', trigger: 'blur' }],
        expectation: [{ required: true, message: '请填写咨询期望', trigger: 'blur' }],
        history: [{ required: true, message: '请填写咨询历史', trigger: 'blur' }],
        test: [{ required: true, message: '请填写心理测试历史', trigger: 'blur' }],
        sleep: [{ required: true, message: '请选择睡眠状况', trigger: 'blur' }],
        relationship: [{ required: true, message: '请选择人机关系状况', trigger: 'blur' }],
        stress: [{ required: true, message: '请选择压力状况', trigger: 'blur' }],
        mood: [{ required: true, message: '请选择心情状况', trigger: 'blur' }],
        hurt: [{ required: true, message: '请选择自伤行为状况', trigger: 'blur' }],
        suicide: [{ required: true, message: '请选择自杀行为状况', trigger: 'blur' }],
        roomId: [{ required: false, message: '请选择咨询室', trigger: 'blur' }]
      }
    }
  },
  watch: {
    'form.type': function (newVal) {
      if (newVal === 'offline') {
        this.$refs.form.rules.roomId[0].required = true
      } else {
        this.form.roomId = null
        this.$refs.form.rules.roomId[0].required = false
      }
    },
    'form.roomId': function (newVal) {
      if (newVal) {
        // TODO 获取咨询室列表
        this.roomConfig.businessHours = defaultConstraint()
        this.roomConfig.selectConstraint = defaultConstraint()
        getRoomConstraintById(newVal).then((res) => {
          this.roomConfig.selectConstraint = res // 传入限制时间数组
          getRoomCalendarById(newVal).then((res) => {
            this.roomConfig.initialEvents = res // 传入咨询室日程
            this.roomConfig
          }).catch((err) => {
            console.log(err)
            this.$notify.error({
              title: '提示',
              message: '网络忙，咨询室日程获取失败',
            })
          })
        }).catch((err) => {
          console.log(err)
          this.$notify.error({
            title: '提示',
            message: '网络忙，咨询室限制信息获取失败',
          })
        })
      }
    }
  },
  created() {
    this.form.doctorId = this.$route.params.doctorId
    this.form.orderId = this.$route.params.orderId
    this.form.ordertime = this.$route.params.selectInfo.start
    if (Boolean(this.initVisible) === false) {
      // 获取预约form【查看预约】
      getOrderById(this.form.orderId).then((res) => {
        if (res.code === 0) {
          this.form = transForm(res.data[0])
        } else {
          this.$notify.error({
            title: '提示',
            message: '预约信息获取失败',
          })
        }
      }).catch((err) => {
        console.log(err)
        this.$notify.error({
          title: '提示',
          message: '网络忙，预约信息获取失败',
        })
      })
    }
  },
  methods: {
    handleWeekendsToggle() {
      this.roomConfig.weekends = !this.roomConfig.weekends // update a property
    },

    handleEvents(events) {
      this.currentEvents = events
    },
    errorHandler() {
      return true
    },

    // 预约信息处理
    // 添加/修改预约
    handleAddOrder() {
      console.log(this.getForm())
      this.$refs['form'].validate((valid) => {
        if (valid) {
          // 添加form信息
          postOrder(RetransForm(this.getForm())).then((res) => {
            if (res.code === 0) {
              this.$router.back(-1)
              this.$notify.success({
                title: '提示',
                message: '预约添加成功',
              })
            } else {
              this.$notify.error({
                title: '提示',
                message: '预约添加失败',
              })
            }
          }).catch((err) => {
            console.log(err)
            this.$notify.error({
              title: '提示',
              message: '网络忙，预约添加失败',
            })
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },

    // 拒绝预约
    handleDeleteOrder() {
      deleteOrderById(this.form.orderId).then((res) => {
        if (res.code === 0) {
          this.$router.back(-1)
          this.$notify.success({
            title: '提示',
            message: '预约拒绝成功',
          })
        } else {
          this.$notify.error({
            title: '提示',
            message: res.code + '预约拒绝失败',
          })
        }
      }).catch((err) => {
        console.log(err)
        this.$notify.error({
          title: '提示',
          message: '网络忙，预约拒绝失败',
        })
      })
    },

    // 修改预约
    handleUpdateOrder() {
      updateOrderById(this.form.orderId, RetransForm(this.getForm())).then((res) => {
        if (res.code === 0) {
          this.$router.back(-1)
          this.$notify.success({
            title: '提示',
            message: '预约信息修改成功',
          })
        }
      }).catch((err) => {
        console.log(err)
        this.$notify.error({
          title: '提示',
          message: '网络忙，预约信息修改失败',
        })
      })
    },

    getForm() {
      const params = JSON.parse(JSON.stringify(this.form))
      params['start'] = this.orderSelectInfo.startStr
      params['end'] = this.orderSelectInfo.endStr
      return params
    }
  },
}
</script>
