package com.utiexian.utils.cibfintech.econtract;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.utiexian.utils.MyException;
import com.utiexian.utils.cibfintech.CibConfig;
import com.utiexian.utils.cibfintech.CibUtil;
import com.utiexian.utils.cibfintech.econtract.until.FileUtil;
import com.utiexian.utils.cibfintech.econtract.until.VerifyUtil;
import com.utiexian.utils.utils.EmailUtil;
import com.utiexian.utils.utils.HttpUtil;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 电子签章（兴业数金）快捷签
 * @author WKX
 */
public class EcontractUtil {
	
	private static final HttpUtil http = new HttpUtil();
	
	/**
	 * 调用发生错误-返回值
	 * @author WKX
	 */
	private static Map<String,Object> ERROR(){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> meta = new HashMap<String,Object>();
		result.put("resultCode", "1");//状态码 0 成功 1 失败 2 已注册 3操作已经执行
		result.put("resultMessage", "操作失败！请联系客服");//成功或失败的描述
		
		meta.put("retCode", "econtract");//状态码
		meta.put("msgs", "操作失败！请联系客服");//成功或失败的描述
		result.put("meta", meta);
		return result;
	}
	
	/**
	 * 生成合同编号（前缀加"C"）
	 * @author WKX
	 * @param no 订单编号（或开户号）（作为合同编号："C"+no）
	 */
	public static String contractNum(String no){
		if(StringUtils.isBlank(no))no = CibUtil.date_random(3);//默认订单编号
		StringBuffer str = new StringBuffer("C");//前置
		str.append(no);//相关订单号或开户号
		return str.toString();
	}
	
	//-----------------------------------下载合同------------------------------------------------------------------------------------
	
	/**
	 * 校验是否有该合同（通过下载合同的方式）
	 * @author WKX
	 * @param contractNum 合同编号
	 * @return 返回参数{
	 * contractNum 合同编号
	 * resultCode 状态码 0成功1 失败
	 * resultMessage 成功或失败的描述
	 * }
	 */
	public static Map<String,Object> hasContract(String contractNum) throws IOException {
		if(StringUtils.isBlank(contractNum))return ERROR();
		
		Map<String,Object> result = downLoadContract(contractNum);
		if(result!=null && result.get("contractFile")!=null){
			result.remove("contractFile");//去掉文件，减少带宽占用
		}
		return result;
	}
	
	/**
	 * 校验是否有该合同（通过下载合同的方式）跨平台
	 * @author WKX
	 * @param envelopeId 信封编号（合同编号）
	 * @return 返回参数{
	 * contractNum 合同编号
	 * resultCode 状态码 0成功1 失败
	 * resultMessage 成功或失败的描述
	 * }
	 */
	public static Map<String,Object> hasUnionContract(String envelopeId) throws IOException {
		if(StringUtils.isBlank(envelopeId))return ERROR();
		
		Map<String,Object> result = downLoadUnionContract(envelopeId);
		if(result!=null && result.get("contractFile")!=null){
			result.remove("contractFile");//去掉文件，减少带宽占用
		}
		return result;
	}

