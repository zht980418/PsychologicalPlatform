<template>
  <el-row class="page">
    <el-backtop
      :visibility-height=0
      :right=60
      :bottom=60
    >
      <el-tooltip
        class="item"
        effect="dark"
        content="点击按钮立即报警"
        placement="top-start"
      >
        <i class="el-icon-phone" />
      </el-tooltip>
    </el-backtop>
    <h2
      v-if="formFlag"
      style="margin:30px 40%;"
    >首次咨询记录表</h2>
    <h2
      v-else
      style="margin:30px 40%;"
    >再次咨询记录表</h2>
    <el-button
      v-on:click="formToggle"
      style="margin-left:30px;"
    >
      <span>切换记录表</span>
    </el-button>
    <!--    首次咨询表-->
    <el-form
      v-if="formFlag"
      label-position="left"
      ref="form"
      :model="form1"
      autocomplete="on"
      label-width="80px"
    >
      <el-row>
        <el-col
          :span="22"
          :offset="1"
        >
          <el-row :gutter="20">
            <el-col
              :span="12"
              :offset="0"
            >
              <el-form-item
                label-width="100px"
                label="来访者编号"
                disabled="true"
              >
                <el-input
                  :disabled="true"
                  v-model="form1.userid"
                ></el-input>
              </el-form-item>
            </el-col>
<<<<<<< HEAD
            <el-col
              :span="12"
              :offset="0"
            >
              <el-form-item
                label-width="60px"
                label="咨询师"
              >
                <el-input v-model="form1.doctorName"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col
              :span="12"
              :offset="0"
            >
              <el-form-item
                label-width="100px"
                label="咨询次数"
              >
                <el-input v-model="form1.consultNo"></el-input>
=======
            <el-col :span="12" :offset="0">
              <el-form-item  label-width="60px" label="咨询师">
                <el-input v-model="form1.doctorname"></el-input>
>>>>>>> ee0c8db3894d5a3aa2081b251b853884811dcf6e
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item
              label="来访者主诉（S）:"
              label-width="130px"
            >
              <el-input
                type="textarea"
                :rows="2"
                v-model="form1.complaint"
              ></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item
              label="初次主要印象(O):"
              label-width="130px"
            >
              <el-input
                type="textarea"
                :rows="1"
                v-model="form1.impression"
              ></el-input>
            </el-form-item>
          </el-row>
          <el-row>
<<<<<<< HEAD
            <el-form-item
              label="咨询要点记录(A):"
              label-width="130px"
            >
              <el-input
                type="textarea"
                :rows="10"
                v-model="form1.mainPoint"
              ></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item
              label="后期跟进计划（P）:"
              label-width="140px"
            >
              <el-form-item
                label="是否进行下一次预约:"
                label-width="150px"
              >
                <el-radio-group v-model="form1.nextExist">
=======
            <el-form-item label="咨询要点记录(A):" label-width="130px">
              <el-input type="textarea" :rows="10" v-model="form1.mainpoint"></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="后期跟进计划（P）:" label-width="140px">
              <el-form-item label="是否进行下一次预约:" label-width="150px">
                <el-radio-group v-model="form1.nextexist">
>>>>>>> ee0c8db3894d5a3aa2081b251b853884811dcf6e
                  <el-radio label="是"></el-radio>
                  <el-radio label="否"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-row>
                <el-form-item
                  label="预计下次咨询时间"
                  label-width="180px"
                >
                  <el-input v-model="form1.date3"></el-input>
                </el-form-item>
              </el-row>
              <el-row>
<<<<<<< HEAD
                <el-form-item
                  label="咨询方式:"
                  label-width="180px"
                >
                  <el-input v-model="form1.consultType"></el-input>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item
                  label="依据严重性程度，是否需要特别关注？"
                  label-width="270px"
                >
                  <el-radio-group v-model="form1.specialAttention">
=======
                <el-form-item label="咨询方式:" label-width="180px">
                  <el-input v-model="form1.consulttype"  ></el-input>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item label="依据严重性程度，是否需要特别关注？" label-width="270px">
                  <el-radio-group v-model="form1.specialattention">
