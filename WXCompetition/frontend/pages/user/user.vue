<template>
	<view>
		<u-navbar :is-back="false" title="　" :border-bottom="false">
			<view class="u-flex u-row-right" style="width: 100%;">
				<view class="camera u-flex u-row-center">
				</view>
			</view>
		</u-navbar>
		<!-- 用户信息 -->
		<view class="u-flex user-box u-p-l-30 u-p-r-20 u-p-b-30" @tap="login">
			<view class="u-m-r-10">
				<u-avatar :src="pic" size="140"></u-avatar>
			</view>
			<view class="u-flex-1">
				<view class="u-font-18 u-p-b-20">{{name}}</view>
				<view class="u-font-14 u-tips-color">电话号:{{phone}}</view>
			</view>
			<!-- <view class="u-m-l-10 u-p-10">
				<u-icon name="scan" color="#969799" size="28"></u-icon>
			</view> -->
			<!-- <view class="u-m-l-10 u-p-10">
				<u-icon name="arrow-right" color="#969799" size="28"></u-icon>
			</view> -->
		</view>


		<view class="u-m-t-20">
			<u-cell-group>
				<u-cell-item icon="phone-fill" title="登录后请授权您的联系信息" @click="getPhonePopup"></u-cell-item>
			</u-cell-group>
			<u-popup  v-model="getTelPopup" mode="center" border-radius="14" width="500rpx" height="335rpx">
				<view class="form">
					<u-form :model="form" ref="uForm">
						<u-form-item label="昵称" >
							<u-input v-model="form.username" placeholder="请输入您的昵称"/>
						</u-form-item>
						<u-form-item label="电话" >
							<u-input v-model="form.phone" placeholder="请输入联系的电话"/>
						</u-form-item>
					</u-form>
					<view class="shouquan">
						<u-button class="yes" @click="confirm">确认授权</u-button>
						<u-button class="no" @click="getTelPopup=false">取消授权</u-button>
					</view>					
				</view>
			</u-popup>
		</view>

		<view class="u-m-t-20">
			<u-cell-group>
				<u-cell-item icon="star" title="发布信息" @tap="informationForm"></u-cell-item>
				<u-cell-item icon="photo" title="发布需求信息" @tap="requirementForm"></u-cell-item>
			</u-cell-group>
		</view>

		<view class="u-m-t-20">
			<u-cell-group>
				<u-cell-item icon="setting" title="关于我们" @click="getaboutPopup"></u-cell-item>
			</u-cell-group>
			<u-popup  v-model="aboutPopup" mode="center" border-radius="14" width="500rpx" height="300rpx">			
				<u-read-more>
						<rich-text :nodes="content"></rich-text>
				</u-read-more>
			</u-popup>
		</view>
		<!-- 消息提示 -->
		<u-toast ref="shouquan" />
	</view>
</template>

<script>
	export default {
		data() {
			return {
				content:"猜一猜电话和鸽子哪一个传输信息快？我猜你会有个答案。其实鸽子也不差！鸽子带着几个T的信息时，甚至更大就会比电话快！让我们来愉快的使用飞鸽校园传递信息吧！",
				UserInfo: {},
				hasUserInfo: false,
				canIUseGetUserProfile: false,
				pic: '',
				show: true,
				name: '',
				/* 授权的到电话弹窗 */
				getTelPopup: false,
				/* 用户输入的联系方式 */
				form: {
					phone: '',
					username:''
				},
				/* 得到的联系方式 */
				phone:'',
				/*关于我们弹窗 */
				aboutPopup:false
			}
		},
		onLoad() {},
		methods: {
			/* 登录 */
			login() {
				/* 获取用户信息 */
				wx.getUserProfile({
					desc: '用于完善资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
					success: (res) => {
						let that = this;
						// 得到信息
						that.UserInfo = res.userInfo;
						wx.login({
							success(res) {
								if (res.code) {
									var accountInfo = wx.getAccountInfoSync();
									var appid = accountInfo.miniProgram.appId;
									//发起网络请求
									wx.request({
										url: 'http://192.168.43.111:8812/wxLogin',//http://192.168.43.111,http:/xiaoyuan.vaiwan.com
										method: 'POST',
										header: {
											'content-type': 'application/x-www-form-urlencoded'
										},
										data: {
											code: res.code,
											appid: appid
										},
										success(res) {
											//如果接受成功则保存
											if (res.statusCode == 200) {
												let token = res.data.token;
												uni.setStorage({
													key: 'Login',
													data: token,
													success: function() {
														that.pic = that
															.UserInfo
															.avatarUrl;
														that.name = that
															.UserInfo.nickName;
														that.phone=res.data.phone;
														console.log(
															'登录并且存储token成功'
															);
														
													}
												});
											} else {
												uni.removeStorage({
													key: 'Login',
												});
											}

										}
									})
								} else {
									console.log('登录失败！' + res.errMsg)
									wx.checkSession({
										success() {
											//session_key 未过期，并且在本生命周期一直有效
											wx.showToast({
												title: '处于登录状态',
												icon: 'success',
												duration: 20000
											})
										},
										fail() {
											// session_key 已经失效，需要重新执行登录流程
											wx.showToast({
												title: '登录已失效',
												icon: 'none',
												duration: 20000
											})

										}
									})
								}
							}
						})
					}
				})
			},
			/* 授权获得联系方式 */
			getPhonePopup() {
				this.getTelPopup = true;
			},
			confirm(){
				let phone=this.form.phone;				
				let Login = uni.getStorageSync('Login');
				uni.request({
					url: 'http://192.168.43.111:8812/updateUser', 
					method: "POST",
					data: {
						wxID: Login,
						phone:phone,
						username:this.form.username
					},
					header: {
						"Content-type": "application/x-www-form-urlencoded" //自定义请求头信息
					},
					success: (res) => {
						this.getTelPopup = false;
						this.phone=res.data.phone;
						this.$refs.shouquan.show({
											title: '授权成功',
											type: 'success',
										})
					}
				});
			},
			/* 表单路由 */
			informationForm() {
				this.$u.route({ //可以简写直接写在括号里
					url: 'pages/form/information-form',
					params: {
						name: ''
					}
				})
			},
			requirementForm() {
				this.$u.route({ //可以简写直接写在括号里
					url: 'pages/form/requirement-form',
					params: {
						name: ''
					}
				})
			},
			
			/* 关于的弹窗 */
			getaboutPopup(){
				this.aboutPopup=true;
			}
		}
	}
</script>

<style lang="scss">
	page {
		background-color: #ededed;
	}

	.camera {
		width: 54px;
		height: 44px;

		&:active {
			background-color: #ededed;
		}
	}

	.user-box {
		background-color: #fff;
	}

	.form {
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
		.shouquan{
			width: 100%;
			height: auto;
			display: flex;
			
			.yes{
				width: 50%;
				height: 100%;
				padding: 10rpx;
			}
			.no{
				width: 50%;
				height: 100%;
				padding: 10rpx;
			}
		}
	}
</style>
