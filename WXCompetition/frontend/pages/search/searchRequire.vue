<template>
	<view class="wrap">
		<!-- 搜索 -->
		<u-search placeholder="输入账号ID搜索" v-model="params" :show-action="true" action-text="搜索" :animation="true"
			@search="search(params)" @custom="search(params)"></u-search>
		<!-- 瀑布列表 -->
		<u-waterfall v-model="flowList" ref="uWaterfall">
			<template v-slot:left="{ leftList }">
				<view class="demo-warter" v-for="(item, index) in leftList" :key="index" @tap="navDetail(item.shangpin_ID)">
					<u-lazy-load threshold="-450" border-radius="10" :image="item.imgadress1" :index="index"></u-lazy-load>
					<view class="demo-title">{{ item.shangpin }}</view>
				</view>
			</template>
			<template v-slot:right="{ rightList }">
				<view class="demo-warter" v-for="(item, index) in rightList" :key="index" @tap="navDetail(item.shangpin_ID)">
					<u-lazy-load threshold="-450" border-radius="10" :image="item.imgadress1" :index="index"></u-lazy-load>
					<view class="demo-title">{{ item.shangpin }}</view>
				</view>
			</template>
		</u-waterfall>
		<u-loadmore bg-color="rgb(240, 240, 240)" :status="loadStatus" @loadmore="addRandomData"></u-loadmore>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				//搜索传过来的参数
				params: '',
				/* 列表数据，懒加载 */
				page: 0,
				loadStatus: '请搜索~', //是否到底状态
				flowList:[],

			};
		},
		onLoad(option) {
			/* 接受页面传过来的参数 */
			this.params = option.keyword;
			let Login = uni.getStorageSync('Login');
			console.log("我搜索了东西");
			uni.request({
				url: 'http://192.168.43.111:8812/linkShowNeeds', //仅为示例，并非真实接口地址。
				data: {
					wxID: Login,
					keyword: this.params
				},
				// header: {
				// 	'custom-header': 'hello' //自定义请求头信息
				// },
				success: (res) => {
					// 赋值
					// 追加数据至信息列表
					
					this.flowList=res.data
					this.loadStatus = res.header.loadStatus;
					console.log("this.flowList:",this.flowList);
					console.log("返回的查询结果:",res);	
						
						
					
				}
			});
		},
		onShow() {

		},
		computed: {
			/* 序列化传过来的参数，json的是data里的params */
			paramsStr() {
				return JSON.stringify(this.params);
			}
		},
		onReachBottom() {
			this.page++;
			let Login = uni.getStorageSync('Login');
			uni.request({
				url: 'http://192.168.43.111:8812/showShop', //仅为示例，并非真实接口地址。
				data: {
					wxID: Login,
					page: this.page
				},
				// header: {
				//     'custom-header': 'hello' //自定义请求头信息
				// },
				success: (res) => {
					console.log("加载着呢");
					let list = [];
					// //是否到底 
					this.loadStatus = res.header.loadStatus;
					// // 追加数据至信息列表
					list = res.data;
					this.flowList = this.flowList.concat(list);
					
				}
			});

		

		},
		methods: {
			/* 搜索 */
			search(keyword) {
				let Login = uni.getStorageSync('Login');
				uni.request({
					url: 'http://192.168.43.111:8812/linkShowNeeds', //仅为示例，并非真实接口地址。
					data: {
						wxID: Login,
						keyword: keyword
					},
					// header: {
					// 	'custom-header': 'hello' //自定义请求头信息
					// },
					success: (res) => {
						// 初始化
						this.flowList=res.data
						console.log("我搜索了东西");
					}
				});
			},
			/* 卡片清除 */
			clear() {
				this.$refs.uWaterfall.clear();
			},
			/* 路由至商品详情 */
			navDetail(id) {
				this.$u.route({ //可以简写直接写在括号里
					url: 'pages/detail/information-detail',
					params: {
						id: id
					}
				})
			}
		}
	};
</script>

<style>
	/* page不能写带scope的style标签中，否则无效 */
	page {
		background-color: rgb(240, 240, 240);
	}
</style>

<style lang="scss" scoped>
	.demo-warter {
		border-radius: 8px;
		margin: 5px;
		background-color: #ffffff;
		padding: 8px;
		position: relative;
	}


	.demo-img-wrap {}

	.demo-image {
		width: 100%;
		border-radius: 4px;
	}

	.demo-title {
		font-size: 30rpx;
		margin-top: 5px;
		color: $u-main-color;
		word-break: break-all;
	}
</style>