>>>>>>> ee0c8db3894d5a3aa2081b251b853884811dcf6e
                    <el-radio label="是"></el-radio>
                    <el-radio label="否"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-row>
              <el-row>
<<<<<<< HEAD
                <el-form-item
                  label="本次咨询作业和下次咨询计划:"
                  label-width="260px"
                >
                  <el-input
                    type="textarea"
                    :rows="5"
                    v-model="form1.followPlan"
                  ></el-input>
=======
                <el-form-item label="本次咨询作业和下次咨询计划:" label-width="260px">
                  <el-input type="textarea" :rows="5" v-model="form1.followplan"></el-input>
>>>>>>> ee0c8db3894d5a3aa2081b251b853884811dcf6e
                </el-form-item>
              </el-row>
            </el-form-item>
          </el-row>
          <el-row>
            <el-col
              :span="12"
              :offset="0"
            >
              <el-form-item
                label-width="100px"
                label="咨询师签字"
              >
                <el-input v-model="form1.sign"></el-input>
              </el-form-item>
            </el-col>
            <el-col
              :span="12"
              :offset="0"
            >
              <el-col :span="11">
                <el-date-picker
                  type="date"
                  placeholder="选择日期"
                  v-model="form1.date1"
                  style="width: 100%;"
                ></el-date-picker>
              </el-col>
              <el-col
                :span="10"
                :offset="1"
              >
                <el-time-picker
                  placeholder="选择时间"
                  v-model="form1.date2"
                  style="width: 100%;"
                ></el-time-picker>
              </el-col>
            </el-col>
          </el-row>
          <br>
          <el-form-item v-if="canSubmit">
            <el-row>
<<<<<<< HEAD
              <el-col
                :span="5"
                :offset="7"
              >
                <el-button
                  type="primary"
                  @click="onSubmit"
                >立即创建</el-button>
              </el-col>
              <el-col
                :span="5"
                :offset="1"
              >
                <el-button>取消创建</el-button>
              </el-col>
=======
            <el-col :span="5" :offset="7">
              <el-button type="primary" @click="onSubmit">立即提交</el-button>
            </el-col>
            <el-col :span="5" :offset="1">
              <el-button>取消提交</el-button>
            </el-col>
>>>>>>> ee0c8db3894d5a3aa2081b251b853884811dcf6e
            </el-row>
          </el-form-item>
          <el-form-item v-else>
            <el-row>
              <el-col :span="5" :offset="7">
                <el-button type="primary" @click="returnList">返回列表</el-button>
              </el-col>
            </el-row>
          </el-form-item>
          <br>
          <br>
          <br>
        </el-col>
      </el-row>
    </el-form>
    <!--    再次咨询表-->
    <el-form
      v-else
      label-position="left"
      ref="form"
      :model="form2"
      autocomplete="on"
      label-width="80px"
    >
      <el-row>
        <el-col
          :span="22"
          :offset="1"
        >
          <el-row :gutter="20">
            <el-col
              :span="12"
              :offset="0"
            >
              <el-form-item
                label-width="100px"
                label="来访者编号"
              >
                <el-input
                  :disabled="true"
                  v-model="form2.userid"
                ></el-input>
              </el-form-item>
            </el-col>
<<<<<<< HEAD
            <el-col
              :span="12"
              :offset="0"
            >
              <el-form-item
                label-width="60px"
                label="咨询师"
              >
                <el-input v-model="form2.doctorName"></el-input>
=======
            <el-col :span="12" :offset="0">
              <el-form-item  label-width="60px" label="咨询师">
                <el-input v-model="form2.doctorname"></el-input>
>>>>>>> ee0c8db3894d5a3aa2081b251b853884811dcf6e
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
<<<<<<< HEAD
            <el-col
              :span="12"
              :offset="0"
            >
              <el-form-item
                label-width="100px"
                label="咨询次数"
              >
                <el-input v-model="form2.consultNo"></el-input>
=======
            <el-col :span="12" :offset="0">
              <el-form-item  label-width="100px" label="咨询次数">
                <el-input v-model="form2.consultno"></el-input>
