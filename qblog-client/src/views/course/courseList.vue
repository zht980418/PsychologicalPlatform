<template>
  <div class="app-container">
    <ul class="course-list">
      <CourseItem
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
    </ul>
  </div>
</template>

<script>
import { getCourseList } from '@/api/course'

export default {
  name: 'CourseCentre',
  components: {
    CourseItem: () => import('./components/Item')
  },
  data() {
    return {
      courselist: [{ course_id: '2020082602', title: '课程1', sum: '10', completed: '2', start: '2020/10/1', end: '2020/11/12' }, { course_id: '2020082801', title: '课程2', sum: '10', completed: '6', start: '2020/11/1', end: '2020/11/12' }, { course_id: '2020082602', title: '课程3', sum: '10', completed: '3' }]
    }
  },
  created() {
    getCourseList().then((res) => {
      if (res.code === 0) {
        console.log(res.data)
      }
    })
  },
  methods: {
    handleGetCourseList() {
      getCourseList().then((res) => {
        if (res.code === 0) {
          console.log(res.data)
          // TODO--获取Courselist
        }
      })
    },

    handleCourse(course_id) {
      console.log('点击课程')
      this.$router.push({ name: 'CoursePage', params: { course_id: course_id } })// 传入course_id
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
