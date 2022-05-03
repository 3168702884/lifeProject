<template>
	<view class="content">
		<!-- 背景卡片 -->
		<view class="bg-card">
		</view>

		<!-- 卡片一 -->
		<view class="card">
			<view class="card-market"
				style="background-image: url(https://img.zcool.cn/community/01238159a6266da8012028a9256632.gif);"
				@tap="informationMmarket">
				<view class="cardButton animated" :class="{backOutDown:isActive}">					
						<view class="cardFont">
							<p>信息市场</p>
						</view>
				</view>
			</view>
			<view class="card-market"
				style="background-image: url(https://img.zcool.cn/community/01d3ab59a6266da8012028a906f418.gif);"
				@tap="requirementMmarket">
				<view class="cardButton">
					<view class="cardFont">
						<p>需求市场</p>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello',
				isActive:false
			}
		},
		onLoad() {

		},
		methods: {
			/* 信息市场跳转 */
			informationMmarket() {
				this.isActive=true;
				this.$u.route({ //可以简写直接写在括号里
					url: 'pages/market/information',
					params: {
						name: ''
					}
				})

			},

			/* 需求市场跳转 */
			requirementMmarket() {
				this.$u.route({ //可以简写直接写在括号里
					url: 'pages/market/requirement',
					params: {
						name: ''
					}
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		position: relative;

		
		/* 背景卡片 */
		.bg-card {
			width: 100%;
			height: 400rpx;
			background: linear-gradient(45deg, #00DAFE, #00A8FF);
			border-bottom: 1rpx solid linear-gradient(45deg, #00DAFE, #45D0EA);
			border-radius: 0rpx 0rpx 50rpx 50rpx;
			z-index: -1;
			
		}

		.card {
			position: absolute;
			top: 300rpx;
			left: 40rpx;

			.card-market {
				width: 670rpx;
				height: 350rpx;
				// 布局
				display: flex;
				align-items: center;
				justify-content: center;
				// 背景边距
				background-size: 100% 100%;
				background-repeat: no-repeat;
				margin: 60rpx auto;
				// 盒子样式
				border-radius: 20rpx;
				box-shadow: 20rpx 0rpx 30rpx 0rpx rgba(0, 0, 0, 0.1),
					-20rpx 0rpx 30rpx 0rpx rgba(0, 0, 0, 0.1),
					0rpx 20rpx 30rpx 0rpx rgba(0, 0, 0, 0.1),
					0rpx -20rpx 30rpx 0rpx rgba(0, 0, 0, 0.1);
				box-sizing: border-box;
				padding: 20rpx;
				font-size: 28rpx;

				.cardButton {
					width: 300rpx;
					height: 150rpx;
					border: 1px solid #000000;
					border-radius: 10px;
					display: flex;
					align-items: center;
					justify-content: center;
					.cardFont{
						color: #FFFFFF;
					}
				}
			}
		}
	}
</style>
