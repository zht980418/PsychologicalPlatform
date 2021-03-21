<template>
  <el-row class="app-container">
    <ul class="course-list">
      <DocItem
        v-for="item in doctorlist"
        :key="item.doctorId"
        :doctorName="item.doctorName"
        :doctorId="item.doctorId"
      />
    </ul>
  </el-row>
</template>

<script>
import { getDoctorList } from '@/api/order'
export default {
  components: {
    DocItem: () => import('./components/DocItem')
  },
  data() {
    return {
      doctorlist: [],
    };
  },
  created() {
    getDoctorList().then((res) => {
      if (res.code === 0) {
        this.doctorlist = res.data
      }
    })
  },
}
</script>
