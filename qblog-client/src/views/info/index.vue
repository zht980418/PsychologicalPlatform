<template>
  <div class="app-container">
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
            <el-tabs
              v-model="activeTab"
              style="height:97vh;"
            >
              <el-tab-pane
                label="心理自测记录"
                name="TestHistory"
              >
                <TestHistory />
              </el-tab-pane>
              <el-tab-pane
                label="预约咨询记录"
                name="ConsultHistory"
              >
                <ConsultHistory />
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
import ConsultHistory from './components/ConsultHistory'
import infoModify from './components/InfoModify'
import { mapGetters } from 'vuex'

export default {
  name: 'Profile',
  components: {
    UserCard, TestHistory, ConsultHistory, infoModify,
  },
  data() {
    return {
      activeTab: 'TestHistory'// 默认tab
    }
  },
  computed: {
    ...mapGetters([
      'user',
    ])
  },
  created() {
  },
  methods: {
  }
}
</script>
