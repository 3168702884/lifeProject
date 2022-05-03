export default {
  '/api': {
    target: 'http://localhost:8812',
    changeOrigin: true,
    pathRewrite: {
      '^/api': '',
    },
  },
};
