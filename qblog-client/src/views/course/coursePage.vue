<template>
  <div style="margin-left:3vh;">
    <el-row class="app-container">
      <el-col :span="18">
        <h1>{{ course.title }}</h1>
        <br>
        <iframe
          class="video"
          src="//player.bilibili.com/player.html?aid=204577853&bvid=BV1hh411Q71K&cid=309144601&page=1"
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
        <br>
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
        <br>
        <RecItem
          class="course-list"
          v-for="item in courselist"
          :key="item.courseId"
          :courseId="item.courseId"
          :icon="item.icon"
          :title="item.title"
          :time="item.time"
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
      courselist: [{ course_id: '2020082602', title: '课程1', sum: '10', completed: '2', start: '2020/10/1', end: '2020/11/12' }, { course_id: '2020082801', title: '课程2', sum: '10', completed: '6', start: '2020/11/1', end: '2020/11/12' }, { course_id: '2020082602', title: '课程3', sum: '10', completed: '3' }, { course_id: '2020082602', title: '课程1', sum: '10', completed: '2', start: '2020/10/1', end: '2020/11/12' }, { course_id: '2020082801', title: '课程2', sum: '10', completed: '6', start: '2020/11/1', end: '2020/11/12' }, { course_id: '2020082602', title: '课程3', sum: '10', completed: '3' },],
      taglist: ['哈利波特', '电影'],
      // videoSrc: require('./example.mp4')
      videoSrc: '//vjs.zencdn.net/v/oceans.mp4'
    }
  },
  mounted() {
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
    getCourse(this.$route.params.courseId).then((res) => {
      if (res.code === 0) {
        this.course = res.data
        console.log(res);
      }
    }).catch((err) => {
      console.log(err)
      this.$notify.error({
        title: "提示",
        message: "网络忙，获取课程信息失败",
      })
    })
    getCourseList().then((res) => {
      if (res.code === 0) {
        this.courselist = res.data
      }
    }).catch((err) => {
      console.log(err)
      this.$notify.error({
        title: "提示",
        message: "网络忙，获取课程列表失败",
      })
    })
  },
  methods: {
    // 跳转页面
    handleCourse(course_id) {
      console.log('点击课程')
      this.$router.push({ name: 'CoursePage', params: { course_id: course_id } })// 传入course_id
    },
    playVideo() {
      var vdo = document.getElementById("videoPlay");
      vdo.play();
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
