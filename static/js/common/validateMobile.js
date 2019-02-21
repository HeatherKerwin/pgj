/**
 * 验证手机号码格式是否存在
 * @param {Object} mob
 */
function validateMobile(mobile){
	var validate = /^(13[0-9]|15[012356789]|17[01235678]|18[0-9]|14[57])[0-9]{8}$/;
    var flag = validate.test(mobile); //true
    return flag;
}