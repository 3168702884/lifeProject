import React, { useState, useEffect } from 'react';
import { PageContainer } from '@ant-design/pro-layout';
import { Card, Alert, Typography, Statistic } from 'antd';
import { useIntl, FormattedMessage } from 'umi';
import ProCard from '@ant-design/pro-card';
import { Pie, Column } from '@ant-design/plots';
import styles from './Welcome.less';
import { getGraphicChartDat } from '../services/common/api';
import { userMange } from './personManage/services';

export default () => {
  const [userNum, setuserNum] = useState(); //后台用户数
  const [graphiNum, setgraphiNum] = useState(); //图文总数
  const [pieData, setpieData] = useState([]); //扇形图数据

  useEffect(() => {
    // 获得图文数据总数以及获得扇形图数据
    (async () => {
      let res = await getGraphicChartDat();
      setgraphiNum(res.length);

      // 获取所有分类的属性值
      let categoryList = res.map((i) => {
        return i.category;
      });

      //reduce:计算数组元素相加后的总和,方法接受两个参数，第一个是函数，第二个是初始值
      //把分类和分类数量组成一个对象
      let numObject = categoryList.reduce(function (prev, next) {
        prev[next] = prev[next] + 1 || 1;
        return prev;
      }, {});

      // 把对象操作进数组
      let temp = [];
      for (let i in numObject) {
        let obj = {
          type: i,
          value: numObject[i],
        };
        temp.push(obj);
      }

      setpieData(temp);
    })();

    (async () => {
      let res = await userMange();
      setuserNum(res.length);
    })();

    return () => {};
  }, []);

  const intl = useIntl();

  const configPie = {
    appendPadding: 10,
    data: pieData,
    angleField: 'value',
    colorField: 'type',
    radius: 0.8,
    label: {
      type: 'outer',
      content: '{name} {percentage}',
    },
    interactions: [
      {
        type: 'pie-legend-active',
      },
      {
        type: 'element-active',
      },
    ],
  };

  return (
    <PageContainer>
      <Alert
        message={intl.formatMessage({
          id: 'pages.welcome.alertMessage',
          defaultMessage: 'Faster and stronger heavy-duty components have been released.',
        })}
        type="info"
        // showIcon
        banner
        style={{
          margin: -12,
          marginBottom: 24,
        }}
      />

      <ProCard.Group title="后台数据">
        <ProCard>
          <Statistic title="用户数" value={userNum} suffix="人" />
        </ProCard>
        <ProCard.Divider type={'vertical'} />
        <ProCard>
          <Statistic title="图文总数" value={graphiNum} suffix="条" />
        </ProCard>
        <ProCard.Divider type={'vertical'} />
      </ProCard.Group>

      <ProCard>
        <ProCard hoverable bordered>
          <Pie {...configPie} />
        </ProCard>
      </ProCard>
    </PageContainer>
  );
};
