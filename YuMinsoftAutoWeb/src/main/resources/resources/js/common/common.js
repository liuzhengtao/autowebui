/**
 * common.js
 */

//layui模块
layui.use(["element","form"], function(){
	var form = layui.form();	
	//监听select选择
	form.on("select(lay-select)", function(data){
		//console.log(data.elem); 得到select原始DOM对象
		//console.log(data.value); 得到被选中的值
		
		//设置select的值
		$("#" + data.elem.name).val(data.value);
	});	
	//监听checkbox复选
	form.on("checkbox(lay-checkbox)", function(data){
		//console.log(data.elem.checked); 是否被选中，true或者false
		//console.log(data.value); //复选框value值，也可以通过data.elem.value得到
		
	});
	//监听radio单选
	form.on("radio(lay-radio)", function(data){
		//console.log(data.elem); //得到radio原始DOM对象
		//console.log(data.value); //被点击的radio的value值
		
	});
	
});

/**
 * 弹出一个Tips层
 * msg 
 */
function tuTips(msg,id){
	//
	layer.tips(
		msg,
		id,
		{
			tips:2
		}
	);
}

/**
 * 弹出一个Alert提示框
 * msg 
 */
function tuAlert(msg){
	//
	layer.alert(msg, {icon: 0,offset: ["40%"]});	
}

/**
 * 弹出一个Succes提示框
 * msg 
 */
function tuSucces(msg){
	//
	layer.alert(msg, {icon: 1,offset: ["40%"]});	
}

/**
 * 弹出一个Error提示框
 * msg 
 */
function tuError(msg){
	//
	layer.alert(msg, {icon: 2,offset: ["40%"]});
}

/**
 * 弹出一个Warn提示框
 * flag
 * msg 
 */
function tuWarn(flag,msg){
	//
	if(flag == 1){
		tuSucces(msg);
	}else {
		tuError(msg);
	}
}

/**
 * 弹出一个Confirm提示框
 * msg 
 */
function tuConfirm(msg,onYes){
	//
	layer.confirm(
		msg,
		{
			icon: -1,
			title:'提示',
			offset: ["40%"]
		},
		onYes//确认的回调
	);
}

/**
 * 弹出一个iframe层
 * @param title
 * @param url
 * @param width
 * @param height
 * @returns
 */
function tuDialog(title,url,width,height){
	layer.open({
		type: 2,
		title: title,
		maxmin: true,
		resize: false,
		scrollbar: false,
		shadeClose: false, //点击遮罩关闭层
		area : [width , height],
		content: url
	});
}

/**
 * 弹出一个iframe层New
 * @param title
 * @param url
 * @param width
 * @param height
 * @param offset
 * @returns
 */
function tuDialogNew(title,url,width,height,offset){
	//
	var v_offset = "50px";
	if(isNotNull(offset)){
		v_offset = offset;
	}
	layer.open({
		type: 2,
		title: title,
		maxmin: true,
		resize: false,
		scrollbar: false,
		shadeClose: false, //点击遮罩关闭层
		area : [width , height],
		offset: [v_offset],
		content: url
	});
}

/**
 * 关闭iframe层
 * @returns
 */
function cloaseTuDialog(){
	//当你在iframe页面关闭自身时
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index); //再执行关闭
}

/**
 * 判断null 
 * @param value
 * @returns
 */
function isNull(value){
	return (value == "" || value == undefined || value == null) ? true : false; 
}

/**
 * 判断非null 
 * @param value
 * @returns
 */
function isNotNull(value){
	return !isNull(value); 
}
