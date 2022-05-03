<template>
	<view>
		<view class="wrap">
			<!-- 消息提示 -->
			<u-toast ref="xiajia" />
			<u-toast ref="jieshou" />
			<u-toast ref="jujue" />
			<u-toast ref="endxinxi" />
			<!-- 信息和需求信息Tap -->
			<view class="u-tabs-box">
				<u-tabs-swiper activeColor="#70C1EC" ref="tabs" :list="list" :current="current" @change="change"
					:is-scroll="false" swiperWidth="750"></u-tabs-swiper>
			</view>
			<!-- 正在交易的信息 -->
			<swiper class="swiper-box" :current="swiperCurrent" @transition="transition"
				@animationfinish="animationfinish">

				<!-- 联系的信息 -->
				<swiper-item class="swiper-item" @scrolltolower="reachBottom">
					<!-- 用户没有联系的信息时 -->
					<u-empty text="你还没有联系的信息哦~" style="height: 100%;" mode="favor" v-show="userRepositoryBuy!='1'">
					</u-empty>
					<!-- 用户没有联系的信息时end -->

					<!-- 用户有联系的信息时可滚动信息列表 -->
					<view class="" v-show="userRepositoryBuy==='1'" style="height: 100%;width: 100%;">
						<!-- 可滚动区域 -->
						<scroll-view scroll-y="true" style="height: 100%;width: 100%;">
							<block v-for="(item, index) in buyList" :key="index">
								<view class="order" @tap="navDetail(item.shangpin_ID)">
									<view class="top">
										<view class="left">			
											<!-- <u-image :src="" mode="aspectFit"></u-image> -->
											<image :src="item.imgadress1" mode="scaleToFill"></image>
										</view>
										<view class="right">
											<view class="title">
												{{item.shangpin}}
											</view>
											<u-divider half-width="250" :use-slot="useSlot"></u-divider>
											<view class="content">
												{{item.jianjie}}
											</view>
										</view>
									</view>
									<view class="bottom">
										<!-- 进入信息详情 -->
										<view class="btn">
											进入信息详情
										</view>
									</view>
								</view>
							</block>
						</scroll-view>

					</view>
					<!-- 用户有联系的信息时可滚动信息列表end -->

				</swiper-item>
				<!-- 联系的信息end -->


				<!-- 发布的信息 -->
				<swiper-item class="swiper-item">
					<!-- 用户没有联系的信息时 -->
					<u-empty text="你还没有发布的信息哦~~" mode="favor" :show="userRepositorySell!='1'" style="height: 100%;"></u-empty>
					<!-- 用户没有联系的信息时end -->

					<!-- 用户有联系的信息时可滚动信息列表 -->
					<view class="" v-show="userRepositorySell==='1'" style="height: 100%;width: 100%;">
						<!-- 可滚动区域 -->
						<scroll-view scroll-y="true" style="height: 100%;width: 100%;" >
							<block v-for="(item, index) in sellList" :key="index">
								<view class="order" >
									<view class="top">
										<view class="left">			
											<!-- <u-image :src="" mode="aspectFit"></u-image> -->
											<image :src="item.imgadress1" mode="scaleToFill"></image>
										</view>
										<view class="right">
											<view class="title">
												{{item.shangpin}}
											</view>
											<u-divider half-width="250" :use-slot="useSlot"></u-divider>
											<view class="content">
												{{item.jianjie}}
											</view>
										</view>
									</view>
									<!-- 底部不同的状态情况 -->
										<!-- 正在上架 -->
										<view class="bottom" v-if="item.sendButtonStatu==='正在上架'">
											<view class="btn btnpadding">
												{{item.sendButtonStatu}}
											</view>
											<view class="btn btnpadding" @click="xiaJiamodale()">
												我要下架
											</view>
											<u-modal v-model="xiaJiamodaleshow" content="是否确认要下架信息" @confirm="confirmXiaJia(item.shangpin_ID)" mask-close-able="true" show-cancel-button="true"></u-modal>
										</view>
										
										<!-- 正在联系，得到联系人的电话 -->
										<view class="bottom" v-else-if="item.sendButtonStatu==='正在联系'">
											<view class="btn btnpadding"  @tap="getBuyTelPopup(item.shangpin_ID)">
												{{item.sendButtonStatu}}
											</view>
											<view class="btn btnpadding"  @click="endmodale()">
												结束联系
											</view>
											<u-popup v-model="BuyTelPopup" mode="center" border-radius="14" width="500rpx" height="300rpx">
												<view class="buyPhoneNumber">												
													联系人电话号码为：{{buyPhoneNumber}}
												</view>
											</u-popup>
											<u-modal v-model="endmodalshow" content="是否确认要结束联系" @confirm="confirmend(item.shangpin_ID)" mask-close-able="true" show-cancel-button="true"></u-modal>
										</view>
										
										<!-- 有人联系,获得用户列表在弹出层， -->
										<view class="bottom" v-else-if="item.sendButtonStatu==='有人联系'">
											<view class="btn btnpadding" @click="getUserListPopup(item.shangpin_ID)" >
												{{item.sendButtonStatu}}
											</view>
											<view class="btn btnpadding" @click="haveXiaJiamodale()">
												我要下架
											</view>
											<u-modal v-model="haveUserXiajiamodaleshow" content="有人联系,是否确认要下架信息" @confirm="confirmXiaJia(item.shangpin_ID)" mask-close-able="true" show-cancel-button="true"></u-modal>
											<!-- 弹出层里的用户列表 -->
											<u-popup v-model="UserLisPopup" mode="center" border-radius="14" width="600rpx" height="800rpx">
												<view class="userListOrder ">
												<scroll-view scroll-y="true" style="height:800rpx;width: 100%;" >																
													<block  v-for="(user,index) of userList" :key="index">
														<view class="content">
															<view class="left">
																<view class="container">
																	{{user.username}}
																</view>
															</view>
															<view class="right">
																<view class="yesBtn" @click="jieShou(user.wxID)">
																	接受
																</view>
																<view class="noBtn" @click="jujue(user.wxID)">
																	拒绝
																</view>
															</view>
														</view>
														<view class="line"></view>							
													</block>
												</scroll-view>
												</view>
											</u-popup>
										</view>
						
								</view>
							</block>
						</scroll-view>
					</view>
					<!-- 用户有联系的信息时可滚动信息列表end -->
				</swiper-item>
				<!-- 发布的信息end -->
			</swiper>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				/* 打开的信息ID */
				openShangpinID:0,
				/* 联系者列表 */
				userList:[],
				/* 联系者的电话 */
				buyPhoneNumber:'',
				/* 弹出层 */
				BuyTelPopup:false,
				UserLisPopup:false,
				endmodalshow:false,
				xiaJiamodaleshow:false,
				haveUserXiajiamodaleshow:false,
				/* 分割线 */
				useSlot:false,
				/* 用户信息仓库数量 */
				userRepositoryBuy: '',
				userRepositorySell: '',
				/* 联系的信息列表 */
				buyList: [],
				/* 发布的信息列表 */
				sellList: [],
				/* tabSwiper标签 */
				list: [{
						name: '联系的信息'
					},
					{
						name: '发布的信息'
					},

				],
				current: 0,
				swiperCurrent: 0,
				tabsHeight: 0,
				dx: 0,
				
				
			};
		},
		onLoad() { 
		},
		onShow() {
			console.log("onShow了");
			this.initBuy();
			this.initSell();
		},
		computed: {
			// let that=this;
			// uni.getStorage({
			// 	key:"Login",
			// 	success(res){
					
			// 		that.token=res.data;
			// 		this.informationList = res.data.informationList;
			// 	}
			// })
		},
		methods: {
			/* 初始化咕咕页面。初始用户作为买方，正在请求和正在联系的信息；初始用户作为卖方，发布的信息，显示被请求和正在联系的状态 */
			initBuy() {
				let Login = uni.getStorageSync('Login');
				uni.request({
					url: 'http://192.168.43.111:8812/buyOrders', //仅为示例，并非真实接口地址。
					method: "POST",
					data: {
						wxID: Login,
					},
					header: {
						"Content-type": "application/x-www-form-urlencoded" //自定义请求头信息
					},
					success: (res) => {
						this.buyList = res.data;
						this.userRepositoryBuy = res.header.result;
						// console.log("联系的信息数据数组:",this.userRepositoryBuy);
					}
				});
			},
			initSell() {
				let Login = uni.getStorageSync('Login');
				uni.request({
					url: 'http://192.168.43.111:8812/sendOrders', //仅为示例，并非真实接口地址。
					method:"POST",
					data: {
						wxID: Login
					},
					header: {
						"Content-type": "application/x-www-form-urlencoded" //自定义请求头信息
					},
					success: (res) => {
						this.sellList = res.data;
						this.userRepositorySell = res.header.result;
						// console.log("发布的信息数据数组:",this.sellList);
					}
				});
			},
			/* 路由至商品详情 */
			navDetail(id) {
				this.$u.route({ //可以简写直接写在括号里
					url: 'pages/detail/information-detail',
					params: {
						id: id
					}
				})
			},
			navUserDetail(id){
				// @tap="navUserDetail(item.shangpin_ID)"
				this.$u.route({ //可以简写直接写在括号里
					url: 'pages/detail/userDetail',
					params: {
						id: id
					}
				})
			},
			
			/* 正在联系，获得联系者的电话号码 */
			getBuyTelPopup(id){
				this.BuyTelPopup=true;
				let Login = uni.getStorageSync('Login');
				uni.request({
					url: 'http://192.168.43.111:8812/isContacting', 
					method: "POST",
					data: {
						wxID: Login,
						goodID:id
					},
					header: {
						"Content-type": "application/x-www-form-urlencoded" //自定义请求头信息
					},
					success: (res) => {
						console.log(res);
						this.buyPhoneNumber=res.data.phoneNumber;
					}
				});
			},
			/* 有人联系，获得联系人列表，做处理 */
			getUserListPopup(id){
				this.UserLisPopup=true;
				this.openShangpinID=id;
				let Login = uni.getStorageSync('Login');
				uni.request({
					url: 'http://192.168.43.111:8812/someoneContacting', 
					method: "POST",
					data: {
						wxID: Login,
						goodID:id
						
					},
					header: {
						"Content-type": "application/x-www-form-urlencoded" //自定义请求头信息
					},
					success: (res) => {
						this.userList=res.data
						console.log("用户列表",this.userList);
					}
				});
			},
			/* 接受联系 */
			jieShou(id){
				this.UserLisPopup=true;
				let Login = uni.getStorageSync('Login');
				uni.request({
					url: 'http://192.168.43.111:8812/accept', 
					method: "POST",
					data: {
						wxID: Login,
						goodID:this.openShangpinID,
						buy_ID:id
					},
					header: {
						"Content-type": "application/x-www-form-urlencoded" //自定义请求头信息
					},
					success: (res) => {
						console.log(res);
						this.UserLisPopup=true;
						this.initSell();
						this.$refs.jieshou.show({
											title: '接受了一个请求',
											type: 'success',										
										})
					}
				});
			},
			/* 拒绝联系 */
			jujue(id){
				let Login = uni.getStorageSync('Login');
				uni.request({
					url: 'http://192.168.43.111:8812/reduce', 
					method: "POST",
					data: {
						wxID: Login,
						goodID:this.openShangpinID,
						buy_ID:id
						
					},
					header: {
						"Content-type": "application/x-www-form-urlencoded" //自定义请求头信息
					},
					success: (res) => {
						console.log(res);
						this.getUserListPopup(this.openShangpinID);
						this.$refs.jujue.show({
											title: '拒绝了一个请求',
											type: 'error',										
										})
					}
				});
			},
			/* 下架信息 */
			xiaJiamodale(id){				
				this.xiaJiamodaleshow=true;								
			},
			haveXiaJiamodale(){
				this.haveUserXiajiamodaleshow=true;
			},
			confirmXiaJia(id){
				let Login = uni.getStorageSync('Login');
				uni.request({
					url: 'http://192.168.43.111:8812/UnShelve', //仅为示例，并非真实接口地址。
					method: "POST",
					data: {
						wxID: Login,
						goodID:id
					},
					header: {
						"Content-type": "application/x-www-form-urlencoded" //自定义请求头信息
					},
					success: (res) => {
						console.log("已经下架");
						this.initSell();
						this.$refs.xiajia.show({
											title: '下架成功',
											type: 'success',
										})
					}
				});
			},
			/* 结束联系 */
			endmodale(){
				this.endmodalshow=true;
			},
			confirmend(id){
				let Login = uni.getStorageSync('Login');
				uni.request({
					url: 'http://192.168.43.111:8812/finishContacting', //仅为示例，并非真实接口地址。
					method: "POST",
					data: {
						wxID: Login,
						goodID:id
						
					},
					header: {
						"Content-type": "application/x-www-form-urlencoded" //自定义请求头信息
					},
					success: (res) => {
						this.initSell();
						this.$refs.endxinxi.show({
											title: '结束联系成功',
											type: 'success',
										})
						console.log(res);
					}
				});
			},
			
					
			// tab栏切换
			change(index) {
				this.swiperCurrent = index;
				// this.getOrderList(index);
			},
			transition({
				detail: {
					dx
				}
			}) {
				this.$refs.tabs.setDx(dx);
			},
			animationfinish({
				detail: {
					current
				}
			}) {
				this.$refs.tabs.setFinishCurrent(current);
				this.swiperCurrent = current;
				this.current = current;
			}
		}
	};
