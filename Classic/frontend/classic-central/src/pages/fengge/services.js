import { request } from 'umi';
/** 获取风格实例数据  */
export async function fenggeshilie() {
  return request('/api/fenGe', {
    method: 'GET',
  });
}
