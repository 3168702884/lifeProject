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
				<navigator url="" open-type="redirect" hover-class="other-navigator-hover">
					<view class="sr">


						<view>
							<image src="../../static/tabbar/sr.png" mode="aspectFill"></image>
						</view>
						<view>
							<p>收入</p>
						</view>


					</view>
				</navigator>
				<navigator url="tongji-expend" open-type="reLaunch" hover-class="other-navigator-hover">
					<view class="zc">
						<image src="../../static/tabbar/zc.png" mode="aspectFill"></image>
						<view>
							<p>支出</p>
						</view>

					</view>
				</navigator>
			</view>
		</view>
		<!-- <view class="cxkuai">
			<view class="cx">
				<view class="img">
					<image src="../../static/income-icon/gongzi.png" mode=""></image>
				</view>
				<view class="num">
					
				</view>
				
			</view>
		
			<view class="cx">
				<view class="img">
					<image src="../../static/income-icon/fenhong.png" mode="widthFix"></image>
				</view>
				<view class="num">
					
				</view>
			</view>
			<view class="cx">
				<view class="img">
					<image src="../../static/income-icon/gongsi.png" mode="widthFix"></image>
				</view>
				<view class="num">
					
				</view>
			</view>
			<view class="cx">
				<view class="img">
					<image src="../../static/income-icon/yewu.png" mode="widthFix"></image>
				</view>
				<view class="num">
					
				</view>
			</view>
			<view class="cx">
				<view class="img">
					<image src="../../static/income-icon/jianzhi.png" mode="widthFix"></image>
				</view>
				<view class="num">
					
				</view>
			</view>
			<view class="cx">
				<view class="img">
					<image src="../../static/income-icon/dianpu.png" mode="widthFix"></image>
				</view>
				<view class="num">
					
				</view>
			</view>
			<view class="cx">
				<view class="img">
					<image src="../../static/income-icon/touzi.png" mode="widthFix"></image>
				</view>
				<view class="num">
					
				</view>
			</view>
			<view class="cx">
				<view class="img">
					<image src="../../static/income-icon/hongbao.png" mode="widthFix"></image>
				</view>
				<view class="num">
					
				</view>
			</view>
		</view>
		<view class="zonghe">
			
		</view>
		 -->
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
				classnum: {
					classIO:1,
					
					Date01: currentDate,
					
					classification_index01: "",
				
					gongzi: '', 
				
				
					fuli: '',
				
				
					zhuying: '',
				
				
					qitayewu: '',
				
				
					jianzhi: '',
				
				
					dianpu: '',
				
				
					touzihuihong: '',
				
				
					hongbao: '',
				
				}
			} 
		},
		onLoad() {
			uniCloud.callFunction({
				name:'Selec_Dec',
				data:this.classnum.Date01
			}).then((res)=>{
				this.classnum.gongzi=res.result.data.data[0].shouru.gongzi;
				this.classnum.fuli=res.result.data.data[0].shouru.fuli;
				this.classnum.zhuying=res.result.data.data[0].shouru.zhuying;
				this.classnum.qitayewu=res.result.data.data[0].shouru.qitayewu;
				this.classnum.jianzhi=res.result.data.data[0].shouru.jianzhi;
				this.classnum.dianpu=res.result.data.data[0].shouru.dianpu;
				this.classnum.touzihuihong=res.result.data.data[0].shouru.touzihuihong;
				this.classnum.hongbao=res.result.data.data[0].shouru.hongbao;
				console.log(this.classnum.gongzi);
			})
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
			getServerData(){
				uni.request({
					url: 'https://www.ucharts.cn/data.json',
					data:{
					},
					success: function(res) {
						console.log(res.data.data)
						let Arcbar1={series:[]};
						let Arcbar2={series:[]};
						let Arcbar3={series:[]};
						//这里我后台返回的是数组，所以用等于，如果您后台返回的是单条数据，需要push进去
						Arcbar1.series=res.data.data.Arcbar1.series;
						Arcbar2.series=res.data.data.Arcbar2.series;
						Arcbar3.series=res.data.data.Arcbar3.series;
						_self.textarea = JSON.stringify(res.data.data.Arcbar1);
						_self.showArcbar("canvasArcbar1",Arcbar1);
						_self.showArcbar2("canvasArcbar2",Arcbar2);
						_self.showArcbar3("canvasArcbar3",Arcbar3);
					},
					fail: () => {
						_self.tips="网络错误，小程序端请检查合法域名";
					},
				});
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
			}
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
		height: 200rpx
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
		margin-bottom: 150rpx;
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
	//查询的板块
	.cxkuai{
		width: 90%;
		height: 500rpx;
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
	.cxkuai image{
		width: 100rpx;
		height: 100rpx;
	}
	.cx{
		display: flex;
		justify-content: space-around;
		align-items: center;
	
		width: 40%;
		height: 100rpx;
		
	}
	.cx .image{
		//图片在盒子里垂直水平居中
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
		width: 100rpx;
		height: 100rpx;
		border-radius: 20rpx;
	}
	.zonghe{
		width: 90%;
		height: 100rpx
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
</style>
