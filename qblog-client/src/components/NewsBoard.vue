<template>
  <div class="card-border" >
    <el-row :gutter="10" border="1">
<!--      新闻列表-->
      <el-col class= "major-card" :xs="{span:9,offset:0}" :sm="{span:11,offset:2}" :md="{span:9,offset:0}">
        <div>
          <el-row :gutter="10" >
            <el-col :span="18" :offset="3" >
              <div v-for="item in news" :key="item.day">
              <div class="newsdata text-center">{{ item.day }}<br><span style="font-size: 14px">{{ item.year_month }}</span></div>
              <div class="newstitle">
                <h4>{{ item.newsTitle }}</h4>
                <p> {{ item.newsAbstract }}</p>
              </div><br><el-divider></el-divider><br>
              </div>
            </el-col>

          </el-row>
        </div>
      </el-col>
<!--新闻图片走马灯-->
      <el-col class= "major-card"  :xs="{span:15,offset:0}" :sm="{span:10,offset:1}" :md="{span:13,offset:1}">
        <div>
          <el-row :gutter="10" type="flex" class="row-bg">
            <el-col :span="24" :offset="0">
              <template>
                <el-carousel indicator-position="outside">
                  <el-carousel-item v-for="item in newsPicture" :key="item.name">
                    <div style="margin: 0 0 0 30px"><img :src="item.url" :alt="item.defaultText"></div>
                  </el-carousel-item>
                </el-carousel>
              </template>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>
  </div>



</template>

<script>
export default {
  name: "NewBoard",
  data() {
    return {
    news:[
      {
        day:"day1",
        year_month:"year_month",
        newsTitle:"newsTitle",
        newsAbstract:"newsAbstract"
      },
      {
        day:"day2",
        year_month:"year_month",
        newsTitle:"newsTitle",
        newsAbstract:"newsAbstract"
      },
      {
        day:"day3",
        year_month:"year_month",
        newsTitle:"newsTitle",
        newsAbstract:"newsAbstract"
      }
      ],
      newsPicture:[
        {
          name:"name1",
          url:"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2035754118,1006010344&fm=26&gp=0.jpg",
          defaultText:"Error！"
        },
        {
          name:"name2",
          url:"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2035754118,1006010344&fm=26&gp=0.jpg"
        },
        {
          name:"name3",
          url:"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2035754118,1006010344&fm=26&gp=0.jpg",
          defaultText:"Error！"
        }

      ]
    }
  }
}
</script>
<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}
</style>
<style scoped>

.newsdata {
  float: left;
  width: 80px;
  border-right: 1px solid #d5d4d4;
  color: #fff;
  font-size: 16px;
  padding: 5px 1px;
  margin: 5px;
  background: #409EFF;
}
.text-center {
  text-align: center;
}
</style>
