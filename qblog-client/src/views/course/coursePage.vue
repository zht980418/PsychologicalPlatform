<template>
  <div style="margin-left:3vh;">
    <el-row class="app-container">
      <el-col :span="18">
        <h1>{{ course.title }}</h1>
        <br>
        <iframe
          class="video"
          :src="course.link"
          scrolling="no"
          border="0"
          frameborder="no"
          framespacing="0"
          allowfullscreen="true"
        />
        <br>
        <br>
        <el-row>
          <p>{{course.description}}</p>
        </el-row>
        <el-row>
          <el-tag
            v-for="item in taglist"
            :key="item"
            style="margin:3px;"
          >
            {{ item }}
          </el-tag>
        </el-row>
      </el-col>
      <el-col :span="6">
        <p>其他推荐课程</p>
        <RecItem
          class="course-list"
          id="xd"
          v-for="item in courselist"
          :key="item.courseId"
          :courseId="item.courseId"
          :icon="item.icon"
          :title="item.title"
          :time="item.time"
          @click.prevent.native="handleChange(item.courseId)"
        />
      </el-col>
    </el-row>
    <!-- <el-row>
      <div v-on:click="playVideo()">
        <video
          :src="course.link"
          id="videoPlay"
          v-show="true"
          class="video"
          controls
        >您的浏览器不支持 video 视屏播放。</video>
      </div>
    </el-row> -->
    <!-- <div class="input_video video">
      <video-player
        class="video-player vjs-custom-skin"
        ref="videoPlayer"
        :playsinline="true"
        :options="playerOptions"
      >
      </video-player>
    </div> -->
  </div>
</template>

<script>
import { getCourseList, getCourse } from '@/api/course'
import { mapGetters } from 'vuex'

export default {
  name: 'CoursePage',
  components: {
    RecItem: () => import('./components/Item2'),
  },
  data() {
    return {
      courseId: this.$route.params.courseId, // 当前课程编号
      course: {},
      courselist: [],
      taglist: ['得觉'],
      // videoSrc: require('./example.mp4')
      // videoSrc: '//vjs.zencdn.net/v/oceans.mp4'
    }
  },
  computed: {
    ...mapGetters([
      'avatar',
      'token',
      'id',
      'name'
    ]),
  },
  created() {
    this.handleChange(this.courseId)
    getCourseList().then((res) => {
      if (res.code === 0) {
        this.courselist = res.data
      }
    }).catch((err) => {
      console.log(err)
      this.$notify.error({
        title: "提示",
        message: "网络忙，获取推荐课程列表失败",
      })
    })
  },
  methods: {
    playVideo() {
      var vdo = document.getElementById("videoPlay");
      vdo.play();
    },
    handleChange(id) {
      getCourse(id).then((res) => {
        if (res.code === 0) {
          console.log(res)
          this.course = res.data
        }
      }).catch((err) => {
        console.log(err)
        this.$notify.error({
          title: "提示",
          message: "网络忙，获取课程信息失败",
        })
      })
    }
  }
}
</script>

<style lang='scss' scoped>
.video {
  height: 70vh;
  width: 100vh;
}
.input_video {
  width: 400px;
  height: 400px;
  margin: 0 auto;
}
</style>
