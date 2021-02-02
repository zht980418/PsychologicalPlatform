<template>
  <el-row  style="min-height: 300px">
    <el-image style="index:-1;height:100%;width:100%; position: absolute;" :src="img_BG" fit="fill"></el-image>

<!--    主要广告版，预约流程、温馨提示-->
    <el-col  style="index:1;position: absolute;height:100%; min-width: 570px;" :xs="{span:16,offset:1}" :sm="{span:12,offset:2}" :md="{span:12,offset:4}" :lg="{span:12,offset:5}" :xl="{span:12,offset:5}">
<!--      sm及以上大小时显示-->
      <el-row class="hidden-xs-only" style="width:100%;height:90%;margin-top:2%;background-color: rgba(255,255,255,0.8);text-align: left;border-radius: 10px">
        <div style="padding:10px 10px 0 10px;font-size: 16px;font-weight: 550"><p>预约流程：</p></div>
        <div class="process">
          <i class="el-icon-monitor">用户登录</i>
          <i class="el-icon-right"></i>
          <i class="el-icon-user">选择老师</i>
          <i class="el-icon-right"></i>
          <i class="el-icon-time">选择时间</i>
          <i class="el-icon-right"></i>
          <i class="el-icon-document">填写信息</i>
          <i class="el-icon-right"></i>
          <i class="el-icon-chat-dot-round">面谈咨询</i>
        </div>

        <div class="tips" style="padding:3px 10px 0 10px;font-size: 16px;">
          <ul>
            <ol>您可以点击导航栏或菜单栏目的咨询预约进入预约界面</ol>
            <ol>若您无法按时赴约，请提前至少24小时取消预约</ol>
          </ul>
        </div>
        <div style="padding:3px 10px 10px 10px;font-size: 16px;font-weight: 550"><p>温馨提示：</p></div>
        <p style="padding :0 15px;">心理咨询师只能服务于心理问题，凡是医院确诊了的抑郁障碍、焦虑障碍、双相情感障碍以及精神分裂症等各种精神障碍，不属于心理咨询师的工作范围，心理咨询师仅能在精神科医师认为可以辅助的范围内工作，此类问题无法仅仅靠心理咨询获得痊愈</p>
      </el-row>
<!--      xs时显示-->
      <el-row class="hidden-sm-and-up" style="width:100%;height:94%;margin-top:2%;background-color: rgba(255,255,255,0.8);text-align: left;border-radius: 10px">
        <el-row>
          <div style="padding:10px 10px 0 10px;font-size: 16px;font-weight: 550"><p>预约流程：</p></div>
          <div class="process">
            <i class="el-icon-monitor">用户登录</i>
            <i class="el-icon-right"></i>
            <i class="el-icon-user">选择老师</i>
            <i class="el-icon-right"></i>
            <i class="el-icon-time">选择时间</i>
            <i class="el-icon-right"></i>
            <i class="el-icon-document">填写信息</i>
            <i class="el-icon-right"></i>
            <i class="el-icon-chat-dot-round">面谈咨询</i>
          </div>

          <div class="tips" style="padding:3px 10px 0 10px;font-size: 16px;">
            <ul>
              <ol>您可以点击导航栏或菜单栏目的咨询预约进入预约界面</ol>
              <ol>若您无法按时赴约，请提前至少24小时取消预约</ol>
            </ul>
          </div>

          <el-row  style="margin-top: 2%">
            <el-col :span="11" :offset="0">
              <div style="padding:3px 10px 10px 10px;font-size: 16px;font-weight: 550"><p>温馨提示：</p></div>
              <p style="padding :0 15px;">心理咨询师只能服务于心理问题，医院确诊的精神障碍无法仅仅靠心理咨询获得痊愈</p>
            </el-col>
            <el-col  :span="5" :offset="3">
              <el-image style="display: inline-block; width: 100%; max-width:100px;" :src="DJ_GZH " fit="contain"></el-image>
              <div style="font-size: 14px"><p>手机扫描二维码</p></div>
              <div style="font-size: 14px"><p>了解咨询详情</p></div>
            </el-col>
            <el-col :span="5" :offset="0">

              <div style="padding:3px 10px 0 10px;font-size: 14px;font-weight: 550"><p>预约热线：</p></div>
              <div style="padding:5px 10px 0 10px;font-size: 14px"><p>028-85561717</p></div>
              <div style="padding:5px 10px 0 10px;font-size: 14px; font-weight: 550"><p>客服微信：</p></div>
              <div style="padding:5px 10px 3px 10px;font-size: 14px"><p>13752980870</p></div>
            </el-col>
          </el-row>
        </el-row>






      </el-row>
    </el-col>

<!--    独立联系方式板块，屏幕大小xs尺寸时合并入主要广告板-->
    <el-col class="hidden-xs-only" style="index:1;position:absolute;height:100%;min-width:120px;" :span="2" :offset="20">
      <div style="background-color: rgba(255,255,255,0.8);margin: 10% 0 0 0;text-align: center;border-radius: 5px">
        <el-image style="width: 90%; height: 90%;margin-top:10%" :src="DJ_GZH " fit="contain"></el-image>
        <div style="font-size: 14px"><p>手机扫描二维码</p></div>
        <div style="font-size: 14px">
          <p>了解咨询详情</p>
        </div>

        <div style="padding:3px 10px 0 10px;font-size: 14px;font-weight: 550"><p>预约电话：</p></div>
        <div style="padding:5px 10px 0 10px;font-size: 14px"><p>028-85561717</p></div>
        <div style="padding:5px 10px 0 10px;font-size: 14px; font-weight: 550"><p>客服微信：</p></div>
        <div style="padding:5px 10px 3px 10px;font-size: 14px"><p>13752980870</p></div>
      </div>
    </el-col>
  </el-row>

</template>

<script>
export default {
  name: "AdBoard",
  data(){
    return{
      img_BG:require('@/assets/bg1.jpg'),
      DJ_GZH:"https://mp.weixin.qq.com/mp/qrcode?scene=10000004&size=102&__biz=MzI4MzY5MTAwMg==&mid=2247502878&idx=2&sn=1c77e54e285bfc7534cbc2b7c3bc9cb6&send_time="
    }

  }
}
</script>

<style scoped>
.process i{
  padding:15px 5px;
  text-align: center;
}

.process{
  text-align: center;
}

.tips ol{
  font-size:14px;
  padding:0 10px;
  list-style-type: lower-alpha;
}
</style>
