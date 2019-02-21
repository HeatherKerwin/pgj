<!DOCTYPE html>
<html style="font-size: 50px;">
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
    <meta content="yes" name="app-moblie-web-app-capable">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title></title>
</head>

<style type="text/css">
    *{
        margin: 0;
        padding: 0;
        border: 0;
        outline: none;
        vertical-align: top;
        box-sizing: border-box;
    }
    body {
        font-family:"Helvetica Neue", Helvetica;
        background-color: #f0f0f0;
        font-size: 0.30rem;
        color: #2d2d2d;
        -webkit-text-size-adjust: none
    }
    ul, li {
        margin: 0px;
        padding: 0px;
        list-style-type: none;
    }

    img {
        border-top-width: 0px;
        border-right-width: 0px;
        border-bottom-width: 0px;
        border-left-width: 0px;
        border-top-style: none;
        border-right-style: none;
        border-bottom-style: none;
        border-left-style: none;
        max-width: 100%;
    }
    button{
        align-items: flex-start;
        text-align: center;
        cursor: default;
    }
    .pay-nav{
        width: 100%;
        height: auto;
        background: #ffffff;
        padding-bottom: 0.5rem;
    }
    .pay-success{
        width: 100%;
        height: auto;
        text-align: center;
    }
    .pay-success img{
        width: 1rem;
        height: 1rem;
        margin-top: 0.4rem;
    }
    .pay-success p{
        font-size: 0.5rem;
        margin-top: 0.1rem;
    }
    ul.pay-list{
        padding: 0 0.15rem;
        margin-top: 0.5rem;
        border-top: 1px solid #e7e7e7;
        border-bottom: 1px solid #e7e7e7;
    }
    ul.pay-list li{
        line-height: 0.7rem;
        border-bottom: 1px solid #e7e7e7;
    }
    ul.pay-list li:last-child {
        border-bottom: none;
    }
    ul.pay-list li span{
        float: right;
        color: #666;
    }
    p.pay-prompt{
        font-size: 0.13rem;
        margin-top: 0.2rem;
        padding-left: 0.15rem;
    }
    p.pay-prompt-red{
        color: #d43c33;
    }
    p.pay-prompt-gray{
        color: #666;
    }
    .pay-btn {
        width: 90%;
        height: .80rem;
        border-radius: .06rem;
        background: #d43c33;
        border: none;
        color: #fff;
        font-size: .34rem;
        margin: 0.4rem 5%;
    }
    .pay-btn:active{
        background: #C42019;
    }
</style>
<body>

<div class="pay-nav">
    <div class="pay-success">
        <img src="https://img.utiexian.com/rymobile/bill99/pay.png">
        <p>您已支付成功</p>
    </div>
    <ul class="pay-list">
        <li>订单号：<span>${orderId}</span></li>
        <li>查询方：<span>${payer}</span></li>
        <li>付款金额：<span>${orderAmount}元</span></li>
    </ul>
    <p class="pay-prompt pay-prompt-gray">我们将为您提供查询查复服务，并以短信的方式通知您结果。</p>
    <p class="pay-prompt pay-prompt-red">*各出票行反馈时间有所差异，请耐心等待结果，一般为1-2个工作日。</p>
</div>
</body>
<script type="javascript">
    window.onload = function(){
        autoWidth()
    }
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
</script>
</html>