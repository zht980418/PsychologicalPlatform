import request from '@/utils/request'

export function getBlogInfo() {
  return request({
    url: '/info',
    method: 'get'
  })
}

export function getArticlesChartData() {
  return request({
    url: '/auth/chart/articles',
    method: 'get'
  })
}