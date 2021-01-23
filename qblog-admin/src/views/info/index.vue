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
          <el-card>
            <el-tabs v-model="activeTab">
              <el-tab-pane
                label="咨询记录"
                name="CourseHistory"
              >
                <CourseHistory />
              </el-tab-pane>
              <el-tab-pane
                label="预约记录"
                name="ExamHistory"
              >
                <ExamHistory />
              </el-tab-pane>
              <el-tab-pane
                label="修改信息"
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
import CourseHistory from './components/CourseHistory'
import ExamHistory from './components/ExamHistory'
import infoModify from './components/InfoModify'
// import { getInfo } from '@/api/user'

export default {
  name: 'Profile',
  components: { UserCard, CourseHistory, ExamHistory, infoModify },
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
