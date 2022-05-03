<template>
	<view class="wrap" @click="init">
		<!-- 信息图片 -->
		<!-- <u-swiper :list="informationList.image" effect3d='true'></u-swiper> -->
		<u-image width="100%" height="450rpx" :src="informationList.image" :fade="true" duration="450" :border-radius="50"></u-image>
		<!-- 标题 -->
		<view class="title">
			<view class="">
				<u-icon name="attach" margin-right="10rpx"></u-icon>
				标题：
			</view>
			{{informationList.title}}
		</view>
		<!-- 信息描述 -->
		<view class="desc">
			<view class="">
				<u-icon name="order"></u-icon>
				描述：
			</view>
			<u-read-more>
				<rich-text :nodes="informationList.goodIntroduction"></rich-text>
			</u-read-more>
		</view>
		<!-- 信息按钮 -->
		<view class="button">
			<!-- 联系发布人，发送联系请求向信息发布人 -->
			<view class="" v-if="buttonStatu==='联系发布人'">
				<u-button @click="open">
					{{buttonStatu}}
				</u-button>
				<u-modal v-model="modalshow" :content="modalContent" @confirm="confirm()" mask-close-able="true" show-cancel-button="true"></u-modal>
			</view>
			<!-- 正在请求，等待信息发布人拒绝或者接受,结束联系（结束请求） -->
			<view class="" v-else-if="buttonStatu==='正在请求'">
				<u-button @click="endPopup=true">
					{{buttonStatu}}
				</u-button>
				<u-popup v-model="endPopup" mode="center" border-radius="14" width="500rpx" height="300rpx">
					<view class="endButtonStatu">					
						<u-button @click="endButtonStatu">结束请求</u-button>
					</view>
				</u-popup>
			</view>
			<!-- 正在联系,获得电话在弹出层， -->
			<view class="" v-else-if="buttonStatu==='正在联系'">
				<u-button @click="numberGet">
					{{buttonStatu}}
				</u-button>
				<u-popup v-model="numberPopup" mode="center" border-radius="14" width="500rpx" height="300rpx">
					<view class="fabuPhoneNumber">
						发布人电话号码为：{{number}}
					</view>
				</u-popup>
			</view>
			<!-- 已被拒绝 -->
			<view class="" v-else-if="buttonStatu==='已被拒绝'">
				<u-button >
					{{buttonStatu}}
				</u-button>
			</view>
		</view>
		<!-- 消息提示 -->
		<u-toast ref="enterRequest"/>
		<u-toast ref="endRequest"/>	
	</view>
</template>

