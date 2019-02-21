package com.utiexian.third.controller.yonyou.api;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.utiexian.third.service.ParamsService;
import com.utiexian.third.utils.MyProperties;
import com.utiexian.third.utils.code.ResponseEncode;
import com.utiexian.utils.BaseController;
import com.utiexian.utils.MyException;
import com.utiexian.utils.cibfintech.CibUtil;
import com.utiexian.utils.utils.FileUtil;
import com.utiexian.utils.utils.HttpUtil;

/**
 * 开户（申请绑定）
 * @author WKX
 */
@RestController
@RequestMapping("/api/cib")
public class ApiCibController extends BaseController{
	
	@Autowired
    ParamsService paramsService;
	
	@Autowired
    RestTemplate restTemplate;
	
	@Autowired
	private MyProperties myProperties;
	
	private static final String ERROR = "请求参数异常！";
	private static final String CONTACTMOBILE = "企业信息获取失败！";
	
	private static final String type_error = "角色未知！请联系客服";
	private static final String email_error = "请输入邮箱地址！";
	private static final String name_error = "请输入企业名称！";
	private static final String address_error = "请输入联系地址！";
	private static final String bankAcctBankNo_error = "请输入开户银行行号！";
	private static final String bankAcctCnapsCode_error = "请输入分支行的人行大小额行号！";
	private static final String bankAcctBankBranch_error = "请输入分支行完整名称！";
	private static final String bankAcctAcctNo_error = "请输入银行账号！";
	private static final String bankAcctAcctName_error = "请输入银行账户名！";
	
	private static final String bizLicenceRegisteredNo_error = "请输入注册号（企业法人营业执照）！";
	private static final String bizLicenceName_error = "请输入名称（企业法人营业执照）！";
	private static final String bizLicenceType_error = "请输入公司类型（企业法人营业执照）！";
	private static final String bizLicenceAddr_error = "请输入住所（企业法人营业执照）！";
	private static final String bizLicenceLegalName_error = "请输入法定代表人姓名（企业法人营业执照）！";
	private static final String bizLicenceFoundedDate_error = "请输入成立日期（企业法人营业执照）！";
	
	private static final String legalCertNo_error = "请输入法人代表身份证号（法人代表）！";
	private static final String agentCertNo_error = "请输入经办人身份证号（经办人）！";
	
	private static final String treasurerName_error = "请输入财务人员姓名（财务）！";
	private static final String treasurerCertNo_error = "请输入财务人员身份证号（财务）！";
	
	private static final String orgCodeCertCode_error = "请输入代码（组织机构代码证）！";
	private static final String orgCodeCertName_error = "请输入机构名称（组织机构代码证）！";
	private static final String orgCodeCertType_error = "请输入机构类型（组织机构代码证）！";
	
	private static final String contactName_error = "请输入联系人姓名（企业联系人）！";
	private static final String contactMobile_error = "请输入联系人手机（企业联系人）！";
	
	private static final String imgPathA1_error = "请上传法人身份证（正面）！";
	private static final String imgPathA2_error = "请上传法人身份证（背面）！";
	private static final String imgPath20_error = "请上传企业法人营业执照！";
	
	private static final String amt_error = "请输入正确的鉴定金额！";
	
	private static final String bank_branch_error = "请输入分支行号或名称！";
	
