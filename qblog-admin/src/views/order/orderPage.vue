
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
            <el-col :span="6">
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
                title="选择线下咨询后可在页面最下方预约咨询室"
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
                <el-col :span="11">
                  <el-input v-model="form.name" />
                </el-col>
                <el-col
                  :span="10"
                  :offset="1"
                >
                  <el-button
                    type="primary"
                    plain
                    @click="userDialogVisible=true"
                  >选择用户</el-button>
                </el-col>
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
                  :autosize="{ minRows: 2, maxRows: 4}"
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
                  :autosize="{ minRows: 2, maxRows: 4}"
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
                  :autosize="{ minRows: 2, maxRows: 4}"
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
                  :autosize="{ minRows: 2, maxRows: 4}"
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
                  :autosize="{ minRows: 2, maxRows: 4}"
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
              {{ form.roomId===null ? '': roomList[this.roomList.findIndex((item) => {
              if (item.roomId == form.roomId) { return true }
            })].name }}
              <el-button
                @click="dialogEditVisible=true"
                type="primary"
                plain
                style="margin-left:20px;"
              >选择咨询室</el-button>
            </el-form-item>
          </el-col>
        </el-form>
      </el-col>
    </el-row>
    <el-dialog
      :visible.sync="userDialogVisible"
      title="选择用户"
      style="height=200px"
    >
      <el-col :span="20">
        <el-input
          placeholder="请输入姓名搜索用户"
          prefix-icon="el-icon-search"
          v-model="search"
          @keyup.enter.native="getUserList(search)"
        />
      </el-col>
      <el-col
        :span="2"
        :offset="1"
      >
        <el-button
          type="primary"
          plain
          @click="getUserList(search)"
        >搜索</el-button>
      </el-col>
      <el-table :data="userList">
        <el-table-column
          prop="uid"
          label="uid"
          min-width="120"
          align="center"
        />
        <el-table-column
          prop="name"
          label="姓名"
          min-width="120"
          align="center"
        />
        <el-table-column
          prop="phone"
          label="电话号码"
          min-width="120"
          align="center"
        />
        <el-table-column
          label="操作"
          min-width="120"
          align="center"
        >
          <template slot-scope="scope">
            <el-button
              type="primary"
              plain
              @click="handleChoose(scope.row)"
            >选择
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button
        slot="footer"
        type="info"
        plain
      >返回</el-button>
    </el-dialog>
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
            @click="handleUpdateOrder(true)"
          >接受预约</el-button>
          <el-button
            type="danger"
            icon="el-icon-delete"
            plain
            @click="handleUpdateOrder(false)"
          >拒绝预约</el-button>
        </div>
      </el-col>
    </el-row>
    <el-dialog
      :visible.sync="dialogEditVisible"
      title="咨询室使用情况"
    >
      <span style="margin-left:10px">咨询室：</span>
      <el-select
        v-model="form.roomId"
        placeholder="请选择咨询室"
        clearable
      >
        <el-option
          v-for="item in roomList"
          :key="item.roomId"
          :value="item.roomId"
          :label="item.name"
        />
      </el-select>
      <el-row>
        <el-col
          :span="5"
          :offset="1"
        >
          <h2>功能介绍</h2>
          <ul>
            <li>咨询室日程表</li>
            <li>可查看各咨询室时段使用情况</li>
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
        </el-col>
        <el-col
          :span="17"
          :offset="1"
        >
          <h1 align="center">咨询室排班表</h1>
          <FullCalendar
            id="ca"
            ref="ca"
            :options="roomConfig"
          />
        </el-col>
      </el-row>
      <el-button
        type="primary"
        plain
        @click="dialogEditVisible=false"
      >确认选择</el-button>
      <el-button
        type="info"
        plain
        @click="handleClose"
      >取消选择</el-button>
    </el-dialog>
  </div>
</template>

