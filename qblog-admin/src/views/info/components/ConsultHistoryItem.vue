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
      <div>
        <p class="score">分数： {{ score }}</p>
        <p class="time">{{ start }} - {{ end }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ExamHistoryItem',
  functional: false,
  props: {
    icon: {
      type: String,
      default: require('@/icons/img/test2.jpg')
    },
    score: {
      type: String,
      default: '80'
    },
    title: {
      type: String,
      default: '考试名'
    },
    office: {
      type: String,
      default: '科室名'
    },
    start: {
      type: String,
      default: '起始时间'
    },
    end: {
      type: String,
      default: '终止时间'
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
    .score {
      float: left;
      font-size: 16px;
      margin-left: 30%;
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
