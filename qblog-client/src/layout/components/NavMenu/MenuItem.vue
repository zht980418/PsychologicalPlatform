<template>
  <el-col :span="3">
    <div v-if="!item.hidden">
      <template v-if="hasOneShowingChild(item.children,item)">
        <el-menu-item :index="basePath">
          {{ item.meta.title }}
        </el-menu-item>
      </template>
      <el-submenu
        v-else
        :index="basePath"
      >
        <template
          v-if="!hasOneShowingChild(item.children,item)"
          slot="title"
        >
          {{ item.meta.title }}
        </template>
        <el-menu-item
          v-for="child in item.children"
          :key="child.path"
          :index="child.path"
          :v-if="!child.hidden"
        >
          {{ child.meta.title }}
        </el-menu-item>
      </el-submenu>
    </div>
  </el-col>
</template>

<script>
export default {
  props: {
    item: {
      type: Object,
      required: true
    },
    basePath: {
      type: String,
      default: ''
    }
  },
  methods: {
    hasOneShowingChild(children = [], parent) {
      console.log(this.item)
      //   if (this.item.children) {
      //     if (this.item.children.length > 1) {
      //       console.log(this.item.children)
      //       return false
      //     }
      //     return true
      //   }
      //   return true
      const showingChildren = children.filter(item => {
        if (item.hidden) {
          return false
        } else {
          // Temp set(will be used if only has one showing child)
          this.onlyOneChild = item
          return true
        }
      })
      // When there is only one child router, the child router is displayed by default
      if (showingChildren.length === 1) {
        return true
      }
      // Show parent if there are no child router to display
      if (showingChildren.length === 0) {
        this.onlyOneChild = { ...parent, path: '', noShowingChildren: true }
        return true
      }
      return false
    },
  },
}
</script>

<style>
</style>