>>>>>>> ee0c8db3894d5a3aa2081b251b853884811dcf6e
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
<<<<<<< HEAD
            <el-form-item
              label="咨询要点记录(A):"
              label-width="130px"
            >
              <el-input
                type="textarea"
                :rows="10"
                v-model="form2.mainPoint"
              ></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item
              label="后期跟进计划（P）:"
              label-width="140px"
            >
              <el-form-item
                label="是否进行下一次预约:"
                label-width="150px"
              >
                <el-radio-group v-model="form2.nextExist">
=======
            <el-form-item label="咨询要点记录(A):" label-width="130px">
              <el-input type="textarea" :rows="10" v-model="form2.mainpoint"></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="后期跟进计划（P）:" label-width="140px">
              <el-form-item label="是否进行下一次预约:" label-width="150px">
                <el-radio-group v-model="form2.nextexist">
>>>>>>> ee0c8db3894d5a3aa2081b251b853884811dcf6e
                  <el-radio label="是"></el-radio>
                  <el-radio label="否"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-row>
                <el-form-item
                  label="预计下次预约时间:"
                  label-width="180px"
                >
                  <el-input v-model="form2.date3"></el-input>
                </el-form-item>
              </el-row>
              <el-row>
<<<<<<< HEAD
                <el-form-item
                  label="咨询方式:"
                  label-width="180px"
                >
                  <el-input v-model="form2.consultType"></el-input>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item
                  label="依据严重性程度，是否需要特别关注？"
                  label-width="270px"
                >
                  <el-radio-group v-model="form2.specialAttention">
=======
                <el-form-item label="咨询方式:" label-width="180px">
                  <el-input v-model="form2.consulttype"  ></el-input>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item label="依据严重性程度，是否需要特别关注？" label-width="270px">
                  <el-radio-group v-model="form2.specialattention">
>>>>>>> ee0c8db3894d5a3aa2081b251b853884811dcf6e
                    <el-radio label="是"></el-radio>
                    <el-radio label="否"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-row>
              <el-row>
<<<<<<< HEAD
                <el-form-item
                  label="本次咨询作业和下次咨询计划:"
                  label-width="260px"
                >
                  <el-input
                    type="textarea"
                    :rows="5"
                    v-model="form2.followPlan"
                  ></el-input>
=======
                <el-form-item label="本次咨询作业和下次咨询计划:" label-width="260px">
                  <el-input type="textarea" :rows="5" v-model="form2.followplan"></el-input>
>>>>>>> ee0c8db3894d5a3aa2081b251b853884811dcf6e
                </el-form-item>
              </el-row>
            </el-form-item>
          </el-row>
          <el-row>
            <el-col
              :span="12"
              :offset="0"
            >
              <el-form-item
                label-width="100px"
                label="咨询师签字"
              >
                <el-input v-model="form2.sign"></el-input>
              </el-form-item>
            </el-col>
            <el-col
              :span="12"
              :offset="0"
            >
              <el-col :span="11">
                <el-date-picker
                  type="date"
                  placeholder="选择日期"
                  v-model="form2.date1"
                  style="width: 100%;"
                ></el-date-picker>
              </el-col>
              <el-col
                :span="10"
                :offset="1"
              >
                <el-time-picker
                  placeholder="选择时间"
                  v-model="form2.date2"
                  style="width: 100%;"
                ></el-time-picker>
              </el-col>
            </el-col>
          </el-row>
<<<<<<< HEAD
          <el-form-item v-if="routeType">
            <el-button
              type="primary"
              @click="onSubmit"
            >立即创建</el-button>
            <el-button>取消</el-button>
=======
          <el-form-item v-if="canSubmit">
            <el-button type="primary" @click="onSubmit">立即提交</el-button>
            <el-button>取消提交</el-button>
          </el-form-item>
          <el-form-item v-else>
            <el-row>
              <el-col :span="5" :offset="7">
                <el-button type="primary" @click="returnList">返回列表</el-button>
              </el-col>
            </el-row>
>>>>>>> ee0c8db3894d5a3aa2081b251b853884811dcf6e
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </el-row>
</template>

<script>
import { getRecordTableByNo, addRecordTableInfo, updateRecordTableByNo } from "@/api/recordTable";

