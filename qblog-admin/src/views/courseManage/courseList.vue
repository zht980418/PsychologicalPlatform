<template>
  <div class="app-container">
    <CourseTabPane :courselist="all" />
  </div>
</template>

<script>
import CourseTabPane from './components/tabPane'

export default {
  name: 'CourseCentre',
  components: { CourseTabPane },
  data() {
    return {
      user_id: '',
      all: [{ course_id: '2020082602', title: '课程1', sum: '10', completed: '2', start: '2020/10/1', end: '2020/11/12' }, { course_id: '2020082801', title: '课程2', sum: '10', completed: '6', start: '2020/11/1', end: '2020/11/12' }, { course_id: '2020082602', title: '课程3', sum: '10', completed: '3' }]
    }
  },
  created() {
    // init the default selected tab
    this.user_id = this.$route.params.user_id
  },
  methods: {
    // TODO--获取Courselist
    handleGetCourseList() {

    },
    addExam() {
      console.log();
      this.$router.push({ name: 'CourseAddPage' })
    },
  }
}
</script>

