<template>
  <div class="app-container">
    <div v-if="user">
      <el-row :gutter="20">
        <el-col
          :span="6"
          :xs="24"
        >
          <user-card :user="user" />
        </el-col>
        <el-col
          :span="18"
          :xs="24"
        >
          <el-card class="demo-app">
            <el-tabs v-model="activeTab">
              <el-tab-pane
                label="心理自测记录"
                name="CourseHistory"
              >
                <TestHistory />
              </el-tab-pane>
              <el-tab-pane
                label="预约咨询记录"
                name="ExamHistory"
              >
                <ExamHistory />
              </el-tab-pane>
              <el-tab-pane
                label="修改密码"
                name="infoModify"
              >
                <infoModify :user="user" />
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import UserCard from './components/UserCard'
import TestHistory from './components/TestHistory'
import ExamHistory from './components/ExamHistory'
import infoModify from './components/InfoModify'
// import { getInfo } from '@/api/user'

export default {
  name: 'Profile',
  components: { UserCard, TestHistory, ExamHistory, infoModify },
  data() {
    return {
      user: {},
      activeTab: 'CourseHistory'// 默认tab
    }
  },
  created() {
    this.getUser()
  },
  methods: {
    getUser() {
      // const data = { type: 'getInfo', user_id: '1' }
      // getInfo(data).then((response) => {
      //   console.log('个人信息：')
      //   console.log(response)
      //   this.user = response.data[0]
      // })
    }
  }
}
</script>
