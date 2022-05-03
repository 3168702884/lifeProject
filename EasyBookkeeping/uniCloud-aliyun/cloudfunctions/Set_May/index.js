'use strict';
const db = uniCloud.database()
exports.main = async (event, context) => {
	//event为客户端上传的参数
	const Collection = db.collection('May');
	let res;
	if(event.classIO==1){
		if(event.classification_index01=='1'){
			res=await Collection.where({
				date:event.Date01
			}).update({'shouru.gongzi':event.gongzi})
		}
		else if(event.classification_index01=='2'){
			res=await Collection.where({
				date:event.Date01
			}).update({'shouru.fuli':event.fuli})
		}
		else if(event.classification_index01=='3'){
			res=await Collection.where({
				date:event.Date01
			}).update({'shouru.zhuying':event.zhuying})
		}
		else if(event.classification_index01=='4'){
			res=await Collection.where({
				date:event.Date01
			}).update({'shouru.qitayewu':event.qitayewu})
		}
		else if(event.classification_index01=='5'){
			res=await Collection.where({
				date:event.Date01
			}).update({'shouru.jianzhi':event.jianzhi})
		}
		else if(event.classification_index01=='6'){
			res=await Collection.where({
				date:event.Date01
			}).update({'shouru.dianpu':event.dianpu})
			
		}
		else if(event.classification_index01=='7'){
			res=await Collection.where({
				date:event.Date01
			}).update({'shouru.touzihuihong':event.touzihuihong})
		}
		else if(event.classification_index01=='8'){
			res=await Collection.where({
				date:event.Date01
			}).update({'shouru.hongbao':event.hongbao})
		}
	}
	else{
		if(event.classification_index01=='1'){
			res=await Collection.where({
				date:event.Date01
			}).update({'pay.shop':event.gongzi})
		}
		else if(event.classification_index01=='2'){
			res=await Collection.where({
				date:event.Date01
			}).update({'pay.travel':event.fuli})
		}
		else if(event.classification_index01=='3'){
			res=await Collection.where({
				date:event.Date01
			}).update({'pay.hourse':event.zhuying})
		}
		else if(event.classification_index01=='4'){
			res=await Collection.where({
				date:event.Date01
			}).update({'pay.card':event.qitayewu})
		}
		else if(event.classification_index01=='5'){
			res=await Collection.where({
				date:event.Date01
			}).update({'pay.eat':event.jianzhi})
		}
		else if(event.classification_index01=='6'){
			res=await Collection.where({
				date:event.Date01
			}).update({'pay.play':event.dianpu})
			
		}
		else if(event.classification_index01=='7'){
			res=await Collection.where({
				date:event.Date01
			}).update({'pay.shuidian':event.touzihuihong})
		}
		else if(event.classification_index01=='8'){
			res=await Collection.where({
				date:event.Date01
			}).update({'pay.richang':event.hongbao})
		}
	}
	return {
		data:res
	}
};

