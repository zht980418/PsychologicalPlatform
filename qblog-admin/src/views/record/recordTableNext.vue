<template>
  <el-row class="page">
    <el-button type="primary" v-print="'#printNotFirst'" style="margin-left:30px;">打印再次表</el-button>
    <!--    再次咨询表-->
    <el-form
      label-position="left"
      ref="form"
      :model="form"
      autocomplete="on"
      label-width="80px"
    >
      <el-row>
        <el-col
          :span="22"
          :offset="1"
        >
          <el-row id="printNotFirst">
            <h2
              style="margin:30px 40%;"
            >再次咨询记录表</h2>
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
                  v-model="form.userid"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" :offset="0">
              <el-form-item  label-width="60px" label="咨询师">
                <el-input v-model="form.doctorname"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
            <el-row>
            <el-col :span="12" :offset="0">
              <el-form-item  label-width="100px" label="咨询次数">
                <el-input v-model="form.times"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
            <el-row>
            <el-form-item label="咨询要点记录(A):" label-width="130px">
              <el-input type="textarea" :rows="10" v-model="form.mainpoint"></el-input>
            </el-form-item>
          </el-row>
            <el-row>
            <el-form-item label="后期跟进计划（P）:" label-width="140px">
              <el-form-item label="是否进行下一次预约:" label-width="150px">
                <el-radio-group v-model="form.nextexist">
                  <el-radio label="是"></el-radio>
                  <el-radio label="否"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-row>
                <el-form-item
                  label="预计下次预约时间:"
                  label-width="180px"
                >
                  <el-input v-model="form.date3"></el-input>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item label="咨询方式:" label-width="180px">
                  <el-input v-model="form.consulttype"  ></el-input>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item label="依据严重性程度，是否需要特别关注？" label-width="270px">
                  <el-radio-group v-model="form.specialattention">
                    <el-radio label="是"></el-radio>
                    <el-radio label="否"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item label="本次咨询作业和下次咨询计划:" label-width="260px">
                  <el-input type="textarea" :rows="5" v-model="form.followplan"></el-input>
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
                <el-input v-model="form.sign"></el-input>
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
                  v-model="form.date1"
                  style="width: 100%;"
                ></el-date-picker>
              </el-col>
              <el-col
                :span="10"
                :offset="1"
              >
                <el-time-picker
                  placeholder="选择时间"
                  v-model="form.date2"
                  style="width: 100%;"
                ></el-time-picker>
              </el-col>
            </el-col>
          </el-row>
          </el-row>
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
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </el-row>
</template>

<script>
import { getRecordTableByNo, addRecordTableInfo, updateRecordTableByNo, getRecordTableCount } from "@/api/recordTable";

export default {
  data() {
    return {
      // 如果是执行编辑或者添加，显示提交按钮
      canSubmit: this.$route.query.type === "edit" ||  this.$route.query.type === "add",
      form: {
        type: 'NotFirst',
        userid: this.$route.query.userid,
        doctorname: '',
        consultno: '',
        times:2,
        date1: '',
        date2:'',
        date3:'',
        consulttype:'',
        mainpoint:'',
        nextexist: false,
        specialattention:'',
        followplan: '',
        sign: '',
      },
    }
  },
  created() {
    // 获取咨询次数
    getRecordTableCount(this.$route.query.userid)
      .then(res => this.form.times = res.data + 1)
      .catch((err) => {
      console.log(err)
      this.$notify.error({
        title: '提示',
        message: '网络忙，咨询记录获取失败',
      })
    })
    if(this.$route.query.type === 'edit' || this.$route.query.type === 'view'){
      getRecordTableByNo(this.$route.query.consultno).then((res) => {
        if (res.code === 0) {
          console.log('getRecordTableByNo',res)
          let data = res.data
          this.form = {...data}
        }
      }).catch((err) => {
        console.log(err)
        this.$notify.error({
          title: '提示',
          message: '网络忙，咨询记录获取失败',
        })
      })
    }
  },
  methods: {
    onSubmit() {
      //触发添加
      if(this.$route.query.type === 'add'){
        addRecordTableInfo(this.form).then((res) => {
          if (res.code === 0) {
            this.$notify.success({
              title: '提示',
              message: '提交成功',
            })
            this.$router.push({
              name: 'viewRecord',
              query:{
                userid:　this.$route.query.userid,
                nickname: this.$route.query.nickname,
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
        updateRecordTableByNo(this.$route.query.consultno, this.form).then((res) => {
          if (res.code === 0) {
            this.$notify.success({
              title: '提示',
              message: '提交成功',
            })
            this.$router.push({
              name: 'viewRecord',
              query: {
                userid: this.$route.query.userid,
                nickname: this.$route.query.nickname,
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
    returnList() {
      this.$router.push({
        name: 'viewRecord',
        query:{
          userid:　this.$route.query.userid,
          nickname: this.$route.query.nickname,
        }
      })
    },
    handleAlert(){
      console.log("报警警")
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
