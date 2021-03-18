<template>
  <div class="app-container">
    <el-card class="demo-app">
      <el-row>
        <el-form
          ref="form"
          :model="form"
          label-width="120px"
        >
          <el-form-item label="标 题">
            <el-input v-model="form.title" />
          </el-form-item>
          <el-form-item label="主讲人">
            <el-input v-model="form.teacher" />
          </el-form-item>
          <el-form-item label="课程描述">
            <el-input
              v-model="form.description"
              type="textarea"
            />
          </el-form-item>
          <el-form-item label="视频链接">
            <el-input v-model="form.link" />
          </el-form-item>
          <el-form-item label="课程文件">
            <el-upload
              class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :before-remove="beforeRemove"
              multiple
              :limit="3"
              :on-exceed="handleExceed"
              :file-list="fileList"
            >
              <el-button
                size="small"
                type="primary"
              >点击上传</el-button>
              <div
                slot="tip"
                class="el-upload__tip"
              >只能上传MP4文件，且不超过1000M</div>
            </el-upload>
          </el-form-item>
        </el-form>
      </el-row>
      <div align="center">
        <el-button
          type="primary"
          @click="onSubmit"
        >确定</el-button>
        <el-button @click="$router.back(-1)">取 消</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {},
      fileList: [{ name: 'food.mp4', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100' }, { name: 'food2.mp4', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100' }]
    }
  },
  methods: {
    onSubmit() {
      console.log('on submit')
      
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    }
  }
}
</script>


