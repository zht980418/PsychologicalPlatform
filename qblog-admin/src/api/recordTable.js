/* eslint-disable */
import request from '@/utils/request'

/**
 * 获取咨询记录列表
 */
export function getRecordTableListById(id) {
  return request({
    url: 'recordByuserid/' + id,
    method: 'get',
  })
}
export function getRecordTableList() {
  return request({
    url: '/records',
    method: 'get',
  })
}

/**
 * 按序号获取某个记录的信息
 * @param consultNo
 */
export function getRecordTableByNo(consultNo) {
  return request({
    url: '/recordByconsultno/' + consultNo,
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
    url: '/record/' + consultNo,
    method: 'delete',
  })
}


export function updateRecordTableByNo(consultNo, form)　{
  return request({
    url: '/record/' + consultNo,
    method: 'put',
    data: form,
  })
}


