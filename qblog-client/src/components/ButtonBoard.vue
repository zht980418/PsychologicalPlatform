<template>
    <el-row>
      <el-col :span="17" :offset="4">
        <el-button >预约咨询</el-button>
        <el-button>心理自测</el-button>
        <el-button>心理教育</el-button>
        <el-button>预约查询</el-button>
      </el-col>
    </el-row>
</template>

<script>
export default {
  name: "ButtonBoard"
}
</script>

<style scoped>
    .el-button{
      margin:30px 20px;
      width: 210px;
      height: 100px;
      font-size: 20px;}
</style>
