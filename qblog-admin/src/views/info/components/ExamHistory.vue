<template>
  <div class="exam-history">
    <el-timeline>
      <el-timeline-item
        v-for="(item,index) of historyList"
        :key="index"
        :timestamp="item.time_stamp"
        placement="top"
      >
        <el-card>
          <ExamHistoryItem
            :icon="item.icon"
            :title="item.exam_title"
            :office="item.office"
            :start="item.start"
            :end="item.end"
          />
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>

<script>
import ExamHistoryItem from '../components/ExamHistoryItem'
// import { getExamHistory } from '@/api/exam'

export default {
  components: { ExamHistoryItem },
  data() {
    return {
      historyList: [{}]
    }
  },
  created() {
    console.log('获取考试记录数据')
    this.handleGetExamHistory()
  },
  methods: {
    // 获取考试记录数据
    // handleGetExamHistory() {
    //   const data = { type: 'getExamHistory', user_id: '1' }
    //   getExamHistory(data).then((response) => {
    //     console.log('考试记录：')
    //     console.log(response)
    //     this.historyList = response.data
    //   })
    // }
  }
}
</script>
