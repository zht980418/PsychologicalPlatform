<template>

  <el-row style="height:180px;">
    <el-col
      style="height:180px;"
      :xs="{span:10,offset:1}"
      :sm="{span:10,offset:1}"
      :md="{span:10,offset:2}"
      :lg="{span:10,offset:2}"
      :xl="{span:10,offset:2}"
    >
      <img
        style="height:65%;min-height:80px;margin-top:30px; vertical-align:middle;"
        :src="img_SCU"
      >
    </el-col>
    <el-col
      :xs="{span:3,offset:9}"
      :sm="{span:3,offset:6}"
      :md="{span:3,offset:6}"
      :lg="{span:3,offset:6}"
      :xl="{span:3,offset:3}"
    >
      <router-link to="/Login">
        <el-row style="margin:70px 0 0 120px">
          <el-button type="primary">
            <i
              class="el-icon-s-custom"
              style="color:rgb(250,250,250);font-size:2vw"
            ></i>
            <span style="text-align:center;font-size: 1vw;">登陆/注册</span>
          </el-button>

        </el-row>
      </router-link>
    </el-col>
  </el-row>

</template>

<script>
export default {
  name: "HeaDER",
  data() {
    return {
      img_SCU: require('@/assets/SCU_LOGO.png')
    }
  }
}
</script>

<style scoped>
</style>
