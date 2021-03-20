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
          :roomName="item.roomName"
        />
      </el-card>
    </el-timeline-item>
  </el-timeline>
</template>

<script>
import ConsultHistoryItem from '../components/ConsultHistoryItem'
import { getOrderHistoryById } from '@/api/order'
import { mapGetters } from 'vuex'

export default {
  components: { ConsultHistoryItem },
  data() {
    return {
      historyList: []
    }
  },
  created() {
    getOrderHistoryById(this.id).then((res) => {
      this.historyList = res.data
    }).catch((err) => {
      console.log(err)
      this.$notify.error({
        title: "提示",
        message: "网络忙，获取咨询历史失败",
      })
    })
  },
  computed: {
    ...mapGetters([
      'id'
    ])
  },
  methods: {
  }
}
</script>
