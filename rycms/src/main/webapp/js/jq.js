// JavaScript Document
$(function () {
	$(".k").click(function(){
						   $(".k").removeClass("red");
						     $(this).addClass("red");
						   })
	$(".j").click(function(){
						   $(".j").removeClass("red");
						     $(this).addClass("red");
						   })
	$(".s").click(function(){
						   $(".s").removeClass("red");
						     $(this).addClass("red");
						   })
	$(".b").click(function(){
						   $(".b").removeClass("red");
						     $(this).addClass("red");
						   })
	$(".x").click(function(){
						   $(".x").removeClass("reds");
						     $(this).addClass("reds");
						   })
	$(".xx").click(function(){
						   $(".xx").removeClass("reds");
						     $(this).addClass("reds");
						   })	
	$(".xxx").click(function(){
						   $(".xxx").removeClass("reds");
						     $(this).addClass("reds");
						   })
	$(".ddd").click(function(){
						   $(".ddd").attr("style",'');
						   $(".ddd").removeClass("typecss");
						   $(this).addClass("typecss");
						    $(this).attr('style',"color: #990000");
						   })
	$(".ccc").click(function(){
						   $(".ccc").attr("style",'');
						   $(".ccc").removeClass("typecss");
						   $(this).addClass("typecss");
						    $(this).attr('style',"color: #990000");
						   })
	$(".ddds").click(function(){
						   $(".ddds").attr("style",'');
						   $(".ddds").removeClass("typecss");
						   $(this).addClass("typecss");
						    $(this).attr('style',"color: #990000");
						   })
	$(".gg").click(function(){
							   $(".gg").attr("style",'');
						   $(".gg").removeClass("typecss");
						     $(this).addClass("typecss");
							 $(this).attr('style',"color: #990000");
						   })
	$(".acts").click(function(){
						   $(".acts").removeClass("red");
						     $(this).addClass("red");
						   })
	
	
    })

/*我要贴现*/
$(document).ready(function(){  
            $(".txtadsblk01 li").mouseover(function(){  
                $('.txtadsblk01 li').removeClass("now");  
                $(this).addClass("now");  
                var likey = $(".txtadsblk01 li").index(this);   
                  
                $('.xuanxiangkadiv').addClass("hide").removeClass("block");  
                $(".xuanxiangkadiv:eq("+likey+")").addClass("block").removeClass("hide");  
            });  
			
			$(".qie").mouseover(function(){
						   $(".qie").removeClass("rr");
						     $(this).addClass("rr");
						   })
        })  

/*票据管理*/
 $(document).ready(function(){  
            $(".cl").click(function(){  
               $('.cl').removeClass("nows"); 
                $(this).addClass("nows");
			var clid=$(this).attr("id");
			if(clid=='zhi'){
				$("#zhi1").removeClass("hide").addClass("block");
				$("#dian1").removeClass("block").addClass("hide");
				}else{
					$("#dian1").removeClass("hide").addClass("block");
						$("#zhi1").removeClass("block").addClass("hide");
					}
            });  
        })  
 /*申请联系*/
 $(function(){
      $("#prolist dl dt img").css("opacity","1");
	  $("#prolist dl dt img").hover(function(){
	     $(this).css("opacity","0.5");
	  },function(){
	     $(this).css("opacity","1");
	  });
	  var page=1; //当前页
	  var imgNum=$("#prolist dl dt img").length;
	  var num=4;
	  var pageMax=Math.ceil(imgNum/num);//总页数
	  
	  
	  $(".right").click(function(){
	    if(!$("#prolists").is(":animated")){
	    //向左滑动，若当前页为最后一页，则直接切换到第一页
		   if(page==pageMax){
		       $("#prolists").stop().animate({left:"0"});
			   page=1;
		   }else{
		      $("#prolists").stop().animate({left:"-=1100px"});
			  page++;
		   }
		}
	  });
	  
	  $(".left").click(function(){
	    if(!$("#prolists").is(":animated")){
	    //向右滑动，若当前页为第一页，则直接切换到最后一页
		   if(page==1){
		       $("#prolists").stop().animate({left:"-"+(pageMax-1)*1100+"px"});
			   page=pageMax;
		   }else{
		      $("#prolists").stop().animate({left:"+=1100px"});
			  page--;
		   }
		}
	  });
	  
	  setInterval(function(){
	    $(".right").click(); 
	  },3000)	  
   })
/* 图片滑动*/
 $(function(){
     $(".tops").click(function(){
						   $("html,body").animate({scrollTop:"0px"},1000);
						   }) 
   })
/* 返回顶部*/
