<template>
  <div class="app-container">
    <el-row>
      <Header></Header>
      <el-divider></el-divider>
    </el-row>
    <el-row>
      <el-col
        :span="15"
        :offset="3"
      >
        <NavMenu></NavMenu>
      </el-col>
    </el-row>
    <div v-if="user">
      <el-row :gutter="20">
        <el-col
          :span="6"
          :xs="24"
          :offset="1"
        >
          <user-card :user="user" />
        </el-col>
        <el-col
          :span="17"
          :xs="24"
        >
          <el-card>
            <el-tabs v-model="activeTab" style="height:97vh;">
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
                <ConsultHistory />
              </el-tab-pane>
              <el-tab-pane
                label="修改个人信息"
                name="infoModify"
              >
                <infoModify :user="user" />
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <el-row>
      <Footer></Footer>
    </el-row>
  </div>
</template>

<script>
import UserCard from './components/UserCard'
import TestHistory from './components/TestHistory'
import ConsultHistory from './components/ConsultHistory'
import infoModify from './components/InfoModify'
// import { getInfo } from '@/api/user'

export default {
  name: 'Profile',
  components: {
    UserCard, TestHistory, ConsultHistory, infoModify,
    Footer: () => import("@/components/Footer"),
    Header: () => import("@/components/Header"),
    NavMenu: () => import("@/components/NavMenu"),
  },
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
