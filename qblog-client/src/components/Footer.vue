<template>
  <div>
    <el-row >
      <el-col :span="24" :offset="0" >
        <el-divider></el-divider>
        <div  style = "index:1;position:absolute;width:100%;padding:40px 0 40px 0;background-color:rgba(255,200,80,0.9)">
          <el-row>
            <el-col :span="8" :offset="2">
              <div>
                <div class="footer-text" ><p>地址：四川省成都市一环路南一段24号四川大学望江校区</p></div>
                <div class="footer-text" ><p>&copy; SCU 2020-2021 四川大学得觉文化研究中心</p></div>
                <div class="footer-text" ><p>心理热线：028-85561717</p></div>
                <div class="footer-text" ><p>客服微信：13752980870</p></div>
                <div class="footer-text" ><p>技术支持：电子科技大学生命学院数字健康实验室</p></div>
              </div>
          </el-col>
            <el-col :span="10" :offset="0">
              <div style="index:1;position: absolute;text-align: center;border-radius: 5px">
                <el-image style="width: 120px; height: 120px;background-color:rgba(255,255,255,0.8);" :src="DJ_GZH " fit="contain"></el-image>
                <div style="margin: 0 0 0 0;color:black;font-size: 12px"><p>关注我们</p></div>
              </div>
            </el-col>
            <el-col :span="5" :offset="6">
              <el-image style="index:-1;width: 250px; position: absolute;margin: 10px 0" :src="img_SCU" fit="contain"></el-image>
            </el-col>
          </el-row>
        </div>
      </el-col>

    </el-row>

  </div>
</template>

<script>
export default {
  name: "Footer",
  data(){
    return{
      img_SCU:require('@/assets/SCU_LOGO.png'),
      DJ_GZH:"https://mp.weixin.qq.com/mp/qrcode?scene=10000004&size=102&__biz=MzI4MzY5MTAwMg==&mid=2247502878&idx=2&sn=1c77e54e285bfc7534cbc2b7c3bc9cb6&send_time="
  }
  }
}
</script>

<style scoped>
.footer-text{
  color: black;
  padding:5px 10px 0 10px;
  font-size: 12px;
}
</style>