	/**
	 * 保存开户（第一步）解密
	 * @param appid 商户授权编号
	 * @param signature
	 * @param message
	 */
	@ResponseEncode
	@RequestMapping(value="/corp/save")
	public String decodeCorpSave(String appid,String signature,String message,String imgPathA1,String imgPathA2,String imgPath20,HttpServletRequest request){
		Map<String,Object> params = new HashMap<String,Object>();
		try {
			params = paramsService.getParams(appid, signature, message);
			if(params==null)throw new MyException(ERROR);
			
			if(params.get("userId")==null || params.get("enterpriseId")==null)throw new MyException("请求参数不完整（ID）！");
			if(params.get("type")==null)throw new MyException(type_error);
			if(params.get("email")==null)throw new MyException(email_error);
			if(params.get("name")==null)throw new MyException(name_error);
			if(params.get("address")==null)throw new MyException(address_error);
			if(params.get("bankAcctBankNo")==null)throw new MyException(bankAcctBankNo_error);
			if(params.get("bankAcctCnapsCode")==null)throw new MyException(bankAcctCnapsCode_error);
			if(params.get("bankAcctBankBranch")==null)throw new MyException(bankAcctBankBranch_error);
			if(params.get("bankAcctAcctNo")==null)throw new MyException(bankAcctAcctNo_error);
			if(params.get("bankAcctAcctName")==null)throw new MyException(bankAcctAcctName_error);
			
			if(params.get("bizLicenceRegisteredNo")==null)throw new MyException(bizLicenceRegisteredNo_error);
			if(params.get("bizLicenceName")==null)throw new MyException(bizLicenceName_error);
			if(params.get("bizLicenceType")==null)throw new MyException(bizLicenceType_error);
			if(params.get("bizLicenceAddr")==null)throw new MyException(bizLicenceAddr_error);
			if(params.get("bizLicenceLegalName")==null)throw new MyException(bizLicenceLegalName_error);
			if(params.get("bizLicenceFoundedDate")==null)throw new MyException(bizLicenceFoundedDate_error);
			
			if(params.get("legalCertNo")==null)throw new MyException(legalCertNo_error);
			if(params.get("agentCertNo")==null)throw new MyException(agentCertNo_error);
			
			if(params.get("treasurerName")==null)throw new MyException(treasurerName_error);
			if(params.get("treasurerCertNo")==null)throw new MyException(treasurerCertNo_error);
			
			if(params.get("orgCodeCertCode")==null)throw new MyException(orgCodeCertCode_error);
			if(params.get("orgCodeCertName")==null)throw new MyException(orgCodeCertName_error);
			if(params.get("orgCodeCertType")==null)throw new MyException(orgCodeCertType_error);
			
			if(params.get("contactName")==null)throw new MyException(contactName_error);
			if(params.get("contactMobile")==null)throw new MyException(contactMobile_error);
			
			String type = params.get("type").toString();
			String email = params.get("email").toString();
			String name = params.get("name").toString();
			String address = params.get("address").toString();
			String bankAcctBankNo = params.get("bankAcctBankNo").toString();
			String bankAcctCnapsCode = params.get("bankAcctCnapsCode").toString();
			String bankAcctBankBranch = params.get("bankAcctBankBranch").toString();
			String bankAcctAcctNo = params.get("bankAcctAcctNo").toString();
			String bankAcctAcctName = params.get("bankAcctAcctName").toString();
			
			//非必传（机构信用代码证）
			String orgCreditCodeCertCode = params.get("bizLicenceRegisteredNo")!=null?params.get("bizLicenceRegisteredNo").toString():null;
			String orgCreditCodeCertName = params.get("orgCreditCodeCertName")!=null?params.get("orgCreditCodeCertName").toString():null;
			String orgCreditCodeCertAddr = params.get("orgCreditCodeCertAddr")!=null?params.get("orgCreditCodeCertAddr").toString():null;
			String orgCreditCodeCertExpiredDate = params.get("orgCreditCodeCertExpiredDate")!=null?params.get("orgCreditCodeCertExpiredDate").toString():null;
			
			String bizLicenceRegisteredNo = params.get("bizLicenceRegisteredNo").toString();
			String bizLicenceName = params.get("bizLicenceName").toString();
			String bizLicenceType = params.get("bizLicenceType").toString();
			String bizLicenceAddr = params.get("bizLicenceAddr").toString();
			String bizLicenceLegalName = params.get("bizLicenceLegalName").toString();
			String bizLicenceFoundedDate = params.get("bizLicenceFoundedDate").toString();
			
			//非必传（税务登记证）
			String taxCertGovTaxNo = params.get("taxCertGovTaxNo")!=null?params.get("taxCertGovTaxNo").toString():null;
			String taxCertLocalTaxNo = params.get("taxCertLocalTaxNo")!=null?params.get("taxCertLocalTaxNo").toString():null;
			String taxCertName = params.get("taxCertName")!=null?params.get("taxCertName").toString():null;
			
			String legalCertNo = params.get("legalCertNo").toString();
			String agentCertNo = params.get("agentCertNo").toString();
			
			String treasurerName = params.get("treasurerName").toString();
			String treasurerCertNo = params.get("treasurerCertNo").toString();
			
			String orgCodeCertCode = params.get("orgCodeCertCode").toString();
			String orgCodeCertName = params.get("orgCodeCertName").toString();
			String orgCodeCertType = params.get("orgCodeCertType").toString();
			
			//非必传（开户许可证）
			String acctPermitNo = params.get("acctPermitNo")!=null?params.get("acctPermitNo").toString():null;
			String acctPermitPermitNo = params.get("acctPermitPermitNo")!=null?params.get("acctPermitPermitNo").toString():null;
			
			String contactName = params.get("contactName").toString();
			String contactMobile = params.get("contactMobile").toString();
			
//			String imgPathA1 = params.get("imgPathA1").toString();
//			String imgPathA2 = params.get("imgPathA2").toString();
//			String imgPath20 = params.get("imgPath20").toString();
			
			String userId = params.get("userId").toString();
			String enterpriseId = params.get("enterpriseId").toString();
			return this.corpSave(userId, enterpriseId, type, email, name, address, bankAcctBankNo, bankAcctCnapsCode, bankAcctBankBranch, bankAcctAcctNo, bankAcctAcctName, orgCreditCodeCertCode, orgCreditCodeCertName, orgCreditCodeCertAddr, orgCreditCodeCertExpiredDate, bizLicenceRegisteredNo, bizLicenceName, bizLicenceType, bizLicenceAddr, bizLicenceLegalName, bizLicenceFoundedDate, taxCertGovTaxNo, taxCertLocalTaxNo, taxCertName, legalCertNo, agentCertNo, treasurerName, treasurerCertNo, orgCodeCertCode, orgCodeCertName, orgCodeCertType, acctPermitNo, acctPermitPermitNo, contactName, contactMobile, imgPathA1, imgPathA2, imgPath20,request);
		} catch (Exception e) {
			return this.FAILED(e);
		}
	}
	
