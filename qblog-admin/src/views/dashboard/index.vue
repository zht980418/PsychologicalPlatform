<template>
  <div class="dashboard">
    <el-row class="header" :gutter="20">
      <el-col :span="6">
        <el-card>
          <div class="item">
            <svg-icon class="icon" icon-class="documentation" />
            <div class="text">文章数: {{blogInfo.articleCount}}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div class="item">
            <svg-icon class="icon" icon-class="eye-open" />
            <div class="text">浏览量：{{blogInfo.totalViews}}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div class="item">
            <svg-icon class="icon" icon-class="documentation" />
            <div class="text">文章数: {{blogInfo.articleCount}}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div class="item">
            <svg-icon class="icon" icon-class="eye-open" />
            <div class="text">浏览量：{{blogInfo.totalViews}}</div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row class="chart-container">
      <el-col :span="22" :offset="1">
        <v-chart class="chart" :options="chartOptions" theme="macarons" />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { getBlogInfo, getArticlesChartData } from "@/api/setting";
import ECharts from "vue-echarts";
import "echarts/lib/chart/line";
import "echarts/lib/component/polar";
import "echarts/lib/component/tooltip";
import "echarts/lib/component/toolbox";
import "echarts/lib/component/markPoint";
import "echarts/lib/component/markLine";
import "echarts/lib/component/title";
import "echarts/lib/component/legend";
import "echarts/lib/component/dataZoom";

let chartOptionsData = {
  title: {
    text: "发布文章数统计",
  },
  toolbox: {
    show: true,
    feature: {
      dataView: {
        show: true,
      },
      restore: {
        show: true,
      },
      dataZoom: {
        show: true,
      },
      saveAsImage: {
        show: true,
      },
      magicType: {
        type: ["line", "bar"],
      },
    },
  },
  tooltip: {
    trigger: "axis",
  },
  legend: {
    data: ["文章数"],
  },
  xAxis: {
    data: [],
  },
  yAxis: {},
  dataZoom: [
    {
      type: "slider",
      start: 0,
      end: 50,
    },
  ],
  series: [
    {
      name: "文章数",
      type: "line",
      data: [],
      label: {
        normal: {
          show: true,
          position: "top",
        },
      },
      markPoint: {
        data: [
          { type: "max", name: "最大值" },
          { type: "min", name: "最小值" },
        ],
      },
      markLine: {
        data: [{ type: "average", name: "平均值" }],
      },
    },
  ],
  animationDuration: 2000,
};

export default {
  name: "Dashboard",
  computed: {
    ...mapGetters(["name"]),
  },
  data() {
    return {
      blogInfo: {},
      chartOptions: {},
    };
  },
  components: {
    "v-chart": ECharts,
  },
  methods: {},
  created() {
    getBlogInfo().then((res) => {
      this.blogInfo = res.data;
    });
    getArticlesChartData().then((res) => {
      res = res.data;
      chartOptionsData.xAxis.data = res.xaxisData;
      chartOptionsData.series[0].data = res.yaxisData;
      let len = res.xaxisData.length;
      chartOptionsData.dataZoom[0].end = len < 10 ? 100 : 1000 / len;
      this.chartOptions = chartOptionsData;
    });
  },
};
</script>

<style lang="scss" scoped>
.dashboard {
  .header {
    .el-card {
      margin: 20px 10px;
      height: 100px;
      text-align: center;
      .item {
        display: flex;
        justify-content: space-around;
        .icon {
          width: 60px;
          height: 60px;
          color: #409eff;
        }
        .text {
          line-height: 60px;
          color: #303133;
          font-size: 18px;
        }
      }
    }
  }
  .chart-container {
    .chart {
      width: 100%;
      height: 400px;
    }
  }
}
</style>
