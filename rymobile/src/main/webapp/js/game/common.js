/* 2014 Web De: gongxy */


$(function(){
	
	window.vv={};
	vv.gamePoint=1000;
	vv.gameTime=15;
	vv.gameCTime=0;
	vv.gameTimer=false;
	vv.updataed=false;
	vv.inviteNum=$("#invite").text()*1;
	vv.buyLink='https://app.utiexian.com/down.html';
	
	var Edown, Eup, Emove;
	if($.os.phone){
		Edown='touchstart';
		Eup='touchend';
		Emove='touchmove';
	}else{
		Edown='mousedown';
		Eup='mouseup';
		Emove='mousemove';
	}	
	
	window.touchX=undefined;
	window.touchY=undefined;
	window.touchMX=undefined;
	window.touchMY=undefined;
	$(document).on(Edown,function(e){ 
		if($.os.phone){
			touchX = e.targetTouches[0].pageX;
			touchY = e.targetTouches[0].pageY;
		}else{
			touchX = e.pageX;
			touchY = e.pageY;
		}
	})
	$(document).on(Emove,function(e){ 
		if($.os.phone){
			touchMX = e.targetTouches[0].pageX-touchX;
			touchMY = e.targetTouches[0].pageY-touchY;
		}else{
			touchMX = e.pageX-touchX;
			touchMY = e.pageY-touchY;
		}
	});
	
	
	/***** onload *****/
	window.setTimeout(function(){
		$(".loading").fadeOut(200,function(){
			$(".s1").fadeIn(200);
		})
	},200)
	
	
	
	/***** cut *****/
	//到首页
	$('.s6 .btn2, .s7 .btn2').on(Edown,function(){ 
		$('section:visible').fadeOut(100,function(){
			$('.s1').fadeIn(200);
		})
	})
	
	//到游戏结果
	$('.s4 .btn2, .s5 .btn2').on(Edown,function(){
		goResult();
	})
	function goResult(){
		var tmp = vv.gamePoint;
		$('.s3 dt span').text(tmp+'万元');
		if(eval(tmp)<30000)$(".s3 .btn4").hide();
		$('section:visible').fadeOut(100,function(){
			$('.s3').fadeIn(200);
		})
	}
	//到分享
	$('.s3 .an3, .s6 .btn1').on(Edown,function(){  $('.share').fadeIn(100); })
	$('.share').on(Edown,function(){  $('.share').fadeOut(200); })
	//到游戏
	$('.s1 .btn1, .s3 .an1, .s5 .btn1').on(Edown,function(){ 
		vv.gamePoint=0;
		vv.gameCTime=vv.gameTime;
		$('section:visible').fadeOut(100,function(){
			$('.s2 h5 var').eq(0).text(vv.gameCTime.toFixed(1,10));
			$('.s2 h5 var').eq(1).text('000');
			$('.s2 h6').show();
			$('.s2 .qq, .s2 .qqq').remove();
			//$('.s2 dl').height($(document).height()-50)
			//$('.s2 dt, .s2 dd').height($(document).height()-$('.s2 h6').offset().top-$('.s2 h6').offset().height-40);
			$('.s2').fadeIn(200);
			window.setTimeout(function(){ sqBegin() },5000);
		})
	})
	
	/***** game *****/
	$(".s2 dt span").on(Edown,function(){
		if(vv.gameCTime==vv.gameTime){ sqBegin(); }
		$(".s2 dd .qq").size()==0 && $(".s2 dd").append($(".s2 dd q").eq(0).clone().attr('class','qq'));
	})
	$(".s2 dt span").on(Emove,function(e){
		e.preventDefault();
		if(vv.gameCTime<0){ return; }
		$(".s2 dd .qq").css('-webkit-transform', 'translateY('+touchMY*4+'px)');
	})
	$(".s2 dt span").on(Eup,function(e){
		var offsetY=touchMY;
		touchMY=0;
		if(vv.gameCTime<0){ return; }
		if(offsetY<-5){
			vv.gamePoint+=1000;
			$(".s2 h5 var").eq(1).text(vv.gamePoint);
			$(".s2 dd .qq").attr('class','qqq').animate({translateY:-$(".wrapper").height()*2+'px', scaleX:0.7, scaleY:0.7}, 500, function(){ $(this).remove(); })
			createjs.Sound.play("qian", !0);
		}else{
			$(".s2 dd .qq").animate({translateY:'0px'}, 100);
		}
	})
	var soundTouched=false;
	$(document).on('touchstart',function(){
		!soundTouched && (soundTouched=true) && createjs.Sound.play("qian", !0);
	})
	function sqBegin(){
		if(vv.gameTimer){ return;}
		$(".s2 h6").hide();
		vv.gameTimer=window.setInterval(function(){
			vv.gameCTime-=0.1;
			var txtTime=vv.gameCTime*1;
			txtTime=txtTime.toFixed(1,10);
			if(txtTime.length<4){ txtTime='0'+txtTime; }
			$(".s2 h5 var").eq(0).text(txtTime);
			if(vv.gameCTime<0){
				window.clearInterval(vv.gameTimer);
				vv.gameTimer=false;
				vv.updataed=false;
				touchMY=0;
				$(".s2 h5 var").eq(0).text('00.0');
				window.setTimeout(function(){
					goResult();
				},1000)
			}
		},100)
	}
	

})
