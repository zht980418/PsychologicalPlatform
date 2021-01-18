<template>
  <el-row style="height:90vh;">
    <el-col
      :span="6"
      v-for="(item, index) in doctorlist"
      :key="item.name"
      :offset="index > 0 ? 2 : 0"
    >
      <el-card :body-style="{ padding: '0px' }">
        <el-image
          style="width: 100px; height: 100px"
          :src="item.img"
        ></el-image>
        <div style="padding: 14px;">
          <span>{{item.name}}</span>
          <tr>{{item.timelist}}</tr>
          <div class="bottom clearfix">
            <el-button
              type="text"
              class="button"
              @click="goToPage(index)"
            >点击预约</el-button>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>

<style>
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}
</style>

<script>
export default {
  data() {
    return {
      currentDate: new Date(),
      doctorlist: [
        {
          name: "张三",
          timelist: ["09:00:00", "14:00:00", "16:00:00"],
          img: "https://img1.dxycdn.com/2020/0206/245/3395151617954823081-22.jpg!wh200"
        },
        {
          name: "李四",
          timelist: ["09:00:00", "14:00:00", "16:00:00"],
          img: "https://img1.dxycdn.com/2020/0206/245/3395151617954823081-22.jpg!wh200"
        },
        {
          name: "王五",
          timelist: ["09:00:00", "14:00:00", "16:00:00"],
          img: "https://img1.dxycdn.com/2020/0206/245/3395151617954823081-22.jpg!wh200"
        },
      ],
    };
  },
  methods: {
    goToPage(index) {
      console.log(this.doctorlist[index].name)
      this.$router.push(
        {
          name: "ordertable",
          params:
          {
            doctorname: this.doctorlist[index].name,
            timelist: this.doctorlist[index].timelist,
          }
        })
    }
  }
}
</script>
