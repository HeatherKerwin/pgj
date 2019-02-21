/**
 * Created by Administrator on 2016/8/5.
 */
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
/*$('.btn-bot').click(function () {
    $('.nav-con').animate({'height':'4rem'})
    $('.btn-bot').hide()
})
// 以上是导航内容的打开
$('.btn-top').click(function () {
    $('.nav-con').animate({'height':'0.8rem'})
    $('.btn-bot').show()
})
// 以上是导航内容的关闭
$('.nav-con span').click(function () {
    $('.nav-con span a').eq(0).css('color','#f33')
    $('.nav-con span a').css('color','#333')
    $('.nav-con span a').eq($(this).index('.nav-con span')).css('color','#f33')
})
$('.nav-sub-con span').click(function () {
    $('.nav-sub-con span').css('color','#999')
    $('.nav-sub-con span').eq($(this).index('.nav-sub-con span')).css('color','#09f')
})
var head = document.getElementsByTagName('header')[0]
var banner = document.getElementById('banner')
var z = parseInt(getStyle(head,'height')) + parseInt(getStyle(banner,'height'))
// 以上是导航内容的颜色变化
var d = 0
var n = 1
var $c = $('.carousel img')
var car = document.getElementById('carul')
for(i=0;i<$c.length;i++){
    car.innerHTML+='<li>'+'</li>'
}
$('#carul li').eq(0).css({'width':'0.14rem','height':'0.14rem','background':'#fff'})
setInterval(function () {
    caro($c.eq(d),$c.eq(n))
    d++
    n=d+1
    if(d==$c.length-1){
        n=0
    }
    if(d==$c.length){
        d=0
        n=1
    }
    $('#carul li').css({'width':'0.1rem','height':'0.1rem','background':'#ccc'})
    $('.carousel p').css('display','none')
    $('#carul li').eq(d).css({'width':'0.14rem','height':'0.14rem','background':'#fff'})
    $('.carousel p').eq(d).css('display','block')
},5000)
function caro(obj, next) {
    obj.animate({'left':'-100%'},1000)
    setTimeout(function () {
        obj.css({'z-index':'1','left':'100%'})
    },1500)
    next.css('z-index','888').animate({'left':'0'},1000)
}

// 以上是轮播内容JS
var t = 0
setInterval(function () {
    $('.board ul div').css({'transform':'translateY('+t+'rem)'})
    t=t-0.42
    if(t==-2.52){
        t=0
    }
},2000)
// 以上是奥运热讯内容切换
$('.fixed-top').click(function () {
    document.body.scrollTop = 0
})
var col = document.getElementById('col')
st = parseInt(getStyle(head,'height')) + parseInt(getStyle(banner,'height'))+parseInt(getStyle(col,'height'))
window.onscroll = function () {
    var body = document.body
    if(body.scrollTop>st){
        $('.fixed').css('display','block')
    }
    else{
        $('.fixed').css('display','none')
    }//右侧返回顶部的显示
    if(body.scrollTop>z){
        $('.nav').css('position','fixed')
    }else {
        $('.nav').css('position','relative')
    }//导航的固定
}
setTimeout(function () {
    $('.red-packet').css({'transform':'rotate(8deg)'})
},100)
setTimeout(function () {
    $('.red-packet').css({'transform':'rotate(-12deg)'})
},200)
setTimeout(function () {
    $('.red-packet').css({'transform':'rotate(8deg)'})
},300)
setTimeout(function () {
    $('.red-packet').css({'transform':'rotate(-12deg)'})
},400)
setTimeout(function () {
    $('.red-packet').css({'transform':'rotate(0deg)'})
},500)
// 以上是固定返回顶部

setTimeout(function () {
    $('.fixed-ad').css({'bottom':'0'})
},1000)
$('.close').click(function () {
    $('.fixed-ad').remove()
})
// 以上是底部固定广告
$('.setting').click(function () {
    $('.login-show').css({'opacity': 1,'visibility': 'visible'})
    $('.login').css('left',0)
})
$('.login-show').click(function () {
    $('.login-show').css({'opacity': 0,'visibility': 'hidden'})
    $('.login').css('left','-100%')
})
// 以上是登陆内容区
$('.qw').click(function () {
    $('.page').css('display','block')
    $(this).hide()
})*/
function getStyle(ele,style){
    return ele.currentStyle?ele.currentStyle[style]:window.getComputedStyle(ele,null)[style]
}