	/**
	 * 下载合同（返回JSON）
	 * @author WKX
	 * @param contractNum 合同编号
	 * @return 返回参数{
	 * contractNum 合同编号
	 * contractFile 返回的合同文件（base64格式）
	 * resultCode 状态码 0成功1 失败
	 * resultMessage 成功或失败的描述
	 * }
	 */
	public static Map<String,Object> downLoadContract(String contractNum) {
		String url = CibConfig.ECONTRACT_DOMAIN + "/econtract-webapp/restapi/v1/server/apps/" + CibConfig.APPID + "/contracts/" + contractNum;
		try {
			if(StringUtils.isBlank(contractNum))throw new MyException();
			
			String jsonMsg = http.get(url, null);
			Map<String,Object> result = JSON.parseObject(jsonMsg);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}
	
	/**
	 * 下载合同（返回JSON）跨平台
	 * @author WKX
	 * @param envelopeId 信封编号（合同编号）
	 * @return 返回参数{
	 * contractNum 合同编号
	 * contractFile 返回的合同文件（base64格式）
	 * resultCode 状态码 0成功1 失败
	 * resultMessage 成功或失败的描述
	 * }
	 */
	public static Map<String,Object> downLoadUnionContract(String envelopeId) {
		String url = CibConfig.ECONTRACT_DOMAIN + "/econtract-webapp/restapi/v2/server/apps/" + CibConfig.APPID + "/contracts/" + envelopeId;
		try {
			if(StringUtils.isBlank(envelopeId))throw new MyException();
			
			String jsonMsg = http.get(url, null);
			Map<String,Object> result = JSON.parseObject(jsonMsg);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}
	
	/**
	 * 下载合同至邮箱
	 * @author WKX
	 * @param contractNum 合同编号
	 * @param email 邮箱地址
	 */
	public static Map<String,Object> downLoadContractToEmail(String contractNum,String email) throws IOException {
		if(StringUtils.isBlank(contractNum) || StringUtils.isBlank(email))return ERROR(); 
		
		Map<String,Object> result = downLoadContract(contractNum);
		if(result!=null && result.get("contractFile")!=null){
			String contractFile = (String)result.get("contractFile");
			
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b = decoder.decodeBuffer(contractFile);
			for(int i=0;i<b.length;++i){
				if(b[i]<0){//调整异常数据
					b[i]+=256;
				}
			}
			List<String> p = new ArrayList<String>();
			p.add(email);
			InputStream [] iss={new ByteArrayInputStream(b)};
			String [] names = {"/contract.pdf"};
			
			EmailUtil.sendEmail_SSL(p,"《票据管家-电子合同》", "<p style='font-style:italic'>请查收附件！</p>",iss,names);
		}
		return result;
	}
	
	/**
	 * 下载合同至邮箱
	 * @author WKX
	 * @param envelopeId 信封编号（合同编号）
	 * @param email 邮箱地址
	 */
	public static Map<String,Object> downLoadUnionContractToEmail(String envelopeId,String email) throws IOException {
		if(StringUtils.isBlank(envelopeId) || StringUtils.isBlank(email))return ERROR(); 
		
		Map<String,Object> result = downLoadUnionContract(envelopeId);
		if(result!=null && result.get("contractFile")!=null){
			String contractFile = (String)result.get("contractFile");
			
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b = decoder.decodeBuffer(contractFile);
			for(int i=0;i<b.length;++i){
				if(b[i]<0){//调整异常数据
					b[i]+=256;
				}
			}
			List<String> p = new ArrayList<String>();
			p.add(email);
			InputStream [] iss={new ByteArrayInputStream(b)};
			String [] names = {"/contract.pdf"};
			
			EmailUtil.sendEmail_SSL(p,"《票据管家-电子合同》", "<p style='font-style:italic'>请查收附件！</p>",iss,names);
		}
		return result;
	}
	
	//----------------------------------注册-------------------------------------------------------------------------------------
	
	/**
	 * 用户注册（企业注册）仅限：企业用户
	 * @author WKX
	 * @param params{
	 * name 企业名称（个人姓名姓名）
	 * email 用户邮箱
	 * telephoneNum 联系电话（手机号码）
	 * certType 证书类型（事件型和非事件性两种）0 非事件性1 事件性
	 * address 企业联系地址（个人联系地址）
	 * principal 企业负责人（个人用户不需要传递）
	 * userType 用户类型0：企业用户1：个人用户
	 * idType 证件类型(0 身份证，1 护照，7 组织机构代码，N 统一信用代码)注意：三证合一后必须使用统一信用代码，不可使用组织机构代码
	 * idNum 证件号（个人为身份证件号，企业为组织机构代码，护照为护照号）
	 * signVal 参数的签名值
	 * tickesId 操作标识,保证幂等性
	 * }
	 * @return 返回参数{
	 * userId 由兴业统一生成的唯一用户标识
	 * resultCode 状态码0 成功 1 失败2 已注册3操作已经执行
	 * resultMessage 成功或失败的描述
	 * }
	 * 注意（注册唯一性条件）：个人是手机号 +证件类型+证件号；企业是邮箱+证件类型+证件号
	 */
	public static Map<String,Object> userRegister(Map<String,String> params) {
		String url = CibConfig.ECONTRACT_DOMAIN + "/econtract-webapp/restapi/v1/server/apps/" + CibConfig.APPID + "/users";
		try {
			params.put("certType", "0");//证书类型（事件型和非事件性两种）0 非事件性1 事件性
			params.put("userType", "0");//用户类型0：企业用户1：个人用户
			params.put("idType", "N");//证件类型(0 身份证，1 护照，7 组织机构代码，N 统一信用代码)注意：三证合一后必须使用统一信用代码，不可使用组织机构代码
			
			params.put("tickesId", CibUtil.date_random(3));//操作标识,保证幂等性
			
			String requestData = VerifyUtil.parseRequestData(JSON.toJSONString(params));
	        String signVal = VerifyUtil.sign(requestData, CibConfig.EXCH_PRIVATE_KEY);//平台私钥
	        params.put("signVal", signVal);
	        
			String jsonMsg = http.post(url, params);
			Map<String,Object> result = JSON.parseObject(jsonMsg);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}
	
	/**
	 * 合同签署（快捷签）
	 * @author WKX
	 * @param params{
	 * contractNum 合同编号（不可重复）
	 * userId 合同签署人（用户注册时，由兴业统一生成的唯一用户标识）
	 * contractFile 待签署的合同文（首次发起签署是传输文件的base64 码,再次签署不需要传递）
	 * keyWord 签署关键字(签署位置由关键字识别)
	 * }
	 * @return 返回参数{
	 * userId 合同签署人（用户注册时，由兴业统一生成的唯一用户标识）
	 * contractNum 合同编号
	 * resultCode 状态码 0 成功1 失败
	 * resultMessage 成功或失败的描述
	 * }
	 */
	public static Map<String,Object> quickSign(Map<String,String> params) {
		String url = CibConfig.ECONTRACT_DOMAIN + "/econtract-webapp/restapi/v1/server/apps/" + CibConfig.APPID + "/quickEnvelopes";
		try {
			params.put("tickesId", CibUtil.date_random(3));//操作标识,保证幂等性
			
			String requestData = VerifyUtil.parseRequestData(JSON.toJSONString(params));
	        String signVal = VerifyUtil.sign(requestData, CibConfig.EXCH_PRIVATE_KEY);//平台私钥
	        params.put("signVal", signVal);
	        
			String jsonMsg = http.post(url, params);
			Map<String,Object> result = JSON.parseObject(jsonMsg);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}
	
	/**
	 * 合同签署（跨平台签）
	 * @author WKX
	 * @param params{
	 * envelopeId 信封编号（发起时甲方为空，签署时不能为空）
	 * contractName 合同文件的标题(乙方签署不用传)
	 * contractFile 待签署的合同文件(乙方签署不用传)
	 * userId 合同签署人（用户注册时，由兴业统一生成的唯一用户标识）
	 * keyWord 签署关键字(签署位置由关键字识别)企业名称
	 * }
	 * @return 返回参数{
	 * userId 合同签署人（用户注册时，由兴业统一生成的唯一用户标识）
	 * envelopeId 信封编号
	 * resultCode 状态码 0 成功1 失败
	 * resultMessage 成功或失败的描述
	 * }
	 */
	public static Map<String,Object> quickSignUnion(Map<String,String> params) {
		String url = CibConfig.ECONTRACT_DOMAIN + "/econtract-webapp/restapi/v2/server/apps/" + CibConfig.APPID + "/quickEnvelopes";
		try {
			params.put("tickesId", CibUtil.date_random(3));//操作标识,保证幂等性
			
			String requestData = VerifyUtil.parseRequestData(JSON.toJSONString(params));
	        String signVal = VerifyUtil.sign(requestData, CibConfig.EXCH_PRIVATE_KEY);//平台私钥
	        params.put("signVal", signVal);
	        
			String jsonMsg = http.post(url, params);
			Map<String,Object> result = JSON.parseObject(jsonMsg);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}
	
	//---------------------------------数据调试模拟-----------------------------------------------------------------------------------
	
	/**
	 * 模拟用户注册
	 * @author WKX
	 */
	public static Map<String,Object> userRegister() {
		Map<String,String> params = new HashMap<String, String>();
		params.put("name", "上海票据管家有限公司");//企业名称（个人姓名姓名）
		params.put("email", "1397009898@qq.com");//用户邮箱
		params.put("telephoneNum", "13818994981");//联系电话（手机号码）
		params.put("certType", "0");//证书类型（事件型和非事件性两种）0 非事件性1 事件性
		params.put("address", "上海白玉兰");//企业联系地址（个人联系地址）
		params.put("principal", "张三");//企业负责人（个人用户不需要传递）
		params.put("userType", "0");//用户类型0：企业用户1：个人用户
		params.put("idType", "N");//证件类型(0 身份证，1 护照，7 组织机构代码，N 统一信用代码)注意：三证合一后必须使用统一信用代码，不可使用组织机构代码
		params.put("idNum", "91310000MA1K300B33");//证件号（个人为身份证件号，企业为组织机构代码，护照为护照号）
		return userRegister(params);
	}
	
	/**
	 * 模拟快捷签（甲方）
	 * @author WKX
	 * @param userId 注册ID
	 */
	public static Map<String,Object> quickSign(String userId) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("contractNum", "111122223333111122111");//合同编号（不可重复）
		params.put("userId", userId);//合同签署人（用户注册时，由兴业统一生成的唯一用户标识）
		
		BASE64Encoder base64Encoder = new BASE64Encoder();
	    String file = base64Encoder.encode(FileUtil.file2byte("E://wts-bns.pdf"));
		params.put("contractFile", file);//待签署的合同文（首次发起签署是传输文件的base64 码,再次签署不需要传递）
		
		params.put("keyWord", "公司公章");//签署关键字(签署位置由关键字识别)
		return quickSign(params);
	}
	
	/**
	 * 模拟快捷签（乙方）
	 * @author WKX
	 * @param userId 注册ID
	 */
	public static Map<String,Object> quickSign2nd(String userId) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("contractNum", "111122223333");//合同编号（不可重复）
		params.put("userId", userId);//合同签署人（用户注册时，由兴业统一生成的唯一用户标识）
		
		params.put("keyWord", "或兴业银行任一");//签署关键字(签署位置由关键字识别)
		return quickSign(params);
	}
	
	/**
	 * 生成文件（保存指定位置）
	 * @author WKX
	 * @param contractNum 合同编号
	 * @param filePath 保存路径
	 */
	public static void downLoadContractToPdf(String contractNum,String filePath) throws Exception {
		Map<String,Object> result = downLoadContract(contractNum);
		if(result==null || result.get("contractFile")==null)throw new Exception();
		String contractFile = (String)result.get("contractFile");
		
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = decoder.decodeBuffer(contractFile);
		for(int i=0;i<b.length;++i){
			if(b[i]<0){//调整异常数据
				b[i]+=256;
			}
		}
		File uploadDir = new File(filePath);
		if(!uploadDir.isDirectory()){
			uploadDir.mkdirs();
		}
		File saveDirFile = new File(filePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		String fileName = "contract.pdf";
		fileName = "contract-" + CibUtil.date_random(3) + ".pdf";//重命名
		OutputStream pic = new FileOutputStream(filePath+fileName);
		pic.write(b);
	    pic.flush();
	    pic.close();
	}
	
	/**
	 * 生成文件（保存指定位置）
	 * @author WKX
	 * @param contractNum 合同编号
	 * @param filePath 保存路径
	 */
	public static void downLoadUnionContractToPdf(String contractNum,String filePath) throws Exception {
		Map<String,Object> result = downLoadUnionContract(contractNum);
		if(result==null || result.get("contractFile")==null)throw new Exception();
		String contractFile = (String)result.get("contractFile");
		
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = decoder.decodeBuffer(contractFile);
		for(int i=0;i<b.length;++i){
			if(b[i]<0){//调整异常数据
				b[i]+=256;
			}
		}
		File uploadDir = new File(filePath);
		if(!uploadDir.isDirectory()){
			uploadDir.mkdirs();
		}
		File saveDirFile = new File(filePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		String fileName = "contract.pdf";
		fileName = "contract-" + CibUtil.date_random(3) + ".pdf";//重命名
		OutputStream pic = new FileOutputStream(filePath+fileName);
		pic.write(b);
	    pic.flush();
	    pic.close();
	}
	
	public static void main(String[] args)throws Exception {
		//System.err.println(userRegister());//ff8080816166035101616e786e0d3e96、ff80808161d51b2b0161db1a1ce81699
		//System.err.println(quickSign("ff8080816166035101616e786e0d3e96"));//甲方
		//System.err.println(quickSign2nd("ff80808161d51b2b0161db1a1ce81699"));//乙方
		//downLoadContractToPdf("111122223333","e://");
		//System.err.println(downLoadContract("ff80808162a868690162a86eec660046"));
		//System.err.println(hasContract("11112222333311"));
		
		
		downLoadUnionContractToPdf("ff80808162a868690162a86eec660046","e://");
	}
}