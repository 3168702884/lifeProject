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
			<view class="xianshi-img">
				<template v-if="classification_index===1||classification_index===0">
				  <image src="../../static/income-icon/gongzi.png" mode="widthFix"></image>
				</template>
				<template v-else-if="classification_index===2">
				  <image src="../../static/income-icon/fenhong.png" mode="widthFix"></image>
				</template>
				<template v-else-if="classification_index===3">
				  <image src="../../static/income-icon/gongsi.png" mode="widthFix"></image>
				</template>
				<template v-else-if="classification_index===4">
				  <image src="../../static/income-icon/yewu.png" mode="widthFix"></image>
				</template>
				<template v-else-if="classification_index===5">
				  <image src="../../static/income-icon/jianzhi.png" mode="widthFix"></image>
				</template>
				<template v-else-if="classification_index===6">
				  <image src="../../static/income-icon/dianpu.png" mode="widthFix"></image>
				</template>
				<template v-else-if="classification_index===7">
				  <image src="../../static/income-icon/touzi.png" mode="widthFix"></image>
				</template>
				<template v-else-if="classification_index===8">
				  <image src="../../static/income-icon/hongbao.png" mode="widthFix"></image>
				</template>
			</view>
			<view class="xianshi">
				<view class="shuzi">
					{{num}}
				</view>
				<view class="xiahuaxian">
				
				</view>
			</view>
			<view class="nav">
				<navigator url="index-income" open-type="reLaunch" hover-class="other-navigator-hover">
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
		<view class="mid">

			<view class="box">
				<view class="img" @tap="setImgClass(1)">
					<image src="../../static/income-icon/gongzi.png" mode="aspectFill"></image>
					<p>工资</p>
				</view>
			</view>
			<view class="box">
				<view class="img" @tap="setImgClass(2)">
					<image src="../../static/income-icon/fenhong.png" mode="aspectFit"></image>
					<p>分红</p>
				</view>
			</view>
			<view class="box">
				<view class="img" @tap="setImgClass(3)">
					<image src="../../static/income-icon/gongsi.png" mode="aspectFill"></image>
					<p>公司</p>
				</view>
			</view>
			<view class="box">
				<view class="img" @tap="setImgClass(4)">
					<image src="../../static/income-icon/yewu.png" mode="aspectFill"></image>
					<p>业务</p>
				</view>
			</view>
			<view class="box">
				<view class="img" @tap="setImgClass(5)">
					<image src="../../static/income-icon/jianzhi.png" mode="aspectFill"></image>
					<p>兼职</p>
				</view>
			</view>
			<view class="box">
				<view class="img" @tap="setImgClass(6)">
					<image src="../../static/income-icon/dianpu.png" mode="aspectFill"></image>
					<p>店铺</p>
				</view>
			</view>
			<view class="box">
				<view class="img" @tap="setImgClass(7)">
					<image src="../../static/income-icon/touzi.png" mode="aspectFill"></image>
					<p>投资</p>
				</view>
			</view>
			<view class="box">
				<view class="img" @tap="setImgClass(8)">
					<image src="../../static/income-icon/hongbao.png" mode="aspectFill"></image>
					<p>红包</p>
				</view>
			</view>
		</view>
		<view class="numbutton">
			<view class="btns">


				<view>
					<view hover-class="bg" @tap="numBtn" data-val="7">7</view>
					<view hover-class="bg" @tap="numBtn" data-val="8">8</view>
					<view hover-class="bg" @tap="numBtn" data-val="9">9</view>

				</view>

				<view>
					<view hover-class="bg" @tap="numBtn" data-val="4">4</view>
					<view hover-class="bg" @tap="numBtn" data-val="5">5</view>
					<view hover-class="bg" @tap="numBtn" data-val="6">6</view>
				</view>



				<view>
					<view hover-class="bg" @tap="numBtn" data-val="1">1</view>
					<view hover-class="bg" @tap="numBtn" data-val="2">2</view>
					<view hover-class="bg" @tap="numBtn" data-val="3">3</view>
				</view>



				<view>
					<view hover-class="bg" @tap="numBtn" data-val="0">0</view>
					<view hover-class="bg" @tap="dotBtn">.</view>

				</view>
			</view>
			<view class="btns-gn">
				<view>
					<view @tap="delBtn">清除</view>

				</view>

				<view>
					<view @tap="jl">记录</view>
				</view>
			</view>
		</view>
		
	</view>
</template>

