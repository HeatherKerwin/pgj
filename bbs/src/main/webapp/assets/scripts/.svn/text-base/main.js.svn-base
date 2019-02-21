
function go_signin(){
	swal({
		title:"提示信息", 
		text:"您没有登录，3秒后跳转到登录页面！", 
		type:"warning",
		confirmButtonText:"点击跳转",
		timer: 3000
	},function(isConfirm){
		if (isConfirm) {
			setTimeout(function(){
				window.location.href= BASE + '/signin';
			}, 300);
		} else{
			window.location.href= BASE + '/signin';
		}
	});
}

function len(o){  
   var n, count = 0;  
   for(n in o){  
      if(o.hasOwnProperty(n)){  
         count++;  
      }  
   }  
   return count;
}  

/* 
弹出窗口居中 
*/  
function openWindow(url,name,iWidth,iHeight) {  
    var url;                             //转向网页的地址;  
    var name;                            //网页名称，可为空;  
    var iWidth;                          //弹出窗口的宽度;  
    var iHeight;                         //弹出窗口的高度;  
    //获得窗口的垂直位置  
    var iTop = (window.screen.availHeight-30-iHeight)/2;         
    //获得窗口的水平位置  
    var iLeft = (window.screen.availWidth-10-iWidth)/2;            
    window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');  
}  

function alertError(msg){
	swal({
		title:"提示信息", 
		text: msg, 
		type:"error",
		timer: 3000
	});
}

function alertOk(msg){
	swal({
		title:"提示信息", 
		text: msg, 
		type:"success",
		timer: 3000
	});
}

/**
 * 搜索帖子
 * @returns {Boolean}
 */
function doSearch() {
    var q = document.getElementById("q");
    if (q.value != "") {
        var url = BASE + '/search/' + q.value;
        if (navigator.userAgent.indexOf('iPad') > -1 || navigator.userAgent.indexOf('iPod') > -1 || navigator.userAgent.indexOf('iPhone') > -1) {
            location.href = url;
        } else {
            window.open(url, "_blank");
        }
        return false;
    } else {
        return false;
    }
}

function unique(arr){
	var uniqueArr = [];
	$.each(arr, function(i, el){
	    if($.inArray(el, uniqueArr) === -1) uniqueArr.push(el);
	});
	return uniqueArr;
}

function emoji(content){
	if(content && content.indexOf(':') != -1){
		return content.replace(/:([a-z-_]{2,30}):/g, "<img src='"+ CDN_URL + "/assets/emojis/$1.png'  height='20' width='20' />");
	}
	return content;
}

function change_captcha(){
	var timestamp = (new Date()).valueOf();  
	$('#captcha').attr('src', BASE + '/captcha?t=' + timestamp);
	return false;
}

//预览帖子
$('#topic-add .preview').on('click', function(){
	//var content = $("#topic-add #content").val();
	var content = UE.getEditor('content').getContent();
	if(content){
		$.post(BASE + '/markdown', {content : content}, function(response){
			if(response){
				$("#markdown_preview").html(response).removeClass('hide');
				$('#markdown_preview pre code').each(function(i, block) {
				    hljs.highlightBlock(block);
				});
			}
		});
	} else{
		$("#markdown_preview").html('').addClass('hide');
	}
});


$('#topic-edit .preview').on('click', function(){
	//var content = $("#topic-edit #content").val();
	var content = UE.getEditor('content').getContent();
	if(content){
		$.post(BASE + '/markdown', {content : content}, function(response){
			if(response){
				$("#markdown_preview").html(response).removeClass('hide');
				$('#markdown_preview pre code').each(function(i, block) {
				    hljs.highlightBlock(block);
				});
			}
		});
	} else{
		$("#markdown_preview").html('').addClass('hide');
	}
});

//帖子点赞
$('.topic-footer .heart').on('click', function(){
	var _this = $(this);
	var A = $(this).attr("rel");
	var C=parseInt($("#likeCount").text());
	var D = $("#likeCount");
	$(this).css("background-position","")
	if(A == 'like'){
		D.text(C+1);
		_this.addClass("heartAnimation").attr("rel","unlike");
	} else{
		D.text(C-1);
		_this.removeClass("heartAnimation").attr("rel","like");
		_this.css("background-position","left");
	}
	var tid = $(this).attr('tid');
	$.post(BASE + '/favorite', {type:'love',event_id : tid}, function(response){
		if(response){
			if(response.status == 200){
			} else if(response.status == 401){
				go_signin();
			} else{
				alertError(response.msg);
			}
		}
	});
});

