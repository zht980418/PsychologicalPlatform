<template>
<el-row>
  <h2 v-if="formFlag" style="margin:30px 40%;">首次咨询记录表</h2>
  <h2 v-else style="margin:30px 40%;">再次咨询记录表</h2>
  <el-button v-on:click="formToggle" style="margin-left:30px; ">
    <span >切换记录表</span>
  </el-button>
  <el-form v-if="formFlag" label-position="left" ref="form" :model="form" autocomplete="on" label-width="80px">
    <el-row>
      <el-col :span="22" :offset="1">
      <el-row gutter="20">
        <el-col :span="12" :offset="0">
          <el-form-item  label-width="100px" label="来访者编号">
            <el-input v-model="form.patientNo"  ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12" :offset="0">
          <el-form-item  label-width="60px" label="咨询师">
            <el-input v-model="form.doctorName"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12" :offset="0">
          <el-form-item  label-width="100px" label="咨询次数">
            <el-input v-model="form.consultNo"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12" :offset="0">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
          </el-col>
          <el-col :span="10" :offset="1">
            <el-time-picker placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
          </el-col>
        </el-col>
      </el-row>

        <el-row>
          <el-form-item label="来访者主诉（S）:" label-width="130px">
            <el-input type="textarea" :rows="2" v-model="form.complaint"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="初次主要印象(O):" label-width="130px">
            <el-input type="textarea" :rows="1" v-model="form.impression"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="咨询要点记录(A):" label-width="130px">
            <el-input type="textarea" :rows="10" v-model="form.mainPoint"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="后期跟进计划（P）:" label-width="140px">
              <el-form-item label="是否进行下一次预约:" label-width="150px">
                <el-radio-group v-model="form.nextExist">
                  <el-radio label="是"></el-radio>
                  <el-radio label="否"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-row>
                <el-form-item label="如果预约的话请选择时间:" label-width="180px">
                  <el-input v-model="form.date3"  ></el-input>
                </el-form-item>
              </el-row>
            <el-row>
              <el-form-item label="咨询方式:" label-width="180px">
                <el-input v-model="form.consultType"  ></el-input>
              </el-form-item>
            </el-row>
            <el-row>
              <el-form-item label="依据严重性程度，是否需要特别关注？" label-width="270px">
                <el-radio-group v-model="form.specialAttention">
                  <el-radio label="是"></el-radio>
                  <el-radio label="否"></el-radio>
                </el-radio-group>
              </el-form-item>
            </el-row>
            <el-row>
              <el-form-item label="本次咨询作业和下次咨询计划:" label-width="260px">
                <el-input type="textarea" :rows="5" v-model="form.followPlan"></el-input>
              </el-form-item>
            </el-row>
          </el-form-item>
        </el-row>
        <el-row>
          <el-col :span="12" :offset="0">
            <el-form-item  label-width="100px" label="咨询师签字">
              <el-input v-model="form.sign"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" :offset="0">
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" v-model="form.date4" style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col :span="10" :offset="1">
              <el-time-picker placeholder="选择时间" v-model="form.date5S" style="width: 100%;"></el-time-picker>
            </el-col>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">立即创建</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
  <el-form v-else label-position="left" ref="form" :model="form" autocomplete="on" label-width="80px">
    <el-row>
      <el-col :span="22" :offset="1">
        <el-row gutter="20">
          <el-col :span="12" :offset="0">
            <el-form-item  label-width="100px" label="来访者编号">
              <el-input v-model="form.patientNo"  ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" :offset="0">
            <el-form-item  label-width="60px" label="咨询师">
              <el-input v-model="form.doctorName"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" :offset="0">
            <el-form-item  label-width="100px" label="咨询次数">
              <el-input v-model="form.consultNo"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" :offset="0">
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col :span="10" :offset="1">
              <el-time-picker placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
            </el-col>
          </el-col>
        </el-row>

        <el-row>
          <el-form-item label="咨询要点记录(A):" label-width="130px">
            <el-input type="textarea" :rows="10" v-model="form.mainPoint"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="后期跟进计划（P）:" label-width="140px">
            <el-form-item label="是否进行下一次预约:" label-width="150px">
              <el-radio-group v-model="form.nextExist">
                <el-radio label="是"></el-radio>
                <el-radio label="否"></el-radio>
              </el-radio-group>
            </el-form-item>
            <el-row>
              <el-form-item label="如果预约的话请选择时间:" label-width="180px">
                <el-input v-model="form.date3"  ></el-input>
              </el-form-item>
            </el-row>
            <el-row>
              <el-form-item label="咨询方式:" label-width="180px">
                <el-input v-model="form.consultType"  ></el-input>
              </el-form-item>
            </el-row>
            <el-row>
              <el-form-item label="依据严重性程度，是否需要特别关注？" label-width="270px">
                <el-radio-group v-model="form.specialAttention">
                  <el-radio label="是"></el-radio>
                  <el-radio label="否"></el-radio>
                </el-radio-group>
              </el-form-item>
            </el-row>
            <el-row>
              <el-form-item label="本次咨询作业和下次咨询计划:" label-width="260px">
                <el-input type="textarea" :rows="5" v-model="form.followPlan"></el-input>
              </el-form-item>
            </el-row>
          </el-form-item>
        </el-row>
        <el-row>
          <el-col :span="12" :offset="0">
            <el-form-item  label-width="100px" label="咨询师签字">
              <el-input v-model="form.sign"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" :offset="0">
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" v-model="form.date4" style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col :span="10" :offset="1">
              <el-time-picker placeholder="选择时间" v-model="form.date5S" style="width: 100%;"></el-time-picker>
            </el-col>
          </el-col>
        </el-row>
        <el-form-item>
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
      form: {
        patientNo: '',
        doctorName: '',
        consultNo: '',
        date1: '',
        date2:'',
        date3:'',
        date4:'',
        consultType:'',
        complaint: '',
        impression:'',
        mainPoint:'',
        delivery: false,
        nextExist: [],
        resource: '',
        desc: '',
        specialAttention:'',
        followPlan:'',
      },
      formFlag: true,
    }
  },
  methods:{
    onSubmit() {
      alert("表单已经提交")
    },
    formToggle:function(){
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
