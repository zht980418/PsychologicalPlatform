<template>
  <div style="padding:30px;">
    <el-form
      class="user_info"
      ref="modify"
      :model="modify"
      :rules="rules"
    >
      <!-- <el-form-item label="头像">
        <el-image
          :src="user.avatar"
          style="width:200px; height:200px;"
        />
      </el-form-item> -->
      <el-form-item
        label="旧密码"
        prop="old"
      >
        <el-input
          v-model.trim="modify.old"
          show-password
        />
      </el-form-item>
      <el-form-item
        label="新密码"
        prop="new"
      >
        <el-input
          v-model.trim="modify.new"
          show-password
        />
      </el-form-item>
      <el-form-item
        label="重复新密码"
        prop="new2"
      >
        <el-input
          v-model.trim="modify.new2"
          show-password
        />
      </el-form-item>
      <br>
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
import { modifyUser, getUserById } from '@/api/user'

export default {
  name: 'Info',
  props: {
    user: {
      type: Object,
      default: () => {
        return {
          id: '',
          name: '',
          avatar: '',
          phoneNumber: '',
        }
      }
    }
  },
  data() {
    const validatePasswordRepeat = (rule, value, callback) => {
      if (value !== this.modify.new) {
        callback(new Error('两次密码必须相同'))
      } else {
        callback()
      }
    }
    const validateNewPassword = (rule, value, callback) => {
      if (value === this.modify.old) {
        callback(new Error('新密码和旧密码不能相同'))
      } else {
        callback()
      }
    }
    return {
      modify: {},
      rules: {
        old: [
          { required: true, message: '请输入旧密码', trigger: 'blur' },
          { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' },],
        new: [
          { required: true, message: '请输入新密码', trigger: 'blur' },
          { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' },
          { validator: validateNewPassword, trigger: 'blur' }],
        new2: [
          { required: true, message: '请输入新密码', trigger: 'blur' },
          { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' },
          { validator: validatePasswordRepeat, trigger: 'blur' },
          { validator: validateNewPassword, trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    // ...mapGetters([
    //   'user'
    // ])
  },
  methods: {
    submit() {
      this.$refs['modify'].validate((valid) => {
        if (valid) {
          const data = { oldPassword: this.modify.old, newPassword: this.modify.new }
          getUserById(this.user.id).then((res) => {
            if (res.code === 0) {
              if (res.data.password === this.modify.new) {
                modifyUser(this.user.id, data).then((res) => {
                  if (res.code === 0) {
                    console.log(res);
                  }
                })
              } else {
                this.notify.error({
                  title: "提示",
                  message: "旧密码错误！",
                })
              }
            }
          }).catch((err) => {
            console.log(err)
            this.$notify.error({
              title: "提示",
              message: "网络忙，修改密码错误！",
            })
          })
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.head-container {
  height: 150px;
  width: 150px;
  background: lightyellow;
  .user-head {
    width: 150px;
  }
}
</style>
