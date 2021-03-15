<template>
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
</template>

<script>
import CourseItem from '../components/Item'

export default {
  name: 'CourseTabPane',
  functional: false,
  components: { CourseItem },
  props: {
    courselist: {
      type: Object,
      default: () => []
    }
  },
  methods: {
    handleCourse(course_id) {
      console.log('点击课程')
      this.$router.push({ name: 'CoursePage', params: { course_id: course_id } })// 传入course_id
    }
  }
}
</script>

<style lang="scss" scoped>
.course-list {
  list-style-type: none;
  display: flex;
  direction: row;
  flex-wrap: wrap;
}
</style>
