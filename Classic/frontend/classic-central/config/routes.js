// 菜单路由配置,第一个是用户登录，之后的是菜单配置

export default [
  {
    path: '/user',
    layout: false,
    routes: [
      {
        path: '/user',
        routes: [
          {
            name: 'login',
            path: '/user/login',
            component: './user/Login',
          },
        ],
      },
      {
        component: './404',
      },
    ],
  },
  {
    path: '/welcome',
    name: 'welcome',
    icon: 'SmileTwoTone',
    component: './Welcome',
  },
  {
    path: '/fengGe',
    name: 'fenggetitle',
    icon: 'FlagTwoTone',
    component: './fengGe',
  },
  {
    path: '/design',
    name: 'designManage',
    icon: 'EditTwoTone',
    component: './designManage',
  },
  {
    path: '/lunbo',
    name: 'lunBoManage',
    icon: 'PlaySquareTwoTone',
    component: './lunBoManage',
  },
  {
    path: '/process',
    name: 'process',
    icon: 'ProfileTwoTone',
    component: './process',
  },
  {
    path: '/custom',
    name: 'custom',
    icon: 'ScheduleTwoTone',
    component: './custom',
  },
  {
    path: '/barrierFree',
    name: 'barrierFree',
    icon: 'SafetyCertificateTwoTone',
    component: './barrierFree',
  },
  {
    path: '/menu',
    name: 'menuManage',
    icon: 'AppstoreTwoTone',
    component: './menuManage',
  },
  {
    path: '/personManage',
    name: 'personManage',
    icon: 'CrownTwoTone',
    access: 'canAdmin',
    component: './personManage',
  },
  // {
  //   name: 'newpage',
  //   icon: 'DislikeTwoTone',
  //   path: '/newpage',
  //   component: './newpage',
  //  routes: [
  //     {
  //       path: '/personManage/Manage',
  //       name: 'sub-page',
  //       icon: 'smile',
  //       component: './Welcome',
  //     },
  //     {
  //       component: './404',
  //     },
  //   ],
  // },
  {
    path: '/',
    redirect: '/welcome',
  },
  {
    component: './404',
  },
];
/**
 * name:string 配置菜单的 name，如果配置了国际化，name 为国际化的 key。
 * icon:string 配置菜单的图表，默认使用 antd 的 icon 名，默认不适用二级菜单的 icon。
 * access:string 权限配置，需要预先配置权限
 * hideChildrenInMenu:true 用于隐藏不需要在菜单中展示的子路由。
 * hideInMenu:true 可以在菜单中不展示这个路由，包括子路由。
 * hideInBreadcrumb:true 可以在面包屑中不展示这个路由，包括子路由。
 * headerRender:false 当前路由不展示顶栏
 * footerRender:false 当前路由不展示页脚
 * menuRender: false 当前路由不展示菜单
 * menuHeaderRender: false 当前路由不展示菜单顶栏
 * flatMenu 子项往上提，只是不展示父菜单
 **/
