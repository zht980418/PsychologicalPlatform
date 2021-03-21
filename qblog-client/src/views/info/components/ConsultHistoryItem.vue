<template>
  <div>
    <el-row>
      <el-col :span="10">
        <el-image :src=icon></el-image>
      </el-col>
      <el-col :span="14">
        <el-row>
          <el-col
            :span="19"
            :offset="1"
          >
            <h3 class="item_text">咨询师：{{ doctorName }}</h3>
            <h3
              class="item_text"
              v-if="roomName"
            >咨询地点：{{roomName}}</h3>
            <h3
              v-else
              class="item_text"
            >咨询方式：线上</h3>
            <h3 class="item_text">预约时间：{{ start }} - {{ end.split(' ')[1] }}</h3>
          </el-col>
          <el-col :span="2">
            <el-button
              type="primary"
              plain
              @click="open"
            >咨询</el-button>
            <el-dialog
              :visible.sync="dialog"
              align="center"
            >
              <el-image
                style="display: inline-block; width: 100%; max-width:200px;"
                :src="DJ_GZH "
                fit="contain"
              ></el-image>
              <div style="font-size: 14px">
                <br>
                <h2>手机扫描二维码</h2>
                <br>
                <h2>开始咨询</h2>
              </div>
              <br><br>
            </el-dialog>
          </el-col>
        </el-row>
        <el-col
          :offset="1"
          :span="23"
        >
          <el-steps>
            <el-step
              title="预约"
              description="前往咨询师预约界面预约"
              status="success"
            />
            <el-step
              :title="Title[0]"
              :description="Description[0]"
              :status="Status[0]"
            />
            <el-step
              :title="Title[1]"
              :description="Description[1]"
              :status="Status[1]"
            />
          </el-steps>
        </el-col>

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
    },
    roomName: {
      type: String,
      default: '咨询室'
    }
  },
  computed: {
    Status() {
      switch (this.status) {
        case '接受': return ['success', 'wait']
        case '已拒绝': return ['error', 'wait']
        case '待确认': return ['finish', 'wait']
        case '咨询中': return ['success', 'finish']
        case '已结束': return ['success', 'success']
      }
      return false
    },
    Title() {
      switch (this.status) {
        case '接受': return ['已接受', '未开始']
        case '已拒绝': return ['已拒绝', '无法咨询']
        case '待确认': return ['待确认', '无法咨询']
        case '咨询中': return ['已接受', '咨询中']
        case '已结束': return ['已接受', '已结束']
      }
      return false
    },
    Description() {
      switch (this.status) {
        case '接受': return ['咨询师已确认接受预约', '等待开始咨询']
        case '已拒绝': return ['咨询师已拒绝您的预约，请重新预约', '无法开始咨询']
        case '待确认': return ['等待咨询师确认并接受预约', '无法开始咨询']
        case '咨询中': return ['咨询师已确认接受预约', '正在进行咨询']
        case '已结束': return ['咨询师已确认接受预约', '已结束咨询']
      }
      return false
    },

  },
  data() {
    return {
      dialog: false,
      DJ_GZH: "https://mp.weixin.qq.com/mp/qrcode?scene=10000004&size=102&__biz=MzI4MzY5MTAwMg==&mid=2247502878&idx=2&sn=1c77e54e285bfc7534cbc2b7c3bc9cb6&send_time="
    }
  },
  created() {

  },
  methods: {
    open() {
      this.dialog = true;
    }

  }
}
</script>

<style scoped>
.item_text {
  color: #606266;
}
</style>