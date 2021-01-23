<template>
  <div class="course-history">
    <el-timeline>
      <el-timeline-item
        v-for="(item,index) of historyList"
        :key="index"
        :timestamp="item.time_stamp"
        placement="top"
      >
        <el-card>
          <TestHistoryItem
            :icon="item.icon"
            :title="item.title"
            :office="item.office"
            :sum="item.sum"
            :completed="item.completed"
            :start="item.start"
            :end="item.end"
            @click.native.prevent="handleClick()"
          />
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>

<script>
import TestHistoryItem from '../components/TestHistoryItem'
// import { getCourseHistory } from '@/api/course'

export default {
  components: { TestHistoryItem },
  data() {
    return {
      historyList: [{}]
    }
  },
  created() {
    console.log('获取课程学习记录')
    this.handleGetCourseHistory()
  },
  methods: {
    // 获取courseList
    // handleGetCourseHistory() {
    //   const data = { type: 'getCourseHistory', user_id: '1' }
    //   getCourseHistory(data).then((response) => {
    //     console.log('课程记录')
    //     console.log(response)
    //     this.historyList = response.data
    //   })
    // },
    handleClick() {
      console.log('点击课程卡片')
      // this.$router.push({ name: 'CoursePage' })
    }
  }
}
</script>
