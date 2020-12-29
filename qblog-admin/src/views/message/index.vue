<template>
  <div class="app-container">
    <el-alert
      v-if="unreadNum"
      :title="`${unreadNum}条新增留言`"
      type="warning"
      show-icon
    >
    </el-alert>
    <div class="message-box">
      <el-card
        class="message-item"
        v-for="(message, index) in pageInfo.records"
        :key="index"
      >
        <div class="message-info">
          <div class="content">
            <span class="unread-flag" v-if="message.status === '未读'">*</span>
            <span class="nickname">{{ message.nickname }}：</span>
            {{ message.content }}
          </div>
          <div class="email">
            <el-tag type="info" size="mini">{{ message.email }}</el-tag>
          </div>
          <div class="create-time">{{ message.gmtCreate }}</div>
        </div>
        <div class="reply" v-if="message.replyContent">
          <div class="reply-content">
            <span class="admin-name">我的回复：</span>
            {{ message.replyContent }}
          </div>
          <div class="reply-time">{{ message.gmtReply }}</div>
        </div>
        <div class="op">
          <el-button
            type="text"
            size="mini"
            @click="handleDelMessage(message.id)"
            >删除</el-button
          >
          <el-button type="text" size="mini" @click="openDialog(message)"
            >回复</el-button
          >
        </div>
      </el-card>
    </div>
    <el-dialog title="留言回复" :visible.sync="dialogFormVisible">
      <el-input
        type="textarea"
        :rows="5"
        placeholder="请输入要回复的内容内容"
        v-model="replyInfo.content"
      >
      </el-input>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleReply">确 定</el-button>
      </div>
    </el-dialog>
    <el-pagination
      background
      :hide-on-single-page="true"
      @current-change="handleCurrentChange"
      :current-page="pageInfo.current"
      :page-size="pageInfo.size"
      layout="total, prev, pager, next, jumper"
      :total="pageInfo.total"
    ></el-pagination>
  </div>
</template>

<script>
import {
  getMessageList,
  deleteMessageById,
  replyMessageById,
} from "@/api/message";

export default {
  data() {
    return {
      pageInfo: {
        size: 5,
        current: 1,
        total: 0,
        records: [],
      },
      unreadNum: 0,
      dialogFormVisible: false,
      replyInfo: {
        id: null,
        content: "",
      },
    };
  },
  created() {
    this.fetchData(1, 5);
  },
  methods: {
    fetchData(page, limit) {
      getMessageList({ page, limit }).then((res) => {
        this.pageInfo = res.data;
        this.unreadNum = this.pageInfo.records.filter(
          (ele) => ele.status === "未读"
        ).length;
        this.pageInfo.records.forEach((ele) => {
          ele.status === "未读" &&
            replyMessageById(ele.id, {
              status: 1,
            });
        });
      });
    },
    handleCurrentChange(page) {
      this.fetchData(page, 5);
    },
    openDialog(message) {
      this.dialogFormVisible = true;
      this.replyInfo.content = message.replyContent;
      this.replyInfo.id = message.id;
    },
    handleDelMessage(id) {
      this.$confirm("此操作将永久删除该留言, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteMessageById(id).then((res) => {
          this.$notify.success({
            title: "提示",
            message: "留言删除成功",
          });
          this.fetchData(this.pageInfo.current, 5);
        });
      });
    },
    handleReply() {
      replyMessageById(this.replyInfo.id, {
        replyContent: this.replyInfo.content,
      }).then((res) => {
        if (res.code === 0) {
          this.$notify.success({
            title: "提示",
            message: "回复成功",
          });
          this.fetchData(this.pageInfo.current, 5);
        } else {
          this.$notify.error({
            title: "提示",
            message: res.msg,
          });
        }
        this.dialogFormVisible = false;
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.message-box {
  padding: 20px;
}
.message-item {
  border-radius: 0;
  margin-bottom: 10px;
  padding: 10px 0;
}
.message-info {
  display: grid;
  grid-template-columns: 4fr 1fr 1fr;
}
.message-info .nickname {
  color: #409eff;
}
.message-info .unread-flag {
  color: red;
  font-size: 20px;
}
.message-info .unread-flag::after {
  content: " ";
}
.message-info .content {
  font-size: 14px;
  color: #606266;
}
.message-info .create-time {
  color: #909399;
  font-size: 12px;
}
.reply {
  border-radius: 0;
  border-top: 1px #cccccc solid;
  display: grid;
  grid-template-columns: 5fr 1fr;
  margin: 10px 0 0 40px;
  padding-top: 10px;
}
.reply .reply-content {
  font-size: 14px;
  color: #606266;
}
.reply .admin-name {
  color: #409eff;
}
.reply .reply-time {
  color: #909399;
  font-size: 12px;
}
.op {
  float: right;
}
.el-pagination {
  margin-top: 10px;
}
</style>
