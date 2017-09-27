/**
 * verify.js
 */

/**
 * 判断是否是中文
 */
function checkIsChinese(value){
	//var reg=/^[\u0391-\uFFE5]+$/;
	var reg = /^[\u4e00-\u9fa5]{0,}$/;
	if(!reg.test(value)){ 
		return false;
	}
	return true;
}

/**
 * 判断是否是英文字母
 */
function checkIsEnglish(value){ 
	var reg=/^[a-zA-Z]*$/; 
	if(!reg.test(value)){
		return false; 
	}
	return true;
}

/**
 * 判断是否是数字
 */
function checkIsNumber(value){
	var reg = /^[0-9]+$/; 
	if(!reg.test(value)){
		return false; 
	}
	return true;
}

/**
 * 判断是否是英文字母和数字
 */
function checkIsEnglishOrNum(value){
	var reg=/^[0-9a-zA-Z]*$/; 
	if(!reg.test(value)){ 
		return false; 
	}
	return true;
}

/**
 * 验证邮箱
 */
function checkEmail(value){
	//var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	if(!reg.test(value)) {
		return false;
	}
	return true;
}

/**
 * 验证手机号码
 * @param value
 * @returns
 */
function checkMobile(value) {
	var reg = /^[1][0-9]{10}$/;
	return reg.test(value);
}

/**
 * 验证电话号码
 * @param value
 * @returns
 */
function checkTel(value){
	var reg = /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
	if(!reg.test(value)){
		return false;
    }
	return true;
}

/**
 * 去掉字符串头尾空格
 * @param value
 * @returns
 */
function valueTrim(value) {   
    return value.replace(/(^\s*)|(\s*$)/g, "");   
}

/**
 * 验证Url
 */
function checkUrl(value){
	var reg = /^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$/;
	if(!reg.test(value)){
		return false;
    }
	return true;
}

/**
 * 验证IP地址
 */
function checkIp(value){
	var reg = /((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)/;
	// reg = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
	if(!reg.test(value)){
		return false;
    }
	return true;
}

/**
 * 验证用户名
 */
function checkUserName(value){
	var reg = /^[a-z0-9_-]{3,16}$/;
	if(!reg.test(value)){
		return false;
    }
	return true;
}

/**
 * 验证密码
 */
function checkPassWord(value){
	var reg = /^[a-z0-9_-]{1,18}$/;
	if(!reg.test(value)){
		return false;
    }
	return true;
}
