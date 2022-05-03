import { request } from 'umi';
/** 获取风格实例数据  */
export async function menu() {
  return request('/api/menu', {
    method: 'GET',
  });
}
