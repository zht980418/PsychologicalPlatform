<template>
  <el-card style="height:100vh;margin:5px;">
    <el-row>
      <el-col :offset="21">
        <el-button
          type="primary"
          icon="el-icon-plus"
          plain
        >添加咨询室</el-button>
      </el-col>
    </el-row>
    <br>
    <el-row>
      <el-col>
        <el-table
          :data="roomList"
          element-loading-text="加载中......"
          border
          fit
          highlight-current-row
        >
          <el-table-column
            prop="name"
            label="咨询室名称"
            min-width="180"
            align="center"
          />
          <el-table-column
            prop="address"
            label="咨询室地址"
            min-width="180"
            align="center"
          />
          <el-table-column
            label="操作"
            min-width="180"
            align="center"
          >
            <el-button
              type="primary"
              icon="el-icon-reading"
              plain
              @click="handleView"
            >查看</el-button>
            <el-button
              type="success"
              icon="el-icon-edit"
              plain
              @click="handleEdit"
            >管理</el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              plain
              @click="handleDelete"
            >删除</el-button>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
export default {
  data() {
    return {
      view: false,
      edit: true,
      roomList: [{ name: 'room1', address: '二教103' }, { name: 'room2', address: '二教104' }]
    }
  },
  created() {
    // TODO 获取咨询室信息
  },
  methods: {
    handleView() {
      this.$router.push({ name: 'View', params: { op: this.view } })
    },
    handleEdit() {
      this.$router.push({ name: 'View', params: { op: this.edit } })
    },
    handleDelete() {
      if (confirm(`你确定要删除该咨询室吗？`)) {
        // TODO 删除咨询室
      }
    }
  }
}
</script>

<style>
</style>
