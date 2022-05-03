import Vue from 'vue'
import App from './App'
// 导入uview
import uView from "uview-ui";
// 导入animate
import animate from "./common/animate.min.css";
// 使用uview
Vue.use(uView);
// 使用animate
Vue.use(animate);

/* 小程序分享 */
let mpShare = require('uview-ui/libs/mixin/mpShare.js');
Vue.mixin(mpShare)
/* 小程序分享end */
Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
