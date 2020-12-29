<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading"
      :data="pageInfo.records"
      element-loading-text="加载中......"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="标题">
        <template slot-scope="scope">{{ scope.row.title }}</template>
      </el-table-column>
      <el-table-column label="作者" width="90" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.author }}</span>
        </template>
      </el-table-column>
      <el-table-column label="类型" width="50" align="center">
        <template slot-scope="scope">{{ scope.row.type }}</template>
      </el-table-column>
      <el-table-column label="分类" width="110" align="center">
        <template slot-scope="scope">{{ scope.row.category }}</template>
      </el-table-column>
      <el-table-column class-name="status-col" label="标签" width="150" align="center">
        <template slot-scope="scope">
          <el-tag
            v-for="(tag,index) in scope.row.tags"
            :key="index"
            size="mini"
            type="success"
          >{{ tag }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="创建时间" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.gmtCreate }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="更新时间" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.gmtUpdate }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="230">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleDel(scope.row.id,scope.row.title)">删除</el-button>
          <el-button
            type="primary"
            size="mini"
            @click="$router.push({ name: 'Editor', params: { articleId: scope.row.id}})"
          >修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      :hide-on-single-page="true"
      @current-change="handleCurrentChange"
      :current-page="pageInfo.current"
      :page-size="pageInfo.size"
      layout="total, prev, pager, next, jumper"
      :total="pageInfo.total"
    ></el-pagination>
  </div>
</template>

<script>
import { getArticleList, deleteArticleById } from "@/api/article";

export default {
  data() {
    return {
      pageInfo: {
        size: 10,
        current: 1,
        total: 0,
        records: [],
      },
      listLoading: true,
    };
  },
  created() {
    this.fetchData(1, 10);
  },
  methods: {
    fetchData(page, limit) {
      this.listLoading = true;
      getArticleList({ page, limit }).then((res) => {
        this.pageInfo = res.data;
        this.listLoading = false;
      });
    },
    handleCurrentChange(page) {
      this.fetchData(page, 10);
    },
    handleDel(id, title) {
      this.$confirm("此操作将永久删除该文章, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteArticleById(id, title).then((res) => {
          this.$notify({
            title: "提示",
            message: `文章《${title}》删除成功`,
            type: "success",
          });
          this.fetchData(this.pageInfo.current, 10);
        });
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.el-tag {
  margin: 0 5px;
}
.el-pagination {
  margin-top: 10px;
}
</style>
