<template>
  <el-row class="app-container">
    <el-col
      :span="6"
      v-for="(item, index) in doctorlist"
      :key="item.doctorId"
      :offset="index > 0 ? 2 : 0"
    >
      <el-card class="demo-app">
        <el-image
          style="width: 100px; height: 100px"
          :src="item.img"
          align="center"
        />
        <br>
        <br>
        <p>{{item.doctorName}}</p>
        <p>{{ item.position }}</p>
        <div>
          <el-button
            type="primary"
            @click="goToPage(index)"
            style="float:right;"
          >点击预约</el-button>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
export default {
  data() {
    return {
      doctorlist: [
        {
          doctorId: "zhangsan123",
          doctorName: '张三',
          position: '金牌咨询师',
          img: "https://img1.dxycdn.com/2020/0206/245/3395151617954823081-22.jpg!wh200"
        },
        {
          doctorId: "lisi123",
          doctorName: '李四',
          position: '金牌咨询师',
          img: "https://img1.dxycdn.com/2020/0206/245/3395151617954823081-22.jpg!wh200"
        },
        {
          doctorId: "wangwu123",
          doctorName: '王五',
          position: '金牌咨询师',
          img: "https://img1.dxycdn.com/2020/0206/245/3395151617954823081-22.jpg!wh200"
        },
      ],
    };
  },
  methods: {
    goToPage(index) {
      console.log(this.doctorlist[index].doctorId)
      this.$router.push(
        {
          name: "ordertable",
          params:
          {
            doctorId: this.doctorlist[index].doctorId,
            timelist: this.doctorlist[index].timelist,
          }
        })
    }
  }
}
</script>
