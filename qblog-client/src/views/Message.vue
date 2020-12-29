<template>
  <el-row>
    <el-col :span="14" :offset="5">
      <el-card>
        <div slot="header">
          <h4>留言互动</h4>
          <br />
          务必填写有效的邮箱地址，否则不会收到回复信息的哦 ~
        </div>
        <el-form ref="form" :model="message" :rules="rules" label-width="80px">
          <el-form-item label="昵称" prop="nickname">
            <el-input v-model="message.nickname"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input type="email" v-model="message.email"></el-input>
          </el-form-item>
          <el-form-item label="内容" prop="content">
            <el-input
              type="textarea"
              v-model="message.content"
              rows="6"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitMessage">提交</el-button>
            <el-button @click="resetMessage">重置</el-button>
          </el-form-item>
        </el-form>
        <div v-for="(message, index) in pageInfo.records" :key="index">
          <el-divider></el-divider>
          <div class="info-box">
            <div class="avatar" :style="'background-color:#' + message.bgColor">
              {{ message.nickname[0] }}
            </div>
            <div class="nickname">{{ message.nickname }}</div>
            <div class="time">{{ message.gmtCreate }}</div>
          </div>
          <div class="content">{{ message.content }}</div>
          <div class="reply-box" v-if="message.replyContent">
            <div class="reply-content">
              <span class="author-text">作者回复：</span>
              {{ message.replyContent }}
            </div>
            <div class="reply-time">{{ message.gmtReply }}</div>
          </div>
        </div>
        <el-pagination
          background
          @current-change="handleCurrentChange"
          :current-page.sync="pageInfo.current"
          :page-size="pageInfo.size"
          layout="prev, pager, next, jumper"
          :total="pageInfo.total"
          :hide-on-single-page="true"
        ></el-pagination>
      </el-card>
    </el-col>
    <el-backtop></el-backtop>
  </el-row>
</template>

<script>
import request from "@/http/request";
export default {
  name: "Message",
  data() {
    return {
      message: {
        nickname: "",
        email: "",
        content: "",
      },
      rules: {
        nickname: [
          { required: true, message: "请输入您的昵称", trigger: "blur" },
        ],
        email: [
          { required: true, message: "请输入您的邮箱地址", trigger: "blur" },
        ],
        content: [
          { required: true, message: "请输入您的留言内容", trigger: "blur" },
        ],
      },
      pageInfo: {},
    };
  },
  methods: {
    submitMessage() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          request
            .postMessages(this.message)
            .then((res) => {
              if (res.code === 0) {
                this.$notify.success({
                  title: "提示",
                  message: "提交成功！",
                });
                let page = this.pageInfo.current;
                let size = this.pageInfo.size;
                this.getPageMessages(page, size);
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
                message: "网络忙，留言提交失败",
              });
            });
          this.resetMessage();
        } else {
          this.$notify.warning({
            title: "提示",
            message: "请填写完整信息再提交哦",
          });
        }
      });
    },
    resetMessage() {
      this.message = {
        nickname: "",
        email: "",
        content: "",
      };
    },
    getPageMessages(page, limit) {
      request
        .getMessages(page, limit)
        .then((res) => {
          if (res.code === 0) {
            this.pageInfo = res.data;
            this.pageInfo.records.forEach(
              (ele) =>
                (ele.bgColor = Math.random()
                  .toString(16)
                  .substr(2, 6)
                  .toUpperCase())
            );
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
            message: "网络忙，留言获取失败",
          });
        });
    },
    handleCurrentChange(page) {
      this.getPageMessages(page, 5);
    },
  },
  created() {
    this.getPageMessages(1, 5);
  },
};
</script>

<style scoped>
.el-card {
  margin: 15px 0;
  padding: 20px;
}
.info-box {
  display: grid;
  grid-template-columns: 60px 5fr 1fr;
}
.avatar {
  width: 40px;
  height: 40px;
  line-height: 40px;
  border-radius: 50%;
  color: #ffffff;
  font-size: 20px;
  font-weight: bold;
  text-align: center;
}
.nickname {
  height: 40px;
  line-height: 40px;
  color: #409eff;
  font-weight: bold;
}
.time {
  font-size: 12px;
  height: 40px;
  line-height: 40px;
  color: #909399;
}
.content {
  margin: 10px 0 0 40px;
  color: #606266;
  font-size: 14px;
}
.reply-box {
  margin: 10px 0 0 40px;
  padding-top: 10px;
  border-radius: 0;
  border-top: 1px #cccccc solid;
  display: grid;
  grid-template-columns: 5fr 1fr;
}
.reply-box .author-text {
  color: #409eff;
}
.reply-box .reply-content {
  color: #606266;
  font-size: 14px;
}
.reply-box .reply-time {
  font-size: 12px;
  color: #909399;
}
.el-pagination {
  margin-top: 15px;
  text-align: center;
}
</style>