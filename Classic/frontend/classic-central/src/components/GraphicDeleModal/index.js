import React, { useEffect, useState } from 'react';
import { Modal, message } from 'antd';
import { NotificationTwoTone } from '@ant-design/icons';
import { ModalForm, ProFormText, ProFormUploadButton } from '@ant-design/pro-form';
import { deleteRow } from '../../services/common/api';

const DeleModal = (props) => {
  const { setisdeleModal, isdeleModal, modalid, actionRef } = props;
  const [confirmLoading, setconfirmLoading] = useState(false);

  useEffect(() => {
    return () => {};
  }, []);

  // 取消模态框
  const handleCancel = () => {
    setisdeleModal(false);
  };

  // 确认模态框
  const handleOk = async () => {
    setconfirmLoading(true);
    let res = await deleteRow(modalid);
    if (res.msg == '删除成功') {
      setconfirmLoading(false);
      actionRef.current.reload();
      setisdeleModal(false);
      message.success('删除成功辽~');
    }
  };
  return (
    <>
      <Modal
        visible={isdeleModal}
        onOk={handleOk}
        confirmLoading={confirmLoading}
        onCancel={handleCancel}
      >
        <div
          style={{
            display: 'flex',
            justifyContent: 'center',
          }}
        >
          <NotificationTwoTone
            style={{
              width: '50px',
              paddingTop: '5px',
            }}
          />
          <h3>确认删除此项？</h3>
        </div>
      </Modal>
    </>
  );
};

export default DeleModal;
