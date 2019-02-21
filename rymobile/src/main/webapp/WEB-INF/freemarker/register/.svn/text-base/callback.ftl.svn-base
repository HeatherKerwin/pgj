<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>正在跳转...</title>
		<link rel="shortcut icon" href="https://m.utiexian.com/favicon.ico"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<script type="text/javascript">
			if("success"=='${result}'){//注册成功
				window.location.href = "../app/success.htm";
			}else if("over"=='${result}'){//注册成功[但领红包活动已过期]
				window.location.href = "../app/over.htm";
			}else if("registered"=='${result}'){//已注册
				window.location.href = "../app/registered.htm?code=${invitationCode}";
			}else if("error"=='${result}'){//验证码不正确
				alert('${msg}');
				window.location.href = "../app/register.htm?code=${invitationCode}";
			}else{//未注册
				window.location.href = "../app/register.htm";
			}
		</script>
	</head>
	<body>
		正在跳转...
	</body>
</html>