<script>
	export default {
		data() {
			return {
				/* 信息ID */
				id: 0,
				/* 信息内容 */
				informationList: {
					/* 轮播图数据 */
					image: [],
					/* 标题 */
					title: '',
					/* 信息简介 */
					goodIntroduction: '',

				},
				/* 模态框,弹出层状态 */
				modalshow: false,
				endPopup:false,
				numberPopup:false,
				/* 模态框内容 */
				modalContent: '是否要请求交易信息',
				/* 得到的电话 */
				number:'',
				/* 信息的联系状态 */
				buttonStatu: '',
				/* 登录者信息 */
				token:''
			}
		},
		onLoad(option) {
			/* 接受页面传过来的参数 */
			this.id = option.id;
			console.log("从信息ID为：",this.id,"传过来");
			this.init();

		},
		computed: {

		},
		methods: {
			/* 初始化详情页 */
			init() {
				let Login = uni.getStorageSync('Login');
				uni.request({
					url: 'http://192.168.43.111:8812/buyOrderGet1',
					method:"POST",
					data: {
						// wxID:"oP5qv0TwLbCk7EoBdZeGpO3_GqIo",
						wxID:Login,
						goodID: this.id,
					},
					header: {
					    "Content-type":"application/x-www-form-urlencoded"
					},
					success: (res) => {
						
						// 初始化信息的内容
						this.informationList = res.data.informationList
						
						// 传入信息联系状态
						this.buttonStatu = res.data.informationList.buttonStatu
						
						
					}
				});
			},

			/* 联系发布人按钮点击 */
			open() {
				this.modalshow = true;
			},
			confirm() {
				console.log("我确认了要联系信息发布人");
				let Login = uni.getStorageSync('Login');
				uni.request({
					url: 'http://192.168.43.111:8812/buyOrderGet',
					method:"POST",
					data: {
						goodID: this.id,
						wxID: Login,
						buttonStatu: "联系发布人",
					},
					header: {
					    "Content-type":"application/x-www-form-urlencoded"
					},
					success: (res) => {
						this.buttonStatu = res.data.buttonStatu;
						this.$refs.enterRequest.show({
											title: '进入正在请求状态',
											type: 'success',
										})
					}
				});
			},
			
			/* 正在请求，点错了结束请求，回到请求前的状态 */
			endButtonStatu(){
				let Login = uni.getStorageSync('Login');
				uni.request({
					url: 'http://192.168.43.111:8812/buyOrderGet',
					method:"POST",
					data: {
						goodID: this.id,
						wxID: Login,
						buttonStatu: "正在请求",
					},
					header: {
						
					    "Content-type":"application/x-www-form-urlencoded"
					},
					success: (res) => {
						this.buttonStatu = res.data.buttonStatu
						this.endPopup=false;
						this.init();
						this.$refs.endRequest.show({
											title: '结束了请求',
											type: 'success',
										})
					}
				});
			},
			/* 正在联系状态，点击获得得到的电话 */
			numberGet(){
				this.numberPopup=true;
				let Login = uni.getStorageSync('Login');
				uni.request({
					url: 'http://192.168.43.111:8812/buyOrderGet',
					
					method:"POST",
					data: {
						wxID:Login,
						buttonStatu: "正在联系",
						goodID: this.id,
					},
					header: {
					    "Content-type":"application/x-www-form-urlencoded"//自定义请求头信息
					},
					success: (res) => {
						this.number = res.data.sendPhoneNumber;
						
					}
				});
			}
		}
	}
</script>

<style lang="scss" scoped>
	.wrap {
		padding: 40rpx;
	}
	.img{
		width: 100%;
		height: 40%;
	}
	.button {
		position: absolute;
		bottom: 20rpx;
		right: 20rpx;
	}
	// 结束请求
	.endButtonStatu{
		width: 100%;
		height: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	// 标题块
	.title{
		width: 670rpx;
		height: 100rpx;
		// 布局
		display: flex;
		align-items: center;
		justify-content: flex-start;
		// 背景边距
		background-size: 100% 100%;
		background-repeat: no-repeat;
		margin: 60rpx auto;
		// 盒子样式
		border-radius: 20rpx;
		box-shadow: 10rpx 0rpx 20rpx 0rpx rgba(0, 0, 0, 0.1),
			-10rpx 0rpx 20rpx 0rpx rgba(0, 0, 0, 0.1),
			0rpx 10rpx 20rpx 0rpx rgba(0, 0, 0, 0.1),
			0rpx -10rpx 20rpx 0rpx rgba(0, 0, 0, 0.1);
		box-sizing: border-box;
		padding: 20rpx;
		font-size: 28rpx;
	}
	.desc{
		width: 670rpx;
		height: 400rpx;
		// 布局
		display: flex;
		flex-direction: column;
		align-items: flex-start;
		justify-content: flex-start;
		// 背景边距
		background-size: 100% 100%;
		background-repeat: no-repeat;
		margin: 60rpx auto;
		// 盒子样式
		border-radius: 10rpx;
		box-shadow: 10rpx 0rpx 20rpx 0rpx rgba(0, 0, 0, 0.1),
			-10rpx 0rpx 20rpx 0rpx rgba(0, 0, 0, 0.1),
			0rpx 10rpx 20rpx 0rpx rgba(0, 0, 0, 0.1),
			0rpx -10rpx 20rpx 0rpx rgba(0, 0, 0, 0.1);
		box-sizing: border-box;
		padding: 20rpx;
		font-size: 28rpx;
	}
	.fabuPhoneNumber{
		width: 100%;
		height: 100%;
		display: flex;
		justify-content: flex-start;
		align-items: center;
	}
</style>
<style>
</style>