//帖子下沉
$('.topic-detail-heading .sinks').on('click', function(){
	var tid = $(this).attr("tid");
	var _this = $(this);
	$.post(BASE + '/sink', {tid : tid}, function(response){
		if(response){
			if(response.status == 200){
				window.location.reload();	
			} else if(response.status == 401){
				go_signin();
			} else{
				alertError(response.msg);
			}
		}
	});
});

//帖子收藏
$('.topic-footer .follow').on('click', function(){
	var tid = $(this).attr("tid");
	var _this = $(this);
	$.post(BASE + '/favorite', {type:'topic', event_id : tid}, function(response){
		if(response){
			if(response.status == 200){
				window.location.reload();	
			} else if(response.status == 401){
				go_signin();
			} else{
				alertError(response.msg);
			}
		}
	});
});

//设置精华帖
$('.topic-footer .essence').on('click', function(){
	var tid = $(this).attr("tid");
	var _this = $(this);
	$.post(BASE + '/essence', {tid : tid}, function(response){
		if(response){
			if(response.status == 200){
				window.location.reload();	
			} else if(response.status == 401){
				go_signin();
			} else{
				alertError(response.msg);
			}
		}
	});
});

//管理员删帖
$('.topic-footer .deltopic').on('click', function(){
	var tid = $(this).attr("tid");
	var _this = $(this);
	$.post(BASE + '/delete', {tid : tid}, function(response){
		if(response){
			if(response.status == 200){
				window.location.reload();	
			} else if(response.status == 401){
				go_signin();
			} else{
				alertError(response.msg);
			}
		}
	});
});

//分享到微博
$('.topic-footer .share-weibo').on('click', function(){
	var title = $('.topic-detail-heading .panel-title').text();
	var href = window.location.href;
	var share_url = 'http://service.weibo.com/share/share.php?url=' + href + '&title=' + encodeURI(title);
	openWindow(share_url, '', 550, 422);
});

//at用户
$('.comment-list .at-user').on('click', function(){
	var user_name = $(this).attr('alt');
	var ctn = $('#comment-form #content');
	var text = ctn.val();
	var at = '@' + user_name + ' ';
	if(text == at){
		ctn.val('');		
	} else{
		var br = text ? '\r\n' : '';
		ctn.val(text + br + at);
	}
	ctn.focus();
});

$('.profile .following').on('click', function(){
	var uid = $(this).attr("uid");
	var _this = $(this);
	$.post(BASE+'/favorite', {type:'following', event_id : uid}, function(response){
		if(response){
			if(response.status == 200){
				window.location.reload();	
			} else if(response.status == 401){
				go_signin();
			} else{
				alertError(response.msg);
			}
		}
	});
});

//节点收藏
$('.node-head .favorite').on('click', function(){
	var nid = $(this).attr("nid");
	var _this = $(this);
	$.post(BASE + '/favorite', {type:'node', event_id : nid}, function(response){
		if(response){
			if(response.status == 200){
				window.location.reload();	
			} else if(response.status == 401){
				go_signin();
			} else{
				alertError(response.msg);
			}
		}
	});
});

var info_data = {};
$("#info_form :input").change(function (){
	var key = $(this).attr('name');
	info_data[key] = $(this).val();
});

// 修改个人基本信息
$("#info_form .submit").on('click', function(e){
	e.preventDefault();
	if(len(info_data) > 0){
		var l = Ladda.create(this);
		l.start();
		$.post(BASE+'/settings?type=info', info_data, function(response){
			info_data = {};
			setTimeout(function () {
	            l.stop();
	        }, 600);
			if(response){
				if(response.status == 200){
					alertOk("修改成功！");
				} else if(response.status == 401){
					go_signin();
				} else{
					alertError(response.msg);
				}
			}
		});
	}
});

////////////////////帖子操作:START//////////////////////

