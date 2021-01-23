<template>
  <div class="history-container">
    <div class="img-container">
      <img :src="icon">
    </div>
    <div class="msg-container">
      <div class="title-container">
        <p class="course-title">{{ title }}</p>
        <p class="office">{{ office }}</p>
      </div>
      <div class="progress-container">
        <p class="progress-text">已学习{{ completed }}/{{ sum }}</p>
        <el-progress
          class="progress"
          label="已学习"
          :percentage="Percentage"
          show-text="false"
        />
      </div>
      <p class="time">{{ start }} - {{ end }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CourseHistoryItem',
  functional: false,
  props: {
    icon: {
      type: String,
      default: require('@/icons/img/test1.png')
    },
    title: {
      type: String,
      default: '课程名'
    },
    office: {
      type: String,
      default: '科室名'
    },
    sum: {
      type: Number,
      default: 100
    },
    completed: {
      type: Number,
      default: 50
    },
    start: {
      type: String,
      default: '起始时间'
    },
    end: {
      type: String,
      default: '终止时间'
    }
  },
  data() {
    return {
      // 计算百分比
      Percentage: this.completed === 0 ? 0 : this.completed / this.sum * 100
    }
  }
}
</script>

<style lang="scss" scoped>
.history-container {
  display: flex;
  direction: row;
  flex-wrap: wrap;
  .img-container {
    width: 30%;
    margin-right: 10px;
    img {
      width: 100%;
    }
  }
  .msg-container {
    width: 63%;
    margin-left: 5px;
    .title-container {
      direction: row;
      display: flex;
      width: 100%;
      margin-left: 10px;
      .course-title {
        width: 50%;
        font-size: 18px;
        color: #283443;
        font-weight: 800;
        float: left;
      }
      .office {
        width: 50%;
        font-size: 14px;
        font-weight: 600;
        color: rgb(117, 111, 111);
        padding-top: 7px;
        padding-right: 10px;
        float: right;
      }
    }
    .progress-container {
      direction: row;
      width: 100%;
      margin-left: 10px;
      margin-bottom: 10px;
      .progress-text {
        font-size: 12px;
        font-weight: 600;
        color: #283443;
        float: left;
        padding-right: 10px;
      }
      .progress {
        padding-top: 10px;
        float: right;
        width: 70%;
      }
    }
    .time {
      font-size: 12px;
      color: darkgray;
      margin-top: 20px;
      float: right;
    }
  }
}
</style>
