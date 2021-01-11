<template>
  <el-table
      :data="tableData"
      style="width: 100%">
    <el-table-column
        prop="date"
        label="日期"
        width="150">
    </el-table-column>
    <el-table-column
        prop="title"
        label="活动名"
        width="210">
    </el-table-column>
    <el-table-column
        prop="author"
        label="发起人"
        width="210">
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "ForumTable",
  data() {
    return {
      currentDate: new Date(),
      tableData: [{
        date: '05-02',
        title: '如何应对考前焦虑',
        author:"兰斯洛特"
      }, {
        date: '05-04',
        title: '躁郁症的自我修养',
        author:"佚名"
      },{
        date: '12-04',
        title: '亲密关系的建立与保持',
        author:"念来过倒会虚宋小"
      },{
        date: '12-14',
        title: 'PTSD的前世今生',
        author:"战争结束就结婚"
      },{
        date: '12-19',
        title: '如何识别抑郁症的征兆',
        author:"LoveCraft"
      }]
    };
  }
}
</script>

<style scoped>

</style>
