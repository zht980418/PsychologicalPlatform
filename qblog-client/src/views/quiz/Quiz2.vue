<template>
<el-form ref="form" :model="form" label-width="80px" label-position="left">
  <h3>PTSR压力测试表</h3>
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
            {tittle:"从未"},
            {tittle:"很少"},
            {tittle:"有时"},
            {tittle:"经常"},
            {tittle:"总是"}
          ],
        questionList:[
          {tittle:'1.我受背痛之苦',result:''},
          {tittle:'2.我的睡眠不定且睡不安稳 ',result:''},
          {tittle:'3.我有头痛',result:''},
          {tittle:'4.我颚部疼痛 ',result:''},
          {tittle:'5.若须等候，我会不安',result:''},
          {tittle:'6.我的后颈感到疼痛',result:''},         
          {tittle:'7.我比多数人更神经紧张 ',result:''},
          {tittle:'8.我很难入睡',result:''},
          {tittle:'9.我的头感到紧或痛',result:''},    
          {tittle:'10.我的胃有毛病',result:''},
          {tittle:'11.我对自己没有信心 ',result:''},  
          {tittle:'12.我对自己说话',result:''},
          {tittle:'13.我忧虑经济问题',result:''}, 
          {tittle:'14.与人见面时， 我会窘怯',result:''},
          {tittle:'15.我怕发生可怕的事',result:''},
          {tittle:'16.白天我觉得累',result:''},
          {tittle:'17.下午我感到喉咙痛',result:''}, 
          {tittle:'18.我心情不安、 无法静坐 ',result:''},
          {tittle:'19.我感到非常口乾 ',result:''},
          {tittle:'20.我有心脏毛病',result:''},
          {tittle:'21.我觉得自己不是很有用',result:''},   
          {tittle:'22.我吸烟',result:''},
          {tittle:'23.我肚子不舒服',result:''},
          {tittle:'24.我觉得不快乐',result:''},
          {tittle:'25.我流汗',result:''},
          {tittle:'26.我喝酒',result:''},
          {tittle:'27.我很自觉(自己需要、情绪、动机、计划、目标等）',result:''},
          {tittle:'28.我觉得自己像四分五裂',result:''},
          {tittle:'29.我的眼睛又酸又累',result:''},
          {tittle:'30.我的腿，或脚抽筋 ',result:''},
          {tittle:'31.我的心跳快速 ',result:''},
          {tittle:'32.我怕结识人',result:''},
          {tittle:'33.我手脚冰冷',result:''},
          {tittle:'34.我患便秘',result:''},
          {tittle:'35.我发现自己很容易哭 ',result:''},     
          {tittle:'36.我消化不良 ',result:''},
          {tittle:'37.我咬指甲 ',result:''},
          {tittle:'38.我耳中有嗡嗡声 ',result:''},   
          {tittle:'39.我小便频密',result:''},
          {tittle:'40.我有胃溃疡的毛病',result:''},  
          {tittle:'41.我有皮肤方面的毛病 ',result:''},
          {tittle:'42.我未经医师指示使用各种药物（但并非由於染上感冒）',result:''},
          {tittle:'43.我的咽喉很紧',result:''},
          {tittle:'44.我有十二指肠溃疡的毛病',result:''},   
          {tittle:'45.我担心我的生活',result:''},
          {tittle:'46.我口腔溃烂',result:''},
          {tittle:'47.我为琐事忧虑',result:''},
          {tittle:'48.我呼吸浅促',result:''},
          {tittle:'49.我觉得胸部紧迫',result:''},   
          {tittle:'50.我发现难做决定',result:''},
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