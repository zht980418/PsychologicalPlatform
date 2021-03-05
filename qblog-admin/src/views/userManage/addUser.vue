<template>
  <el-row>
    <el-col :span="16" :offset="4">
      <el-card style="margin:20px 0;">
        <div slot="header" class="clearfix">
          <span>添加用户</span>
        </div>
        <div class="user-profile">
        </div>
        <div class="user-info">
          <div class="user-info user-info-section">
            <el-form class="info-form" inline label-position="left" label-width="80px">
              <el-form-item label="用户名" inline-message>
                <el-input v-model="user.username"/>
              </el-form-item>
              <el-form-item label="密码" inline-message>
                <el-input v-model="user.password"/>
              </el-form-item>
              <el-form-item label="姓名" inline-message>
                <el-input v-model="user.name"/>
              </el-form-item>
              <el-form-item label="电话">
                <el-input v-model="user.phoneNumber" />
              </el-form-item>
              <el-form-item label="权限">
                <el-checkbox-group  v-model="user.permissionList">
                  <el-checkbox label="咨询师" name="permission"></el-checkbox>
                  <el-checkbox label="编辑" name="permission"></el-checkbox>
                  <el-checkbox label="管理员" name="permission"></el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-row class="btn">
                <el-button type="primary" @click="onSubmit">提交</el-button>
                <el-button @click="onCancel">取消</el-button>
              </el-row>
            </el-form>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
import {getUserById, register} from '@/api/user'

export default {
  name: 'addUser',
  data(){
    return{
     user:{
       username:'Lovecraft',
       password:'123456',
       name: '洛夫',
       phoneNumber: '123456789',
       permissionList:[],
     },
    }
  },
  methods:{
    onSubmit:function() {
      //检查用户名是否可用
          register({
              userid: this.user.username,
              nickname: this.user.name,
              password: this.user.password,
              phonenumber: this.user.phoneNumber,
              rolename: this.user.permissionList.join(" ")
            }
          ).then((res) => {
            console.log(res)
            if(res.code === 0){
              this.$notify.success({
                title: '提示',
                message: '用户添加成功',
              })
              this.$router.push({name: 'addUser'})
            }else{
              this.$notify.error({
                title: '提示',
                message: '用户名已被占用',
              })
            }
          }).catch((err) => {
              console.log(err)
    })
    },

    onCancel: function(){
      this.$router.push({name:'userManage'})
    }
  }

}
</script>

<style lang="scss" scoped>
.el-form-item{
  padding:0 10px;
}

.el-form-item.btn {
  display: block;
}


.box-center {
  margin: 0 auto;
  display: table;
}

.user-profile {
  .user-name {
    font-weight: bold;
  }

  .box-center {
    padding-top: 10px;
  }

  .user-role {
    padding-top: 10px;
    font-weight: 400;
    font-size: 14px;
  }
}

.user-info {
  margin-top: 20px;
  color: #606266;

  .user-info-section {
    font-size: 14px;
    padding: 15px 0;

    .user-info-header {
      border-bottom: 1px solid #dfe6ec;
      padding-bottom: 10px;
      margin-bottom: 10px;
      font-weight: bold;
      span {
        padding-left: 4px;
      }
    }
    .info-form {
      padding-left: 8px;
    }
  }
}
</style>
