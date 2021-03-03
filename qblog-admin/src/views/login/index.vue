<template>
  <div class="login-container">
    <div class="blur" />
    <el-form v-if="toggleLoginRegister"
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      auto-complete="on"
      label-position="left"
    >
      <div class="title-container">
        <h3 class="title">四川大学心理健康教育教育平台后台管理系统</h3>
      </div>

      <el-form-item   prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="用户名"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item   prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          placeholder="密码"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleLogin"
        />
        <span
          class="show-pwd"
          @click="showPwd"
        >
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>

      <el-button
        :loading="loading"
        type="primary"
        style="width:100%;margin-bottom:30px;"
        @click.native.prevent="handleLogin"
      >登录</el-button>

      <el-link  type="primary"  @click="handleToggle">没有账号?现在注册</el-link>

    </el-form>

    <el-form v-if="!toggleLoginRegister"
      ref="registerForm"
      :model="registerForm"
      :rules="registerRules"
      class="login-form"
      label-position="left"
    >
      <div class="title-container">
        <h3 class="title">四川大学心理健康教育教育平台后台管理系统</h3>
      </div>

      <el-form-item   prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="username"
          v-model="registerForm.username"
          placeholder="用户名"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item   prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="registerForm.password"
          :type="passwordType"
          placeholder="密码"
          name="password"
          tabindex="2"
          auto-complete="on"
        />
        <span
          class="show-pwd"
          @click="showPwd"
        >
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>

      <el-form-item  prop="passwordRepeat">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          :key="passwordType"
          ref="passwordR"
          v-model="registerForm.passwordRepeat"
          :type="passwordType"
          placeholder="重复密码"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleRegister"
        />
        <span
          class="show-pwd"
          @click="showPwd"
        >
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>

      <el-button
        :loading="loading"
        type="primary"
        style="width:100%;margin-bottom:30px;"
        @click.native.prevent="handleRegister"
      >注册</el-button>

      <el-link type="primary" @click="handleToggle">已有账号?现在登录</el-link>
    </el-form>
  </div>
</template>

<script>


export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!value) {
        callback(new Error('用户名不能为空'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 5) {
        callback(new Error('密码不能小于5个字符'))
      } else {
        callback()
      }
    }
    const validatePasswordRepeat = (rule, value, callback) => {
      if (value !== this.registerForm.passwordRepeat) {
        callback(new Error('两次密码必须相同'))
      } else {
        callback()
      }
    }
    return {
      toggleLoginRegister : true,
      loginForm: {
        username: '',
        password: '',
      },
      registerForm: {
        username: '',
        password: '',
        passwordRepeat:'',
      },
      loginRules: {
        username: [
          { required: true, trigger: 'blur', validator: validateUsername },
        ],
        password: [
          { required: true, trigger: 'blur', validator: validatePassword },
        ],
      },
      registerRules: {
        username: [
          { required: true, trigger: 'blur', validator: validateUsername },
        ],
        password: [
          { required: true, trigger: 'blur', validator: validatePassword },
        ],
        passwordRepeat: [
          { required: true, trigger: 'blur', validator: validatePasswordRepeat },
        ],
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined,
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true,
    },
  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleToggle(){
      this.toggleLoginRegister =!this.toggleLoginRegister;
    },

    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          // 显示 loading
          this.loading = true
          this.$store
            // 执行 vuex 中的登录方法
            .dispatch('user/login', this.loginForm)
            .then(() => {
              // this.redirect 为本来要跳转的页面，如果有值的话登录之后直接跳转到本来要跳转的页面，否则跳转到首页
              this.$router.push({ path: this.redirect || '/' })

              // 关闭 loading
              this.loading = false
            })
            .catch(() => {
              // 关闭 loading
              this.loading = false
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    handleRegister() {
      this.$refs.registerForm.validate((valid) => {
        if (valid) {
          // 显示 loading
          this.loading = true
          this.$store
          // 执行 vuex 中的注册方法
          .dispatch('user/login', this.registerForm)
          .then(() => {
          // this.redirect 为本来要跳转的页面，如果有值的话登录之后直接跳转到本来要跳转的页面，否则跳转到首页
          this.$router.push({ path: this.redirect || '/' })
          // 关闭 loading
          this.loading = false
          })
          .catch(() => {
          // 关闭 loading
          this.loading = false
          })
        } else {
          console.log('error register!!')
          return false
        }
      })
    },
  },
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #283443;
$light_gray: #fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

   .blur {
    // background-image: url("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1603619028761&di=fbf77e68f9d4ee3884e9c6ca1d72907b&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F8%2F53ed7a4907e17.jpg");
    background-image: url("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1603620908596&di=77812b2da1ada6318fd2af28388a7ce0&imgtype=0&src=http%3A%2F%2F01.minipic.eastday.com%2F20180827%2F20180827192747_d3c1239df05a21e179f3e8740eac5d7e_8.jpg");
    filter: blur(30px);
    position: absolute;
    height: 100%;
    width: 100%;
    background-size: cover;
  }

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