	/**
	 * 申请开户（第一步）（contactMobile将用于注册票据管家）作为唯一标识
	 * @author WKX
	 * @param userId 用户主键（用友）
	 * @param enterpriseId 企业主键（用友）
	 * @param type 角色：企业0、机构1
	 * @param name 企业名称
	 * @param email 邮箱[倚天剑]
	 * @param address 联系地址
	 * @param bankAcctBankNo 开户银行行号（见兴业数金附录）
	 * @param bankAcctCnapsCode 分支行的人行大小额行号
	 * @param bankAcctBankBranch 分支行完整名称
	 * @param bankAcctAcctNo 银行账号
	 * @param bankAcctAcctName 银行账户名
	 * @param orgCreditCodeCertCode 代码（机构信用代码证）【如果没有该信息，平台传“-”给数金】
	 * @param orgCreditCodeCertName 名称（机构信用代码证）【如果没有该信息，平台传“-”给数金】
	 * @param orgCreditCodeCertAddr 地址（机构信用代码证）【如果没有该信息，平台传“-”给数金】
	 * @param orgCreditCodeCertExpiredDate 有效期（机构信用代码证）【如果没有该信息，平台传“-”给数金】
	 * @param bizLicenceRegisteredNo 注册号（企业法人营业执照）[“三证合一”时填证件上统一社会信用代码]
	 * @param bizLicenceName 名称（企业法人营业执照）
	 * @param bizLicenceType 公司类型（企业法人营业执照）
	 * @param bizLicenceAddr 住所（企业法人营业执照）
	 * @param bizLicenceLegalName 法定代表人姓名（企业法人营业执照）
	 * @param bizLicenceFoundedDate 成立日期（企业法人营业执照）
	 * @param taxCertGovTaxNo 国税字号（税务登记证）[“三证合一”时填证件上统一社会信用代码]【如果没有该信息，平台传“-”给数金】
	 * @param taxCertLocalTaxNo 地税字号（税务登记证）[“三证合一”时填证件上统一社会信用代码]【如果没有该信息，平台传“-”给数金】
	 * @param taxCertName 纳税人名称（税务登记证）[“三证合一”时填证件上名称]【如果没有该信息，平台传“-”给数金】
	 * @param legalCertNo 法人代表身份证号（法人代表）
	 * @param agentCertNo 经办人身份证号（经办人）
	 * @param treasurerName 财务人员姓名（财务）
	 * @param treasurerCertNo 财务人员身份证号（财务）
	 * @param orgCodeCertCode 代码（组织机构代码证）[“三证合一”时填证件上统一社会信用代码]
	 * @param orgCodeCertName 机构名称（组织机构代码证）[“三证合一”时填证件上名称]
	 * @param orgCodeCertType 机构类型（组织机构代码证）[“三证合一”时填证件上类型]
	 * @param acctPermitNo 编号（开户许可证）【如果没有该信息，平台传“-”给数金】
	 * @param acctPermitPermitNo 核准号（开户许可证）【如果没有该信息，平台传“-”给数金】
	 * @param contactName 联系人姓名（企业联系人）
	 * @param contactMobile 联系人手机（企业联系人）[同时用于登录企业控台，初始登录密码是企业编号（corpNo）后6位]
	 * @param imgPathA1 法人身份证-正面
	 * @param imgPathA2 法人身份证-背面
	 * @param imgPath20 企业法人营业执照
	 */
    public String corpSave(String userId,String enterpriseId,String type,String email,String name,String address,String bankAcctBankNo,String bankAcctCnapsCode,String bankAcctBankBranch,String bankAcctAcctNo,String bankAcctAcctName,
    		String orgCreditCodeCertCode,String orgCreditCodeCertName,String orgCreditCodeCertAddr,String orgCreditCodeCertExpiredDate,
    		String bizLicenceRegisteredNo,String bizLicenceName,String bizLicenceType,String bizLicenceAddr,String bizLicenceLegalName,String bizLicenceFoundedDate,
    		String taxCertGovTaxNo,String taxCertLocalTaxNo,String taxCertName,String legalCertNo,String agentCertNo,String treasurerName,String treasurerCertNo,
    		String orgCodeCertCode,String orgCodeCertName,String orgCodeCertType,String acctPermitNo,String acctPermitPermitNo,String contactName,String contactMobile,
    		String imgPathA1,String imgPathA2,String imgPath20,HttpServletRequest request){
		try {
			if(StringUtils.isBlank(type))throw new MyException(type_error);
			if(StringUtils.isBlank(email))throw new MyException(email_error);
			if(StringUtils.isBlank(name))throw new MyException(name_error);
			if(StringUtils.isBlank(address))throw new MyException(address_error);
			if(StringUtils.isBlank(bankAcctBankNo))throw new MyException(bankAcctBankNo_error);
			if(StringUtils.isBlank(bankAcctCnapsCode))throw new MyException(bankAcctCnapsCode_error);
			if(StringUtils.isBlank(bankAcctBankBranch))throw new MyException(bankAcctBankBranch_error);
			if(StringUtils.isBlank(bankAcctAcctNo))throw new MyException(bankAcctAcctNo_error);
			if(StringUtils.isBlank(bankAcctAcctName))throw new MyException(bankAcctAcctName_error);
			
			if(StringUtils.isBlank(bizLicenceRegisteredNo))throw new MyException(bizLicenceRegisteredNo_error);
			if(StringUtils.isBlank(bizLicenceName))throw new MyException(bizLicenceName_error);
			if(StringUtils.isBlank(bizLicenceType))throw new MyException(bizLicenceType_error);
			if(StringUtils.isBlank(bizLicenceAddr))throw new MyException(bizLicenceAddr_error);
			if(StringUtils.isBlank(bizLicenceLegalName))throw new MyException(bizLicenceLegalName_error);
			if(StringUtils.isBlank(bizLicenceFoundedDate))throw new MyException(bizLicenceFoundedDate_error);
			
			if(StringUtils.isBlank(legalCertNo))throw new MyException(legalCertNo_error);
			if(StringUtils.isBlank(agentCertNo))throw new MyException(agentCertNo_error);
			
			if(StringUtils.isBlank(treasurerName))throw new MyException(treasurerName_error);
			if(StringUtils.isBlank(treasurerCertNo))throw new MyException(treasurerCertNo_error);
			
			if(StringUtils.isBlank(orgCodeCertCode))throw new MyException(orgCodeCertCode_error);
			if(StringUtils.isBlank(orgCodeCertName))throw new MyException(orgCodeCertName_error);
			if(StringUtils.isBlank(orgCodeCertType))throw new MyException(orgCodeCertType_error);
			
			if(StringUtils.isBlank(contactName))throw new MyException(contactName_error);
			if(StringUtils.isBlank(contactMobile))throw new MyException(contactMobile_error);
			
			if(StringUtils.isBlank(imgPathA1))throw new MyException(imgPathA1_error);
			if(StringUtils.isBlank(imgPathA2))throw new MyException(imgPathA2_error);
			if(StringUtils.isBlank(imgPath20))throw new MyException(imgPath20_error);
			
			if(StringUtils.isBlank(orgCreditCodeCertCode))orgCreditCodeCertCode = "-";
			if(StringUtils.isBlank(orgCreditCodeCertName))orgCreditCodeCertName = "-";
			if(StringUtils.isBlank(orgCreditCodeCertAddr))orgCreditCodeCertAddr = "-";
			if(StringUtils.isBlank(orgCreditCodeCertExpiredDate))orgCreditCodeCertExpiredDate = "-";
			if(StringUtils.isBlank(taxCertGovTaxNo))taxCertGovTaxNo = "-";
			if(StringUtils.isBlank(taxCertLocalTaxNo))taxCertLocalTaxNo = "-";
			if(StringUtils.isBlank(taxCertName))taxCertName = "-";
			if(StringUtils.isBlank(acctPermitNo))acctPermitNo = "-";
			if(StringUtils.isBlank(acctPermitPermitNo))acctPermitPermitNo = "-";
			
			MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
			params.set("userId",userId);
			params.set("enterpriseId",enterpriseId);
			params.set("type",type);
			params.set("name",name);
			params.set("address",address);
			params.set("email",email);
			
			params.set("bankAcctBankNo",bankAcctBankNo);
			params.set("bankAcctCnapsCode",bankAcctCnapsCode);
			params.set("bankAcctBankBranch",bankAcctBankBranch);
			params.set("bankAcctAcctNo",bankAcctAcctNo);
			params.set("bankAcctAcctName",bankAcctAcctName);
			
			params.set("orgCreditCodeCertCode",orgCreditCodeCertCode);
			params.set("orgCreditCodeCertName",orgCreditCodeCertName);
			params.set("orgCreditCodeCertAddr",orgCreditCodeCertAddr);
			params.set("orgCreditCodeCertExpiredDate",orgCreditCodeCertExpiredDate);
			
			params.set("bizLicenceRegisteredNo",bizLicenceRegisteredNo);
			params.set("bizLicenceName",bizLicenceName);
			params.set("bizLicenceType",bizLicenceType);
			params.set("bizLicenceAddr",bizLicenceAddr);
			params.set("bizLicenceLegalName",bizLicenceLegalName);
			params.set("bizLicenceFoundedDate",bizLicenceFoundedDate);
			
			params.set("taxCertGovTaxNo",taxCertGovTaxNo);
			params.set("taxCertLocalTaxNo",taxCertLocalTaxNo);
			params.set("taxCertName",taxCertName);
			
			params.set("legalCertNo",legalCertNo);
			params.set("agentCertNo",agentCertNo);
			
			params.set("treasurerName",treasurerName);
			params.set("treasurerCertNo",treasurerCertNo);
			
			params.set("orgCodeCertCode",orgCodeCertCode);
			params.set("orgCodeCertName",orgCodeCertName);
			params.set("orgCodeCertType",orgCodeCertType);
			
			params.set("acctPermitNo",acctPermitNo);
			params.set("acctPermitPermitNo",acctPermitPermitNo);
			
			params.set("contactName",contactName);
			params.set("contactMobile",contactMobile);
			
			params.set("imgPathA1",this.base64ToImage(imgPathA1));//转存图片
			params.set("imgPathA2",this.base64ToImage(imgPathA2));//转存图片
			params.set("imgPath20",this.base64ToImage(imgPath20));//转存图片
			
			params.set("mobile",contactMobile);//注册手机号（经办人作为唯一标识）
			
			params.set("qudao","PC");//渠道：PC,APP
			params.set("hezuo","yonyou");//用友
			params.set("ip",HttpUtil.getIpAddr(request));
			params.set("uuid",UUID.randomUUID().toString());
			String res = restTemplate.postForObject("http://utiexian-server/thirdkey/cib/save",params, String.class);
			return this.SUCCESS(res);
		} catch (Exception e) {
			return this.FAILED(e);
		}
    }
    
