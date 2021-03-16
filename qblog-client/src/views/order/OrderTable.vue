<template>
  <div class='demo-app'>
    <div class='demo-app-sidebar'>
      <div class='demo-app-sidebar-section'>
        <h2>功能介绍</h2>
        <ul>
          <li>点击时段即可预约</li>
          <li>再次点击预约即可删除</li>
        </ul>
      </div>
      <div class='demo-app-sidebar-section'>
        <label>
          <input
            type='checkbox'
            :checked='calendarOptions.weekends'
            @change='handleWeekendsToggle'
          />
          展示周末
        </label>
      </div>
    </div>
    <div class='demo-app-main'>
      <FullCalendar
        class='demo-app-calendar'
        :options='calendarOptions'
      >
        <template v-slot:eventContent='arg'>
          <b>{{ arg.timeText }}</b>
          <i>{{ arg.event.title }}</i>
        </template>
      </FullCalendar>
    </div>
    <el-dialog
      title="知情同意书"
      :visible.sync="dialogTextVisible"
      center
    >
      <TextSample />
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button @click="dialogTextVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="(dialogFormVisible = true) & (dialogTextVisible = false)"
        >确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog
      title="咨询预约"
      :visible.sync="dialogFormVisible"
      :inline="true"
      :before-close="handleClose"
      center
    >
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
              <el-select
                v-model="form.type"
                placeholder="请选择咨询方式"
              >
                <el-option
                  label="线上"
                  value="online"
                ></el-option>
                <el-option
                  label="线下"
                  value="offline"
                ></el-option>
              </el-select>
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
                    :readonly="true"
                  />
                </el-col>
                <el-col
                  :span="1"
                  :offset="6"
                >
                  <i
                    class="el-icon-warning-outline"
                    @mouseover="alertTypeDuration = true"
                    @mouseleave="alertTypeDuration = false"
                  />
                </el-col>
                <el-col
                  :span="11"
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
                  <el-input v-model="form.name"></el-input>
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
                  >
                  </el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item
                  label="职业"
                  :label-width="formLabelWidth"
                  prop="occupation"
                >
                  <el-input v-model="form.occupation"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-form-item
                label="联系方式"
                :label-width="formLabelWidth"
                prop="phone"
              >
                <el-input v-model="form.phone"></el-input>
              </el-form-item>
            </el-row>
            <el-row>
              <el-form-item
                label="家庭住址"
                :label-width="formLabelWidth"
                prop="address"
              >
                <el-input v-model="form.address"></el-input>
              </el-form-item>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item
                  label="紧急联系人"
                  :label-width="formLabelWidth"
                  prop="emergency"
                >
                  <el-input v-model="form.emergency"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item
                  label="紧急联系人电话"
                  :label-width="formLabelWidth"
                  prop="emergencyphone"
                >
                  <el-input v-model="form.emergencyphone"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-form-item
              label="来询问题"
              :label-width="formLabelWidth"
              prop="question"
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
                    show-icon
                  >
                  </el-alert>
                </el-col>
              </el-row>
            </el-form-item>
            <el-form-item
              label="家庭情况"
              :label-width="formLabelWidth"
              prop="family"
            >
              <el-row>
                <el-col :span="21">
                  <el-input
                    type="textarea"
                    v-model="form.family"
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
                    title="你过去是否有重大躯体疾病史、或重大成长事件影响到现在困惑的你?"
                    type="info"
                    :closable="false"
                    v-show="alertFamilyVisible"
                    show-icon
                  >
                  </el-alert>
                </el-col>
              </el-row>
            </el-form-item>
            <el-form-item
              label="咨询期望"
              :label-width="formLabelWidth"
              prop="expectation"
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
                    title="你期待通过咨询达到什么样的目标?"
                    type="info"
                    :closable="false"
                    v-show="alertExpectationVisible"
                    show-icon
                  >
                  </el-alert>
                </el-col>
              </el-row>
            </el-form-item>
            <el-form-item
              label="咨询历史"
              :label-width="formLabelWidth"
              prop="history"
            >
              <el-row>
                <el-col :span="21">
                  <el-input
                    type="textarea"
                    v-model="form.history"
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
                    title="以前有没有做过咨询，得到什么结果?"
                    type="info"
                    :closable="false"
                    v-show="alertHistoryVisible"
                    show-icon
                  >
                  </el-alert>
                </el-col>
              </el-row>
            </el-form-item>
            <el-form-item
              label="心理测试"
              :label-width="formLabelWidth"
              prop="test"
            >
              <el-row>
                <el-col :span="21">
                  <el-input
                    type="textarea"
                    v-model="form.test"
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
                    title="以前有没有做过心理测试，得到什么结果?"
                    type="info"
                    :closable="false"
                    v-show="alertTestVisible"
                    show-icon
                  >
                  </el-alert>
                </el-col>
              </el-row>
            </el-form-item>
            <el-col
              :span="22"
              :offset="2"
            >
              <el-form-item
                label="在过去一个月里，睡眠状况如何？"
                prop="sleep"
              >
                <el-radio-group v-model="form.sleep">
                  <el-radio label="很差"></el-radio>
                  <el-radio label="不满意"></el-radio>
                  <el-radio label="正常"></el-radio>
                  <el-radio label="良好"></el-radio>
                  <el-radio label="很好"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item
                label="在过去一个月里，人际关系如何？"
                prop="relationship"
              >
                <el-radio-group v-model="form.relationship">
                  <el-radio label="很差"></el-radio>
                  <el-radio label="不满意"></el-radio>
                  <el-radio label="正常"></el-radio>
                  <el-radio label="良好"></el-radio>
                  <el-radio label="很好"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item
                label="在过去一个月里，压力水平如何？"
                prop="stress"
              >
                <el-radio-group v-model="form.stress">
                  <el-radio label="很高"></el-radio>
                  <el-radio label="高"></el-radio>
                  <el-radio label="中等"></el-radio>
                  <el-radio label="还好"></el-radio>
                  <el-radio label="很好"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item
                label="在过去一个月里，心情如何？"
                prop="mood"
              >
                <el-radio-group v-model="form.mood">
                  <el-radio label="很低落"></el-radio>
                  <el-radio label="低落"></el-radio>
                  <el-radio label="一般"></el-radio>
                  <el-radio label="心情不错"></el-radio>
                  <el-radio label="心情特别好"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item
                label="是否有过自伤行为？"
                prop="hurt"
              >
                <el-radio-group v-model="form.hurt">
                  <el-radio label="无"></el-radio>
                  <el-radio label="曾经有"></el-radio>
                  <el-radio label="现在有"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item
                label="是否有过自杀的想法或行为？"
                prop="suicide"
              >
                <el-radio-group v-model="form.suicide">
                  <el-radio label="无"></el-radio>
                  <el-radio label="曾经有"></el-radio>
                  <el-radio label="现在有"></el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-form>
        </el-col>
      </el-row>
      <el-row>
        <el-col
          :span="22"
          :offset="1"
        >
          <el-alert
            type="error"
            :closable="false"
          >
            <h3>点击确认按钮即表示本人已经详细阅读《心理咨询知情同意书》，对整个咨询流程已经了解，没有异议。</h3>
          </el-alert>
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
        <el-button @click="dialogFormVisible=false">取消预约</el-button>
        <el-button
          type="primary"
          @click="addEvent(form,selection)"
        >确认预约</el-button>
      </div>
      <div
        v-if="dialogEditVisible"
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="info"
          icon="el-icon-close"
          @click="(dialogFormVisible=false)&(dialogEditVisible=false)&handleClose"
        >取消查看</el-button>
        <el-button
          type="success"
          icon="el-icon-edit"
          @click="handleEventEdit(form,selection)"
        >修改预约</el-button>
        <el-button
          type="danger"
          icon="el-icon-delete"
          @click="handleEventDelete(form,selection)"
        >删除预约</el-button>
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
import { createEventId, defaultConstraint, transEvent } from '@/utils/event-utils'
import { transForm, newForm, RetransForm } from '@/utils/form-utils'
import { getConstraintById, getCalendarById, postOrder, getOrderById, deleteOrderById, updateOrderById } from '@/api/order'
import TextSample from './textsample'
import { mapGetters } from 'vuex'

