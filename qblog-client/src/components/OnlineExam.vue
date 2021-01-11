<template>
    <el-collapse v-model="activeNames" @change="handleChange">
      <el-collapse-item title="青少年自我价值感量表" name="1">
        <div>以下的每一个题目都是青少年朋友从不同侧面对自己的种种评价。在每个题目后都有5种选择，即“完全不符合”、“大部分不符合”、“部分符合部分不符合”、“大部分符合”，“完全符合”。由于每个人的生活经历和对自己的看法的不同，因而对以下题目的回答也不同。</div>
        <div><el-link  href="/quiz/quiz1"><el-button type="primary" icon="el-icon-edit">答题</el-button></el-link></div>      
      </el-collapse-item>
      <el-collapse-item title="PTSR压力测试表" name="2">
        <div>仔细考虑下列每一个项目， 看它究竟有多适合你， 然後将你对每一项的评分， 根据下面这个发生频率表列出来。</div>
        <div><el-link  href="/quiz/quiz2"><el-button type="primary" icon="el-icon-edit">答题</el-button></el-link></div>
      </el-collapse-item>
      <el-collapse-item title="SAS自评表" name="3">
        <div>本评定量表共有20个题目，请您仔细阅读，然后根据最近一星期以内你的实际感受，选择一个与你的情况最相符合的答案，根据第一印象作出判断。</div>
        <div><el-link  href="/quiz/quiz3"><el-button type="primary" icon="el-icon-edit">答题</el-button></el-link></div>
      </el-collapse-item>
    </el-collapse>
</template>

<script>
export default {
  name: "OnlineExam",
  data(){
    return{
      activeNames: ['1'],
     }
  },
  methods:{
    handleChange(val) {
        console.log(val);
      },
  }
}
</script>

<style scoped>

</style>