    /**
     * 保存图片（BASE64保存临时路径）返回保存的地址
     * @param base64Image BASE64原始图片
     */
    public String base64ToImage(String base64Image) throws Exception{
    	String path = myProperties.getUploadPath();
    	Map<String,Object> result = FileUtil.image(base64Image, null, path);
    	if(result!=null && result.get("base64Image")!=null){
    		return result.get("base64Image").toString();
    	}
    	return null;
    }
    
    /**
     * 小额鉴定（第二步）解密
	 * @param appid 商户授权编号
     * @param signature
     * @param message
     */
    @ResponseEncode
    @PostMapping(value="/corp/authenticate")
	public String decodeAuthenticate(String appid,String signature,String message){
    	Map<String,Object> params = new HashMap<String,Object>();
		try {
			params = paramsService.getParams(appid, signature, message);
			if(params==null)throw new MyException(ERROR);
			
			if(params.get("userId")==null || params.get("enterpriseId")==null)throw new MyException("请求参数不完整（ID）！");
			if(params.get("contactMobile")==null)throw new MyException(CONTACTMOBILE);
			if(params.get("amt")==null)throw new MyException(amt_error);
			
			String contactMobile = params.get("contactMobile").toString();
			String amt = params.get("amt").toString();
			
			String userId = params.get("userId").toString();
			String enterpriseId = params.get("enterpriseId").toString();
			return this.authenticate(enterpriseId, userId,contactMobile,amt);
		} catch (Exception e) {
			return this.FAILED(e);
		}
    }
    
