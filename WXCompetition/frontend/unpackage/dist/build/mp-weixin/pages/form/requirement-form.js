(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/form/requirement-form"],{"26f0":function(n,e,t){"use strict";t.r(e);var u=t("cb84"),o=t("7a9c");for(var r in o)"default"!==r&&function(n){t.d(e,n,(function(){return o[n]}))}(r);t("7f48");var i,a=t("f0c5"),c=Object(a["a"])(o["default"],u["b"],u["c"],!1,null,null,null,!1,u["a"],i);e["default"]=c.exports},"45af":function(n,e,t){"use strict";(function(n){t("9a42");u(t("66fd"));var e=u(t("26f0"));function u(n){return n&&n.__esModule?n:{default:n}}n(e.default)}).call(this,t("543d")["createPage"])},"592a":function(n,e,t){"use strict";(function(n){Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var t={data:function(){return{maxCount:1,action:"http://192.168.43.111:8812/releaseShop2",autoUpload:!1,form:{wxID:"",title:"",goodIntroduction:"",school:""},rules:{title:[{required:!0,message:"请输入标题",trigger:["change","blur"]}],jianjie:[{min:5,message:"简介不能少于5个字",trigger:"change"}],school:[{required:!0,message:"要输入你的需求信息去哪个校园~",trigger:["change","blur"]}]}}},onLoad:function(e){var t=n.getStorageSync("Login");this.form.wxID=t},onReady:function(){this.$refs.uForm.setRules(this.rules)},computed:{},methods:{submit:function(){var n=this;this.$refs.uForm.validate((function(e){e?(console.log("验证通过,开始请求"),n.$refs.uUpload.upload()):console.log("验证失败")}))}}};e.default=t}).call(this,t("543d")["default"])},"7a9c":function(n,e,t){"use strict";t.r(e);var u=t("592a"),o=t.n(u);for(var r in u)"default"!==r&&function(n){t.d(e,n,(function(){return u[n]}))}(r);e["default"]=o.a},"7f48":function(n,e,t){"use strict";var u=t("aa50"),o=t.n(u);o.a},aa50:function(n,e,t){},cb84:function(n,e,t){"use strict";t.d(e,"b",(function(){return o})),t.d(e,"c",(function(){return r})),t.d(e,"a",(function(){return u}));var u={uForm:function(){return t.e("uview-ui/components/u-form/u-form").then(t.bind(null,"70c3"))},uFormItem:function(){return Promise.all([t.e("common/vendor"),t.e("uview-ui/components/u-form-item/u-form-item")]).then(t.bind(null,"a493"))},uInput:function(){return Promise.all([t.e("common/vendor"),t.e("uview-ui/components/u-input/u-input")]).then(t.bind(null,"4b31"))},uUpload:function(){return Promise.all([t.e("common/vendor"),t.e("uview-ui/components/u-upload/u-upload")]).then(t.bind(null,"0b9e"))},uButton:function(){return t.e("uview-ui/components/u-button/u-button").then(t.bind(null,"1435"))}},o=function(){var n=this,e=n.$createElement;n._self._c},r=[]}},[["45af","common/runtime","common/vendor"]]]);