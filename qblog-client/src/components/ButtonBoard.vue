<template>
    <el-row>

      <div>
      <el-col :span="20" :offset="2">
        <el-col :xs="{span:8,offset:2}" :sm="{span:8,offset:2}" :md="{span:8,offset:3}" :lg="{span:4,offset:0}" :xl="{span:4,offset:0}" style="height:200px;">
          <div class="button-container">
            <div class="button-bg">
              <el-image style="height:100%;width:100%;" :src="BBG" fit="fill"></el-image>
            </div>
            <div class="button-font">
              <span >预约咨询</span>
            </div>
          </div>


        </el-col>
        <el-col :xs="{span:8,offset:3}" :sm="{span:8,offset:3}" :md="{span:8,offset:3}" :lg="{span:4,offset:2}" :xl="{span:4,offset:2}"  style="height:200px;">
          <div class="button-container">
          <div class="button-bg">
          <el-image style="height:100%;width:100%;" :src="BBG2" fit="fill"></el-image>
          </div>

          <div class="button-font" >
            <span>预约咨询</span>
          </div>
          </div>

        </el-col>

        <el-col :xs="{span:8,offset:2}" :sm="{span:8,offset:2}" :md="{span:8,offset:3}" :lg="{span:4,offset:2}" :xl="{span:4,offset:2}"  style="height:200px;">
          <div class="button-container">
          <div class="button-bg">
            <el-image style="height:100%;width:100%;" :src="BBG3" fit="fill"></el-image>
          </div>

          <div class="button-font">
            <span>预约咨询</span>
          </div>
          </div>
        </el-col>
        <el-col :xs="{span:8,offset:3}" :sm="{span:8,offset:3}" :md="{span:8,offset:3}" :lg="{span:4,offset:2}" :xl="{span:4,offset:2}"  style="height:200px;">
          <div class="button-container">
          <div class="button-bg">
            <el-image style="height:100%;width:100%;" :src="BBG4" fit="fill"></el-image>
          </div>
          <div class="button-font">
            <span >预约咨询</span>
          </div>
          </div>
        </el-col>
      </el-col>
      </div>
    </el-row>
</template>

<script>
export default {
  name: "ButtonBoard",
  data(){
    return{
      BBG:require('@/assets/BBG.jpeg'),
      BBG2:require('@/assets/BBG2.jpeg'),
      BBG3:require('@/assets/BBG3.jpeg'),
      BBG4:require('@/assets/BBG4.jpeg'),

    }
  }

}
</script>

<style scoped>
    .button-container{
      width: 220px;
      height: 120px;
      margin-left: 30px;
      position: absolute;

    }

    .button-bg{
      width: 100%;
      height: 100%;
      position: absolute;
      z-index:-1;
      border: 2px white solid;
      border-radius: 5px;
      opacity: 0.8;
    }




    .button-font{
      width: 100%;
      height: 100%;
      background-color: rgba(255,200,80,0.9);
      border: 2px white solid;
      border-radius: 5px;
      position: absolute;
      z-index:1;
      cursor:pointer;
      line-height: 1;
      text-align: center;
      font: 400 24px Arial;
      font-weight: 500;
      opacity: 0.8;
    }

    .button-font span{
      display:inline-block;
      margin-top:45px
    }

    .button-font:hover {
      color: #409EFF;
      border-color: #c6e2ff;
      background-color: #ecf5ff;
      opacity: 0.8;
    }

</style>