    /**
	 * 小额鉴定（第二步）
	 * @author WKX
	 * @param userId 用户主键（用友）
	 * @param enterpriseId 企业主键（用友）
	 * @param contactMobile 联系人手机（企业联系人）作为平台唯一标识
	 * @param amt 金额
	 * @return 响应字段说明：
	 * /corp/corp_no 企业编号
	 * /corp/name 企业名称
	 * /corp/status 公司状态(0：待审核 1：审核失败 2：正常 3：锁定 4：无效 5：审核通过待鉴定 6：鉴定失败)
	 * /authenticate_order/left_count 剩余次数（总共6次）
	 */
    public String authenticate(String enterpriseId,String userId,String contactMobile,String amt){
		try {
			if(StringUtils.isBlank(contactMobile))throw new MyException(CONTACTMOBILE);
			if(StringUtils.isBlank(amt))throw new MyException(amt_error);
			MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
			
			params.set("userId",userId);
			params.set("enterpriseId",enterpriseId);
			
			params.set("mobile",contactMobile);//注册手机号（经办人作为唯一标识）
			params.set("amt",amt);
			params.set("type",0);//角色：企业0、机构1
			String res = restTemplate.postForObject("http://utiexian-server/thirdkey/cib/corp/authenticate",params, String.class);
			return this.SUCCESS(res);
		} catch (Exception e) {
			return this.FAILED(e);
		}
    }
    
