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
  components: {
    CourseItem: () => import('./components/Item')
  },
  data() {
    return {
      courselist: []
    }
  },
  created() {
    getCourseList().then((res) => {
      if (res.code === 0) {
        console.log(res.data)
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

<style lang="scss" scoped>
</style>
