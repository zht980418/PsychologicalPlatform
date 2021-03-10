<template>
  <div class="app-container">
    <el-card class="demo-app">
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
      </el-form>
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
      form: {}
    }
  },
  methods: {
    onSubmit() {

    },

  }
}

</script>