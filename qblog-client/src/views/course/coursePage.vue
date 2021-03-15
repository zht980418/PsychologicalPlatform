<template>
  <div style="margin-left:3vh;">
    <el-row class="app-container">
      <el-col :span="18">
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
          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Est odit eos deserunt molestias placeat voluptates perspiciatis tempore et eligendi. Explicabo neque sapiente sit sed voluptate tempora cum consequatur repellat earum.</p>
          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Est odit eos deserunt molestias placeat voluptates perspiciatis tempore et eligendi. Explicabo neque sapiente sit sed voluptate tempora cum consequatur repellat earum.</p>
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
          :key="item.key"
          :courseid="item.course_id"
          :icon="item.icon"
          :title="item.title"
          :office="item.office"
          :sum="item.sum"
          :completed="item.completed"
          :start="item.start"
          :end="item.end"
          @click.native.prevent="handleCourse(item.course_id)"
        />
      </el-col>
    </el-row>
  </div>
</template>

<script>
// import 'video.js/dist/video-js.css'
import { mapGetters } from 'vuex'

export default {
  name: 'CoursePage',
  components: { RecItem: () => import('./components/Item2') },
  data() {
    return {
      course_id: '', // 当前课程编号
      courselist: [{ course_id: '2020082602', title: '课程1', sum: '10', completed: '2', start: '2020/10/1', end: '2020/11/12' }, { course_id: '2020082801', title: '课程2', sum: '10', completed: '6', start: '2020/11/1', end: '2020/11/12' }, { course_id: '2020082602', title: '课程3', sum: '10', completed: '3' },{ course_id: '2020082602', title: '课程1', sum: '10', completed: '2', start: '2020/10/1', end: '2020/11/12' }, { course_id: '2020082801', title: '课程2', sum: '10', completed: '6', start: '2020/11/1', end: '2020/11/12' }, { course_id: '2020082602', title: '课程3', sum: '10', completed: '3' },],
      taglist: ['哈利波特', '电影']
    }
  },
  computed: {
    ...mapGetters([
      'avatar',
      'token',
      'id',
      'name'
    ])
  },
  created() {
    this.course_id = this.$route.params
    this.currentFrame = this.list[0]
  },
  methods: {
    handleCourse(course_id) {
      console.log('点击课程')
      this.$router.push({ name: 'CoursePage', params: { course_id: course_id } })// 传入course_id
    }
  }
}
</script>

<style lang='scss' scoped>
.video {
  height: 90vh;
  width: 70vw;
}
</style>
