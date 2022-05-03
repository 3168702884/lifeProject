import { request } from 'umi';
/** 获取风格实例数据  */
export async function barrierFree() {
  return request('/api/barrierFree', {
    method: 'GET',
  });
}