    /**
     * 查询开户信息（解密）
	 * @param appid 商户授权编号
     * @param signature
     * @param message
     */
	@ResponseEncode
	@RequestMapping(value="/corp/query")
	public String decodeCorpQuery(String appid,String signature,String message,HttpServletRequest request){
		Map<String,Object> params = new HashMap<String,Object>();
		try {
			params = paramsService.getParams(appid, signature, message);
			if(params==null)throw new MyException(ERROR);
			
			if(params.get("userId")==null || params.get("enterpriseId")==null)throw new MyException("请求参数不完整（ID）！");
			if(params.get("contactMobile")==null)throw new MyException(CONTACTMOBILE);
			
			String contactMobile = params.get("contactMobile").toString();
			String userId = params.get("userId").toString();
			String enterpriseId = params.get("enterpriseId").toString();
			return this.corpQuery(userId, enterpriseId,contactMobile,request);
		} catch (Exception e) {
			return this.FAILED(e);
		}
	}
	
	/**
	 * 查询开户信息
	 * @author WKX
	 * @param userId 用户主键（用友）
	 * @param enterpriseId 企业主键（用友）
	 * @param contactMobile 联系人手机（企业联系人）作为平台唯一标识
	 * @return 响应字段说明：
	 * /corp/corp_no 企业编号
	 * /corp/name 企业名称
	 * /corp/status 公司状态
	 * /corp/v_acct/acct_no 虚拟账户
	 * /corp/v_acct/acct_name 虚拟账户名称
	 * /corp/v_acct/balance 可用余额
	 * /corp/v_acct/frozen_balance 冻结余额
	 * /corp/v_acct/trading_amt 交易中金额
	 * /cib 开户信息
	 * /login_url 兴业数金登录地址
	 */
    public String corpQuery(String userId,String enterpriseId,String contactMobile,HttpServletRequest request){
		try {
			if(StringUtils.isBlank(contactMobile))throw new MyException(CONTACTMOBILE);
			MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
			params.set("userId",userId);
			params.set("enterpriseId",enterpriseId);
			
			params.set("type",0);//角色：企业0、机构1
			params.set("mobile",contactMobile);//注册手机号（经办人作为唯一标识）
			
			params.set("qudao","PC");//渠道：PC,APP
			params.set("hezuo","yonyou");//用友
			params.set("ip",HttpUtil.getIpAddr(request));
			params.set("uuid",UUID.randomUUID());
			String res = restTemplate.postForObject("http://utiexian-server/thirdkey/cib/corp/query",params, String.class);
			return this.SUCCESS(res);
		} catch (Exception e) {
			return this.FAILED(e);
		}
    }
    
