<template>
  <el-timeline>
    <el-timeline-item
      v-for="(item,index) of historyList"
      :key="index"
      :timestamp="item.start"
      placement="top"
    >
      <el-card>
        <ConsultHistoryItem
          :icon="item.icon"
          :doctorName="item.doctorName"
          :status="item.status"
          :start="item.start"
          :end="item.end"
        />
      </el-card>
    </el-timeline-item>
  </el-timeline>
</template>

<script>
import ConsultHistoryItem from '../components/ConsultHistoryItem'
// import { getConsultHistory } from '@/api/exam'

export default {
  components: { ConsultHistoryItem },
  data() {
    return {
      historyList: [{ uid: 'uid123', orderId: '20210220155532703', doctorId: 'zhangsan123', doctorName: '张三', start: '起始', end: '终止', status: '未开始' }]
    }
  },
  created() {
    console.log('获取咨询记录数据')
    // this.handleGetConsultHistory()
  },
  methods: {
    // 获取咨询记录数据
    // handleGetConsultHistory() {

    // }
  }
}
</script>
