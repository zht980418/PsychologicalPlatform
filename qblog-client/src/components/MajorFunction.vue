<template>

    <el-row class="card-border" style="height: 500px;">
        <el-col  class= "major-card"  :span="6" :offset="0">
          <div style="height:300px;width:70%;border:3px solid black;margin:80px 0 0 50px">这里放一张logo</div>
        </el-col>
<!--        主要功能标签面板-->
        <el-col class= "major-card"  :span="16" :offset="2">
          <div>
            <el-row :gutter="10" type="flex" class="row-bg">
              <el-col :span="24" :offset="0">
                <template>
                  <el-tabs v-model="activeName" @tab-click="handleClick">
                    <el-tab-pane label="心理教育" name="first">
                      <EduMaterial></EduMaterial>
                    </el-tab-pane>
                    <el-tab-pane label="预约咨询" name="second">
                      <Counsel></Counsel>
                    </el-tab-pane>
                    <el-tab-pane label="活动发布" name="third">
                      <ForumTable></ForumTable>
                    </el-tab-pane>
                    <el-tab-pane label="在线自测" name="fourth">
                      <OnlineExam></OnlineExam>
                    </el-tab-pane>
                  </el-tabs>
                </template>
              </el-col>
            </el-row>
          </div>
        </el-col>
    </el-row>

</template>


<script>
export default {
  name: "MajorFunction",
  components: {
    ForumTable : () => import("@/components/ForumTable"),
    // EduMaterial : () => import("@/components/EduMaterial"),
    EduMaterial : () => import("@/components/TimeLine"),
    Counsel: () => import("@/components/Counsel"),
    OnlineExam: () => import("@/components/OnlineExam"),


  },
  data() {
    return {
      activeName: 'first',
      isChildUpdate1:true,

    };
  },
  methods: {
    handleClick(tab,event) {
      console.log(tab);
      console.log(event)
      }
  }

};
</script>

<style>

.card-border{
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  width:100%;
  padding:15px;
  margin-top: 20px;

}

 el-col {
  border-radius: 4px;
}


</style>

<style scoped>
.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
</style>
