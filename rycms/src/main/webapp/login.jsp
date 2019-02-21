<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>睿银CMS</title>
<link rel="shortcut icon" href="favicon.ico">
<link rel="stylesheet" href="css/lrtk.css"/>
<style>
body{margin:0;padding:0;}
</style>
</head>

<body style="background-color:#f1f1f1;">
<div id="login" >
	<div style="padding-top:60px;" ></div>
    <div class="wrapper" >
        <div class="login">
        
            <span style="padding-top:60px;"><img style="position:relative;left:50%;margin-left:-150px;" src="images/logo.png" /></span>
            <form action="<%=path%>/admin/login/" method="post" class="container offset1 loginform">
                <!--<div id="owl-login">
                    <div class="hand"></div>
                    <div class="hand hand-r"></div>
                    <div class="arms">
                        <div class="arm"></div>
                        <div class="arm arm-r"></div>
                    </div>
                </div>-->
                <div class="pad">
                    <input type="hidden" name="_csrf" value="9IAtUxV2CatyxHiK2LxzOsT6wtBE6h8BpzOmk=">
                    <div class="control-group">
                        <div class="controls">
                            <label for="email" class="control-label fa fa-user"></label>
                            <input id="email" type="text" name="username" placeholder="请输入用户名登录" tabindex="1" autofocus="autofocus" class="form-control input-medium">
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <label for="password" class="control-label fa fa-lock"></label>
                            <input id="password" type="password" name="password" placeholder="请输入密码" tabindex="2" class="form-control input-medium">
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <button type="submit" tabindex="4" class="btn btn-primary">登录</button>
                </div>
            </form>
        </div>
    </div>
    <script src="js/jquery.min.js"></script>
    <script>
    $(function() {

        $('#login #password').focus(function() {
            $('#owl-login').addClass('password');
        }).blur(function() {
            $('#owl-login').removeClass('password');
        });
    });
    
    if(top != self){
    	top.location.href = location.href;
    }
    </script>

</div>
</body>
</html>
