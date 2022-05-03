import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import { Button, Space } from 'antd';
import { PlusOutlined } from '@ant-design/icons';
import React, { useEffect, useState, useRef } from 'react';
import { userMange } from './services';

import ModalForm from './components/PersonModalForm';
import DeleModal from './components/PersonDeleModal';

export default function DesignManage() {
  const [isvisible, setisvisible] = useState(false); //编辑弹窗显示
  const [isdeleModal, setisdeleModal] = useState(false); //删除弹窗显示
  const [modalid, setmodalid] = useState(); //行ID
  const [rowlength, setrowlength] = useState(); //行几条
  const [addcategory, setaddcategory] = useState(); //从什么分类上新增

  const actionRef = useRef(actionRef);

  useEffect(() => {
    return () => {};
  }, []);

  // 编辑or新增按钮
  const onEditor = (row, index, action) => {
    if (row) {
      setmodalid(row.id);
      setisvisible(true);
    } else {
      setmodalid(-1);
      setisvisible(true);
    }
  };

  //删除按钮
  const onDele = (row, index, action) => {
    setmodalid(row.id);
    setisdeleModal(true);
  };

  // 表格列
  const columns = [
    {
      dataIndex: 'username',
      title: '用户名',
    },
    {
      dataIndex: 'password',
      title: '密码',
      valueType: 'password',
    },
    {
      dataIndex: 'access',
      title: '权限',
      // valueType: 'select',
    },
    {
      dataIndex: 'createTime',
      title: '用户创建时间',
      valueType: 'dateTime',
    },
    {
      dataIndex: 'updateTime',
      title: '用户更新时间',
      valueType: 'dateTime',
    },
    {
      dataIndex: 'operation',
      title: '操作',
      render: (_, row, index, action) => [
        <Space key={index}>
          <Button
            type="primary"
            onClick={() => {
              onEditor(row, index, action);
            }}
          >
            编辑
          </Button>
          <Button
            type="primary"
            onClick={() => {
              onDele(row, index, action);
            }}
          >
            删除
          </Button>
        </Space>,
      ],
    },
  ];

  return (
    <PageContainer>
      <ProTable
        columns={columns} //列相关
        request={async (params, sorter, filter) => {
          // 表单搜索项会从 params 传入，传递给后端接口。
          // console.log(params, sorter, filter);
          const userMangeData = await userMange();
          setrowlength(userMangeData.length);
          return {
            data: userMangeData,
            // success 请返回 true，
            // 不然 table 会停止解析数据，即使有数据
            success: true,
            // 不传会使用 data 的长度，如果是分页一定要传
            // total: 10,
          };
        }}
        rowKey="id" //列ID和数据库对应
        pagination={{
          showQuickJumper: true,
        }}
        search={false}
        actionRef={actionRef} //表格实例
        toolBarRender={() => [
          <Button
            key="button"
            icon={<PlusOutlined />}
            type="primary"
            onClick={() => {
              onEditor();
            }}
          >
            新建
          </Button>,
        ]} //渲染工具栏
      />

      {/* 行编辑或新增模态框，只是图文模态框 */}
      {isvisible && (
        <ModalForm
          setisvisible={setisvisible}
          isvisible={isvisible}
          modalid={modalid}
          actionRef={actionRef}
          addcategory={addcategory}
          columns={columns}
        />
      )}
      {/* 确认删除模态框 */}
      {isdeleModal && (
        <DeleModal
          modalid={modalid}
          isdeleModal={isdeleModal}
          setisdeleModal={setisdeleModal}
          actionRef={actionRef}
        />
      )}
    </PageContainer>
  );
}
