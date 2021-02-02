<template>
  <el-card style="height:100vh;margin:5px;">
    <el-row>
      <el-col :offset="21">
        <el-button
          type="primary"
          icon="el-icon-plus"
          plain
          @click="handleAdd"
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
            <template slot-scope="scope">
              <el-button
                type="primary"
                icon="el-icon-reading"
                plain
                @click="handleView(scope.$index, scope.row)"
              >查看</el-button>
              <el-button
                type="success"
                icon="el-icon-edit"
                plain
                @click="handleEdit(scope.$index, scope.row)"
              >管理</el-button>
              <el-button
                type="danger"
                icon="el-icon-delete"
                plain
                @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button>
            </template>
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
      roomList: [{ id: '0', name: '咨询室1', address: '二教103' }, { id: '1', name: '咨询室2', address: '二教104' }]
    }
  },
  created() {
    // TODO 获取咨询室信息
  },
  methods: {
    // 查看
    handleView(index, row) {
      this.$router.push({ name: 'View', params: { op: this.view, id: row.id, name: row.name, address: row.address } })
    },
    handleAdd() {
      this.roomList.push({ id: this.roomList.length, name: 'newRoom', address: 'newAddress' })
      // TODO 存储
    },
    // 编辑
    handleEdit(index, row) {
      this.$router.push({ name: 'View', params: { op: this.edit, id: row.id, name: row.name, address: row.address } })
    },
    handleDelete(index, row) {
      if (confirm(`你确定要删除该咨询室吗？`)) {
        this.roomList.splice(index, index + 1)
        // TODO 删除咨询室
      }
    }
  }
}
</script>
