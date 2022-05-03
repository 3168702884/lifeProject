<template>
	<view>
		<view class="start">
			<view class="shijian">

				<view class="uni-list-cell-db">
					<picker mode="date" :value="date" :start="startDate" :end="endDate" @change="bindDateChange">
						<view class="uni-input">{{date}}</view>
					</picker>
				</view>

			</view>
			<view class="nav">
				<navigator url="tongji-income" open-type="reLaunch" hover-class="other-navigator-hover">
					<view class="sr">


						<view>
							<image src="../../static/tabbar/sr.png" mode="aspectFill"></image>
						</view>
						<view>
							<p>收入</p>
						</view>


					</view>
				</navigator>
				<navigator url="" open-type="reLaunch" hover-class="other-navigator-hover">
					<view class="zc">
						<image src="../../static/tabbar/zc.png" mode="aspectFill"></image>
						<view>
							<p>支出</p>
						</view>

					</view>
				</navigator>
			</view>
		</view>
		<view class="qiun-columns">
			<view class="qiun-bg-white qiun-title-bar qiun-common-mt">
				<view class="qiun-title-dot-light">饼状图</view>
			</view>
			<view class="qiun-charts">
				<image src="../../static/bzt.png" mode="widthFix"></image>
				<canvas canvas-id="canvasPie" id="canvasPie" class="charts" @touchstart="touchPie"></canvas>
			</view>
		</view>
	</view>
</template>

<script>
	import uCharts from '@/components/u-charts/u-charts.js';
	var _self;
	var canvaPie = null;
	export default {
		data() {
			const currentDate = this.getDate({
				format: true
			})
			return {
				date: currentDate,
				cWidth: '',
				cHeight: '',
				pixelRatio: 1,
				serverData: '',
			}
		},
		onLoad() {
			_self = this;
			this.cWidth = uni.upx2px(750);
			this.cHeight = uni.upx2px(500);
			this.getServerData();
		},
		methods: {
			//时间选择函数
			bindDateChange: function(e) {
				this.date = e.target.value
			},
			getDate: function(type) {
				const date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();

				if (type === 'start') {
					year = year - 60;
				} else if (type === 'end') {
					year = year + 2;
				}
				month = month > 9 ? month : '0' + month;
				this.month = month;
				day = day > 9 ? day : '0' + day;
				return `${year}-${month}-${day}`;
			},
			//饼状图
			getServerData:function() {
				//日统计
				// let dayDate = new Date();
				// callCloudFunction('money_query', {
				// 	openid: getUserOpenid(),
				// 	startDate: new Date(dayDate.getFullYear(), dayDate.getMonth(), dayDate.getDate(), 0, 0, 0),
				// 	endDate: new Date(dayDate.getFullYear(), dayDate.getMonth(), dayDate.getDate(), 23, 59, 59)
				// }, (dayRes) => {
				// 	if (dayRes.data == null)
				// 		return;
				// 	let Pie = {
				// 		series: []
				// 	};
				// 	let series = [];
				
				// 	let sum = 0; //总数
				// 	let typeMap = {};
				// 	dayRes.data.forEach((data) => {
				// 		if (data.mark == null || data.mark != 'income') {
				// 			let type = data['type'];
				// 			let money = new Number(data['money']);
				// 			let moneySum = typeMap[type];
				// 			if (moneySum == null || moneySum <= 0) {
				// 				moneySum = money;
				// 			} else {
				// 				moneySum += money;
				// 			}
				// 			sum += money;
				// 			typeMap[type] = moneySum;
				// 		}
				// 	});
				// 	for (let key in typeMap) {
				// 		let serie = {
				// 			name: key,
				// 			data: typeMap[key]
				// 		};
				// 		series.push(serie);
				// 	}
				// 	_self.daySum = sum.toFixed(2);
				
				// 	Pie.series = series;
				// 	// _self.textarea = JSON.stringify({
				// 	// 	series: series
				// 	// });
				// 	_self.showPie("dayPie", Pie);
				
				// });
				
			},
			showPie(canvasId, chartData) {
				canvaPie = new uCharts({
					$this: _self,
					canvasId: canvasId,
					type: 'pie',
					fontSize: 11,
					legend: {
						show: true
					},
					background: '#FFFFFF',
					pixelRatio: _self.pixelRatio,
					series: chartData.series,
					animation: true,
					width: _self.cWidth * _self.pixelRatio,
					height: _self.cHeight * _self.pixelRatio,
					dataLabel: true,
					extra: {
						pie: {
							lableWidth: 15
						}
					},
				});
			},
			touchPie(e) {
				canvaPie.showToolTip(e, {
					format: function(item) {
						return item.name + ':' + item.data
					}
				});
			},
		},
		computed: {
			startDate() {
				return this.getDate('start');
			},
			endDate() {
				return this.getDate('end');
			}
		}
	}
</script>

<style>
	//头部
	.start {
		width: 90%;
		height: 140rpx
			/* 20% */
		;
		margin: 50rpx;
		border-radius: 20rpx;
		box-shadow: 20rpx 0rpx 30rpx 0rpx rgba(0, 0, 0, 0.1),
			-20rpx 0rpx 30rpx 0rpx rgba(0, 0, 0, 0.1),
			0rpx 20rpx 30rpx 0rpx rgba(0, 0, 0, 0.1),
			0rpx -20rpx 30rpx 0rpx rgba(0, 0, 0, 0.1);
		display: flex;
		justify-content: space-around;
		align-items: center; 
	}

	.start image {
		width: 50rpx; 
		height: 60rpx;
	}

	.start p {
		color: #333333;
		font-size: small;
		font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	}

	//头部下各个板块
	.shijian {
		box-shadow: 10rpx 0rpx 10rpx 0rpx rgba(0, 0, 0, 0.1),
			-10rpx 0rpx 10rpx 0rpx rgba(0, 0, 0, 0.1),
			0rpx 10rpx 10rpx 0rpx rgba(0, 0, 0, 0.1),
			0rpx -10rpx 10rpx 0rpx rgba(0, 0, 0, 0.1);
		border-radius: 20rpx;
	}

	.nav {
		width: 120rpx;
		height: 100rpx;
		/* display: flex;
			flex-direction: column; */
		padding-left: -20rpx;
	}

	.nav image {
		width: 50rpx;
		height: 40rpx;
	}

	.nav .sr {
		width: 130rpx;
		height: 60rpx;
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-items: center;
		border: 1rpx solid #000;
		border-radius: 20rpx;
		/* padding-bottom: 10rpx; */
	}

	.nav .zc {
		width: 130rpx;
		height: 60rpx;
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-items: center;
		border: 1rpx solid #000;
		border-radius: 20rpx;
		/* padding-top: 10rpx; */
	}
</style>
