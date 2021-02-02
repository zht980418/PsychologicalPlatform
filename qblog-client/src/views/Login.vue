<template>
  <el-row>
    <el-row>
      <Header></Header>
    </el-row>

    <el-row>
      <el-col :span="15" :offset="2"><NavMenu></NavMenu></el-col>
    </el-row>

    <el-divider></el-divider>
    <el-row style="height:300px;margin: 100px 0">
      <el-image style="index:-1;width:100%; position: absolute;" :src="img_BG" fit="contain"></el-image>
      <div class="login-container" style="margin:0 40px 1px 65%" >
        <el-form v-if="show_Login" ref="form" :rules="rules" :model="form" label-width="90px" class="login-form">
          <p class="login-title" >登录系统</p>

          <el-form-item label="用户名" prop="username">
            <el-input v-model="form.username"></el-input>
          </el-form-item>

          <el-form-item label="密码" prop="password">
            <el-input v-model="form.password"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" style="margin:0 15px 0 15px" @click="submitForm('form')">登录</el-button>
            <el-button type="primary" @click="switchForm()">注册</el-button>
          </el-form-item>
        </el-form>
        <el-form v-if="!show_Login" ref="form" :rules="rules" :model="form" label-width="90px" class="login-form">
          <p class="login-title" >注册用户</p>

          <el-form-item label="用户名" prop="username">
            <el-input v-model="form.username"></el-input>
          </el-form-item>

          <el-form-item label="密码" prop="password">
            <el-input v-model="form.password"></el-input>
          </el-form-item>

          <el-form-item label="确认密码" prop="password">
            <el-input v-model="form.password"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" style="margin:0 0 0 45px">确定注册</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-row>

    <el-row>
      <Footer></Footer>
    </el-row>
  </el-row>

</template>
<style acoped>
.login-form {
  width: 300px;
  margin:0 0 0 -20px;
  padding: 20px 30px 0 0;
}

/* 背景 */
.login-container {
  position: absolute;
  background-color: rgba(255,255,255,0.8);
  padding:5px;
  border-radius: 10px;
}

/* 标题 */
.login-title {
  color: #303133;
  text-align: center;
  font-size: 20px;
  margin: -10px 0 10px 70px;
}


</style>

<script>
export default {
  components:{
    Footer: ()=>import("@/components/Footer"),
    Header:() => import("@/components/Header"),
    NavMenu:() => import("@/components/NavMenu"),
  },
  data() {
    return {
      show_Login:true,
      img_BG:require('@/assets/bg1.jpg'),
      form: {
        username: "",
        password: ""
      },
      rules: {
        username: [
          {required: true, message: "用户名不能为空", trigger: 'blur'},
          {min: 3, max: 10, message: "用户名3-5位", trigger: 'blur'}
        ],
        password: [
          {required: true, message: "密码不能为空", trigger: 'blur'},
          {min: 3, max: 10, message: "密码3-5位", trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    switchForm(){
      this.show_Login = false;
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        // console.log(valid) 验证通过为true，有一个不通过就是false
        if (valid) {
          // 通过的逻辑
        } else {
          console.log('验证失败');
          return false;
        }
      });
    },
    switch(){

    },
    open() {
      this.$alert('这是一段内容', '标题名称', {
        confirmButtonText: '确定',
        callback: action => {
          this.$message({
            type: 'info',
            message: `action: ${action}`
          });
        }
      });

    }
  }
};
</script>