//发布帖子
$("#topic-add .ladda-button").on('click', function(e){
	e.preventDefault();
	var form = $('#topic-add')[0];
	if(form.checkValidity()) {
		var l = Ladda.create(this);
		l.start();
		var formData = $('#topic-add').serialize();
		$.post(BASE + '/topic/add', formData, function(response){
			setTimeout(function () {
	            l.stop();
	        }, 600);
			if(response){
				if(response.status == 200){
					 window.location.href = BASE + '/topic/' + response.data;
				 } else if(response.status == 401){
					 go_signin();	
				 } else {
					 alertError(response.msg);
				 }
			}
		});
	}else{
		alertError("请输入帖子标题");
	}
	return true;
});

// 评论帖子
$("#comment-form .ladda-button").on('click', function(e){
	e.preventDefault();
	var form = $('#comment-form')[0];
	if(form.checkValidity()) {
		var l = Ladda.create(this);
		l.start();
		var formData = $('#comment-form').serialize();
		$.post(BASE + '/comment/add', formData, function(response){
			setTimeout(function () {
	            l.stop();
	        }, 600);
			if(response){
				if(response.status == 200){
					window.location.reload();
				 } else if(response.status == 401){
					 go_signin();	
				 } else {
					 alertError(response.msg);
				 }
			}
		});
	}
	return true;
});



var topic = {};
//编辑帖子
topic.edit = function(){
	var formData = $('#topic-edit').serialize();
	$.post(BASE + '/topic/edit', formData, function(response){
		if(response){
			 if(response.status == 200){
				 window.location.href = BASE + '/topic/' + response.data;
			 } else if(response.status == 401){
				 go_signin();	
			 } else {
				 alertError(response.msg);
			 }
		}
	});
	return false;
};

//评论帖子
/*topic.comment = function(){
	var formData = $('#comment-form').serialize();
	$.post(BASE + '/comment/add', formData, function(response){
		if(response){
			 if(response.status == 200){
				 window.location.reload();
			 } else if(response.status == 401){
				 go_signin();	
			 } else {
				 alertError(response.msg);
			 }
		}
	});
	return false;
};*/
////////////////////帖子操作:END//////////////////////

////////////////////个人设置:START//////////////////////
var user = {};
//修改头像（新添加昵称）
user.update_avatar = function(){
	var avatar = $("#avatar_form #user_avatar").val();
	if(avatar && avatar != ''){
		$.post(BASE+'/settings', {type:'avatar', avatar : avatar}, function(response){
			if(response){
				if(response.status == 200){
					alertOk("设置成功!");
				} else if(response.status == 401){
					go_signin();
				} else{
					alertError(response.msg);
				}
			}
		});
	}
};

//我的社区-修改头像（新）
$("#avatar_form .submit").on('click', function(e){
	var avatar = $("#avatar_form #user_avatar").val();
	var nick_name = $("#avatar_form #nick_name").val();
	var login_name = $("#avatar_form #login_name").val();
	if(login_name.trim()=="" || login_name.trim().length<4){
		alertError("请输入4-16位用户名");
		return;
	}
	if((avatar && avatar != '')||(nick_name && nick_name!='')){
		$.post(BASE+'/settings', {type:'avatar',avatar:avatar,nick_name:nick_name,login_name:login_name}, function(response){
			if(response){
				if(response.status == 200){
					alertOk("设置成功!");
				} else if(response.status == 401){
					go_signin();
				} else{
					alertError(response.msg);
				}
			}
		});
	}
});

//修改密码
user.update_pwd = function(){
	var formData = $('#pwd_form').serialize();
	$.post(BASE + '/settings?type=pwd', formData, function(response){
		if(response){
			if(response.status == 200){
				alertOk("密码修改成功!");	
			} else if(response.status == 401){
				go_signin();
			} else{
				alertError(response.msg);
			}
		}
	});
}

////////////////////个人设置:END//////////////////////

