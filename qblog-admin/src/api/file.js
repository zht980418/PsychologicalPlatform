import request from '@/utils/request'
import axios from 'axios';

export function getQiniuToken() {
  return request({
    url: '/file/qiniu/token',
    method: 'get'
  });
}

export function uploadToQiniuCloud(url, formData) {
  return axios({
    url: url,
    method: 'post',
    data: formData,
    headers: { 'Content-Type': 'multipart/form-data' },
  }).then(res => res.data);
}
