<template>
  <div style="padding:30px;">
    <el-form class="user_info">
      <el-form-item label="头像">
        <el-image
          :src="head1"
          style="width:200px; height:200px;"
        />
        <!-- TODO完成头像修改 -->
      </el-form-item>
      <el-form-item label="旧密码">
        <el-input
          v-model.trim="user.oldPassword"
          show-password
        />
      </el-form-item>
      <el-form-item label="新密码">
        <el-input
          v-model.trim="user.newPassword"
          show-password
        />
      </el-form-item>
      <el-form-item label="重复新密码">
        <el-input
          v-model.trim="user.newPassword2"
          show-password
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          @click="submit"
        >提交修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
// import { modifyInfo } from '@/api/user'

export default {
  name: 'Info',
  props: {
    user: {
      type: Object,
      default: () => {
        return {
          id: '',
          name: '',
          sex: '',
          post: '',
          email: '',
          office: '',
          avatar: '',
          role: ''
        }
      }
    }
  },
  data() {
    return {
      head1: require('@/icons/img/head1.jpg')
    }
  },
  methods: {
    // TODO--提交修改
    submit() {
      // modifyInfo()
    }
  }
}
</script>

<style lang="scss" scoped>
.text {
  font-size: 14px;
  margin-bottom: 10px;
  margin-top: 10px;
}
.head-container {
  height: 150px;
  width: 150px;
  background: lightyellow;
  .user-head {
    width: 150px;
  }
}
</style>
