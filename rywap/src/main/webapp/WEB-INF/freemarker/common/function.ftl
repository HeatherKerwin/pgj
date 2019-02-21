<!-- 手机号隐藏中间四位 -->
[#function hideMobile mob maxLength=10]
    [#assign mob=mob?trim]
	[#if (mob?length > maxLength)]
		[#return mob?substring(0,3) + "****" + mob?substring(7,11)]
	[#else]
		[#return mob]
	[/#if]
[/#function]