<template>
    <div>
        <!--    最上端走马灯含标题-->
        <el-carousel indicator-position="none"
                     height="400px"
                     arrow="nerver"
                     :interval="5000">
            <el-carousel-item v-for="item in blogInfo.covers" :key="item">
                <div class="item-box">
                    <img :src="item" class="carimg">
                    <div class="desc-box">
                        <h1>{{ blogInfo.title }}</h1>
                        <p>{{ blogInfo.desc }}</p>
                    </div>
                </div>
            </el-carousel-item>
        </el-carousel>
        <el-row :gutter="20">
            <el-col :span="17" :offset="1">
                <el-alert v-if="!pageInfo.records || !pageInfo.records.length"
                          class="none-article"
                          title="目前博主还没有发布文章，敬请期待 ~ "
                          type="success"
                          :closable="false">
                </el-alert>
                <el-row :gutter="20">
                    <el-col :span="21" :offset="2"><el-divider content-position="left"><h1><i class="el-icon-reading"></i>新闻导读</h1></el-divider></el-col>
                </el-row>
                <NewsBoard></NewsBoard>
                <el-row :gutter="20">
                    <el-col :span="21" :offset="2"><el-divider content-position="left"><h1><i class="el-icon-guide"></i>功能导航</h1></el-divider></el-col>
                </el-row>
                <MajorFunction></MajorFunction>
                <!--        文章板块(未使用)-->
                <!--<article-item v-for="article in pageInfo.records"
                              :key="article.id"
                              :article="article"></article-item>-->
                        <!--文章分页-->
                <!--<el-pagination background
                               @current-change="handleCurrentChange"
                               :current-page.sync="pageInfo.current"
                               :page-size="pageInfo.size"
                               layout="prev, pager, next, jumper"
                               :total="pageInfo.total"
                               :hide-on-single-page="true"></el-pagination>-->
            </el-col>
            <!--      个人信息面板-->
            <el-col :span="4">
                <blog-info></blog-info>
            </el-col>
        </el-row>
        <el-row :gutter="0">
      <el-col :span="24" :offset="0" >
        <div  style = "width:100%;height:120px;border:4px;margin:50px 5px 5px 5px;background-color:#F5F5F5;box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);">
          <div style = "text-align: center">
            <span style="font-size:20px;font-weight:800;color:#005778;margin:30px 0 0 25px; display:inline-block">
                    &copy; UESTC-102
                  </span>
            <span style="font-size:20px;font-weight:1000;color:white; background-color:#008e97;margin:15px 0 0 15px;padding: 0 5px 0 5px;border-radius: 3px; display:inline-block">
                    fakesite.com
                  </span>
            <span style=" font:14px bold;color:#343434; display: block;margin:10px;">
              联系我们：61830677
            </span>
          </div>
        </div>
      </el-col>
    </el-row>
    </div>
</template>

<script>
    import request from "@/http/request";
    import { mapState } from "vuex";

    export default {
        name: "Home",
        data() {
            return {
                pageInfo: {},
            };
        },
        computed: mapState(["blogInfo"]),
        components: {
            BlogInfo: () => import("@/components/BlogInfo.vue"),
            //ArticleItem: () => import("@/components/ArticleItem.vue"),
            MajorFunction: () => import("@/components/MajorFunction.vue"),
            NewsBoard: () => import("@/components/NewsBoard.vue")
        },
        created() {
            this.getPageArticles(1, 5);
        },
        methods: {
            handleCurrentChange(page) {
                this.getPageArticles(page, 5);
            },
            getPageArticles(page, limit) {
                request
                    .getArticles(page, limit)
                    .then((res) => {
                        if (res.code === 0) {
                            this.pageInfo = res.data;
                        } else {
                            this.$notify.error({
                                title: "提示",
                                message: res.msg,
                            });
                        }
                    })
                    .catch((err) => {
                        console.log(err);
                        this.$notify.error({
                            title: "提示",
                            message: "网络忙，文章获取失败",
                        });
                    });
            },
        },
    };
</script>

<style scoped>
    .none-article {
        margin-top: 20px;
    }

    .item-box {
        position: relative;
        width: 100%;
        height: 100%;
    }

    .carimg {
        width: 100%;
        height: 100%;
        overflow: hidden;
        object-fit: cover;
    }

    .desc-box {
        position: absolute;
        bottom: 0;
        left: 50%;
        top: 30%;
        width: 800px;
        height: 40px;
        margin-left: -400px;
        text-align: center;
    }

        .desc-box h1 {
            font-size: 40px;
            color: #ffffff;
            font-weight: normal;
        }

        .desc-box p {
            font-size: 25px;
            margin-top: 30px;
            color: #ffffff;
        }

    .el-pagination {
        margin: 20px 0;
        text-align: center;
    }
</style>
