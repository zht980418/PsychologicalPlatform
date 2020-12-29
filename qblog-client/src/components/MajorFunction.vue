<template>
    <div>
        <div class="card-border">
            <el-row :gutter="10" border="1">
                <!--        纵向展示面板-->
                <el-col class="major-card" :xs="{span:24,offset:0}" :sm="{span:11,offset:2}" :md="{span:6,offset:1}">
                    <!--          标题、标签、连接-->
                    <div>
                        <el-row :gutter="10">
                            <el-col :span="18" :offset="0">
                                <div class="card-title">
                                    <h4 class="t3"><span class="extra-large-text">心理健康中心</span></h4>
                                    <span style="font-size: 16px;color:#9e9e9e">Teenagers mental health</span><el-divider></el-divider>
                                    <el-link type="primary"> 专家咨询 <i class="el-icon-phone"></i></el-link>
                                </div>
                            </el-col>
                        </el-row>
                        <!--            图片-->
                        <el-row :gutter="10">
                            <el-col :span="18" :offset="0">
                                <div class="card-image" >
                                    <img src="http://www.ksruogu.com/images/20200215172052_911.png" :fit="fit" class="image" />
                                </div>
                            </el-col>
                        </el-row>
                    </div>
                </el-col>

                <!--        主要功能标签面板-->
                <el-col class="major-card" :xs="{span:15,offset:0}" :sm="{span:10,offset:1}" :md="{span:15,offset:2}">
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
                                        <el-tab-pane label="主题论坛" name="third">
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
        </div>

    </div>

</template>


<script>
    export default {
        name: "MajorFunction",
        components: {
            ForumTable: () => import("@/components/ForumTable"),
            EduMaterial: () => import("@/components/EduMaterial"),
            Counsel: () => import("@/components/Counsel"),
            OnlineExam: () => import("@/components/OnlineExam")

        },
        data() {
            return {
                activeName: 'first',
                isChildUpdate1: true,

            };
        },
        methods: {
            handleClick(tab, event) {
                console.log(tab);
                console.log(event)
            }
        }

    };
</script>

<style>
    .card-image {
        width: 220px;
        height: 100px;
        border: 3px solid black;
        background-color: orange;
        background-size: 100% 100%;
        margin: 40px 0 50px 20px;
    }

    .card-title {
        margin: 0 20px 0 0;
        display: inline-block;
        height: 40px;
        line-height: 40px;
        font-size: 20px;
    }

    .card-border {
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        width: 85%;
        padding: 20px;
        margin: 10px 0 10px 10%;
    }

    el-col {
        border-radius: 4px;
    }

    .bg-purple-dark {
        background: #99a9bf;
        height: 180px;
        margin: 20px;
    }

    .bg-purple {
        background: #d3dce6;
        height: 60px;
        margin: 20px;
    }

    .bg-purple-light {
        background: #e5e9f2;
        height: 370px;
        margin: 40px;
    }

    .grid-content {
        border-radius: 4px;
        min-height: 36px;
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
        height: 100%;
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
