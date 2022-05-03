/**从数据库拿值：
 * 项目名
 * 轮播地址
 * 轮播内容文字
 * 业务图片地址
 * 业务图片对应内容文字
 */
import React from 'react';
import { useModel } from 'umi';
import { homedata } from './index';

// 头部导航
export const Nav00DataSource = {
  wrapper: { className: 'header0 home-page-wrapper' },
  page: { className: 'home-page' },
  logo: {
    className: 'header0-logo',
    children: 'https://os.alipayobjects.com/rmsportal/mlcYmsRilwraoAe.svg',
    // children:'http://localhost:8812/title.svg',
  },
  Menu: {
    className: 'header0-menu',
    // 一级导航数组
    children: [
      {
        name: 'item0',
        className: 'header0-item',
        children: {
          href: '#',
          children: [{ children: '风格实例', name: 'text' }],
        },
        // 二级导航数组
        subItem: [
          {
            name: 'sub0',
            className: 'item-sub',
            children: {
              className: 'item-sub-item',
              href: '/details',
              children: [
                // {
                //   name: 'image0',
                //   className: 'item-image',
                //   children:
                //     'https://gw.alipayobjects.com/zos/rmsportal/ruHbkzzMKShUpDYMEmHM.svg',
                // },
                {
                  name: 'title',
                  className: 'item-title',
                  children: '原木风格',
                },
                {
                  name: 'content',
                  className: 'item-content',
                  children: '木质纹理相呼应 质朴自然',
                },
              ],
            },
          },
          {
            name: 'sub1',
            className: 'item-sub',
            children: {
              className: 'item-sub-item',
              href: '/details',
              children: [
                // {
                //   name: 'image0',
                //   className: 'item-image',
                //   children:
                //     'https://gw.alipayobjects.com/zos/rmsportal/ruHbkzzMKShUpDYMEmHM.svg',
                // },
                {
                  name: 'title',
                  className: 'item-title',
                  children: '北欧风格',
                },
                {
                  name: 'content',
                  className: 'item-content',
                  children: '简约、自然',
                },
              ],
            },
          },
          {
            name: 'sub2',
            className: 'item-sub',
            children: {
              className: 'item-sub-item',
              href: '/details',
              children: [
                // {
                //   name: 'image0',
                //   className: 'item-image',
                //   children:
                //     'https://gw.alipayobjects.com/zos/rmsportal/ruHbkzzMKShUpDYMEmHM.svg',
                // },
                {
                  name: 'title',
                  className: 'item-title',
                  children: '极简风格',
                },
                {
                  name: 'content',
                  className: 'item-content',
                  children: '追求设计形式和功能的平衡',
                },
              ],
            },
          },
        ],
      },
      {
        name: 'item1',
        className: 'header0-item',
        children: {
          href: '/details',
          children: [{ children: '设计方案', name: 'text' }],
        },
      },
      {
        name: 'item2',
        className: 'header0-item',
        children: {
          href: '/details',
          children: [{ children: '设计过程Tips', name: 'text' }],
        },
      },
      {
        name: 'item3',
        className: 'header0-item',
        children: {
          href: '/details',
          children: [{ children: '无障碍定制', name: 'text' }],
        },
      },
    ],
  },
  mobileMenu: { className: 'header0-mobile-menu' },
};
// 轮播大图
export const Banner10DataSource = {
  wrapper: { className: 'banner1' },
  BannerAnim: {
    children: [
      {
        name: 'elem0',
        BannerElement: { className: 'banner-user-elem' },
        textWrapper: { className: 'banner1-text-wrapper' },
        bg: { className: 'bg bg0' },
        title: {
          className: 'banner1-title',
          children:
            'https://zos.alipayobjects.com/rmsportal/HqnZZjBjWRbjyMr.png',
        },
        content: {
          className: 'banner1-content',
          children: '经典装饰设计中心',
        },
        button: {
          className: 'banner1-button',
          children: 'Learn More',
          href: '/details',
        },
      },
      {
        name: 'elem1',
        BannerElement: { className: 'banner-user-elem' },
        textWrapper: { className: 'banner1-text-wrapper' },
        bg: { className: 'bg bg1' },
        title: {
          className: 'banner1-title',
          children:
            'https://zos.alipayobjects.com/rmsportal/HqnZZjBjWRbjyMr.png',
        },
        content: {
          className: 'banner1-content',
          children: '经典装饰设计中心',
        },
        button: {
          className: 'banner1-button',
          children: 'Learn More',
          href: '/details',
        },
      },
    ],
  },
};
// 三个横向图
export const Content00DataSource = {
  wrapper: { className: 'home-page-wrapper content0-wrapper' },
  page: { className: 'home-page content0' },
  OverPack: { playScale: 0.3, className: '' },
  // 横向标题
  titleWrapper: {
    className: 'title-wrapper',
    children: [{ name: 'title', children: '风格实例' }],
  },
  // 横向内容
  childWrapper: {
    className: 'content0-block-wrapper',
    // 横向内容数组
    children: [
      {
        name: 'block0',
        className: 'content0-block',
        md: 8,
        xs: 24,
        children: {
          className: 'content0-block-item',
          children: [
            {
              name: 'image',
              className: 'content0-block-icon',
              children: '',
              // 'https://pic1.zhimg.com/v2-e8575794e4f0af2fb760fc426eeaae3b_1440w.jpg?source=172ae18b',
            },
            {
              name: 'title',
              className: 'content0-block-title',
              children: '原木风格',
            },
            { name: 'content', children: '木质纹理相呼应 质朴自然' },
          ],
        },
      },
      {
        name: 'block1',
        className: 'content0-block',
        md: 8,
        xs: 24,
        children: {
          className: 'content0-block-item',
          children: [
            {
              name: 'image',
              className: 'content0-block-icon',
              children:
                'https://pic3.zhimg.com/v2-51b92226bd2b546cdb5ccc573f438844_b.jpg',
            },
            {
              name: 'title',
              className: 'content0-block-title',
              children: '北欧风格',
            },
            {
              name: 'content',
              children:
                '北欧风格起源于斯堪的纳维亚地区的设计风格，因此也被称为“斯堪的纳维亚风格”。具有简约、自然、人性化的特点。',
            },
          ],
        },
      },
      {
        name: 'block2',
        className: 'content0-block',
        md: 8,
        xs: 24,
        children: {
          className: 'content0-block-item',
          children: [
            {
              name: 'image',
              className: 'content0-block-icon',
              children:
                'https://pic3.zhimg.com/80/v2-2122f6fb9bdd4b84f48eb1bbedb025fa_720w.jpg',
            },
            {
              name: 'title',
              className: 'content0-block-title',
              children: '极简风格',
            },
            {
              name: 'content',
              children:
                '极简设计的设计核心理念同样是希望通过简约的设计给人们带来更好的使用感，即简约但富有意义',
            },
          ],
        },
      },
    ],
  },
};
// 四个案例图
export const Content50DataSource = {
  wrapper: { className: 'home-page-wrapper content5-wrapper' },
  page: { className: 'home-page content5' },
  OverPack: { playScale: 0.3, className: '' },
  titleWrapper: {
    className: 'title-wrapper',
    children: [
      { name: 'title', children: '设计方案', className: 'title-h1' },
      {
        name: 'content',
        className: 'title-content',
      },
    ],
  },
  block: {
    className: 'content5-img-wrapper',
    gutter: 16,
    // 案列数组，取材地址http://www.jia360.com/case
    children: [
      {
        name: 'block0',
        className: 'block',
        md: 6,
        xs: 24,
        children: {
          wrapper: { className: 'content5-block-content' },
          img: {
            children:
              'http://tencentjiaju.img-cn-beijing.aliyuncs.com/20190211/h280w211-5c6124f64e25b.jpg',
          },
          content: { children: '优雅法式 法式风格' },
        },
      },
      {
        name: 'block1',
        className: 'block',
        md: 6,
        xs: 24,
        children: {
          wrapper: { className: 'content5-block-content' },
          img: {
            children:
              'http://tencentjiaju.img-cn-beijing.aliyuncs.com/20180123/h267w201-5a66eaee32079.jpg',
          },
          content: {
            children:
              '纯净北欧范 简单生活也可以很精致 白色系厨房与原木色餐桌搭配',
          },
        },
      },
      {
        name: 'block2',
        className: 'block',
        md: 6,
        xs: 24,
        children: {
          wrapper: { className: 'content5-block-content' },
          img: {
            children:
              'http://tencentjiaju.img-cn-beijing.aliyuncs.com/20181015/h280w211-5bc44869dcdf7.jpg',
          },
          content: { children: '棕榈泉 “精致生活”贯穿于设计始末' },
        },
      },
      {
        name: 'block3',
        className: 'block',
        md: 6,
        xs: 24,
        children: {
          wrapper: { className: 'content5-block-content' },
          img: {
            children:
              'http://tencentjiaju.img-cn-beijing.aliyuncs.com/20180307/h267w201-5a9f60b708e9f.jpg',
          },
          content: { children: '中式茶楼 去繁存简的设计手法' },
        },
      },
    ],
  },
};
// 一个左图右文
export const Content10DataSource = {
  wrapper: { className: 'home-page-wrapper content1-wrapper' },
  OverPack: { className: 'home-page content1', playScale: 0.3 },
  imgWrapper: { className: 'content1-img', md: 10, xs: 24 },
  img: {
    children: 'https://zos.alipayobjects.com/rmsportal/nLzbeGQLPyBJoli.png',
  },
  textWrapper: { className: 'content1-text', md: 14, xs: 24 },
  title: { className: 'content1-title', children: '设计过程' },
  content: {
    className: 'content1-content',
    children:
      '设计者通过空间布局、形式设计、软装材质、灯光布置及细节处理等方式为业主营造具有特点的居家环境，控制色调给人以温馨或静谧等的居家之感',
  },
};
// 六个简介图文
export const Content30DataSource = {
  wrapper: { className: 'home-page-wrapper content3-wrapper' },
  page: { className: 'home-page content3' },
  OverPack: { playScale: 0.3 },
  titleWrapper: {
    className: 'title-wrapper',
    children: [
      {
        name: 'title',
        children: '装修过程Tips',
        className: 'title-h1',
      },
      {
        name: 'content',
        className: 'title-content',
        children: '一些精心准备的贴士',
      },
    ],
  },
  block: {
    className: 'content3-block-wrapper',
    children: [
      {
        name: 'block0',
        className: 'content3-block',
        md: 8,
        xs: 24,
        children: {
          icon: {
            className: 'content3-icon',
            children:
              'https://zos.alipayobjects.com/rmsportal/ScHBSdwpTkAHZkJ.png',
          },
          textWrapper: { className: 'content3-text' },
          title: {
            className: 'content3-title',
            children: '储存空间的长期规划',
          },
          content: {
            className: 'content3-content',
            children:
              '在装修过程中，我们可能不会太考虑后期的日常生活中的储物功能，所以等到入住后才会发现。最好是在装修前要考虑到室内装修中的存放功能。',
          },
        },
      },
      {
        name: 'block1',
        className: 'content3-block',
        md: 8,
        xs: 24,
        children: {
          icon: {
            className: 'content3-icon',
            children:
              'https://zos.alipayobjects.com/rmsportal/NKBELAOuuKbofDD.png',
          },
          textWrapper: { className: 'content3-text' },
          title: { className: 'content3-title', children: '统一选材' },
          content: {
            className: 'content3-content',
            children:
              '在家居装修中用的材料和家具的选择，由于款式好看，往往会选择不同种类的木材，最后摆放室内的时候，就会发现混乱不堪的局面，最好挑选颜色单一，品种单一的木材家具。',
          },
        },
      },
      {
        name: 'block2',
        className: 'content3-block',
        md: 8,
        xs: 24,
        children: {
          icon: {
            className: 'content3-icon',
            children:
              'https://zos.alipayobjects.com/rmsportal/xMSBjgxBhKfyMWX.png',
          },
          textWrapper: { className: 'content3-text' },
          title: { className: 'content3-title', children: '智能家居' },
          content: {
            className: 'content3-content',
            children:
              '现在很多别墅内部装修都采用智能家居，但在一般的家居装修中，智能家居的安装量相对较小。虽然现在可能不需要安装，但将来使用起来会非常方便。而且，以后安装也很麻烦',
          },
        },
      },
    ],
  },
};
// 页底
export const Footer00DataSource = {
  wrapper: { className: 'home-page-wrapper footer0-wrapper' },
  OverPack: { className: 'home-page footer0', playScale: 0.05 },
  copyright: {
    className: 'copyright',
    children: (
      <span>
        ©2022 <a href="https://motion.ant.design">经典装饰设计中心</a> All
        Rights Reserved
      </span>
    ),
  },
};
