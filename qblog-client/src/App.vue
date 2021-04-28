<template>
  <body style="min-width: 640px">
  <div id="app" >
    <router-view></router-view>
  </div>
  </body>
</template>

<script>

export default {
  name: "App",
  data() {
    return {
      imgSrc:"https://images.pexels.com/photos/1261731/pexels-photo-1261731.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
    };
  },
  created() { 
    if ( sessionStorage.getItem('state') ) {
      this.$store.replaceState( Object.assign( {}, this.$store.state,
          JSON.parse(sessionStorage.getItem('state') ) ) )
    }

    window.addEventListener('beforeunload',()=>{
      sessionStorage.setItem('state',JSON.stringify(this.$store.state) )
    })
  }
};
</script>

<style>
.main-text {
  text-decoration: none;
  color: #303133;
}
.comm-text {
  text-decoration: none;
  color: #606266;
}
.secondary-text {
  text-decoration: none;
  color: #909399;
  font-size: 13px;
}
.space-text {
  text-decoration: none;
  color: #c0c4cc;
  font-size: 13px;
}
.text-center {
  text-align: center;
}
.link {
  text-decoration: none;
}
.link:hover {
  cursor: pointer;
  color: #409eff;
}
.flex {
  display: flex;
  justify-content: space-around;
  align-items: center;
}
</style>
