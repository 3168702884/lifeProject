<template>
	<view class="container">
		<!-- form表单 -->
		<u-form :model="form" ref="uForm">
			<!-- prop参数绑定name验证规则 -->
			<u-form-item required label="标题" prop="title">
				<u-input v-model="form.title" />
			</u-form-item>
			<u-form-item required label="简介" prop="jianjie">
				<u-input v-model="form.goodIntroduction" />
			</u-form-item>
			<u-form-item required label="学校" prop="school">
				<u-input v-model="form.school" />
			</u-form-item>
			<u-form-item label="上传图片" prop="photo" label-width="150" multiple="multiple">
				<u-upload width="160" height="160" :auto-upload="autoUpload" ref="uUpload" name="file" :form-data="form"
					:max-count="maxCount" :action="action"></u-upload>
			</u-form-item>
		</u-form>

		<u-button @click="submit">提交</u-button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				//存储图片路径
				// files: [],
				maxCount: 1,
				action: 'http://192.168.43.111:8812/releaseShop',
				autoUpload: false,
				//表单数据
				form: {
					wxID: '',
					title: '',
					goodIntroduction: '',
					school:''
				},
				//表单验证规则
				rules: {
					title: [{
						required: true,
						message: '请输入标题',
						// 可以单个或者同时写两个触发验证方式 
						trigger: ['change', 'blur'],
					}],
					jianjie: [{
						min: 5,
						message: '简介不能少于5个字',
						trigger: 'change'
					}],
					school:[{
						required: true,
						message: '要输入你的信息去哪个校园~',
						// 可以单个或者同时写两个触发验证方式 
						trigger: ['change', 'blur'],
					}]
				},
			}
		},
		onLoad(option) {
			let Login = uni.getStorageSync('Login');
			this.form.wxID=Login;
		},
		// 必须要在onReady生命周期，因为onLoad生命周期组件可能尚未创建完毕
		onReady() {
			// 开始的时候把验证规则存储进去
			this.$refs.uForm.setRules(this.rules);
		},
		computed: {},
		methods: {
			/* 表单提交 */
			submit() {
				// 表单验证后
				this.$refs.uForm.validate(valid => {
					if (valid) {
						console.log('验证通过,开始请求');
						//手动上传图片
						this.$refs.uUpload.upload();
					} else {
						console.log('验证失败');
					}
				});

			},
		}
	}
</script>

<style>
	image {
		will-change: transform
	}

	.container {
		padding: 20px;
		font-size: 14px;
		line-height: 24px;
	}
</style>
