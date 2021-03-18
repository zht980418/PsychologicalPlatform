<template>
  <el-row>
    <h2 v-if="formFlag" style="margin:30px 40%;">首次咨询记录表</h2>
    <h2 v-else style="margin:30px 40%;">再次咨询记录表</h2>
    <el-button v-on:click="formToggle" style="margin-left:30px;">
      <span>切换记录表</span>
    </el-button>
<!--    首次咨询表-->
    <el-form v-if="formFlag" label-position="left" ref="form" :model="form1" autocomplete="on" label-width="80px">
      <el-row>
        <el-col :span="22" :offset="1">
          <el-row :gutter="20">
            <el-col :span="12" :offset="0">
              <el-form-item  label-width="100px" label="来访者编号" disabled="true">
                <el-input :disabled="true" v-model="form1.userid"  ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" :offset="0">
              <el-form-item  label-width="60px" label="咨询师">
                <el-input v-model="form1.doctorName"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12" :offset="0">
              <el-form-item  label-width="100px" label="咨询次数">
                <el-input v-model="form1.consultNo"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item label="来访者主诉（S）:" label-width="130px">
              <el-input type="textarea" :rows="2" v-model="form1.complaint"></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="初次主要印象(O):" label-width="130px">
              <el-input type="textarea" :rows="1" v-model="form1.impression"></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="咨询要点记录(A):" label-width="130px">
              <el-input type="textarea" :rows="10" v-model="form1.mainPoint"></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="后期跟进计划（P）:" label-width="140px">
              <el-form-item label="是否进行下一次预约:" label-width="150px">
                <el-radio-group v-model="form1.nextExist">
                  <el-radio label="是"></el-radio>
                  <el-radio label="否"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-row>
                <el-form-item label="预计下次咨询时间" label-width="180px">
                  <el-input v-model="form1.date3"  ></el-input>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item label="咨询方式:" label-width="180px">
                  <el-input v-model="form1.consultType"  ></el-input>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item label="依据严重性程度，是否需要特别关注？" label-width="270px">
                  <el-radio-group v-model="form1.specialAttention">
                    <el-radio label="是"></el-radio>
                    <el-radio label="否"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item label="本次咨询作业和下次咨询计划:" label-width="260px">
                  <el-input type="textarea" :rows="5" v-model="form1.followPlan"></el-input>
                </el-form-item>
              </el-row>
            </el-form-item>
          </el-row>
          <el-row>
            <el-col :span="12" :offset="0">
              <el-form-item  label-width="100px" label="咨询师签字">
                <el-input v-model="form1.sign"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" :offset="0">
              <el-col :span="11">
                <el-date-picker type="date" placeholder="选择日期" v-model="form1.date1" style="width: 100%;"></el-date-picker>
              </el-col>
              <el-col :span="10" :offset="1">
                <el-time-picker placeholder="选择时间" v-model="form1.date2" style="width: 100%;"></el-time-picker>
              </el-col>
            </el-col>
          </el-row>
          <br>
          <el-form-item v-if="routeType">
            <el-row>
            <el-col :span="5" :offset="7">
              <el-button type="primary" @click="onSubmit">立即创建</el-button>
            </el-col>
            <el-col :span="5" :offset="1">
              <el-button>取消创建</el-button>
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
    <el-form v-else label-position="left" ref="form" :model="form2" autocomplete="on" label-width="80px">
      <el-row>
        <el-col :span="22" :offset="1">
          <el-row :gutter="20">
            <el-col :span="12" :offset="0">
              <el-form-item  label-width="100px" label="来访者编号">
                <el-input :disabled="true"  v-model="form2.userid"  ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" :offset="0">
              <el-form-item  label-width="60px" label="咨询师">
                <el-input v-model="form2.doctorName"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12" :offset="0">
              <el-form-item  label-width="100px" label="咨询次数">
                <el-input v-model="form2.consultNo"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-form-item label="咨询要点记录(A):" label-width="130px">
              <el-input type="textarea" :rows="10" v-model="form2.mainPoint"></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="后期跟进计划（P）:" label-width="140px">
              <el-form-item label="是否进行下一次预约:" label-width="150px">
                <el-radio-group v-model="form2.nextExist">
                  <el-radio label="是"></el-radio>
                  <el-radio label="否"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-row>
                <el-form-item label="预计下次预约时间:" label-width="180px">
                  <el-input v-model="form2.date3"  ></el-input>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item label="咨询方式:" label-width="180px">
                  <el-input v-model="form2.consultType"  ></el-input>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item label="依据严重性程度，是否需要特别关注？" label-width="270px">
                  <el-radio-group v-model="form2.specialAttention">
                    <el-radio label="是"></el-radio>
                    <el-radio label="否"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item label="本次咨询作业和下次咨询计划:" label-width="260px">
                  <el-input type="textarea" :rows="5" v-model="form2.followPlan"></el-input>
                </el-form-item>
              </el-row>
            </el-form-item>
          </el-row>
          <el-row>
            <el-col :span="12" :offset="0">
              <el-form-item  label-width="100px" label="咨询师签字">
                <el-input v-model="form2.sign"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" :offset="0">
              <el-col :span="11">
                <el-date-picker type="date" placeholder="选择日期" v-model="form2.date1" style="width: 100%;"></el-date-picker>
              </el-col>
              <el-col :span="10" :offset="1">
                <el-time-picker placeholder="选择时间" v-model="form2.date2" style="width: 100%;"></el-time-picker>
              </el-col>
            </el-col>
          </el-row>
          <el-form-item v-if="routeType">
            <el-button type="primary" @click="onSubmit">立即创建</el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </el-row>
</template>

<script>
export default {
  data() {
    return {
      routeType: this.$route.params.type === "edit",
      form1: {
        type: 'First',
        userid: this.$route.params.userid,
        doctorName: '',
        consultNo: '',
        date1: '',
        date2:'',
        date3:'',
        consultType:'',
        mainPoint:'',
        nextExist: false,
        specialAttention:'',
        followPlan:'',
        sign:'',
        complaint: '',
        impression:'',
      },
      form2: {
        type: 'NotFirst',
        userid: this.$route.params.userid,
        doctorName: '',
        consultNo: '',
        date1: '',
        date2:'',
        date3:'',
        consultType:'',
        mainPoint:'',
        nextExist: false,
        specialAttention:'',
        followPlan: '',
        sign: '',
      },
      formFlag: true,
    }
  },
  created() {
    console.log("params", this.$route.params)

  },
  methods:{
    onSubmit() {
      alert("表单已经提交")
    },
    formToggle:function(){
      console.log("切換表格")
      this.formFlag = !this.formFlag;
    }
  }
}
</script>

<style scoped>
.el-form-item{
  margin-right:30px;
}
.el-row{
  margin-top: 20px;
}
</style>
