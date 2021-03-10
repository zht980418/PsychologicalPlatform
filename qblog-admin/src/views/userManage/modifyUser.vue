<template>
  <el-row >
    <el-col :span="16" :offset="4">
      <el-card style="margin:20px 0;">
        <div slot="header" class="clearfix">
          <span>编辑用户</span>
        </div>
        <div class="user-profile">
          <div class="box-center">
            <pan-thumb :image="user.avatar" :height="'100px'" :width="'100px'" :hoverable="false">
              <div>Hello</div>
              {{ user.permission }}
            </pan-thumb>
          </div>
          <div class="box-center">
            <div class="user-name text-center">{{ user.name }}</div>
            <div class="user-role text-center text-muted">{{ user.permission }}</div>
          </div>
        </div>
        <div class="user-info">
          <div class="user-info user-info-section">
            <div class="user-info-header">
              <span>基本信息</span>
            </div>

            <el-form class="info-form" inline label-position="left" label-width="80px">
              <el-form-item label="姓名" inline-message>
                <el-input v-model="user.name" />
              </el-form-item>
              <el-form-item label="电话">
                <el-input v-model="user.phoneNumber" />
              </el-form-item>
              <el-form-item label="权限">
                <el-checkbox-group v-model="user.permissionList">
                  <el-checkbox label="咨询师" name="permissionList" />
                  <el-checkbox label="编辑" name="permissionList" />
                  <el-checkbox label="管理员" name="permissionList" />
                </el-checkbox-group>
              </el-form-item>
              <el-form-item class="btn">
                <el-button type="primary" @click="onSubmit">提交</el-button>
                <el-button>取消</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </el-card>
    </el-col>

  </el-row>
</template>

<script>
import PanThumb from '@/components/PanThumb'
import { getUserById, modifyUser } from '@/api/user'

export default {
  name: 'ModifyUser',
  components: { PanThumb },

  data() {
    return {
      user: {
        userid: '',
        name: '',
        phoneNumber: '',
        permissionList: [],
        permissionString: '',
        avatar: '',
      },
    }
  },
  created() {
    const userId = this.$route.params.userId
    getUserById(userId).then((response) => {
      this.user.userid = response.data.userid
      this.user.name = response.data.nickname
      this.user.phoneNumber = response.data.phonenumber
      this.user.permission = response.data.rolename
      this.user.permissionList = response.data.rolename.split(' ')
      console.log('created', response.data.rolename)
    })
  },
  methods: {
    onSubmit() {
      modifyUser(
        this.user.userid,
        {
          // 按后端数据解构进行转换
          userid: this.user.userid,
          nickname: this.user.name,
          rolename: this.user.permissionList.join(' '),
          phonenumber: this.user.phoneNumber,
        })
        .then((response) => {
          this.$router.push('/userManage')
        }
        ).catch((err) => {
          this.$notify.error({
            title: '提示',
            message: '用户修改失败',
          })
        })
    }
  },

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
