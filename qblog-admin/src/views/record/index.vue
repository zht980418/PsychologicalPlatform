<template>
  <el-row class="app-container">
    <el-table
      :data="userData.filter(data => !search || data.userid.toLowerCase().includes(search.toLowerCase())||data.rolename.match(search))"
      style="width: 100%"
    >
      <el-table-column
        prop="userid"
        label="编号"
        width="180"
      />
      <el-table-column
        prop="nickname"
        label="用户名"
        width="180"
      />
      <el-table-column
        prop="phonenumber"
        label="电话"
        width="180"
      />
      <el-table-column align="left">
        <el-input
          v-model="search"
          style="width: 30%"
          size="mini"
          placeholder="输入关键字搜索"
        />
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            @click="viewRecord(scope.row)"
          >咨询记录</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-row>
</template>

<script>
import router from '@/router'
import { getUsers, deleteUser } from '@/api/user'

export default {
  data() {
    return {
      search: '',
      selectionFlag: false,
      userData: [],
    }
  },
  created() {
    getUsers().then((response) => {
      this.userData = response.data.filter((user) => {
        return user.rolename.trim() === "游客"
      })
    }
    ).catch((err) => {
      console.log(err)
      this.$notify.error({
        title: '提示',
        message: '用户获取失败,请与管理员联系',
      })
    })
  },

  methods: {
    viewRecord: function (row) {
      console.log(row)
      router.push({ name: 'viewRecord', params: { userid: row.userid, nickname: row.nickname } })
    },
  }

}
</script>

<style scoped>
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>
