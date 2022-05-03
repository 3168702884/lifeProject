import { request } from 'umi';

/** 登录接口 POST /api/login/account */
export async function login(body) {
  return request('/api/login/account', {
    method: 'POST',
    data: body,
  });
}

/** 获取当前的用户信息  */
export async function currentUser(options) {
  const { username } = options;
  return request('/api/currentUser', {
    method: 'GET',
    params: {
      username,
    },
  });
}

/** 退出登录接口 POST /api/login/outLogin */
export async function outLogin(options) {
  return request('/api/login/outLogin', {
    method: 'POST',
    ...(options || {}),
  });
}

// 打开弹窗获取某一行数据
export async function rowModalGet(modalid) {
  return request('/api/oneGraphicText', {
    method: 'GET',
    params: {
      id: modalid,
    },
  });
}

// 删除某一行图文数据
export async function deleteRow(modalid) {
  return request('/api/deleteOneGraphicText', {
    method: 'post',
    params: {
      id: modalid,
    },
  });
}

// 提交图文表单数据
export async function submitForm(formData) {
  return request('/api/personFormEditor', {
    method: 'post',
    data: formData,
  });
}

// 提交图文表单数据
export async function getGraphicChartDat() {
  return request('/api/getGraphicChartDat', {
    method: 'get',
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
// // }
// /** 此处后端没有提供注释 GET /api/notices */

// export async function getNotices(options) {
//   return request('/api/notices', {
//     method: 'GET',
//     ...(options || {}),
//   });
// }
// /** 获取规则列表 GET /api/rule */

// export async function rule(params, options) {
//   return request('/api/rule', {
//     method: 'GET',
//     params: { ...params },
//     ...(options || {}),
//   });
// }
// /** 新建规则 PUT /api/rule */

// export async function updateRule(options) {
//   return request('/api/rule', {
//     method: 'PUT',
//     ...(options || {}),
//   });
// }
// /** 新建规则 POST /api/rule */

// export async function addRule(options) {
//   return request('/api/rule', {
//     method: 'POST',
//     ...(options || {}),
//   });
// }
// /** 删除规则 DELETE /api/rule */

// export async function removeRule(options) {
//   return request('/api/rule', {
//     method: 'DELETE',
//     ...(options || {}),
//   });
// }
