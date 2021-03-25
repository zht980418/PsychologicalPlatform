// 悬浮按钮组件
<template>
  <div
    style="position:relative;"
    @click="handleAlert"
  >
    <div
      class="button-box"
      draggable="false"
    >
      <el-button
        type="danger"
        icon="el-icon-phone"
        slot="reference"
        circle
      />
    </div>
  </div>
</template>
<script>
export default {
  methods: {
    // TODO 报警
    handleAlert() {
      this.$notify.warning({
        title: '报警',
        message: '已点击报警按钮'
      })
      console.log("报警警")
    },
  }
}
</script>

<style scoped>
.button-box {
  width: 80px;
  border-radius: 50%;
  position: fixed;
  top: 90vh;
  left: 90vw;
  padding-left: 15px;
  padding-top: 8px;
  cursor: pointer;
  opacity: 0.7;
  z-index: 888;
}
.btn-bg-img {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-image: url("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=886037056,2311123780&fm=26&gp=0.jpg");
  background-size: cover;
}
.button-box:hover {
  color: white;
  opacity: 1;
}

.font-box {
  width: 80px;
  color: #3193ef;
  text-align: center;
}

#exampage {
  top: 100px;
  left: 0;
  right: 0;
  margin: 0 auto;
  width: 400px;
}

.voice-input-button-wrapper {
  width: 42px;
  height: 42px;
  background-color: mediumpurple;
  border-radius: 50%;
}

.result {
  width: 100%;
  padding: 25px;
  border: #e2e2e2 1px solid;
  border-radius: 5px;
  line-height: 2;
  font-size: 16px;
  color: #727272;
  min-height: 24px;
  margin-bottom: 25px;
}
</style>