////////////////////Github绑定:START////////////////////
var github = {};
github.signup = function(){
	$.post(BASE + '/oauth/user/bind', {
		type : 'signup',
		login_name : $('#github_signup_form #login_name').val(),
		email : $('#github_signup_form #email').val(),
		pass_word : $('#github_signup_form #pass_word').val()
	}, function(data){
		if(data){
			if(data == 'exist_login'){
				alertError("用户已存在，请绑定已有帐号!");
			} if(data == 'exist_email'){
				alertError("邮箱已存在，请绑定已有帐号!");
			} else if(data == 'success'){
				$('#github-bind-tab').hide();
				$('#github-tab-content').hide();
				$('.col-md-9 #result').removeClass('hide').html('注册成功，已经向您的邮箱 ' + $('#github_signup_form #email').val() + ' 发送了一封激活申请，请注意查收！' +
						'如未收到邮件可在垃圾邮件里查看或者联系网站管理员进行激活。');
			} else if(data == 'failure'){
				alertError("绑定失败!");
			}
		}
	});
	return false;
};

github.signin = function(){
	$.post(BASE + '/oauth/user/bind', {
		type : 'signin',
		login_name : $('#github_signin_form #login_name').val(),
		pass_word : $('#github_signin_form #pass_word').val()
	}, function(data){
		if(data){
			if(data == 'no_user'){
				alertError("不存在该用户!");
			} else if(data == 'pwd_error'){
				alertError("用户名或密码错误!");
			} else if(data == 'no_active'){
				alertError("该用户未激活，请激活后进行绑定!");
			} else if(data == 'success'){
				window.location.href = BASE;
			}
		}
	});
	return false;
};
////////////////////Github绑定:END//////////////////////

/*-----------------------------------------新增方法-----------------------------------------*/

//签到
$('.checkin-button .todo').on('click', function(){
	var _this = $(this);
	$.post(BASE + '/checkin', function(response){
		if(response){
			if(response.status == 200){
				window.location.reload();	
			} else if(response.status == 401){
				go_signin();
			} else{
				alertError(response.msg);
			}
		}
	});
});

/**
 * 手机号校验
 * @param str
 * @returns {Boolean}
 */
function checkMobile(str) {
	var telReg = !!str.match(/(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$)/);
	if(telReg == false){//如果手机号码不能通过验证
		return false;
	} else {
		return true;
	}
}

/**
 * 我的社区-修改头像（新）
 */
$("#opinion_form .submit").on('click', function(e){
	var phone = $("#opinion_form #phone").val();
	var content = $("#opinion_form #content").val();
	if(content==null || content.trim()==''){
		alertError("请输入反馈内容！");
		return;
	}
	if(phone==null || phone.trim()==""){
		alertError("请输入手机号！");
		return;
	}
	if(!checkMobile(phone)){
		alertError("请输入正确的手机号！");
		return;
	}
	$.post(BASE+'/opinion/do', {phone:phone,content:content}, function(response){
		if(response){
			if(response.status == 200){
				alertOk("反馈成功！");
			} else{
				alertError("反馈失败！");
			}
		}
	});
});

/**
 * 更新密码
 */
$("#password-form .submit").on('click', function(e){
	var curpwd = $("#password-form #curpwd").val();
	var newpwd = $("#password-form #newpwd").val();
	var renewpwd = $("#password-form #renewpwd").val();
	$.post(BASE+'/my/password/set', {curpwd:curpwd,newpwd:newpwd,renewpwd:renewpwd}, function(response){
		if(response){
			if(response.status == 200){
				alertOk("密码修改成功!");
			} else if(response.status == 401){
				go_signin();
			} else{
				alertError(response.msg);
			}
		}
	});
});

/**
 * 领取奖品（填写收货地址）
 */
$("#award_form .ladda-button").on('click', function(e){
	var aid = $("#award_form #aid").val();
	var name = $("#award_form #name").val();
	var phone = $("#award_form #phone").val();
	var prov = $("#award_form #prov").find("option:selected").text();
	var city = $("#award_form #city").find("option:selected").text();
	var dist = $("#award_form #dist").find("option:selected").text();
	var address = $("#award_form #address").val();
	
	if(phone==null || phone.trim()==""){
		alertError("请输入手机号！");
		return;
	}
	if(!checkMobile(phone)){
		alertError("请输入正确的手机号！");
		return;
	}
	$.post(BASE+'/my/award/save', {aid:aid,name:name,phone:phone,prov:prov,city:city,dist:dist,address:address}, function(response){
		if(response){
			if(response.status == 200){
				alertOk(response.data);
			} else if(response.status == 401){
				go_signin();
			} else{
				alertError(response.msg);
			}
		}
	});
});