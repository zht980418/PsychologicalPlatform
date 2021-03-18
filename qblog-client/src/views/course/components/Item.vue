<template >
  <li
    class="course-item"
    @click="$router.push({ name: 'CoursePage', params: { courseId: courseId} })"
  >
    <img
      class="course-img"
      :src="icon"
    >
    <div class="item-description">
      <h2 class="item-title">{{ title }}</h2>
      <br>
      <br>
      <p class="item-time">{{ time }}</p>
    </div>
  </li>
</template>

<script>
export default {
  name: 'CourseItem',
  functional: false,
  props: {
    courseId: {
      type: String,
      default: '2020080101'
    },
    icon: {
      type: String,
      default: require('@/icons/img/test1.png')
    },
    title: {
      type: String,
      default: '课程名'
    },
    time: {
      type: String,
      default: '2020/7/29'
    }
  }
}
</script>

<style lang='scss' scoped>
.course-item {
  border: solid 1px lavender;
  background: ghostwhite;
  margin-right: 20px;
  margin-bottom: 20px;
  width: 200px;
  .course-img {
    width: 200px;
    height: 100px;
    margin-left: 1px;
    margin-right: 1px;
  }
  .item-description {
    padding-left: 10px;
    // .item-title {
    //   font-size: 16px;
    //   color: #283443;
    //   font-weight: 800;
    // }
    .item-office {
      font-size: 14px;
      font-weight: 600;
      color: rgb(117, 111, 111);
    }
    .item-progress-text {
      font-size: 12px;
      font-weight: 600;
      color: #283443;
    }
    .item-progress {
      margin-top: 5px;
      margin-bottom: 5px;
    }
    .item-time {
      font-size: 12px;
      padding-right: 16px;
      color: darkgray;
      float: right;
    }
  }
}
</style>