</script>

<style>
	/* #ifndef H5 */
	page {
		height: 100%;
		background-color: #f2f2f2;
	}

	/* #endif */
</style>

<style lang="scss" scoped>
	image{
		width: 100%;
		height: 100%;
		border: 1rpx solid #ffffff;
		border-radius: 20rpx;
	}
	.line{
		width: 100%;
		height: 1rpx;
		border: 1rpx solid #ECEDF1;
	}
	.scroll-view-item {
		width: 750rpx;
		height: 750rpx;
		border: 2px solid #000000;
	}
	// 得到电话
	.buyPhoneNumber{
		width: 100%;
		height: 100%;
		display: flex;
		justify-content: flex-start;
		align-items: center;
	}
	// 列表块
	.order {
		width: 710rpx;
		height: 480rpx;
		background-color: #ffffff;
		margin: 30rpx auto;
		border-radius: 20rpx;
		box-sizing: border-box;
		padding: 20rpx;
		font-size: 28rpx;
		box-shadow: 10rpx 0rpx 20rpx 0rpx rgba(0, 0, 0, 0.1),
			-10rpx 0rpx 20rpx 0rpx rgba(0, 0, 0, 0.1),
			0rpx 10rpx 20rpx 0rpx rgba(0, 0, 0, 0.1),
			0rpx -10rpx 20rpx 0rpx rgba(0, 0, 0, 0.1);

		.top {
			display: flex;
			justify-content: space-between;
			width: 100%;
			height: 80%;


			.left {
				width: 40%;
				height: 100%;
				margin-right: 10rpx;

			}

			.right {
				width: 55%;
				height: 100%;
				display: flex;
				flex-direction: column;
				flex: 1;
				

				.title {
					width: 100%;
					height: 30%;
					overflow-y: auto;
				}

				.content {
					width: 100%;
					height: 70%;
					overflow-y: auto;
				}
			}
		}

		.bottom {
			display: flex;
			margin-top: 20rpx;
			padding: 0 10rpx;
			justify-content: flex-end;
			align-items: center;

			.btn {
				// 定位
				width: 30%;
				height: 100%;
				padding: 12rpx;
				border: 2rpx solid #70C1EC;
				border-radius: 40rpx;
				color: #70C1EC;
			}
			.btnpadding{
				padding:10rpx 40rpx;
			}
		}
	}
	// 列表块end
	
	// 联系用户列表
	.userListOrder{
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
		
		.content{
			display: flex;
			justify-content: space-between;
			width: 100%;
			height: 10%;
			
			.left{
				width: 35%;
				height: 100%;
				display: flex;
				justify-content: flex-start;
				align-items: center;
				
				.container{
					margin-left: 10%;
				}
				
			}
			.right{
				display: flex;
				width: 40%;
				height: 100%;
				
				.yesBtn{
					display: flex;
					justify-content: center;
					align-items: center;
					margin: 10rpx;										
					width: 50%;
					height: 80%;
					border: 1rpx solid #70C1EC;
					border-radius: 10rpx;
					
				}
				.noBtn{
					display: flex;
					justify-content: center;
					align-items: center;
					margin: 10rpx;
					width: 50%;
					height: 80%;
					border: 1rpx solid #70C1EC;
					border-radius: 10rpx;
					
				}
			}
		}
	}
	// 联系用户列表end
	.wrap {
		display: flex;
		flex-direction: column;
		height: calc(100vh - var(--window-top));
		width: 100%;
	}

	.swiper-box {
		flex: 1;
	}

	.swiper-item {
		height: 100%;
	}
</style>
