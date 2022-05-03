<template>
	<view class="wrap">
		<!-- 搜索 -->
		<u-search placeholder="输入账号关键词搜索" v-model="keyword" :show-action="true" action-text="搜索" :animation="true"
			@search="search(keyword)" @custom="search(keyword)"></u-search>
		<!-- 用户未登陆 -->
		<!-- <u-empty text="你还没有登陆,请登录就能查看市场啦~" mode="favor" ></u-empty> -->
		<!-- 瀑布列表 -->
		<u-waterfall v-model="flowList" ref="uWaterfall">
			<template v-slot:left="{ leftList }">
				<view class="demo-warter" v-for="(item, index) in leftList" :key="index"
					@tap="navDetail(item.shangpin_ID)">
					<u-lazy-load threshold="-450" border-radius="10" :image="item.imgadress1" :index="index"></u-lazy-load>
					<view class="demo-title">{{ item.shangpin }}</view>
				</view>
			</template>
			<template v-slot:right="{ rightList }">
				<view class="demo-warter" v-for="(item, index) in rightList" :key="index"
					@tap="navDetail(item.shangpin_ID)">
					<u-lazy-load threshold="-450" border-radius="10" :image="item.imgadress1" :index="index"></u-lazy-load>
					<view class="demo-title">{{ item.shangpin }}</view>
				</view>
			</template>
		</u-waterfall>
		<u-loadmore bg-color="rgb(240, 240, 240)" :status="loadStatus" ></u-loadmore>
		<u-back-top :scroll-top="scrollTop"></u-back-top>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				/* 搜索 */
				keyword: "",
				/* 列表数据，懒加载 */
				page: 0,
				loadStatus: '', //是否到底状态
				flowList: [],
				/* 滚动参数 */
				scrollTop: 0,
				/* 登陆状态 */
				loginStatu:0
			};
		},
		onLoad() {
			/* 初始化数据 */
			let Login = uni.getStorageSync('Login');
			uni.request({
				url: 'http://192.168.43.111:8812/showShop', //接口地址。
				method: "GET",
				data: {
					wxID: Login,
					page: this.page
				},
				// header: {
				//     'custom-header': 'hello' //自定义请求头信息
				// },
				success: (res) => {
					console.log(res.data);
					/* 登陆状态 */
					
					/* 加载数据列表 */
					this.flowList = res.data;					
					/* 加载状态 */
					this.loadStatus = res.header.loadStatus;
					/* 打印加载数据 */
					
				}
			});
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop;
		},
		onShow() {

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
					// 中间传值变量数组
					let list = [];
					// //是否到底 					
					this.loadStatus = res.header.loadStatus;					
					// 追加数据至信息列表
					console.log("触底得到的数据",res.data);
					list = res.data;
					this.flowList = this.flowList.concat(list);
					console.log("触底添加的数据",this.flowList);
				}
			});

		},
		methods: {
			/* 搜索 */
			search(keyword) {
				this.$u.route({ //可以简写直接写在括号里
					url: 'pages/search/search',
					params: {
						keyword: keyword
					}
				})
			},

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