export default {
  components: {
    FullCalendar, // make the <FullCalendar> tag available
    TextSample,
  },
  data() {
    return {
      doctorId: this.$route.params.doctorId,
      formLabelWidth: '120px',
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
        // initialEvents: INITIAL_EVENTS, // alternatively, use the `events` setting to fetch from a feed
        events: [],
        editable: false,  // 拖动并选择多个时段
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
      selection: '',
      dialogTextVisible: false,// 知情同意书
      dialogFormVisible: false,// 表格
      dialogEditVisible: false,// 修改按钮
      alertTypeDuration: false,
      alertQuestionVisible: false,
      alertFamilyVisible: false,
      alertExpectationVisible: false,
      alertHistoryVisible: false,
      alertTestVisible: false,
      form: newForm(),
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
      }
    }
  },
  computed: {
    ...mapGetters({
      uid: 'id'
    })
  },
  watch: {
    selection(newVal) {
      if (newVal.start) { this.form.ordertime = newVal.start }
    },
  },
  created() {
    this.calendarOptions.businessHours = defaultConstraint()
    this.calendarOptions.selectConstraint = defaultConstraint()
    this.form.doctorId = this.$route.params.doctorId
    // 获取限制信息
    getConstraintById('张三').then((res) => {
      if (res.code === 0) {
        this.calendarOptions.selectConstraint = res.data //传入限制时间数组
        this.calendarOptions.businessHours = res.data //传入显示工作时间数组
        getCalendarById(this.doctorId).then((res) => {
          if (res.code === 0) {
            console.log(res)
            this.calendarOptions.events = transEvent(res.data) //传入预约
          }
        }).catch((err) => {
          console.log(err)
          this.$notify.error({
            title: "提示",
            message: "网络忙，预约日程表信息获取失败",
          })
        })
      } else {
        this.$notify.error({
          title: "提示",
          message: "预约日程表限制信息获出错",
        })
      }
    }).catch((err) => {
      console.log(err)
      this.$notify.error({
        title: "提示",
        message: "网络忙，预约日程表限制信息获取失败",
      })
    })
  },
  methods: {
    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
    },
    handleDateSelect(selectInfo) {
      this.dialogTextVisible = true // 展示信息填写form
      this.selection = selectInfo
    },

    addEvent(form, selectInfo) {
      this.dialogFormVisible = false
      let calendarApi = selectInfo.view.calendar
      calendarApi.unselect() // clear date selection
      this.$refs['form'].validate((valid) => {
        if (valid) {
          // 发送预约信息
          this.form.orderId = createEventId()
          postOrder(RetransForm(this.getForm())).then((res) => {
            console.log(res.data)
            // 日程表预约+1
            calendarApi.addEvent({
              id: this.form.orderId,
              title: form.name,
              start: selectInfo.startStr,
              end: selectInfo.endStr
            })
            // 清空表单
            this.$refs['form'].resetFields()
            this.$notify.success({
              title: "提示",
              message: "预约成功!",
            })
          }).catch((err) => {
            console.log(err)
            this.$notify.error({
              title: "提示",
              message: "网络忙，预约失败",
            })
          })
        }
      })
    },

    handleEventClick(clickInfo) {
      // check是不是自己的预约信息
      if (clickInfo.event.groupId === this.uid) {
        const index = this.calendarOptions.events.findIndex((item => {
          if ((item.groupId === this.uid)) { return true }
        }))
        console.log(index)
        if (this.calendarOptions.events[index].status === '') {
          getOrderById(clickInfo.event.id).then((res) => {
            if (res.code === 0) {
              this.form = transForm(res.data[0]) // 传入表单信息 
              // 打开预约信息表格
              this.dialogEditVisible = true
              this.dialogFormVisible = true
              this.selection = clickInfo
            } else {
              this.$notify.error({
                title: "提示",
                message: "预约信息加载失败",
              })
            }
          }).catch((err) => {
            console.log(err)
            this.$notify.error({
              title: "提示",
              message: "网络忙，预约信息加载失败",
            })
          })
        }
        else if (Boolean(this.calendarOptions.events[index].status) === true) {
          this.$notify.success({
            title: '提示',
            message: '咨询师已接受该预约,请于个人中心查看预约信息！',
          })
        } else if (Boolean(this.calendarOptions.events[index].status) === false) {
          this.$notify.error({
            title: '提示',
            message: '该预约已被咨询师拒绝，请重新预约！',
          })
        }
      } else {
        this.$notify.error({
          title: '提示',
          message: '无法查看其他用户预约信息！',
        })
      }
    },

    handleEventDelete(clickInfo) {
      // 删数据
      deleteOrderById(this.form.orderId).then((res) => {
        console.log(res)
        clickInfo.event.remove()
        // 关表格
        this.dialogEditVisible = false
        this.dialogFormVisible = false
        this.$notify.success({
          title: "提示",
          message: "预约信息删除成功",
        })
      }).catch((err) => {
        console.log(err)
        this.$notify.error({
          title: "提示",
          message: "网络忙，删除预约信息失败",
        })
      })
    },

    // 修改预约信息
    handleEventEdit(form, clickInfo) {
      updateOrderById(form.orderId, RetransForm(this.getForm())).then((res) => {
        if (res.code === 0) {
          this.handleEventDelete(form, clickInfo) // 删除
          this.handleModify(form, clickInfo) // 添加
          // 关表格
          this.dialogFormVisible = false
          this.dialogEditVisible = false
        }
      }).catch((err) => {
        console.log(err)
        this.$notify.error({
          title: "提示",
          message: "网络忙，预约信息修改失败",
        })
      })
    },

    // 重新添加预约信息
    handleModify(form, selectInfo) {
      const calendarApi = selectInfo.view.calendar
      calendarApi.unselect() // clear date selection
      if (form.name) {
        calendarApi.addEvent({
          id: selectInfo.event.id,
          title: form.name,
          start: selectInfo.event.startStr,
          end: selectInfo.event.endStr,
          groupId: this.uid
        })
        // 清空表单
        this.$refs['form'].resetFields()
      }
    },

    handleClose(done) {
      // 清空表单
      this.$refs['form'].resetFields()
      done()
    },

    handleEvents(events) {
      this.currentEvents = events
    },

    errorHandler() {
      return true
    },

    getForm() {
      const params = JSON.parse(JSON.stringify(this.form))
      params['start'] = this.selection.startStr
      params['end'] = this.selection.endStr
      params['uid'] = this.id
      return params
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