<template>
<div>
  <el-form ref="form" :model="form" label-width="80px" label-position="left">
  <h3>青少年自我价值感量表</h3>
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
    <!-- 循环答案 -->
    <el-radio v-for="(itemj,j) in answerList" :key="j" :label="itemj.tittle"></el-radio>
    </el-radio-group>
  </el-form-item>
  <!-- <el-form-item label="备注">
    <el-input type="textarea" v-model="form.desc"></el-input>
  </el-form-item> -->
  <el-form-item position="middle">
    <el-button type="primary" @click="dialogVisible = true" >提交</el-button>
    <el-button>取消</el-button>
  </el-form-item>
</el-form>
<el-dialog
  title="提示"
  :visible.sync="dialogVisible"
  width="30%"
  center>
  <span>本次测试结果为良好</span>
  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
</el-dialog>
</div>
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
        dialogVisible: false,
        result: {
          0: '',1: '',2: '',3: '',4: '',5: '',6: '',7: '',8: '',9: '',10: '',
          11: '',12: '',13: '',14: '',15: '',16: '',17: '',18: '',19: '',20: '',
          21: '',22: '',23: '',24: '',25: '',26: '',27: '',28: '',29: '',30: '',
          31: '',32: '',33: '',34: '',35: '',36: '',37: '',38: '',39: '',40: '',
          41: '',42: '',43: '',44: '',45: '',46: '',47: '',48: '',49: '',50: '',
          51: '',52: '',53: '',54: '',55: '',56: '',
        },
        answerList:[
            {tittle:"完全不符合"},
            {tittle:"大部分不符合"},
            {tittle:"部分符合部分不符合"},
            {tittle:"大部分符合"},
            {tittle:"完全符合"}
          ],
        questionList:[
          {tittle:'1、我喜欢主动与他人交往。',result:''},
          {tittle:'2、我能从失败中吸取教训，作为今后个人成功的动力。',result:''},
          {tittle:'3、当遇到不顺心的事时，我能感受到父母在尽量地鼓励我。',result:''},
          {tittle:'4、班上搞活动常常离不开我。',result:''},
          {tittle:'5、我觉得父母不太关心我。',result:''},
          {tittle:'6、我是一个有出息的人。',result:''},
          {tittle:'7、总的来说，我对自己感到满意。',result:''},
          {tittle:'8、我的体质很好，能更好地为社会作贡献。',result:''},
          {tittle:'9、我很受周围人的欢迎。',result:''},
          {tittle:'10、在大多数课程学习中，同学们都会来找我帮忙。',result:''},
          {tittle:'11、我经常愿意独处，因为这样我感到自由。',result:''},
          {tittle:'12、父母总是让我去做一些我不喜欢的事。',result:''},
          {tittle:'13、我对自己的生长发育状况感到满意。',result:''},
          {tittle:'14、我是一个冷静的人。',result:''},
          {tittle:'15、在学校里，我常常当干部。',result:''},
          {tittle:'16、我常常觉得身体不舒服，难以发挥自己的潜能。',result:''},
          {tittle:'17、我感到自己的长处得到了发挥。',result:''},
          {tittle:'18、父母不能容忍我与他们有不同的见解。',result:''},
          {tittle:'19、只要我认为道德的事就去做，别人怎么看我不在乎。',result:''},
          {tittle:'20、我勇于去做自己想做的事。',result:''},
          {tittle:'21、无论什么事，我是按自己的标准来衡量好坏的。',result:''},
          {tittle:'22、我常常觉得自己一无是处。',result:''},
          {tittle:'23、我在同伴中很有威信。',result:''},
          {tittle:'24、我的身体很好，会有助于我今后的发展。',result:''},
          {tittle:'25、我觉得自己在团体中没有影响力。',result:''},
          {tittle:'26、不论成功或失败，我相信自己选择的道路。',result:''},
          {tittle:'27、我相信我的言行合乎社会道德标准。',result:''},
          {tittle:'28、我的诚实赢得了大家的信赖。',result:''},
          {tittle:'29、我经常帮助有困难的人，从中使我感到快乐。',result:''},
          {tittle:'30、我看得起我自己。',result:''},
          {tittle:'31、我有能力支配自己的生活。',result:''},
          {tittle:'32、在众人的眼里，我是成不了大器的。',result:''},
          {tittle:'33、我的体态健美，令他人羡慕。',result:''},
          {tittle:'34、我相信天生我才必有用 。',result:''},
          {tittle:'35、我的身体很健壮，常能为集体争得荣誉。',result:''},
          {tittle:'36、我在班上的表现常引起人们的关注。',result:''},
          {tittle:'37、我觉得自己的能力不如他人。',result:''},
          {tittle:'38、我觉得自己有能力成就一番事业。',result:''},
          {tittle:'39、大家都说我是一个勤勉的人。',result:''},
          {tittle:'40、我觉得我能为自己的家庭幸福作贡献。',result:''},
          {tittle:'41、周围的不少人都喜欢我。',result:''},
          {tittle:'42、家里做决定时通常不重视我的意见。',result:''},
          {tittle:'43、我是一个有用的人。',result:''},
          {tittle:'44、我的长相，常受到他人的赞扬。',result:''},
          {tittle:'45、我是一个道德高尚的人，人们都愿意跟我相处。',result:''},
          {tittle:'46、我讨厌我自己。',result:''},
          {tittle:'47、周围的不少人都值得我信赖。',result:''},
          {tittle:'48、我很少得到他人的夸奖。',result:''},
          {tittle:'49、我觉得自己做的事对得起良心。',result:''},
          {tittle:'50、我欣赏我的健康体魄。',result:''},
          {tittle:'51、我觉得我的生活过得充实又快乐。',result:''},
          {tittle:'52、我的父母都器重我。',result:''},
          {tittle:'53、我觉得自己没有什么发展前途。',result:''},
          {tittle:'54、我对自己的社交能力感到满意。',result:''},
          {tittle:'55、我认为在道德行为上只要把自己管好就行了。',result:''},
          {tittle:'56、对于我的爱好，我的父母常常支持我。',result:''},
        ],
      }
    },
    methods: {
      onSubmit() {
        console.log(this.questionList[0].result);
      },
    }
  }
</script>