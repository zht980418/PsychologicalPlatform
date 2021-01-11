<template>
  <div class="card-border" >
    <el-row :gutter="10" border="1">
<!--      新闻列表-->
      <el-col class= "major-card" :xs="{span:9,offset:0}" :sm="{span:11,offset:2}" :md="{span:9,offset:0}">
        <div>
          <el-row :gutter="10" >
            <el-col :span="18" :offset="3" >
              <div v-for="item in news" :key="item.day">
                <h4>{{item.year_month}}</h4>
                <el-link :href="item.url">{{item.newsTitle}}</el-link>
              <br><el-divider></el-divider><br>
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
                    <div style="margin: 0 0 0 30px">
                      <img :src="item.url" style="width: 450px; height: 300px" :alt="item.defaultText">
                      <div class="desc-box">
                        <el-card shadow="hover">
                          <el-link ><h4>{{item.name}}</h4></el-link>
                        </el-card>
                      </div>
                      </div>
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
        year_month:"2020/12/21",
        newsTitle:"梁应敞教授入选IEEE Xplore封面作者",
        newsAbstract: "newsAbstract",
        url:"https://news.uestc.edu.cn/?n=UestcNews.Front.Document.ArticlePage&Id=78775"
      },
      {
        day:"day2",
        year_month:"2020/12/31",
        newsTitle:"直博深造、热心公益，她在成电遇见更好的自己！",
        newsAbstract: "newsAbstract",
        url:"https://news.uestc.edu.cn/?n=UestcNews.Front.Document.ArticlePage&Id=78822"
      },
      {
        day:"day3",
        year_month:"2021/1/10",
        newsTitle:"新尚集团董事长唐立新续捐2250万元",
        newsAbstract: "newsAbstract",
        url:"https://news.uestc.edu.cn/?n=UestcNews.Front.Document.ArticlePage&Id=78768"
      }
      ],
      newsPicture:[
        {
          name:"梁应敞教授入选IEEE Xplore封面作者",
          url:"https://news.uestc.edu.cn/upload/image/e57430eb22f17674aff2de31b1b16fa2.png",
          defaultText:"Error！"
        },
        {
          name:"直博深造、热心公益，成电遇见更好的自己！",
          url:"https://news.uestc.edu.cn/upload/image/e80e50b6528c1394c139ce36333fc36e.jpg"
        },
        {
          name:"新尚集团董事长唐立新续捐2250万元",
          url:"https://news.uestc.edu.cn/upload/image/a7d46747096338d9447b933d39a3074b.jpg",
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
.desc-box {
        position: absolute;
        bottom: 0;
        left: 80%;
        top: 200px;
        width: 200px;
        height: 20px;
        margin-left: -400px;
        text-align: center;
    }
</style>