<script>
	// 引入组件
	// import indexList from
	export default {
		components: {

		},
		data() {
			const currentDate = this.getDate({
				format: true
			})
			return {
				//时间
				date: currentDate,
				month:"",
				//类别
				classification_index: 0,
				
				classnum2: {
					classIO:'pay',
					
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

				},
				item: 0,
				num: '0',
				
			}
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
				this.month=month;
				day = day > 9 ? day : '0' + day;
				return `${year}-${month}-${day}`;
			},
			//点击图片,把图片的索引作为参数赋值给类别索引
			//输入数字逻辑
			//重写set函数
			// 数字按钮事件处理函数
			setData: function(obj) {
				let that = this;
				let keys = [];
				let val, data;
				Object.keys(obj).forEach(function(key) {
					keys = key.split('.');
					val = obj[key];
					data = that.$data;
					keys.forEach(function(key2, index) {
						if (index + 1 == keys.length) {
							that.$set(data, key2, val);
						} else {
							if (!data[key2]) {
								that.$set(data, key2, {});
							}
						}
						data = data[key2];
					})
				});
			},
			numBtn: function(e) {
				var num = e.target.dataset.val;

				if (this.num === '0' || this.isClear) {
					this.setData({
						num: num
					});
					this.isClear = false;
				} else {
					this.setData({
						num: this.num + num
					});
				}
			},

			// 小数点事件处理函数
			dotBtn: function() {
				if (this.isClear) {
					this.setData({
						num: '0.'
					});
					this.isClear = false;
					return;
				}

				if (this.num.indexOf('.') >= 0) {
					return;
				}

				this.setData({
					num: this.num + '.'
				});
			},
			// DEL按钮处理函数
			delBtn: function() {
				var num = this.num.substr(0, this.num.length - 1);
				this.setData({
					num: num === '' ? '0' : num
				});
			},
			// C按钮事件处理函数
			resetBtn: function() {
				this.result = null;
				this.isClear = false;
				this.setData({
					num: '0',
					op: ''
				});
			},
			//点击图片函数
			setImgClass: function(index) {
				switch (index) {
					case 1:
						this.classification_index = 1;
						this.classnum2.classification_index01 = 1;
						console.log(1);
						break;
					case 2:
						this.classification_index = 2;
						this.classnum2.classification_index01 = 2;
						console.log(2);
						break;
					case 3:
						this.classification_index = 3;
						this.classnum2.classification_index01 = 3;
						console.log(3);
						break;
					case 4:
						this.classification_index = 4;
						this.classnum2.classification_index01 = 4;
						console.log(4);
						break;
					case 5:
						this.classification_index = 5;
						this.classnum2.classification_index01 = 5;

						console.log(5);
						break;
					case 6:
						this.classification_index = 6;
						this.classnum2.classification_index01 = 6;
						console.log(6);
						break;
					case 7:
						this.classification_index = 7;
						this.classnum2.classification_index01 = 7;
						console.log(7);
						break;
					case 8:
						this.classification_index = 8;
						this.classnum2.classification_index01 = 8;
						console.log(8);
						break;
				}
			},
			//确认键函数
			jl: function() {
				switch (this.classification_index) {
					case 1:
						this.classnum2.gongzi = this.num;
						console.log('类别1数传入成功');
						break;
					case 2:
						this.classnum2.fuli = this.num;
						console.log('类别3数传入成功');
						break;
					case 3:
						this.classnum2.zhuying = this.num;
						console.log('类别3数传入成功');
						break;
					case 4:
						this.classnum2.qitayewu = this.num;
						console.log('类别4数传入成功');
						break;
					case 5:
						this.classnum2.jianzhi = this.num;
						console.log('类别5数传入成功');
						break;
					case 6:
						this.classnum2.dianpu = this.num;
						console.log('类别6数传入成功');
						break;
					case 7:
						this.classnum2.touzihuihong = this.num;
						console.log('类别7数传入成功');
						break;
					case 8:
						this.classnum2.hongbao = this.num;
						console.log('类别8数传入成功');
						break;
				}
				switch(this.month){
					case 1:uniCloud.callFunction({
							name:'Set_Jan',
							data:this.classnum2,
							}).then((res)=>{
							console.log(res);
							});break;
					case 2:uniCloud.callFunction({
							name:'Set_Feb',
							data:this.classnum2,
							}).then((res)=>{
							console.log(res);
							});break;
					case 3:uniCloud.callFunction({
							name:'Set_Mar',
							data:this.classnum2,
							}).then((res)=>{
							console.log(res);
							});break;
					case 4:uniCloud.callFunction({
							name:'Set_Apr',
							data:this.classnum2,
							}).then((dt)=>{
							console.log(dt);
							});break;
					case 5:uniCloud.callFunction({
							name:'Set_May',
							data:this.classnum2,
							}).then((res)=>{
							console.log(res);
							});break;
					case 6:uniCloud.callFunction({
							name:'Set_June',
							data:this.classnum2,
							}).then((res)=>{
							console.log(res);
							});break;
					case 7:uniCloud.callFunction({
							name:'Set_July',
							data:this.classnum2,
							}).then((res)=>{
							console.log(res);
							});break;
					case 8:uniCloud.callFunction({
							name:'Set_August',
							data:this.classnum2,
							}).then((res)=>{
							console.log(res);
							});break;
					case 9:uniCloud.callFunction({
							name:'Set_Sep',
							data:this.classnum2,
							}).then((res)=>{
							console.log(res);
							});break;
					case 10:uniCloud.callFunction({
							name:'Set_Oct',
							data:this.classnum2,
							}).then((res)=>{
							console.log(res);
							});break;
					case 11:uniCloud.callFunction({
							name:'Set_Nov',
							data:this.classnum2,
							}).then((res)=>{
							console.log(res);
							});break;
					case 12:uniCloud.callFunction({
							name:'Set_Dec',
							data:this.classnum2,
							}).then((res)=>{
							console.log(res);
							});break;
					
							
				}
				
				
			},
			cx:function(){
				
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
	.start image{
		width: 50rpx;
		height: 60rpx;
	}
	.start p{
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
		.xianshi-img{
			border-radius: 20rpx;
			width: 60rpx;
			height: 60rpx;
		}
		.xianshi {
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			margin-right: 20rpx;
		}
			.shuzi {
				flex-grow: 2;
			}
			.xiahuaxian{
				width: 120rpx;
				height:10rpx;
				background-color: #000;
				border:  20rpx soild #000;
				border-radius:50rpx;
			}
		.nav{
			width: 120rpx;
			height:100rpx;
			/* display: flex;
			flex-direction: column; */
			padding-left: -20rpx;
		}
			.nav image{
				width: 50rpx;
				height: 40rpx;
			}
			.nav .sr{
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
			.nav .zc{
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
	
	//中间
	.mid {
		width: 90%;
		height: 300rpx
			/* 40% */
		;
		margin: 50rpx;
		/* padding-bottom: 50rpx; */
		border-radius: 20rpx;
		box-shadow: 20rpx 0rpx 30rpx 0rpx rgba(0, 0, 0, 0.1),
			-20rpx 0rpx 30rpx 0rpx rgba(0, 0, 0, 0.1),
			0rpx 20rpx 30rpx 0rpx rgba(0, 0, 0, 0.1),
			0rpx -20rpx 30rpx 0rpx rgba(0, 0, 0, 0.1);
		display: flex;
		justify-content: center;
		align-items: center;
		flex-wrap: wrap;

	}

	//盒子内样式
	.mid .box {
		display: flex;
		justify-content: space-around;
		align-items: center;
		flex-direction: column;
		width: 100rpx;
		height: 100rpx;
		margin: 10rpx;
		padding: 10rpx;
		//鼠标放上去样式
		cursor: pointer;

	}

	.mid .box .img {
		//图片在盒子里垂直水平居中
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
		width: 100rpx;
		height: 100rpx;
		border-radius: 20rpx;
		/* background-color: aqua; */
		//盒子阴影
		box-shadow: 18px 18px 30px rgba(0, 0, 0, 0.2),
			-18px -18px 30px rgba(255, 266, 255, 1),
			inset 10rpx 10rpx 30rpx rgb(0, 0, 0, 0.1),
			inset -10rpx -10rpx 30rpx rgba(255, 266, 255, 1);
		//过度时间先快速后慢速
		/* transition: all 0.2s ease-out; */

	}

	.mid .box .img image {
		width: 60rpx;
		height: 60rpx;
		/* transition: all 0.2s ease-out; */
	}

	.mid .box p {
		color: #333333;
		font-size: xx-small;
		font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	}

	/* .mid .box:hove{
			box-shadow:0 0 0 rgba(0,0,0,0.2),rgba(255,255,255,0.8),
			 inset 18rpx 18rpx 30rpx rgb(0,0,0,0.1) ,
			 inset -18rpx -18rpx 30rpx rgba(255,266,255,1) ;
		}
		.mid .box:hover img{
			width: 58rpx;
		} */

	//数字键盘
	.numbutton {
		width: 90%;
		height: 410rpx
			/* 40% */
		;

		border-radirs: 50rpx;
		margin: 50rpx;
		border-radius: 20rpx;
		box-shadow: 10rpx 0rpx 30rpx 0rpx rgba(0, 0, 0, 0.1),
			-10rpx 0rpx 30rpx 0rpx rgba(0, 0, 0, 0.1),
			0rpx 10rpx 30rpx 0rpx rgba(0, 0, 0, 0.1),
			0rpx -10rpx 30rpx 0rpx rgba(0, 0, 0, 0.1);
		display: flex;


	}

	.bg {
		background: #eee;
	}

	.btns {
		flex-basis: 75%;
		display: flex;
		flex-direction: column;
		font-size: 17pt;
		border-top: 1rpx solid #ccc;
		border-left: 1rpx solid #ccc;
		border-radirs: 50rpx;
	}

	.btns>view {
		flex: 1;
		display: flex;
	}

	.btns>view>view {
		flex: 1;
		border-right: 1rpx solid #ccc;
		border-bottom: 1rpx solid #ccc;
		box-sizing: border-box;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.btns>view:last-child>view:first-child {
		flex-basis: 33%;
	}

	.btns-gn {
		flex-basis: 25%;
		display: flex;
		flex-direction: column;
		justify-content: space-around;
		align-items: center;
		font-size: 17pt;
		background: #F4F5F6;

	}
</style>
