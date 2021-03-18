<template>
  <li
    class="course-item"
    @click="$router.push({ name: 'CoursePage', params: { courseId: courseId} })"
  >
    <el-col :span="15">
      <img
        class="course-img"
        :src="icon"
      >
    </el-col>
    <el-col
      :span="9"
      style="height:120px;"
    >
      <div class="item-description">
        <br>
        <p class="item-title">{{ title }}</p>
        <p class="item-time">{{ time }}</p>
      </div>
    </el-col>
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
  margin-bottom: 20px;
  height: 120px;
  .course-img {
    width: 200px;
    height: 120px;
    margin-left: 1px;
    margin-right: 1px;
  }
  .item-description {
    height: inherit;
    .item-title {
      font-size: 14px;
    }

    .item-time {
      font-size: 12px;
      color: darkgray;
      float: right;
      padding: 3px;
      position: absolute;
    }
  }
}
</style>
