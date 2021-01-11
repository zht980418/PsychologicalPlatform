<template>
  <el-row :gutter="0">
    <el-col :span="8" :offset="0">
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
    <el-col :span="12" :offset="2">
      <template>
        <el-carousel height="400px" direction="vertical">
          <el-carousel-item v-for="item in article" :key="item.name">
            <!-- <div style="margin: 0 0 0 30px"> -->
            <div>
              <el-image :src="item.img"></el-image>
            </div>
          </el-carousel-item>
        </el-carousel>
      </template>
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
      article:[
        {
          name:"新闻标题1",
              img:"http://5b0988e595225.cdn.sohucs.com/images/20180705/b8baa882618142ee950fe411db614fc0.jpeg",
        },
        {
          name:"新闻标题2",
            img:"http://5b0988e595225.cdn.sohucs.com/images/20180705/682ed55765c0491ebc6de93649dd7edb.jpeg",
        },
        {
          name:"新闻标题3",
            img:"http://5b0988e595225.cdn.sohucs.com/images/20180705/430df0f269784ea4bc434ef7d1e3b60f.jpeg",
        },
        {
          name:"新闻标题4",
            img:"http://5b0988e595225.cdn.sohucs.com/images/20180705/bf7e8edfd8164391ab033c91c6f02c14.jpeg",
        },
      ],
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
.desc-box {
        position: absolute;
        bottom: 0;
        left: 50%;
        top: 200px;
        width: 200px;
        height: 20px;
        margin-left: -400px;
        text-align: center;
    }
</style>