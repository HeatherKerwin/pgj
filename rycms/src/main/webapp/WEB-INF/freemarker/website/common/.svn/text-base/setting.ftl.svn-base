[#assign basePath = 'https://test.utiexian.com/website']
[#assign imagePath = 'https://img.utiexian.com']
[#assign jsPath = "https://static.utiexian.com/js/website"]
[#assign comPath = "https://static.utiexian.com/js/common"]
[#assign pluPath = "https://static.utiexian.com/plugins"]
[#assign bootAppPath = 'https://api.utiexian.com:8989']
[#assign staticPath = 'https://static.utiexian.com']



<!-- 手机号隐藏中间四位 -->
[#function hideMobile mob maxLength=10]
    [#assign mob=mob?trim]
	[#if (mob?length > maxLength)]
		[#return mob?substring(0,3) + "****" + mob?substring(7,11)]
	[#else]
		[#return mob]
	[/#if]
[/#function]
