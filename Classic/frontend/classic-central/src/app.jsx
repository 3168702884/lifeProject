/**全局配置文件
 * @see  https://umijs.org/zh-CN/plugins/plugin-initial-state
 */
import { PageLoading } from '@ant-design/pro-layout';
import { history, Link } from 'umi';
import RightContent from '@/components/RightContent';
import Footer from '@/components/Footer';
import { currentUser as queryCurrentUser } from './services/common/api';
import { BookOutlined, LinkOutlined } from '@ant-design/icons';
const isDev = process.env.NODE_ENV === 'development';
const loginPath = '/user/login';

/** 获取用户信息比较慢的时候会展示一个 loading */
export const initialStateConfig = {
  loading: <PageLoading />,
};

// 全局初始化数据
export async function getInitialState() {
  // 获取当前用户信息
  const fetchUserInfo = async (values) => {
    try {
      const msg = await queryCurrentUser(values);
      return msg.user;
    } catch (error) {
      history.push(loginPath);
    }
    return undefined;
  };
  //如果不是登录页面，执行
  // if (history.location.pathname !== loginPath) {
  //   // 通过fetchUserInfo访问mork的api
  //   const currentUser = await fetchUserInfo();
  //   return {
  //     fetchUserInfo,
  //     currentUser,
  //     settings: {},
  //   };
  // }
  const currentUser = await fetchUserInfo();

  return {
    fetchUserInfo,
    currentUser,
    settings: {},
  };
}

/**总体布局相关
 * 其中的 initialState 就是通过初始化插件获得数据，
 * 每次 initialState 改变就会触发 layout 的重新渲染，
 * ProLayout 支持的api https://procomponents.ant.design/components/layout
 */
export const layout = ({ initialState }) => {
  return {
    // 全局头部右侧内容区域
    rightContentRender: () => <RightContent />,
    disableContentMargin: false,
    // 全局水印
    // waterMarkProps: {
    //   content: initialState?.currentUser?.name,
    // },
    // 全局底部内容区域
    footerRender: () => <Footer />,
    // onPageChange: () => {
    //   const { location } = history; // 如果没有登录，重定向到 login

    //   if (!initialState?.currentUser && location.pathname !== loginPath) {
    //     history.push(loginPath);
    //   }
    // },
    // 菜单左下角链接
    // links: isDev
    //   ? [
    //     <Link key="openapi" to="/umi/plugin/openapi" target="_blank">
    //       <LinkOutlined />
    //       <span>OpenAPI 文档</span>
    //     </Link>,
    //     <Link key="docs" to="/~docs">
    //       <BookOutlined />
    //       <span>业务组件文档</span>
    //     </Link>,
    //   ]
    //   : [],
    menuHeaderRender: undefined,
    // 自定义 403 页面
    // unAccessible: <div>unAccessible</div>,
    ...initialState?.settings,
  };
};
