module.exports = {
  devServer: {
    proxy: {
      '/api/v1': {
        target: 'http://localhost:8000/api/v1',
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          '^/api/v1': ''
        }
      }
    }
  }
}
