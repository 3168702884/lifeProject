import { defineConfig } from 'umi';
import routes from './routes';
import proxy from './proxy';

export default defineConfig({
  fastRefresh: {},
  hash: true,
  routes,
  proxy,
  devServer: {
    port: 8001,
  },
});
