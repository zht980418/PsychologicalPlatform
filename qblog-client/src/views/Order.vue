<template>
  <el-row>
    <el-row>
      <Header></Header>
      <el-divider></el-divider>
    </el-row>

    <el-row>
      <el-col :span="15" :offset="3"><NavMenu></NavMenu></el-col>
    </el-row>

    <el-row><el-container style="height: 800px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu  router>
          <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
            {{ item.navItem }}
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view ></router-view>
      </el-main>
    </el-container></el-row>
    <el-row>
      <Footer></Footer>
    </el-row>

  </el-row>
</template>

<script>
  export default {
    components:{
      Footer: ()=>import("@/components/Footer"),
      Header:() => import("@/components/Header"),
      NavMenu:() => import("@/components/NavMenu"),
    },
    data() {
      return {
        navList:[
            {name:'/order/doctorlist',navItem:'心理咨询师'},
            {name:'/order/orderform',navItem:'心理咨询'},
        ]
      }
    }
  };
</script>

<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>
