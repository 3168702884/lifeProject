/* eslint no-undef: 0 */
/* eslint arrow-parens: 0 */
import React, { useState, useEffect } from 'react';
import { useModel } from 'umi';
import { enquireScreen } from 'enquire-js';

import Nav0 from './Nav0';
import Banner1 from './Banner1';
import Content0 from './Content0';
import Content5 from './Content5';
import Content1 from './Content1';
import Content3 from './Content3';
import Footer0 from './Footer0';

import {
  Nav00DataSource,
  Banner10DataSource,
  Content00DataSource,
  Content50DataSource,
  Content10DataSource,
  Content30DataSource,
  Footer00DataSource,
  data,
} from './data.source';

import './less/antMotionStyle.less';
import { fenggeshilie } from '../services/homeApi';

const { location = {} } = typeof window !== 'undefined' ? window : {};

export default function Home() {
  const [isMobile, setisMobile] = useState();
  const [show, setshow] = useState(!location.port);
  const [h, seth] = useState();
  const [fgintroduce, setfgintroduce] = useState();

  const [nav0, setnav0] = useState(Nav00DataSource);
  const [ban0, setban0] = useState(Banner10DataSource);
  const [cont0, setcont0] = useState(Content00DataSource);

  const { homepage } = useModel('homeModel', (model) => ({
    homepage: model.homepage,
  }));
  // enquireScreen((b) => {
  //   isMobile = b;
  // });

  useEffect(() => {
    // 页面请求返回值
    homepage().then((res) => {
      console.log('res', res[0]);
      seth(res[0].imgadress);
      setfgintroduce(res[0].introduce);
    });

    // 适配手机屏幕;
    enquireScreen((b) => {
      setisMobile({ isMobile: !!b });
    });
    // dva 2.0 样式在组件渲染之后动态加载，导致滚动组件不生效；线上不影响；
    /* 如果不是 dva 2.0 请删除 start */
    if (location.port) {
      // 样式 build 时间在 200-300ms 之间;
      setTimeout(() => {
        setshow(true);
      }, 500);
    }
    /* 如果不是 dva 2.0 请删除 end */

    return () => {};
  }, []);

  useEffect(() => {
    setcont0((v) => {
      v.childWrapper.children[0].children.children[0].children = h;
      return v;
    });

    return () => {};
  }, [h]);

  useEffect(() => {
    setcont0((v) => {
      v.childWrapper.children[0].children.children[2].children = fgintroduce;
      return v;
    });

    return () => {};
  }, [fgintroduce]);

  const children = [
    <Nav0 id="Nav0_0" key="Nav0_0" dataSource={nav0} isMobile={isMobile} />,
    <Banner1
      id="Banner1_0"
      key="Banner1_0"
      dataSource={ban0}
      isMobile={isMobile}
    />,
    <Content0
      id="Content0_0"
      key="Content0_0"
      dataSource={cont0}
      isMobile={isMobile}
    />,
    <Content5
      id="Content5_0"
      key="Content5_0"
      dataSource={Content50DataSource}
      isMobile={isMobile}
    />,
    <Content1
      id="Content1_0"
      key="Content1_0"
      dataSource={Content10DataSource}
      isMobile={isMobile}
    />,
    <Content3
      id="Content3_0"
      key="Content3_0"
      dataSource={Content30DataSource}
      isMobile={isMobile}
    />,
    <Footer0
      id="Footer0_0"
      key="Footer0_0"
      dataSource={Footer00DataSource}
      isMobile={isMobile}
    />,
  ];

  return (
    <div
      className="templates-wrapper"
      // ref={(d) => {
      //   this.dom = d;
      // }}
    >
      {/* 如果不是 dva 2.0 替换成 {children} start */}
      {show && children}
      {/* 如果不是 dva 2.0 替换成 {children} end */}
    </div>
  );
}
