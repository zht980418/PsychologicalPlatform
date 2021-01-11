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
      img:'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg35.ddimg.cn%2F15%2F3%2F20025735-1_o.jpg&refer=http%3A%2F%2Fimg35.ddimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1612947936&t=a3816462fa1979d1263a0c4521cb745c'
    }
  }
}
</script>

<style scoped>


</style>
