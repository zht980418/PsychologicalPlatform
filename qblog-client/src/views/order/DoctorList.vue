<template>
<<<<<<< HEAD
  <el-row style="height:90vh;">
    <el-col
      :span="6"
      v-for="(item, index) in doctorlist"
      :key="item.name"
      :offset="index > 0 ? 2 : 0"
    >
      <el-card :body-style="{ padding: '0px' }">
        <el-image
          style="width: 100px; height: 100px"
          :src="item.img"
        ></el-image>
        <div style="padding: 14px;">
          <span>{{item.name}}</span>
          <tr>{{item.timelist}}</tr>
          <div class="bottom clearfix">
            <el-button
              type="text"
              class="button"
              @click="goToPage(index)"
            >点击预约</el-button>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>

<style>
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}
</style>

<script>
export default {
  data() {
    return {
      currentDate: new Date(),
      doctorlist: [
        {
          name: "张三",
          timelist: ["09:00:00", "14:00:00", "16:00:00"],
          img: "https://img1.dxycdn.com/2020/0206/245/3395151617954823081-22.jpg!wh200"
        },
        {
          name: "李四",
          timelist: ["09:00:00", "14:00:00", "16:00:00"],
          img: "https://img1.dxycdn.com/2020/0206/245/3395151617954823081-22.jpg!wh200"
        },
        {
          name: "王五",
          timelist: ["09:00:00", "14:00:00", "16:00:00"],
          img: "https://img1.dxycdn.com/2020/0206/245/3395151617954823081-22.jpg!wh200"
        },
      ],
    };
  },
  methods: {
    goToPage(index) {
      console.log(this.doctorlist[index].name)
      this.$router.push(
        {
          name: "ordertable",
          params:
          {
            doctorname: this.doctorlist[index].name,
            timelist: this.doctorlist[index].timelist,
          }
        })
    }
  }
}
</script>
=======
  <el-carousel indicator-position="outside" height="650px" :autoplay="false">
    <el-carousel-item v-for="(item,index) in itemlist" :key="index">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-row type="flex" class="row-bg" justify="space-between">
            <div class="demo-type">
              <el-avatar shape="square" :fit=item.img :size="180" :src= item.img @error="errorHandler"></el-avatar>
            </div>
          </el-row>
          <el-row type="flex" class="row-bg" justify="space-between"><i class="el-icon-user" style="font-size: 20px; color: #606266">{{item.name}}</i></el-row>
          <el-row type="flex" class="row-bg" justify="space-between"><i class="el-icon-phone" style="font-size: 20px; color: #606266">{{item.phonenumber}}</i></el-row>
        </el-col>
        <el-col :span="18">
          <el-row type="flex" class="row-bg" justify="space-around"><i class="el-icon-date" style="font-size: 20px; color: #606266">预约日程表</i></el-row>
          <el-calendar v-model="value" style="font-size: 10px; color: #606266">
            <template
            slot="dateCell"
            slot-scope="{date, data}"
            @click="test"
          >
            <p :class="data.isSelected ? 'is-selected' : ''">
              {{ data.day.split('-').slice(1).join('-') }} {{ data.isSelected ? '✔️' : ''}}
            </p>
            <el-button @click="dialogTableVisible = true">预约信息</el-button>
          </template>
          </el-calendar>
          <el-dialog
          title="预约信息"
          :visible.sync="dialogTableVisible"
        >
          <el-table :data="gridData">
            <el-table-column
              property="name"
              label="起始时间"
              width="200"
            ></el-table-column>
            <el-table-column
              property="address"
              label="终止时间"
            ></el-table-column>
          </el-table>
          <el-button @click="dialogFormVisible = true">新增预约</el-button>
        </el-dialog>

        <!-- Form -->
        <el-dialog
          title="咨询预约"
          :visible.sync="dialogFormVisible"
        >
          <el-form :model="form">
            <el-form-item
              label="时间选择"
              :label-width="formLabelWidth"
            >
              <template>
                <el-time-select
                  placeholder="起始时间"
                  v-model="form.startTime"
                  :picker-options="{
      start: '09:30',
      step: '01:00',
      end: '18:00'
    }"
                >
                </el-time-select>
              </template>
            </el-form-item>
            <el-form-item
              label="咨询方式"
              :label-width="formLabelWidth"
            >
              <el-select
                v-model="form.type"
                placeholder="请选择咨询方式"
              >
                <el-option
                  label="线上"
                  value="online"
                ></el-option>
                <el-option
                  label="线下"
                  value="offline"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item
              label="咨询主题"
              :label-width="formLabelWidth"
              v-model="form.theme"
            >
              <el-input></el-input>
            </el-form-item>
            <el-form-item
              label="姓名"
              :label-width="formLabelWidth"
              v-model="form.name"
            >
              <el-input></el-input>
            </el-form-item>
            <el-form-item
              label="联系方式"
              :label-width="formLabelWidth"
              v-model="form.phone"
            >
              <el-input></el-input>
            </el-form-item>
          </el-form>
          <div
            slot="footer"
            class="dialog-footer"
          >
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button
              type="primary"
              @click="handleConfirm"
            >确 定</el-button>
          </div>
        </el-dialog>
        </el-col>
      </el-row>
    </el-carousel-item>
  </el-carousel>
</template>


<script>
export default {
    data() {
      return {
        itemlist: [
          {name:"张三",phonenumber:"123",img:"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3649178992,1821853682&fm=26&gp=0.jpg"},
          {name:"李四",phonenumber:"456",img:"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3649178992,1821853682&fm=26&gp=0.jpg"},
          {name:"王五",phonenumber:"789",img:"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3649178992,1821853682&fm=26&gp=0.jpg"},
        ],
        value: new Date(),
        gridData: [{
        date: '2016-05-02',
        name: '9:30',
        address: '10:30'
      }, {
        date: '2016-05-04',
        name: '10:30',
        address: '11:30'
      }, {
        date: '2016-05-01',
        name: '13:30',
        address: '14:30'
      }, {
        date: '2016-05-03',
        name: '14:30',
        address: '15:30'
      }],
      dialogTableVisible: false,
      dialogFormVisible: false,
      form: {
        startTime: '',
        type: '',
        theme: '',
        name: '',
        phone: ''
      },
      formLabelWidth: '120px',
      }
    },
    methods: {
      errorHandler() {
        return true
    },
    handleConfirm() {
      this.dialogFormVisible = false
      this.gridData.some((item, i) => {
            if (item.name == this.form.startTime) {
              this.gridData.splice(i, 1)
              // 在 数组的 some 方法中，如果 return true，就会立即终止这个数组的后续循环
              return true;
            }
          })
    }
    }
  }
</script>

<style>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: EEF1F6;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color:EEF1F6;
  }
  .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: EEF1F6;
  }
</style>
>>>>>>> e1973d45abfd287785a4e3b0caa7a2fbdd1feb01
