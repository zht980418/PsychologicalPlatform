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
          :default-sort="{prop: 'consultno', order: 'ascending'}"
          border
          fit
          highlight-current-row
        >
          <el-table-column
            prop="consultno"
            label="记录编号"
            min-width="180"
            align="center"
          />
          <el-table-column
            prop="doctorname"
            label="咨询师"
            min-width="180"
            align="center"
          />
          <el-table-column
            prop="consulttype"
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
                @click="handleView(scope.$index, scope.row)"
              >查看</el-button>
              <el-button
                type="success"
                icon="el-icon-edit"
                plain
                @click="handleEdit(scope.$index, scope.row)"
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
import {deleteRecordTableByNo, getRecordTableListById} from "@/api/recordTable";

export default {
  name: "viewRecord",
  data() {
    return {
      recordList: [],
      userid: this.$route.params.userid,
      nickname: this.$route.params.nickname,
    }
  },
  created() {
    getRecordTableListById(this.userid).then((res) => {
      if (res.code === 0) {
        console.log(res)
        this.recordList = res.data // 传入咨询室列表
      }
    }).catch((err) => {
      console.log(err)
      this.$notify.error({
        title: '提示',
        message: '网络忙，咨询记录获取失败',
      })
    })
  },
  methods: {
    handleAdd(){
      console.log("add")
      if( this.recordList.length === 0){
        this.$router.push({name:"recordTableFirst", params: { userid: this.$route.params.userid, nickname: this.$route.params.nickname, consultno: -1, type: "add"}})
      }else{
        this.$router.push({name:"recordTableNext", params: { userid: this.$route.params.userid, nickname: this.$route.params.nickname, consultno: -1, type: "add"}})
      }
},
    // 展示编辑dialog
    handleView(index, row) {
      console.log("view")
      this.handleDispatch(index, row, "view")

    },
    handleEdit(index, row) {
      console.log("edit")
      this.handleDispatch(index, row, "edit")
    },
    handleDelete(index, row){
      console.log("delete")
      if (confirm(`你确定要删除该咨询记录吗？`)) {
        deleteRecordTableByNo(row.consultno).then((res) => {
          console.log(res)
          if (res.code === 0) {
            console.log(index)
            this.recordList.splice(index, 1)
            this.$notify.success({
              title: '提示',
              message: '咨询记录删除成功',
            })
          }
        }).catch((err) => {
          console.log(err)
          this.$notify.error({
            title: '提示',
            message: '网络忙，咨询记录删除失败',
          })
        })
      }
    },
    handleDispatch(index, row, routeType){
      console.log(row.consultno)
      if( index === 0){
        this.$router.push({name:"recordTableFirst", params: { userid: this.$route.params.userid, nickname: this.$route.params.nickname, consultno: row.consultno, type: routeType}})
      }else{
        this.$router.push({name:"recordTableNext", params: { userid: this.$route.params.userid, nickname: this.$route.params.nickname, consultno: row.consultno, type: routeType}})
      }
    }
  }
}
</script>

<style scoped>

</style>