export default {
  data() {
    return {
      formFlag: true,
      //如果是新建按钮才显示切换按钮
      canSwitch: this.$route.params.type === 'add',
      // 如果是执行编辑或者添加，显示提交按钮
      canSubmit: this.$route.params.type === "edit" ||  this.$route.params.type === "add",
      form1: {
        type: 'First',
        userid: this.$route.params.userid,
        doctorname: '',
        consultno: '',
        date1: '',
<<<<<<< HEAD
        date2: '',
        date3: '',
        consultType: '',
        mainPoint: '',
        nextExist: false,
        specialAttention: '',
        followPlan: '',
        sign: '',
=======
        date2:'',
        date3:'',
        consulttype:'',
        mainpoint:'',
        nextexist: false,
        specialattention:'',
        followplan:'',
        sign:'',
>>>>>>> ee0c8db3894d5a3aa2081b251b853884811dcf6e
        complaint: '',
        impression: '',
      },
      form2: {
        type: 'NotFirst',
        userid: this.$route.params.userid,
        doctorname: '',
        consultno: '',
        times: '',
        date1: '',
<<<<<<< HEAD
        date2: '',
        date3: '',
        consultType: '',
        mainPoint: '',
        nextExist: false,
        specialAttention: '',
        followPlan: '',
=======
        date2:'',
        date3:'',
        consulttype:'',
        mainpoint:'',
        nextexist: false,
        specialattention:'',
        followplan: '',
>>>>>>> ee0c8db3894d5a3aa2081b251b853884811dcf6e
        sign: '',
      },

    }
  },
  created() {
    console.log("params", this.$route.params)
<<<<<<< HEAD
=======
    if(this.$route.params.type === 'edit' || this.$route.params.type === 'view'){
      getRecordTableByNo(this.$route.params.consultno).then((res) => {
        if (res.code === 0) {
          console.log(res)
          let data = res.data
          if(data.type !== 'NoFirst'){
            this.formFlag = true
            this.form1 = {...data}
            console.log( this.form1)
          }else{
            this.formFlag = false
            this.form2 = {...data}
            this.form2.times = getRecordTableCount(this.$route.params.uesrid)
          }
        }
      }).catch((err) => {
        console.log(err)
        this.$notify.error({
          title: '提示',
          message: '网络忙，咨询记录获取失败',
        })
      })
    }
>>>>>>> ee0c8db3894d5a3aa2081b251b853884811dcf6e
  },
  methods: {
    onSubmit() {
      const form = this.formFlag  ? this.form1 : this.form2
      //触发添加
      if(this.$route.params.type === 'add'){
        addRecordTableInfo(form).then((res) => {
          if (res.code === 0) {
            this.$notify.success({
              title: '提示',
              message: '提交成功',
            })
            this.$router.push({
              name: 'viewRecord',
              params:{
                userid:　this.$route.params.userid,
                nickname: this.$route.params.nickname,
              }
            })
          }
        }).catch((err) => {
          console.log(err)
          this.$notify.error({
            title: '提示',
            message: '网络忙，咨询记录失败',
          })
        })
      }
      // 触发修改
      else {
        updateRecordTableByNo(this.$route.params.consultno, form).then((res) => {
          if (res.code === 0) {
            this.$notify.success({
              title: '提示',
              message: '提交成功',
            })
            this.$router.push({
              name: 'viewRecord',
              params: {
                userid: this.$route.params.userid,
                nickname: this.$route.params.nickname,
              }
            })
          }
        }).catch((err) => {
          console.log(err)
          this.$notify.error({
            title: '提示',
            message: '网络忙，咨询记录失败',
          })
        })
      }

    },
    formToggle: function () {
      console.log("切換表格")
      this.formFlag = !this.formFlag;
    },
    returnList() {
      this.$router.push({
        name: 'viewRecord',
        params:{
          userid:　this.$route.params.userid,
          nickname: this.$route.params.nickname,
        }
      })
    }
  }
}
</script>

<style scoped>
.el-form-item {
  margin-right: 30px;
}
.el-row {
  margin-top: 20px;
}
</style>
