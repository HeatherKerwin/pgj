<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
    <title>票据管家--座谈会</title>
    <link href="https://static.utiexian.com/css/rymobile/zuotan/media.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
</head>
<style>
    body{
        background: #eeeeee;
        font-size: 1rem;
    }
    .zuotan{
        width: 100%;
        height: auto;
        font-size: 1rem;
    }
    .ZTbanner{
        width: 100%;
        height: auto;
    }
    ul.ZTlist{
        width: 94%;
        height: auto;
        margin: 1rem auto;
        background: #fff;
        border-radius: 1rem;
        border: 1px #dbdbdb solid;
    }
    ul.ZTlist img.bmhd {
        width: 25%;
        height: auto;
        margin: 1.2rem 40% 0;
    }
    ul.ZTlist li{
        width: 92%;
        margin: 1rem 4% 0;
    }
    ul.ZTlist li p{
        font-size: 1.2rem;
    }
    ul.ZTlist li input.LISTinput{
        border: 1px #d2d2d2 solid;
        width:100% ;
        height: 2.2rem;
        line-height: 2.2rem;
        background: none;
        margin-top: .5rem;
    }
    ul.ZTlist li img{
        width: 100%;
        height: 1px;
        margin-top: 0.5rem;
    }
    /*验证码输入框*/
    div.yzm{
        width: 100%;
        margin-top: 0.5rem;
    }
    ul.ZTlist li input.YZMinput{
        border: 1px #d2d2d2 solid;
        width: 70%;
        height: 2.2rem;
        background: none;
        float: left;
    }
    /*获取验证码按钮*/
    ul.ZTlist li input.YZMbtn{
        width: 25%;
        height: 2.3rem;
        background: #27a476;
        color: #fff;
        font-size: 1.2rem;
        border: none;
        border-radius: 0.3rem;
        float: right;
    }
    p.ZTtitle{
        text-align: center;
        line-height: 1.8rem;
        margin: 0.5rem 4% 0;
        font-size: 2em;
    }
    /*提交按钮*/
    input.infor{
        width: 92%;
        height: 3rem;
        line-height: 3rem;
        background: #27a476;
        color: #fff;
        font-size: 1.8rem;
        border: none;
        border-radius: 0.4rem;
        margin: 1rem 4%;
    }
    /*活动介绍*/
    .ZThdjs , .ZThdgz , .ZThdlc{
        height: auto;
        line-height: 2rem;
        margin: 1rem 3%;
        padding: 0 4% 1rem;
        border-radius: 1rem;
        background: #fff;
    }
    .ZThdjs img.bdjs , .ZThdgz img.hdgz ,.ZThdlc img.hdlc{
        width: 26%;
        height: auto;
        margin: 1.2rem 40% 0;
    }
    .ZThdjs p{
        text-indent: 2.2rem;
    }
    /*酒店地图*/
    .IMGplace .hotel, .IMGplace .map{
        width: 49%;
        height: auto;
        float: left;
        text-align: center;
    }
    .IMGplace .hotel img , .IMGplace .map img{
        width: 100%;
        height: auto;
    }
    p.place span{
        font-weight: 600;
    }
    /*弹窗*/
    .ZTtcdiv{
        position: fixed;
        top: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0,0,0,0.5);
        z-index: 10;
        display: none;
    }
    .ZTtc{
        width: 75%;
        height: auto;
        position: relative;
        top: 20%;
        left: 12.5%;
        background: #fff;
        border-radius: 0.5rem;
        padding: 2rem 0;
        z-index: 11;
    }
    /*关闭按钮*/
    .close{
        width: 3.6rem;
        height: 3.6rem;
        position: absolute;
        top: 18%;
        right:8.5% ;
        z-index: 12;
    }
    /*二维码*/
    .TCewm{
        width: 50%;
        height: auto;
        position: relative;
        left: 25%;
        z-index: 13;
    }
    .ZTtc p{
        text-align: center;
        font-size: 3.8rem;
        line-height: 2.5rem;
    }
    .ZTtc p span{
        font-weight: 600;
    }
