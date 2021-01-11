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
              <el-image :src="item.img" :fit="fit"></el-image>
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
          name:"新闻标题",
          img:"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg35.ddimg.cn%2F15%2F3%2F20025735-1_o.jpg&refer=http%3A%2F%2Fimg35.ddimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1612947936&t=a3816462fa1979d1263a0c4521cb745c",
        },
        {
          name:"新闻标题",
          img:"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg37.ddimg.cn%2F39%2F10%2F22544517-1_o.jpg&refer=http%3A%2F%2Fimg37.ddimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1612947991&t=cf89972733d26f61ac0bf909c29d1c1b",
        },
        {
          name:"新闻标题",
          img:"https://gimg2.baidu.com/image_search/src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_70%2Cc_zoom%2Cw_640%2Fimages%2F20181211%2F509f90abd0a54385ac3223bae3d58465.jpeg&refer=http%3A%2F%2F5b0988e595225.cdn.sohucs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1612948020&t=25886aad0cac2b13308c32ea9c4753d4",
        },
        {
          name:"新闻标题",
          img:"https://gimg2.baidu.com/image_search/src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20181211%2Fdcfd899b4ddb475d89df0ea253f4dbcd.jpeg&refer=http%3A%2F%2F5b0988e595225.cdn.sohucs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1612948018&t=00224eeb685101b4dd3d68c0a68c36f1",
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