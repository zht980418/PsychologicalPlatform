<template>
  <el-carousel indicator-position="outside" height="650px">
    <el-carousel-item v-for="(item,index) in itemlist" :key="index">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-row type="flex" class="row-bg" justify="space-between">
            <div class="demo-type">
              <el-avatar shape="square" :fit=item.img :size="180" :src= item.img @error="errorHandler"></el-avatar>
            </div>
          </el-row>
          <el-row type="flex" class="row-bg" justify="space-between"><i class="el-icon-user" style="font-size: 20px; color: #606266">{{item.name}}</i></el-row>
          <el-row type="flex" class="row-bg" justify="space-between"><i class="el-icon-phone" style="font-size: 20px; color: #606266">{{item.phonenumber}}</i></el-row>
        </el-col>
        <el-col :span="18">
          <el-row type="flex" class="row-bg" justify="space-around"><i class="el-icon-date" style="font-size: 20px; color: #606266">预约日程表</i></el-row>
          <el-calendar v-model="value" style="font-size: 10px; color: #606266"></el-calendar>
        </el-col>
      </el-row>
    </el-carousel-item>
  </el-carousel>
</template>


<script>
export default {
    data() {
      return {
        itemlist: [
          {name:"张三",phonenumber:"123",img:"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3649178992,1821853682&fm=26&gp=0.jpg"},
          {name:"李四",phonenumber:"456",img:"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3649178992,1821853682&fm=26&gp=0.jpg"},
          {name:"王五",phonenumber:"789",img:"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3649178992,1821853682&fm=26&gp=0.jpg"},
        ],
        value: new Date(),
      }
    },
    methods: {
      errorHandler() {
        return true
    }
    }
  }
</script>

<style>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: EEF1F6;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color:EEF1F6;
  }
  .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: EEF1F6;
  }
</style>