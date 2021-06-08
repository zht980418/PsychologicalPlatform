<template xmlns="http://www.w3.org/1999/html">
  <el-row>
    <el-row class="app-container">
      <!--      :data="userData.filter(data => !search || data.userid.toLowerCase().includes(search.toLowerCase())||data.rolename.match(search))"-->
      <el-table
        :data="userData"
        style="width:100%"
      >
        <el-table-column
          prop="userid"
          label="id"
          width="180"
        />
        <el-table-column
          prop="nickname"
          label="昵称"
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
            <el-row>
              <el-select
                style="width: 15%"
                v-model="label"
                clearable
                placeholder="查找字段">
                <el-option
                  v-for="item in options"
                  :key="item.label"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
              <el-input
                v-model="search"
                style="margin-left:10px;width: 30%"
                placeholder="输入关键字搜索"
              />
          </el-row>
          </template>
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="mini"
              @click="modifyUser(scope.row)"
            >编辑
            </el-button>
            <el-button
              type="danger"
              size="mini"
              @click="deleteUser(scope.row)"
            >删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row>
      <el-col style="margin: 50px 0">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[10, 20, 50, 100, 200]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </el-col>
    </el-row>
  </el-row>

</template>

<script>
import router from '@/router'
import {getUsersWithSearch, deleteUser} from '@/api/user'

export default {
  data() {
    return {
      search: '',
      selectionFlag: false,
      userData: [],
      pageSize: 10,
      total: 400,
      currentPage: 1,
      label:'',
      options: [{
        value: 'userid',
        label: 'id'
      },
        {
        value: 'nickname',
        label: '昵称'
      },
        {
          value: 'phonenumber',
          label: '电话'
        },
        {
          value: 'rolename',
          label: '授权'
        },
  ],
    }
  },
  created() {
    getUsersWithSearch({search: "", page: this.currentPage, limit: this.pageSize}).then((response) => {
        this.userData = response.data.records;
        this.total = response.data.total;
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
  watch :{
      search () {
        getUsersWithSearch({search: this.search , page: this.currentPage, limit: this.pageSize}).then((response) => {
          console.log(response)
          this.userData = response.data.records;
          this.total = response.data.total;
        }).catch((err) => {
          console.log(err)
          this.$notify.error({
            title: '提示',
            message: '用户获取失败,请与管理员联系',
          })
        })
        }
  },

  methods: {

    modifyUser: function (row) {
      console.log(row.userid)
      router.push({name: 'modifyUser', params: {userid: row.userid}})
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
              console.log(this.currentPage)
                getUsersWithSearch({search: this.search, page: this.currentPage, limit: this.pageSize}).then((response) => {
                  this.userData = response.data.records
                  this.total = response.data.total
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
    },



    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(val)
      getUsersWithSearch({search: this.search, page: val, limit: this.pageSize}).then((response) => {
        console.log(response)
        this.userData = response.data.records;
        this.total = response.data.total;
        }
      ).catch((err) => {
        console.log(err)
        this.$notify.error({
          title: '提示',
          message: '用户获取失败,请与管理员联系',
        })
      })
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
