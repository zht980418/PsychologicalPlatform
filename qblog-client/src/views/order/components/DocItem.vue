<template>
  <el-card class="card-conatiner">
    <el-image
      style="width: 100px; height: 100px"
      :src="img"
    />
    <br>
    <br>
    <p align="center">{{doctorName}}</p>
    <!-- <p>{{ position }}</p> -->
    <br>
    <div>
      <el-button
        type="primary"
        @click="$router.push({ name: 'ordertable', params: { doctorId: doctorId } })"
      >点击预约</el-button>
    </div>
  </el-card>
</template>

<script>
export default {
  functional: false,
  props: {
    doctorId: {
      type: String,
      default: 'zhangsan123'
    },
    doctorName: {
      type: String,
      default: '咨询师姓名'
    }
  },
  data() {
    return {
      img: "https://img1.dxycdn.com/2020/0206/245/3395151617954823081-22.jpg!wh200"
    }
  },
}
</script>

<style lang='scss' scoped >
.card-conatiner {
  width: 180px;
  height: 270px;
  padding: 20px;
  margin-right: 20px;
  margin-bottom: 20px;
}
</style>