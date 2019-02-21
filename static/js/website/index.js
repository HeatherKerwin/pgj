/**
 * Created by Ry-kaifa on 2016/10/15.
 */
//上传图片
function fileSelect() {
    document.getElementById("fileToUpload").click();
}
function fileSelected() {
    // 文件选择后触发次函数
}

//返回顶部
function b() {
    // h = $(window).height(),
    h = 1,
    t = $(document).scrollTop(),
    t > h ? $("#moquu_top").show() : $("#moquu_top").hide()
}
$(document).ready(function() {
    b();
    $("#moquu_top").click(function() {
        $(document).scrollTop(0)
    });
}),
    $(window).scroll(function() {
        b();
    });