</style>
<body>
<div class="zuotan">
    <!-- 广告图-->
    <img src="https://img.utiexian.com/rymobile/zuotan/ZTbanner.jpg" class="ZTbanner">
    <!-- 活动报名-->
    <ul class="ZTlist">
        <img src="https://img.utiexian.com/rymobile/zuotan/bmhd.png" class="bmhd">
        <li>
            <p>企业名称*</p>
            <input type="text" id="company" class="LISTinput">
            <img src="https://img.utiexian.com/rymobile/zuotan/input.png">
        </li>
        <li>
            <p>联系人*</p>
            <input type="text" id="name" class="LISTinput">
            <img src="https://img.utiexian.com/rymobile/zuotan/input.png">
        </li>
        <li>
            <p>联系方式*</p>
            <input type="number" id="phone" class="LISTinput" id="phone" onblur="checkMobile(this.value)">
            <img src="https://img.utiexian.com/rymobile/zuotan/input.png">
        </li>
        <li>
            <p>验证码*</p>
            <div class="yzm">
                <input type="number" id="code" class="YZMinput" autocomplete="off">
                <input type="button" value="获取验证码" class="YZMbtn" id="phone_btn1" name="phone_btn" onClick="showtime(60)">
            </div>
            <img src="https://img.utiexian.com/rymobile/zuotan/input.png">
        </li>
        <li>
            <p>所在地</p>
            <input type="text" id="address" class="LISTinput">
            <img src="https://img.utiexian.com/rymobile/zuotan/input.png">
        </li>
        <!-- 提交资料说明-->
        <p class="ZTtitle">资料提交成功后，我们的客服会在1个工作日内与您联系，请您耐心等候</p>
        <p class="ZTtitle">如果有其他问题欢迎直接沟通我们的客服：</p>
        <p class="ZTtitle">客服电话：400-067-0002</p>
        <input type="button" value="提交" class="infor" id="TC_save">
    </ul>

    <!-- 活动介绍-->
    <div class="ZThdjs">
        <img src="https://img.utiexian.com/rymobile/zuotan/hdjs.png" class="bdjs">
        <p> 该座谈会是由上海票管家金融服务有限公司主办
            的，旨在深刻研讨，于当今经济大潮下，企业应如何
            顺应时代生存，以谋进一步发展的深度论坛。本次论
            坛将请到银行、金融机构以及企业多方代表从“银行
            及中小微企业如何在竞争、创新、混经营的大时代下
            抢占先机”角度切入，分享时代变革下的资源配置的
            新思维、新逻辑以及新方向。
        </p>
    </div>
    <!-- 活动规则-->
    <div class="ZThdgz">
        <img src="https://img.utiexian.com/rymobile/zuotan/hdgz.png" class="hdgz">
        <p class="place"><span>活动时间：</span>2016年11月25日星期五14：00</p>
        <p class="place"><span>活动地址：</span>上海浦东新区陆家嘴环路1288号凯宾斯基大酒店二楼哈瓦那厅</p>
        <div class="IMGplace">
            <div class="hotel">
                <img src="https://img.utiexian.com/rymobile/zuotan/hotel.png">
                <p>酒店图片</p>
            </div>
            <div class="map" style="margin-left: 2%">
                <img src="https://img.utiexian.com/rymobile/zuotan/map.png">
                <p>酒店地址</p>
            </div>
        </div>
        <div style="clear: both;"></div>
        <p class="place"><span>交通信息：</span>地铁：地铁2号线陆家嘴站2号口出步行600米</p>
        <p class="place"><span>公交车：</span>119、792、818、浦东15路、607路、陆家嘴金融城3路公交泰东路</p>
        <p class="place"><span>渡口站；</span>774,779,798,799,85,971，992, 陆家嘴金融城4路陆家嘴地铁站</p>
        <p class="place"><span>停车位：</span>酒店有停车场</p>
    </div>
    <!-- 活动流程-->
    <div class="ZThdlc">
        <img src="https://img.utiexian.com/rymobile/zuotan/hdlc.png" class="hdlc">
        <p>1 来宾签到</p>
        <p>2 发布会启动</p>
        <p>3 新品详解</p>
        <p>4 两位神秘嘉宾发表演讲</p>
        <p>5 圆桌会议</p>
        <p>6 答谢致辞</p>
    </div>
