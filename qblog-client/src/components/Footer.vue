<template>

    <el-row style="height:200px;">

        <el-divider></el-divider>
        <el-row  style = "index:1;position:absolute;width:100%;padding:40px 0 40px 0;background-color:rgba(255,200,80,0.9)">
          <el-row>
            <el-col :xs="{span:15,offset:2}" :sm="{span:10,offset:2}" :md="{span:7,offset:2}" :lg="{span:7,offset:2}" :xl="{span:8,offset:2}">
              <div>
                <div class="footer-text" ><p>地址：四川省成都市一环路南一段24号四川大学望江校区</p></div>
                <div class="footer-text" ><p>&copy; SCU 2020-2021 四川大学得觉文化研究中心</p></div>
                <div class="footer-text" ><p>心理热线：028-85561717</p></div>
                <div class="footer-text" ><p>客服微信：13752980870</p></div>
                <div class="footer-text" ><p>技术支持：电子科技大学生命学院数字健康实验室</p></div>
              </div>
          </el-col>
            <el-col style="text-align: center;margin: 0 10px" :xs="{span:6,offset:2}" :sm="{span:6,offset:2}" :md="{span:6,offset:1}" :lg="{span:6,offset:2}" :xl="{span:5,offset:1}">
                <img style="width: 120px; height: 120px;background-color:rgba(255,255,255,0.8);" :src="DJ_GZH ">
                <div style="color:black;font-size: 12px"><p>关注我们</p></div>
            </el-col>
            <el-col style="height:100px;margin-top:10px;" :xs="{span:4,offset:2}" :sm="{span:4,offset:2}" :md="{span:4,offset:2}" :lg="{span:4,offset:3}" :xl="{span:4,offset:2}">
              <img style="height:100%;" :src="img_SCU">
            </el-col>
          </el-row>
        </el-row>

    </el-row>

</template>

<script>
export default {
  name: "Footer",
  data(){
    return{
      img_SCU:require('@/assets/SCU_LOGO2.png'),
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
