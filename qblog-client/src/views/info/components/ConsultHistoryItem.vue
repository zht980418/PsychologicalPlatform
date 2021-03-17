<template>
  <div class="history-container">
    <el-row>
      <el-col :span="10">
        <img :src="icon">
      </el-col>
      <el-col :span="6">
        <h1>咨询师：{{ doctorName }}</h1>
        <br>
        <h2>状态： {{ status }}</h2>
        <br>
        <h2>预约时间：{{ start }} - {{ end }}</h2>
      </el-col>
    </el-row>
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
    status: {
      type: String,
      default: '未完成'
    },
    doctorName: {
      type: String,
      default: '咨询师'
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
