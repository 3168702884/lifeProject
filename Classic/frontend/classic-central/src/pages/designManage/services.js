import { request } from 'umi';
/** 获取风格实例数据  */
export async function design() {
  return request('/api/design', {
    method: 'GET',
  });
}
