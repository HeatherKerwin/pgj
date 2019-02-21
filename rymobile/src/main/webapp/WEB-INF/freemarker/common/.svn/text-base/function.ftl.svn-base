<#ftl strip_whitespace=true>
<#--
 * util.ftl 
 * @author Kevin.Wang
 * @since 1.0
 -->
 <#--
 	*获取浏览器cookie值
 	*@cookieName cookie的名称
 	-->
 <#function getCookie cookieName>
	<#assign cookies = Request.getCookies()>
	<#list cookies as cookie>
		<#if cookie.name = cookieName>
			<#return cookie.value>
		</#if>
	</#list>
	<#return "">
</#function>
 
<#--
	*字符串转日期时间
	*@string 要转换的字符串
	*@oldFormat 旧的时间格式
	*@newFormat 新的时间格式
	-->
<#macro stringToDatetime string oldFormat newFormat="">
	<#if string?has_content>
		<#if fomart2?has_content>
			${string?datetime(oldFormat)?string(newFormat)}
		<#else>
			${string?datetime(oldFormat)}
		</#if>
	</#if>
</#macro>
<#--
	*字符串转日期
	*@string 要转换的字符串
	*@oldFormat 旧的日期格式
	*@newFormat 新的日期格式
	-->
<#macro stringToDate string oldFormat newFormat="">
	<#if string?has_content>
		<#if fomart2?has_content>
			${string?date(oldFormat)?string(newFormat)}
		<#else>
			${string?date(oldFormat)}
		</#if>
	</#if>
</#macro>


<#--
 * 截取字符串
 * @string 待截取的字符串
 * @maxLength 要截取长度
-->
<#macro shortString string maxLength=30>
    <#assign string=string?trim>
	<#if (string?length > maxLength)>
		${string?substring(0,maxLength)}...
	<#else>
		${string}
	</#if>
</#macro>

<#--
 * 截取字符串
 * @string 待截取的字符串
 * @maxLength 要截取长度
-->
<#function funShortString string maxLength=30>
    <#assign string=string?trim>
	<#if (string?length > maxLength)>
		<#return string?substring(0,maxLength) + "...">
	<#else>
		<#return string>
	</#if>
</#function>



<#--
  * 格式化数字
  * @numVal 待格式化数字
  * @format 格式
  * @defaultVal 默认值
  -->
<#function formatNumber numVal format="0.00" defaultVal="--">
	<#if numVal?has_content>
		<#if numVal?is_number>
			<#return numVal?string(format)>
		<#else>
			<#return defaultVal>
		</#if>	
	<#else>
		<#return defaultVal>
	</#if>	
</#function>
<#--
  * 格式化数字（转换成百分数）
  * @numVal 待格式化数字
  * @format 格式
  * @defaultVal 默认值
  -->
<#function percent numVal format="0.00" defaultVal="--">
	<#if numVal?has_content>
		<#if numVal?is_number>
			<#assign val = formatNumber(numVal*100,format) + "%">
			<#return val>
		<#else>
			<#return defaultVal>
		</#if>	
	<#else>
		<#return defaultVal>
	</#if>
</#function>







