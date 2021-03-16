<template>
  <div class="app-container">
    <ul class="course-list">
      <CourseItem
        v-for="item in courselist"
        :key="item.courseId"
        :courseId="item.courseId"
        :icon="item.icon"
        :title="item.title"
        :time="item.time"
      />
    </ul>
  </div>
</template>

<script>
import { getCourseList } from '@/api/course'


export default {
  name: 'CourseCentre',
  components: { CourseItem: () => import('./components/Item') },
  data() {
    return {
      user_id: '',
      courselist: [{ course_id: '2020082602', title: '课程1', sum: '10', completed: '2', start: '2020/10/1', end: '2020/11/12' }, { course_id: '2020082801', title: '课程2', sum: '10', completed: '6', start: '2020/11/1', end: '2020/11/12' }, { course_id: '2020082602', title: '课程3', sum: '10', completed: '3' }]
    }
  },
  created() {
    getCourseList().then((res) => {
      if (res.code == 0) {
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
  }
}
</script>

