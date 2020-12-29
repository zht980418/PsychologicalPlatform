<template>
  <el-row :gutter="0">
    <el-col :span="14" :offset="5">
      <el-timeline v-for="(timeline,index) in timelineData" :key="index">
        <h2 class="year">{{timeline.year}}</h2>
        <el-timeline-item
          v-for="(item, index) in timeline.items"
          :key="index"
          :timestamp="item.gmtCreate"
          placement="top"
        >
          <router-link class="title" :to="'/post/' + item.id">{{item.title}}</router-link>
        </el-timeline-item>
      </el-timeline>
    </el-col>
  </el-row>
</template>

<script>
import request from "@/http/request";
export default {
  name: "TimeLine",
  data() {
    return {
      timelineData: [],
    };
  },
  created() {
    request
      .getTimeline()
      .then((res) => {
        if (res.code === 0) {
          this.timelineData = res.data;
        } else {
          this.$notify.error({
            title: "提示",
            message: res.msg,
          });
        }
      })
      .catch((err) => {
        console.log(err);
        this.$notify.error({
          title: "提示",
          message: "网络忙，标签时间线失败",
        });
      });
  },
};
</script>

<style scoped>
.el-row {
  margin-top: 20px;
}
.year {
  margin-bottom: 10px;
}
.title {
  font-size: 16px;
  text-decoration: none;
  color: #606266;
}
.title:hover {
  cursor: pointer;
  color: #0a0a0a;
}
</style>