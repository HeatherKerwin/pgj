// JavaScript Document

function autoWidth(){
    var w = window.innerWidth/7.50;
    if(w > 100){
        w=100;
    }
    document.documentElement.style.fontSize = w + 'px'
    window.onresize = function () {
        var w = window.innerWidth/7.50
        if(w > 100){
            w=100
        }
        document.documentElement.style.fontSize = w + 'px'
    }
}

//loading
$(window).load(function() {
    $("#status").fadeOut();
    $("#preloader").delay(350).fadeOut("slow");
    
    $("input[type='radio']").change(function () {//加载单选按钮效果
	    $(this).parents("ul").children("li").children("label").css({"background-color":"#fff","border-color":"#85878a","color":"#000"});
	    if ($(this).attr("checked") == "checked") {
			$(this).parents("li").children("label").css({"background-color":"#e84c3d","border-color":"#e84c3d","color":"#fff"});
	    }
	});
});

/**
 * 返回上一页
 */
$(".backBtn").on("click",function(){
    window.history.back(-1);
});

/**
 * Created by Ry-kaifa on 2016/10/15.
 */
//背书手数加减
var setAmount1 = {
    min:0,
    max:999,
    reg:function(x) {
        return new RegExp("^[0-9]\\d*$").test(x);
    },
    amount:function(obj, mode) {
        var x = $(obj).val();
        if (this.reg(x)) {
            if (mode) {
                x++;
            } else {
                x--;
            }
        } else {
            uexToast("请输入正确的背书手数！");
            $(obj).val(0);
        }
        return x;
    },
    reduce:function(obj) {
        var x = this.amount(obj, false);
        if (x >= this.min) {
            $(obj).val(x);
            recalc();
        } else {
            uexToast("背书手数最少为" + this.min);
            $(obj).val(0);
        }
    },
    add:function(obj) {
        var x = this.amount(obj, true);
        if (x <= this.max) {
            $(obj).val(x);
        } else {
            uexToast("背书手数最多为" + this.max);
            $(obj).val(999);
        }
    },
    modify:function(obj) {
        var x = $(obj).val();
        if (x < this.min || x > this.max || !this.reg(x)) {
            uexToast("请输入正确的背书手数！");
            $(obj).val(0);
        }
    }
}
/*
    上传图片
 */
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#blah').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

$("#imgInp").change(function(){
    readURL(this);
});