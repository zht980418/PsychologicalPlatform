<template>
<el-form ref="form" :model="form" label-width="80px" label-position="left">
  <h3>SAS自评表</h3>
  <el-divider content-position="left"></el-divider>
  <el-form-item label="姓名">
    <el-input v-model="form.name"></el-input>
  </el-form-item>
  <el-form-item label="性别">
    <el-select v-model="form.sex" placeholder="请选择性别">
      <el-option label="男" value="male"></el-option>
      <el-option label="女" value="femal"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="填写时间">
    <el-col :span="11">
      <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
    </el-col>
    <el-col class="line" :span="2">-</el-col>
    <el-col :span="11">
      <el-time-picker placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
    </el-col>
  </el-form-item>
  <!-- <el-form-item label="即时配送">
    <el-switch v-model="form.delivery"></el-switch>
  </el-form-item> -->
  <!-- 多选题 -->
  <!-- <el-form-item label="多选题框">
    <el-checkbox-group v-model="form.type">
      <el-checkbox label="美食/餐厅线上活动" name="type"></el-checkbox>
      <el-checkbox label="地推活动" name="type"></el-checkbox>
      <el-checkbox label="线下主题活动" name="type"></el-checkbox>
      <el-checkbox label="单纯品牌曝光" name="type"></el-checkbox>
    </el-checkbox-group>
  </el-form-item> -->
  <!-- 单选题 -->
  <el-divider content-position="left"></el-divider>
  <h3>题目</h3>
  <el-divider content-position="left"></el-divider>
  <el-form-item v-for="(itemi,i) in questionList" :key="i" :label="itemi.tittle" label-width="360px">
    <el-radio-group v-model="itemi.result">
    <el-radio v-for="(itemj,j) in answerList" :key="j" :label="itemj.tittle"></el-radio>
    </el-radio-group>
  </el-form-item>
  <!-- <el-form-item label="备注">
    <el-input type="textarea" v-model="form.desc"></el-input>
  </el-form-item> -->
  <el-form-item position="middle">
    <el-button type="primary" @click="onSubmit" >提交</el-button>
    <el-button>取消</el-button>
  </el-form-item>
</el-form>
</template>

<script>
export default {
    data() {
      return {
        form: {
          name: '',
          sex: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          votedata: [],
          desc: '',
        },
        answerList:[
            {tittle:"没有或很少时间"},
            {tittle:"有时"},
            {tittle:"大部分时间"},
            {tittle:"绝大部分或全部时间"},
          ],
        questionList:[
          {tittle:'1、我觉得比平常容易紧张和着急。',result:''},
          {tittle:'2、我无缘无故地感到害怕。',result:''},
          {tittle:'3、我容易心里烦乱或觉得惊恐。',result:''},
          {tittle:'4、我觉得我可能将要发疯。',result:''},
          {tittle:'5、我觉得一切都很好，也不会发生什么不幸。',result:''},
          {tittle:'6、我手脚发抖打颤。',result:''},
          {tittle:'7、我因为头痛，颈痛和背痛而苦恼。',result:''},
          {tittle:'8、我感觉容易衰弱和疲乏。',result:''},
          {tittle:'9、我觉得心平气和，并且容易安静坐着。',result:''},
          {tittle:'10、我觉得心跳很快。',result:''},
          {tittle:'11、我因为一阵阵头晕而苦恼。',result:''},
          {tittle:'12、我有晕倒发作或觉得要晕倒似的。',result:''},
          {tittle:'13、我呼气吸气都感到很容易。',result:''},
          {tittle:'14、我手脚麻木和刺痛。',result:''},
          {tittle:'15、我因为胃痛和消化不良而苦恼。',result:''},
          {tittle:'16、我常常要小便。',result:''},
          {tittle:'17、我的手常常是干燥温暖的。',result:''},
          {tittle:'18、我脸红发热。',result:''},
          {tittle:'19、我容易入睡并且一夜睡得很好。',result:''},
          {tittle:'20、我做恶梦。',result:''},

        ],
        result:[],
      }
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      }
    }
  }
</script>