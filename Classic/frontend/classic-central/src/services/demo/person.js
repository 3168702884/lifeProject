import { request } from 'umi';

export async function person() {
  return request('/api/person', {
    method: 'GET',
  });
}
// export async function login(body, options) {
//   return request('/api/login/account', {
//     method: 'POST',
//     headers: {
//       'Content-Type': 'application/json',
//     },
//     data: body,
//     ...(options || {}),
//   });
// }
