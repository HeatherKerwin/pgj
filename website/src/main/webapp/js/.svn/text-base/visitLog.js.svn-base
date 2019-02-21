/**
 * 功能统计（用于无实际功能不调用拦截器） 
 * @param {Object} code
 */
function actionLog(url,code){
    $.ajax({
        url:url + "/pc/actionlog",
        type:"post",
        data:{"code":code},
        dataType:"json",
        success:function(data) {
        }, 
    });
}