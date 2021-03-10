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
      <el-table-column
        prop="rolename"
        label="授权"
        width="180"
      />
      <el-table-column align="left">
        <template
          slot="header"
          slot-scope="scope"
        >
          <el-input
            v-model="search"
            style="width: 30%"
            size="mini"
            placeholder="输入关键字搜索"
          />
        </template>
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            @click="modifyUser(scope.row)"
          >编辑</el-button>
          <el-button
            type="danger"
            size="mini"
            @click="deleteUser(scope.row)"
          >删除</el-button>
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
      this.userData = response.data
      console.log(response)
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

    modifyUser: function (row) {
      console.log(row.userid)
      router.push({ name: 'modifyUser', params: { userId: row.userid } })
    },
    deleteUser: function (row) {
      {
        this.$confirm('此操作将永久删除用户, 是否继续?', '提示', {
          cancelButtonText: '取消',
          confirmButtonText: '确定',
          type: 'warning'
        }).then(() => {
          deleteUser(row.userid)
            .then((response) => {
              console.log(response)
              this.$message({
                type: 'success',
                message: '删除成功!'
              })
            })
            .then(() => {
              getUsers().then((response) => {
                this.userData = response.data
                console.log(response)
              })
            }
            ).catch((err) => {
              console.log(err)
              this.$notify.error({
                title: '提示',
                message: '用户获取失败,请与管理员联系',
              })
            })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      }
    }
  },

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
