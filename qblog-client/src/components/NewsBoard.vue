<template>
  <div class="card-border">
    <el-row  :gutter="10" border="1">
      <!--新闻图片走马灯-->
      <el-col class= "major-card"  :xs="{span:18,offset:1}" :sm="{span:18,offset:3}" :md="{span:16,offset:0}" :lg="{span:13,offset:1}" :xl="{span:14,offset:0}">

        <el-row >
            <el-carousel style="width: 501px; height: 334px;margin:2% 0 0 7%" indicator-position="outside">
              <el-carousel-item  style="width:100%;height:100%;" v-for="item in newsPicture" :key="item.name">
                <div style="width:100%;height:100%;position:absolute" >

                  <img :src="item.url" style="index:-1;position: absolute;width: 100%;height:100%" :alt="item.defaultText">

                  <div style="index:1;position: absolute;margin:35% 0 0 60% ;width: 200px;height: 20px;text-align: center;" class="desc-box">
                    <el-card shadow="hover">
                      <el-link ><h4>{{item.name}}</h4></el-link>
                    </el-card>
                  </div>
                </div>

              </el-carousel-item>
            </el-carousel>

        </el-row>

      </el-col>

<!--      新闻列表-->
      <el-col  class= "major-card"  :xs="{span:20,offset:2}" :sm="{span:22,offset:2}" :md="{span:8,offset:0}" :lg="{span:10,offset:0}" :xl="{span:9,offset:0}">
        <div>
          <el-row :gutter="10" >
            <el-col :span="15" :offset="3" >
              <div style="margin:30px 0;border-bottom: 2px solid #9e9e9e" v-for="item in news" :key="item.day">
                <span style="display:inline-block; text-align:center;background-color:#f1f1f1;padding:10px 1px;margin:0 15px 5px 5px">{{item.year_month}}</span>
                <el-link :href="item.url">{{item.newsTitle}}</el-link>
              </div>
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
        year_month:"2020 12-21",
        newsTitle:"梁应敞教授入选IEEE Xplore封面作者",
        newsAbstract: "newsAbstract",
        url:"https://news.uestc.edu.cn/?n=UestcNews.Front.Document.ArticlePage&Id=78775"
      },
      {
        day:"day2",
        year_month:"2020 12-31",
        newsTitle:"直博深造、热心公益，她在成电遇见更好的自己！",
        newsAbstract: "newsAbstract",
        url:"https://news.uestc.edu.cn/?n=UestcNews.Front.Document.ArticlePage&Id=78822"
      },
      {
        day:"day3",
        year_month:"2021 01-10",
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


<style scoped>
.el-carousel__item h4 {
  font-size: 14px;
  margin: 0;
}

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
