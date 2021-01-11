<template>

  <el-row>
    <el-col :span="10" :offset="1">
      <el-image style="width: 250px; height: 300px" :src="img"></el-image>
    </el-col>
    <el-col :span="10" :offset="1">
      <div style="margin:10px 0px 0 90px;">
        <h3>Psy Hub</h3>
      </div>
      <span style="margin:50px 0 0 30px;dding:5px;display: block ;font:18px; "> 本心理咨询站，共有注册咨询师50名，其中高级咨询师25名，青少年咨询经验超过5年...</span>

      <div style="margin:100px 0px 0 80px;">
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
