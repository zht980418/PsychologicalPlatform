<template>
  <div class="app-container">
    <el-card class="demo-app">
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
            :default-sort="{prop: 'roomId', order: 'ascending'}"
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
      <el-dialog
        :visible.sync="dialogEditVisible"
        title="编辑咨询室信息"
      >
        <el-form
          ref="Room"
          :model="Room"
          :rules="rules"
          :inline="true"
        >
          <el-form-item
            label="咨询室名称"
            prop="name"
          >
            <el-input v-model="Room.name" />
          </el-form-item>
          <el-form-item
            label="咨询室地址"
            prop="address"
          >
            <el-input v-model="Room.address" />
          </el-form-item>
        </el-form>
        <div
          slot="footer"
          class="dialog-footer"
        >
          <el-button @click="dialogEditVisible=false">取消修改</el-button>
          <el-button
            type="primary"
            @click="roomEdit"
          >确认修改</el-button>
        </div>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
import { getRoomList, postRoomInfo, deleteRoomInfoById, updateRoomInfoById } from '@/api/room'

export default {
  data() {
    return {
      dialogEditVisible: false,
      view: false,
      Room: {},
      roomList: [],
      rules: {
        name: [{ required: true, message: '请填写咨询室名称，该项不能为空', trigger: 'blur' }],
        address: [{ required: true, message: '请填写咨询室地址，该项不能为空', trigger: 'blur' }]
      },
    }
  },
  created() {
    // 获取咨询室list
    getRoomList().then((res) => {
      if (res.code === 0) {
        this.roomList = res.data // 传入咨询室列表
      }
    }).catch((err) => {
      console.log(err)
      this.$notify.error({
        title: '提示',
        message: '网络忙，咨询室信息获取失败',
      })
    })
  },
  methods: {
    // 查看
    handleView(row) {
      this.$router.push({ name: 'View', params: { roomId: row.roomId, name: row.name, address: row.address } })
    },
    handleAdd() {
      const params = { roomId: this.roomList[this.roomList.length - 1].roomId + 1, name: 'newRoom', address: 'newAddress' }
      postRoomInfo(params).then((res) => {
        if (res.code === 0) {
          this.roomList.push(params)
          this.$notify.success({
            title: '提示',
            message: '咨询室添加成功',
          })
        }
      }).catch((err) => {
        console.log(err)
        this.$notify.error({
          title: '提示',
          message: '网络忙，咨询室添加失败',
        })
      })
    },

    // 展示编辑dialog
    handleEdit(row) {
      // this.$router.push({ name: 'View', params: { op: this.edit, roomId: row.roomId, name: row.name, address: row.address } })
      this.dialogEditVisible = true
      console.log(row)
      this.Room = JSON.parse(JSON.stringify(row))
    },

    // 编辑咨询室信息
    roomEdit() {
      this.$refs['Room'].validate((valid) => {
        if (valid) {
          updateRoomInfoById(this.Room.roomId, this.Room).then((res) => {
            if (res.code === 0) {
              getRoomList().then((res) => {
                if (res.code === 0) {
                  this.roomList = res.data // 传入咨询室列表
                  this.dialogEditVisible = false
                  this.$notify.success({
                    title: '提示',
                    message: '咨询室信息编辑成功,请刷新页面查看',
                  })
                }
              }).catch((err) => {
                console.log(err)
                this.$notify.error({
                  title: '提示',
                  message: '网络忙，咨询室信息获取失败',
                })
              })
            }
          }).catch((err) => {
            console.log(err)
            this.$notify.error({
              title: '提示',
              message: '网络忙，咨询室信息编辑失败',
            })
          })
        }
      })
    },

    // 删除咨询室信息
    handleDelete(index, row) {
      if (confirm(`你确定要删除该咨询室吗？`)) {
        deleteRoomInfoById(row.roomId).then((res) => {
          if (res.code === 0) {
            this.roomList.splice(index, 1)
            this.$notify.success({
              title: '提示',
              message: '咨询室删除成功',
            })
          }
        }).catch((err) => {
          console.log(err)
          this.$notify.error({
            title: '提示',
            message: '网络忙，咨询室删除失败',
          })
        })
      }
    }
  }
}
</script>
