'use strict';
const db = uniCloud.database()
exports.main = async (event, context) => {
	//event为客户端上传的参数
	const Collection = db.collection('June');
	let res= await Collection.where({
		date:event
	}).field({shouru:true}).get()
	return {
		data:res
	}
};
