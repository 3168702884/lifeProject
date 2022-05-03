<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	@import './common/qiun.css';
	/*每个页面公共css */
	
	page{
		display: flex;
		flex-direction: column;
		height: 100%;
		
	}
</style>
