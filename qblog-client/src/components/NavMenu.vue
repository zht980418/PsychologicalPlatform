<template>
    <el-menu  mode="horizontal"   menu-trigger="hover"  router>
      <el-menu-item  index="/">首页</el-menu-item>
      <el-submenu index="2">
        <template  slot="title">心理教育</template>
        <el-menu-item  index="/category/all">分类浏览</el-menu-item>
        <el-menu-item  index="/tag/all">标签浏览</el-menu-item>
        <!-- <el-menu-item index="/timeline">时间线</el-menu-item> -->
      </el-submenu>
      <!--<el-menu-item index="/message">留言板</el-menu-item>-->
      <el-menu-item  index="/quiz">心理自测</el-menu-item>
      <el-menu-item  index="/order">咨询预约</el-menu-item>
    </el-menu>
</template>

<script>
export default {
  name: "NavMenu"
};
</script>

<style>


.el-menu-item{
  padding:5px 30px;
  font-size:18px;

}


.el-submenu__title{
  padding:5px 10px;
  font-size: 18px;


}


</style>
