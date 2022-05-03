import { request } from 'umi';
/** 获取权限数据  */
export async function getAccess() {
  return request('/api/getAccess', {
    method: 'GET',
  });
}

/** 获取人员管理数据  */
export async function userMange() {
  return request('/api/userMange', {
    method: 'GET',
  });
}

/** 获取一个人员管理数据  */
export async function onePerson(modalid) {
  return request('/api/onePerson', {
    method: 'GET',
    params: {
      id: modalid,
    },
  });
}

// 删除某一行人员数据
export async function deleteRow(modalid) {
  return request('/api/userMangeDelete', {
    method: 'post',
    params: {
      id: modalid,
    },
  });
}

// 提交人员表单数据
export async function submitForm(formData) {
  return request('/api/userMangeEditor', {
    method: 'post',
    data: formData,
  });
}
