import React, { useEffect, useState } from 'react';
import { Button, message, Space } from 'antd';
import { ModalForm, ProFormText, ProFormUploadButton, ProFormTextArea } from '@ant-design/pro-form';
import { rowModalGet, submitForm } from '../../services/common/api';

const FengeModalForm = (props) => {
  const { setisvisible, isvisible, modalid, actionRef, addcategory, columns } = props;

  const [category, setcategory] = useState(''); //图文类别
  const [title, settitle] = useState(''); //表题
  const [imgadress, setimgadress] = useState(''); //图片地址
  const [introduce, setintroduce] = useState(''); //简介
  const [detailText, setdetailText] = useState(''); //详细介绍
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
        let res = await rowModalGet(modalid);
        setcategory(res.data.category);
        settitle(res.data.title);
        setimgadress(res.data.imgadress);
        setintroduce(res.data.introduce);
        setdetailText(res.data.detailText);
      };
      getrowdata();
    } else {
      setcategory(addcategory);
    }
    return () => {};
  }, [modalid]);

  // 输入框内容修改时
  const onProFormTextchange = (e) => {
    switch (e.currentTarget.id) {
      case 'title':
        settitle(e.currentTarget.value);
        break;
      case 'imgadress':
        setimgadress(e.currentTarget.value);
        break;
      case 'introduce':
        setintroduce(e.currentTarget.value);
        break;
      case 'detailText':
        setdetailText(e.currentTarget.value);
        break;
      default:
        break;
    }
  };

  return (
    <>
      <ModalForm
        // https://procomponents.ant.design/components/modal-form#api
        title={modalid >= 0 ? '编辑数据' : '新增数据'}
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
              category,
              title,
              imgadress,
              introduce,
              detailText,
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
            if (i.dataIndex != 'detailText' && i.dataIndex != 'operation') {
              return (
                <ProFormText
                  key={i.dataIndex}
                  width="md"
                  name={i.dataIndex} //返回数据的key
                  label={i.title}
                  tooltip={i.dataIndex == 'title' ? '必填的' : ''}
                  placeholder={`请输入${i.title}`}
                  value={eval(i.dataIndex)}
                  onChange={onProFormTextchange}
                />
              );
            } else if (i.dataIndex != 'operation') {
              return (
                <ProFormTextArea
                  key={i.dataIndex}
                  name={i.dataIndex} //返回数据的key
                  label={i.title}
                  placeholder={`请输入${i.title}`}
                  value={eval(i.dataIndex)}
                  onChange={onProFormTextchange}
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

export default FengeModalForm;
