<template>

  <el-row>
    <el-col :span="10" :offset="0">
      <el-image style="height: 400px;" :src="img" fit="contain"></el-image>
    </el-col>
    <el-col :span="12" :offset="0">
      <div style="margin:10px 0px 0 60px;">
        <h3>Psy Hub</h3>
      </div>
      <span style="margin:20px 0 0 10px;padding:2px;display: block ;font-size:14px; "> 得觉咨询团队，有来自重点高校的心理专家, 本地知名中学的心理咨询教师，有丰富个案经验的心理专业博硕士生，以及有企业实战经验的职业导师等。我们将尊重每一位来访者的本真状态，帮助调整身心和谐，促进自我成长与发展，获得内心的喜悦自在。</span>

      <div style="margin:50px 0px 0 60px;">
        <el-link  href="/order/doctorlist"><el-button type="primary">咨询详情</el-button></el-link>
      </div>
    </el-col>
  </el-row>

</template>

<script>
export default {
  name: "Counsel",
  data(){
    return{
        img:'http://5b0988e595225.cdn.sohucs.com/images/20180705/a95508dc64cc4ecc9b6cfd6c9a8205df.jpeg'
    }
  }
}
</script>

<style scoped>


</style>