<script>
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import { newForm } from '@/utils/form-utils'
import { getUserByName } from '@/api/user'
import { getOrderById, postOrder, updateOrderById } from '@/api/order'
import { getRoomCalendarById, getRoomList } from '@/api/room'
import '@fullcalendar/core/locales/zh-cn'
import { mapGetters } from 'vuex'

export default {
  name: 'OrderPage',
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  data() {
    return {
      initVisible: this.$route.params.editType,
      orderSelectInfo: this.$route.params.selectInfo,
      formLabelWidth: '120px',
      userDialogVisible: false,
      dialogEditVisible: false,
      search: '',
      userList: [],
      roomList: [],
      form: newForm(),
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
        slotMaxTime: '18:00:00',
        slotDuration: '01:00:00',
        expandRows: true,
        locale: 'zh-cn',
        // select: this.handleDateSelect,
        // eventClick: this.handleEventClick,
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
      if (newVal != '') {
        getRoomCalendarById(newVal).then((res) => {
          if (res.code === 0) {
            this.roomConfig.events = res.data// 传入咨询室日程
          }
        }).catch((err) => {
          console.log(err)
          this.$notify.error({
            title: '提示',
            message: '网络忙，咨询室日程获取失败',
          })
        })
      } else {
        this.roomConfig.events = []
      }
    }
  },
  computed: {
    ...mapGetters({
      doctorId: 'id'
    })
  },
  created() {
    console.log(this.form.roomId);
    // 获取咨询室list
    getRoomList().then((res) => {
      if (res.code === 0) {
        console.log(res)
        this.roomList = res.data // 传入咨询室列表
      }
    }).catch((err) => {
      console.log(err)
      this.$notify.error({
        title: '提示',
        message: '网络忙，咨询室信息获取失败',
      })
    })
    this.form.doctorId = this.doctorId
    this.form.orderId = this.$route.params.orderId
    this.form.ordertime = this.$route.params.selectInfo.start
    if (Boolean(this.initVisible) === false) {
      // 获取预约form【查看预约】
      getOrderById(this.form.orderId).then((res) => {
        if (res.code === 0) {
          this.form = res.data[0]
          if (this.form.roomId & (this.form.roomId != '')) {
            // 转换roomId，使select显示label
            this.form.roomId = this.roomList[this.roomList.findIndex((item) => {
              if (item.roomId == this.form.roomId) { return true }
            })].roomId
          }
        }
      }).catch((err) => {
        console.log(err)
        this.$notify.error({
          title: '提示',
          message: '网络忙，预约信息获取失败',
        })
      })
    } else {
      this.form.roomId = null
    }
  },
  methods: {
    // 按姓名搜索用户
    getUserList(name) {
      getUserByName(name).then((res) => {
        if (res.code === 0) {
          this.userList = res.data.filter((user) => {
            return user.rolename.trim() === '游客'
          })
        }
      })
    },

    // 选择用户
    handleChoose(row) {
      this.form.uid = row.uid
      this.form.name = row.name
      this.form.phone = row.phone
      this.userDialogVisible = false
    },

    handleClose() {
      this.form.roomId = null
      this.dialogEditVisible = false
    },

    // 预约信息处理
    // 添加预约
    handleAddOrder() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          // 添加form信息
          postOrder(this.getForm()).then((res) => {
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

    // 修改/拒绝预约
    handleUpdateOrder(status) {
      this.form.status = status
      updateOrderById(this.form.orderId, this.getForm()).then((res) => {
        if (res.code === 0) {
          this.$router.back(-1)
          this.$notify.success({
            title: '提示',
            message: '预约修改成功',
          })
        }
      }).catch((err) => {
        console.log(err)
        this.$notify.error({
          title: '提示',
          message: '网络忙，预约修改失败',
        })
      })
    },

    getForm() {
      const params = JSON.parse(JSON.stringify(this.form))
      params['start'] = this.orderSelectInfo.startStr
      params['end'] = this.orderSelectInfo.endStr
      return params
    },

    handleEvents(events) {
      this.currentEvents = events
    },
    errorHandler() {
      return true
    },

  },
}
</script>