</div>
<!-- 弹窗-->
<div class="ZTtcdiv">
    <img src="https://img.utiexian.com/common/close/close.png" class="close" ID="TC_close">
    <div class="ZTtc">
        <img id="qrcode" src="https://img.utiexian.com/rymobile/zuotan/ewm.png" class="TCewm">
        <p>请您保存此<span>二维码</span></p>
        <p>或者凭<span>手机号</span>入场</p>
        <p>谢谢</p>
    </div>
</div>
</body>
<script type="text/javascript">
    //	验证
    function phone(){
        var x=document.getElementById("phone").value;
        document.getElementById("phone").value=x.toUpperCase();
    }
    function checkMobile(str) {
        var telReg = !!str.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
        //如果手机号码不能通过验证
        if(telReg == false){
            alert("请输入正确的手机号！");
            return false;
        } else {
            return true;
        }
    }
    function checkPhone(str) {
        var telReg = !!str.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
        //如果手机号码不能通过验证
        if(telReg == false){
            return false;
        } else {
            return true;
        }
    }
    function showtime(t){
    	var phone = $("#phone").val();
		if (checkPhone(phone)) {
			document.getElementById("phone_btn1").disabled=true;
			$.ajax({
	    		url:"../discussion/sendcode.htm",
	    		type:"post",
	    		data:{"phone":phone},
	    		dataType:"json",
	    		success:function(data){
					if("success"==data.response){
						for(i=1;i<=t;i++) {
							window.setTimeout("update_p(" + i + ","+t+")", i * 1000);
						}
						window.setTimeout(function () {
							document.getElementById("phone_btn1").disabled=false;
						}, t * 1000 + 1000);
						alert(data.msg);
					}else{
						document.getElementById("phone_btn1").disabled=false;
						alert(data.msg);
					}
	    		}
	    	});
		} else {
			alert("请输入正确的手机号！");
		}
    }
    function update_p(num,t) {
        if(num == t) {
            document.getElementById("phone_btn1").value =" 重新发送 ";
        }
        else {
            printnr = t-num;
            document.getElementById("phone_btn1").value =printnr +"s重新发送";
        }
    }
    //	弹窗
    $("#TC_save").click(function(){
    	save();
    });
    $("#TC_close").click(function(){
        $(".ZTtcdiv").hide();
    });
    
    function save(){
		var company = $("#company").val();
		var name = $("#name").val();
		var phone = $("#phone").val();
		var code = $("#code").val();
		var address = $("#address").val();
		if(company==null || company.trim()==""){
			alert("请输入企业名称");
			return null;
		}
		if(name==null || name.trim()==""){
			alert("请输入联系人");
			return null;
		}
		if(phone==null || phone.trim()==""){
			alert("请输入联系方式");
			return null;
		}
		if(code==null || code.trim()==""){
			alert("请输入验证码");
			return null;
		}
		if(address==null || address.trim()==""){
			alert("请输入所在地");
			return null;
		}
		$.ajax({
			url:"../discussion/create.htm",
			type:"post",
			data:{"company":company,"name":name,"phone":phone,"address":address,"code":code},
			dataType:"json",
			success:function(data){
				if(data.response=="success"){
					var result = data.data;
					$("#qrcode").attr("src",data.imgPath+result.imgPath);//二维码图片访问路径
					$(".ZTtcdiv").show();
				}else{
					alert(data.msg);
				}
			}
		});
	}
</script>
</html>