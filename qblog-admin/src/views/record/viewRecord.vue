<template>
  <div class="app-container">
    <el-row>
      <el-col :offset="2">
        {{ userid + '   ' }}{{ nickname }}
      </el-col>
      <el-col :offset="21">
        <el-button
          type="primary"
          icon="el-icon-plus"
          plain
          @click="handleAdd()"
        >添加咨询记录</el-button>
      </el-col>
    </el-row>
    <br>
    <el-row>
      <el-col>
        <el-table
          :data="recordList"
          element-loading-text="加载中......"
          :default-sort="{prop: 'recordNo', order: 'ascending'}"
          border
          fit
          highlight-current-row
        >
          <el-table-column
            prop="consultNo"
            label="记录编号"
            min-width="180"
            align="center"
          />
          <el-table-column
            prop="doctorName"
            label="咨询师"
            min-width="180"
            align="center"
          />
          <el-table-column
            prop="consultType"
            label="咨询类型"
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
                icon="el-icon-edit"
                plain
                @click="handleView(scope.row)"
              >查看</el-button>
              <el-button
                type="success"
                icon="el-icon-edit"
                plain
                @click="handleEdit(scope.row)"
              >编辑</el-button>
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
  </div>
</template>

<script>


export default {
  name: "viewRecord",
  data() {
    return {
      recordList: [{consultNo: "0000001", doctorName: '鸿鸟', consultType:'线下'}, {consultNo: "0000002", doctorName: '鸿鸟', consultType:'线下'}],
      userid: this.$route.params.userid,
      nickname: this.$route.params.nickname,
    }
  },
  created() {

  },
  methods: {
    handleAdd(){
      console.log("add")
      this.$router.push({name:"recordTable", params: { userid: this.$route.params.userid}})
    },
    // 展示编辑dialog
    handleView(row) {
      console.log("view")
      this.$router.push({name:"recordTable", params: { consultNo: row.consultNo, type: "view"}})
    },
    handleEdit(row) {
      console.log("edit")
      this.$router.push({name:"recordTable", params: { consultNo: row.consultNo, type: "edit"}})
    },
    handleDelete(index, row){
      console.log("delete")
    }
  }
}
</script>

<style scoped>

</style>
