import request from '@/utils/request'
/**
 * 分页获取留言信息
 * @param {page:number,limit:number} params 分页参数
 */
export function getMessageList(params) {
  return request({
    url: '/messages',
    method: 'get',
    params
  })
}

/**
 * 根据 id 删除留言
 * @param {id:number} id 留言 id
 */
export function deleteMessageById(id) {
  return request({
    url: '/auth/message/' + id,
    method: "delete"
  })
}

/**
 * 回复留言
 * @param {id:number} id 文章id
 * @param {data:object} data 回复留言参数
 */
export function replyMessageById(id, data) {
  return request({
    url: '/auth/message/' + id,
    method: 'put',
    data
  })
}

