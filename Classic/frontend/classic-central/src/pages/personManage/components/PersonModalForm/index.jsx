import React, { useEffect, useState } from 'react';
import { Button, message, Space } from 'antd';
import {
  ModalForm,
  ProFormText,
  ProFormUploadButton,
  ProFormTextArea,
  ProFormSelect,
} from '@ant-design/pro-form';
import { onePerson, getAccess, submitForm } from '../../services';

const PersonModalForm = (props) => {
  const { setisvisible, isvisible, modalid, actionRef, columns } = props;

  const [username, setusername] = useState(''); //表题
  const [password, setpassword] = useState(''); //图片地址
  const [access, setaccess] = useState(''); //权限
  const [avatar, setavatar] = useState(''); //详细介绍
  const [fileList, setFileList] = useState([
    {
      uid: '-1',
      name: 'image.png',
      status: 'done',
      url: 'https://pic1.zhimg.com/v2-e8575794e4f0af2fb760fc426eeaae3b_1440w.jpg?source=172ae18b',
    },
  ]);

  useEffect(() => {
    // 编辑的情况
    if (modalid >= 0) {
      const getrowdata = async () => {
        let res = await onePerson(modalid);
        setusername(res.data.username);
        setpassword(res.data.password);
        setaccess(res.data.access);
        setavatar(res.data.avatar);
      };
      getrowdata();
    } else {
    }
    return () => {};
  }, [modalid]);

  // 输入框内容修改时
  const onProFormTextchange = (e) => {
    switch (e.currentTarget.id) {
      case 'username':
        setusername(e.currentTarget.value);
        break;
      case 'password':
        setpassword(e.currentTarget.value);
        break;
      case 'avatar':
        setavatar(e.currentTarget.value);
        break;
      default:
        break;
    }
  };

  // 当输入框权限发生改变时
  const onAccessChange = (e) => {
    setaccess(e);
  };
  return (
    <>
      <ModalForm
        // https://procomponents.ant.design/components/modal-form#api
        title={modalid >= 0 ? '编辑人员信息' : '新增人员信息'}
        visible={isvisible}
        submitter={{
          searchConfig: {
            submitText: '提交',
            resetText: '取消',
          },
        }}
        onFinish={async (v) => {
          if (Object.prototype.isPrototypeOf(v) && Object.keys(v).length === 0) {
            message.warn('请修改数据再提交~');
          } else {
            let values = {
              id: modalid,
              username,
              password,
              access,
              avatar,
            };
            let res = await submitForm(values);
            setisvisible(false);
            actionRef.current.reload();
            message.success('修改成功');
            return true;
          }

          return true;
        }}
        // https://ant.design/components/modal-cn/#API
        modalProps={{
          onCancel: () => setisvisible(false),
        }}
      >
        {/* 表单项,循环出列值 */}
        {isvisible &&
          columns.map((i) => {
            if (
              i.dataIndex != 'avatar' &&
              i.dataIndex != 'operation' &&
              i.dataIndex != 'createTime' &&
              i.dataIndex != 'updateTime' &&
              i.dataIndex != 'access'
            ) {
              return (
                <ProFormText
                  key={i.dataIndex}
                  width="md"
                  name={i.dataIndex} //返回数据的key
                  label={i.title}
                  tooltip={i.dataIndex == 'username' ? '必填的' : ''}
                  placeholder={`请输入${i.dataIndex}`}
                  value={eval(i.dataIndex)}
                  onChange={onProFormTextchange}
                  // rules={[{ required: true, message: '这是必填哒' }]}
                />
              );
            } else if (i.dataIndex == 'access') {
              return (
                <ProFormSelect
                  key={i.dataIndex}
                  name={i.dataIndex} //返回数据的key
                  label={i.title}
                  request={async () => {
                    let res = await getAccess();
                    let opt = [];
                    res.map((i) => {
                      let t = {};
                      t.label = i.described;
                      t.value = i.access;
                      opt.push(t);
                    });
                    return opt;
                  }}
                  placeholder={`请输入${i.dataIndex}`}
                  value={eval(i.dataIndex)}
                  onChange={onAccessChange}
                  // rules={[{ required: true, message: '这是必填哒' }]}
                />
              );
            }
          })}

        {/* <ProFormUploadButton
          name="upload"
          label="Upload"
          max={2}
          fieldProps={{
            name: 'file',
            listType: 'picture-card',
          }}
          fileList={fileList}
          action="/upload.do"
        /> */}
      </ModalForm>
    </>
  );
};

export default PersonModalForm;
