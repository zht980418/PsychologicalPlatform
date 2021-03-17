<template>
  <el-card class="demo-app">
    <div
      slot="header"
      class="clearfix"
    >
      <span>个人信息</span>
    </div>
    <div class="user-profile">
      <div class="box-center">
        <pan-thumb
          :image="user.avatar"
          :height="'90px'"
          :width="'90px'"
          :hoverable="false"
        >
          <div>Hello</div>
          {{ user.role }}
        </pan-thumb>
      </div>
      <div class="box-center">
        <div class="user-name text-center">{{ user.name }}</div>
      </div>
    </div>
    <div class="user-info">
      <div class="user-info user-info-section">
        <div class="user-info-header">
          <span>基本信息</span>
        </div>
        <el-form class="info-form">
          <el-form-item label="用户名">
            <el-input
              v-model="user.id"
              :disabled="true"
            />
          </el-form-item>
          <el-form-item label="姓名">
            <el-input
              v-model="user.name"
              :disabled="true"
            />
          </el-form-item>
          <el-form-item label="权限">
            <el-input
              v-model="user.role"
              :disabled="true"
            />
          </el-form-item>
        </el-form>
      </div>
    </div>
  </el-card>
</template>

<script>
import PanThumb from '@/components/PanThumb'

export default {
  components: { PanThumb },
  props: {
    user: {
      type: Object,
      default: () => {
        return {
          login_id: '',
          user_name: '',
          sex: '',
          post: '',
          email: '',
          avatar: ''
        }
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.box-center {
  margin: 0 auto;
  display: table;
}

.user-profile {
  .user-name {
    font-weight: bold;
  }

  .user-role {
    // padding-top: 10px;
    font-weight: 400;
    font-size: 14px;
  }
}

.user-info {
  margin-top: 10px;
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
