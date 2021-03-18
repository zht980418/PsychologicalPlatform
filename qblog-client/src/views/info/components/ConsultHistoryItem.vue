<template>
  <div>
    <el-row>
      <el-col :span="10">
        <el-image :src=icon></el-image>
      </el-col>
      <el-col :span="10">
        <br>
        <h2>咨询师：{{ doctorName }}</h2>
        <br>
        <h2>状态： {{ status }}</h2>
        <br>
        <h2>预约时间：<br><br>{{ start }} -- {{ end }}</h2>
      </el-col>
      <el-col :span="3">
        <br>
        <el-button type="primary">开始咨询</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'ConsultHistoryItem',
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
