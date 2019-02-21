[#assign websitePath = 'https://test.utiexian.com/website']
[#assign imagePath = 'https://img.utiexian.com']
[#assign staticPath = 'https://static.utiexian.com']

[#assign image_Path = 'https://192.168.1.40/']

<!-- 手机号隐藏中间四位 -->
[#function hideMobile mob maxLength=10]
    [#assign mob=mob?trim]
	[#if (mob?length > maxLength)]
		[#return mob?substring(0,3) + "****" + mob?substring(7,11)]
	[#else]
		[#return mob]
	[/#if]
[/#function]