	/**
	 * 分支行名称模糊查询（分支行号精准查询）解密
	 * @param appid 商户授权编号
	 * @param signature
	 * @param message
	 */
	@ResponseEncode
	@RequestMapping(value="/query/back")
	public String decodeQueryBack(String appid,String signature,String message){
		Map<String,Object> params = new HashMap<String,Object>();
		try {
			params = paramsService.getParams(appid, signature, message);
			if(params==null)throw new MyException(ERROR);
			
			if(params.get("bank_branch")==null)throw new MyException(bank_branch_error);
			
			return this.queryBack(params.get("bank_branch").toString());
		} catch (Exception e) {
			return this.FAILED(e);
		}
	}
	
	/**
	 * 分支行名称模糊查询（分支行号精准查询）
	 * @author WKX
	 * @param bank_branch 分支行名称（分支行名称、分支行号[精准查询]）
	 * @return 响应字段说明：
	 * /banks 大小额系统信息（返回结果列表）
	 * /banks/bank_branch 分支行全称
	 * /banks/bank_no 开户银行行号
	 * /banks/cnaps_code 人行大小额行号
	 */
	public String queryBack(String bank_branch){
		try {
			if(StringUtils.isBlank(bank_branch))throw new MyException(bank_branch_error);
			return this.SUCCESS(null, CibUtil.queryBank(bank_branch));
		} catch (Exception e) {
			return this.FAILED(e);
		}
	}
	
	/**
	 * 企业授权委托书
	 * @param appid 商户授权编号
     * @param signature
     * @param message
	 */
	@RequestMapping("/authorization")
	public ModelAndView authorization(String appid,String signature,String message,HttpServletRequest request,Model model) {
		Map<String,Object> param = new HashMap<String,Object>();
		try {
			param = paramsService.getParams(appid, signature, message);
			if(param==null)throw new MyException("请求参数异常！");
			
			if(param.get("userId")==null || param.get("enterpriseId")==null)throw new MyException("请求参数不完整（ID）！");
			if(param.get("contactMobile")==null)throw new MyException("企业信息获取失败！");
			
			String contactMobile = param.get("contactMobile").toString();
			String userId = param.get("userId").toString();
			String enterpriseId = param.get("enterpriseId").toString();
			
			MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
			params.set("userId",userId);
			params.set("enterpriseId",enterpriseId);
			
			params.set("type",0);//角色：企业0、机构1
			params.set("mobile",contactMobile);//注册手机号（经办人作为唯一标识）
			String res = restTemplate.postForObject("http://utiexian-server/thirdkey/cib/corp/query",params, String.class);
			model.addAttribute("data",res);
			
			return new ModelAndView("yonyou/authorization");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
}