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
import infoModify from './components/InfoModify'
import { mapGetters } from 'vuex'

export default {
  name: 'Profile',
  components: { UserCard, infoModify },
  data() {
    return {
      activeTab: 'infoModify'// 默认tab
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
