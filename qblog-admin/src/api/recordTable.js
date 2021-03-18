/* eslint-disable */
import urls from '@/api/urls'
import request from '@/utils/request'

/**
 * 获取咨询记录列表
 */
export function getRecordTableList() {
  return request({
    url: '/record',
    method: 'get',
  })
}

export function getRecordTableByNo(consultNo) {
  return request({
    url: '/record' + consultNo,
    method: 'get',
  })
}

export function addRecordTableInfo(form) {
  return request({
    url: '/record',
    method: 'post',
    data: form
  })
}


export function deleteRecordTableByNo(consultNo) {
  return request({
    url: '/record' + consultNo,
    method: 'delete',
  })
}


export function updateRecordTableByNo(consultNo, params) {
  return request({
    url: '/record' + consultNo,
    method: 'put',
    data: {
    }
  })
}


