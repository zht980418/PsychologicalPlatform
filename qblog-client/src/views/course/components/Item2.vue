<template>
  <li
    class="course-item"
    @click="$router.push({ name: 'CoursePage', params: { courseId: courseId} })"
  >
    <el-col :span="16">
      <img
        class="course-img"
        :src="icon"
      >
    </el-col>
    <el-col :span="5">
      <div class="item-description">
        <br>
        <p class="item-title">{{ title }}</p>
        <!-- <span class="item-progress-text">观看进度{{ completed }}/{{ sum }}</span> -->
        <!-- <el-progress
          class="item-progress"
          :percentage="Percentage"
          show-text="false"
        /> -->
        <br>
        <br>
        <br>
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
  },
  data() {
    return {
    }
  },
  computed: {
    // 计算百分比
    Percentage() {
      return this.completed === 0 ? 0 : this.completed / this.sum * 100
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
  width: 300px;
  height: 120px;
  .course-img {
    width: 200px;
    height: 120px;
    margin-left: 1px;
    margin-right: 1px;
  }
  .item-description {
    padding-left: 10px;
    .item-title {
      font-size: 16px;
      color: #283443;
      font-weight: 800;
    }
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
      // padding-right: 16px;
      color: darkgray;
      float: right;
    }
  }
}
</style>
