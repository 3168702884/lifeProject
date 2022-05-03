import { request } from 'umi';
/** 获取风格实例数据  */
export async function custom() {
  return request('/api/custom', {
    method: 'GET',
  });
}
