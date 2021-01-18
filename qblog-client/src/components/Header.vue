<template>
  <div style="height:120px;width:100%;">
    <el-row>
      <el-col :span="5" :offset="3"><img style="height:90px;vertical-align:middle; margin:10% 5%" :src="img_SCU"></el-col>
      <el-col :span="6" :offset="0"><div style="font-size:24px;margin-top:18%">青少年心理健康研究中心</div></el-col>
      <el-col :span="3" :offset="3">
        <router-link to="/Login">
        <div style="margin:25% 0 0 25%">
          <i class="el-icon-s-custom" style="color:rgb(250,0,0);font-size:30px"></i>
          <div style="display:inline-block;text-align:center;margin-left: 5px;line-height: 100px">登陆/注册</div>
        </div>
        </router-link>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "HeaDER",
  data(){
    return{
      img_SCU:require('@/assets/SCU_LOGO.png')
    }

  }
}
</script>

<style scoped>

</style>
