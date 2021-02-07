<template>
  <el-row>
  <el-table
    :data="userData.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase())||data.id.match(search))"
    style="width: 100%">
    <el-table-column
      prop="id"
      label="编号"
      width="180">
    </el-table-column>
    <el-table-column
      prop="username"
      label="用户名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="phoneNumber"
      label="电话"
      width="180">
    </el-table-column>
    <el-table-column
      prop="permissionList"
      label="授权"
      width="180">
      <template slot-scope="scope">
        <li v-for="item in scope.row.permissionList" style="display: inline">
        {{ item }}
        </li>
      </template>
    </el-table-column>
    <el-table-column
      align="left">
      <template slot="header" slot-scope="scope">
        <el-input
          style="width: 30%"
          v-model="search"
          size="mini"
          placeholder="输入关键字搜索"/>
      </template>
      <template slot-scope="scope">
        <el-button type="primary"  size="mini" @click="modifyUser(scope.row)">编辑</el-button>
        <el-button type="danger" size="mini" @click="deleteUser(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  </el-row>
</template>

<script>
import router from '@/router'
import modifyUser from '@/views/userManage/modifyUser'
// import { getUsers } from '@/api/user'


export default {
  data() {
    return {
      search:'',
      selectionFlag:false,
      userData: [{
        id: '000001',
        username: '新垣结衣',
        phoneNumber:'400123',
        permissionList:['医生','编辑'],
      }, {
        id: '000002',
        username: '长泽雅美',
        phoneNumber:'400124',
        permissionList:['管理员'],
      },  {
        id: '000003',
        username: '新垣结衣',
        phoneNumber:'400123',
        permissionList:['医生'],
      },{
        id: '000004',
        username: '新垣结衣',
        phoneNumber:'400123',
        permissionList:[],
      }]
    }
  },
  // created() {
  //   this.userData = getUsers
  // },
  methods: {

    modifyUser: function(row){
      console.log(row.id);
      router.push({name: 'modifyUser', params: {userId: row.id}});

    },
    deleteUser:function(row){
      console.log(row.id)
      router.push({name: 'modifyUser', params: {userId: row.id}});
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
