<template>

  <el-row style="height:180px;">
    <el-col
      style="height:180px;"
      :xs="{span:10,offset:1}"
      :sm="{span:10,offset:1}"
      :md="{span:10,offset:2}"
      :lg="{span:10,offset:2}"
      :xl="{span:10,offset:2}"
    >
      <img
        style="height:65%;min-height:80px;margin-top:30px; vertical-align:middle;"
        :src="img_SCU"
      >
    </el-col>
    <el-col
      :xs="{span:3,offset:9}"
      :sm="{span:3,offset:6}"
      :md="{span:3,offset:6}"
      :lg="{span:3,offset:6}"
      :xl="{span:3,offset:3}"
    >
      <router-link
        v-if="!id"
        to="/login"
      >
        <el-row style="margin:70px 0 0 120px">
          <el-button type="primary">
            <i
              class="el-icon-s-custom"
              style="color:rgb(250,250,250);font-size:2vw"
            ></i>
            <span style="text-align:center;font-size: 1vw;">登陆/注册</span>
          </el-button>
        </el-row>
      </router-link>
      <el-dropdown
        v-if="id"
        class="avatar-container"
      >
        <el-row style="margin:40px 0 0 120px;">
          <img
            style="margin: 11px"
            :src="avatar"
            :alt="name"
            class="user-avatar"
          />
          <i class="el-icon-caret-bottom" />
          <p>{{ id }}</p>
          <p>{{ name }}</p>
        </el-row>
        <el-dropdown-menu
          slot="dropdown"
          class="user-dropdown"
        >
          <router-link to="/">
            <el-dropdown-item>首页</el-dropdown-item>
          </router-link>
          <router-link to="/info">
            <el-dropdown-item>个人中心</el-dropdown-item>
          </router-link>
          <el-dropdown-item
            divided
            @click.native="logout"
          >
            <span style="display:block;">退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-col>
  </el-row>

</template>

<script>
import { mapGetters } from 'vuex'


export default {
  name: "HeaDER",
  data() {
    return {
      img_SCU: require('@/assets/SCU_LOGO.png'),
    }
  },
  computed: {
    ...mapGetters([
      'avatar',
      'token',
      'id',
      'name'
    ])
  },
  methods: {
    async logout() {
      await this.$store.dispatch("user/logout");
      this.$router.push(`/login?redirect=${this.$route.fullPath}`).catch(err => this.$notify.error(err || "路由错误"));
    },
  }
}
</script>

<style scoped>
.user-avatar {
  cursor: pointer;
  width: 60px;
  height: 60px;
  border-radius: 10px;
}

.avatar-container {
  margin-right: 30px;
}
.avatar-wrapper {
  margin-top: 5px;
  position: relative;
}
.user-avatar {
  cursor: pointer;
  width: 40px;
  height: 40px;
  border-radius: 10px;
}

.el-icon-caret-bottom {
  cursor: pointer;
  position: absolute;
  right: -20px;
  top: 25px;
  font-size: 12px;
}
</style>
