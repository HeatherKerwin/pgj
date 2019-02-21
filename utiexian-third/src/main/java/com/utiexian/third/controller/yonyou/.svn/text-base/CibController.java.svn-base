package com.utiexian.third.controller.yonyou;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.utiexian.utils.BaseController;
import com.utiexian.utils.cibfintech.CibUtil;

@Controller
@RequestMapping(value = "/yonyou/cib")
public class CibController extends BaseController{
	
	@Autowired
    RestTemplate restTemplate;

	/**
	 * 分支行名称模糊查询
	 * @author WKX
	 * @param memberId 用户主键
	 * @param bank_branch 分支行名称（分支行名称、分支行号[精准查询]）
	 * @return 响应字段说明：
	 * /banks 大小额系统信息（返回结果列表）
	 * /banks/bank_branch 分支行全称
	 * /banks/bank_no 开户银行行号
	 * /banks/cnaps_code 人行大小额行号
	 */
	@PostMapping(value="/query/back")
	public String queryBack(Integer memberId,String bank_branch,Model model){
		try {
			if(memberId==null)return this.DATE_FAILED("获取登录信息失败！请重新登录", model);
			if(StringUtils.isBlank(bank_branch))return this.DATE_FAILED("请输入分支行名称！", model);
			String res = this.SUCCESS(null, CibUtil.queryBank(bank_branch));
			return this.DATE_SUCCESS(res, model);
		} catch (Exception e) {
			return this.DATE_FAILED("操作失败！", model);
		}
	}
	
	/**
	 * 申请开户
	 * @author WKX
	 * @param memberId 用户主键
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
	 * @param model
	 */
	@PostMapping("/save")
    public String corp(Integer memberId,Integer id,Integer type,String email,String name,String address,String bankAcctBankNo,String bankAcctCnapsCode,String bankAcctBankBranch,String bankAcctAcctNo,String bankAcctAcctName,
    		String orgCreditCodeCertCode,String orgCreditCodeCertName,String orgCreditCodeCertAddr,String orgCreditCodeCertExpiredDate,
    		String bizLicenceRegisteredNo,String bizLicenceName,String bizLicenceType,String bizLicenceAddr,String bizLicenceLegalName,String bizLicenceFoundedDate,
    		String taxCertGovTaxNo,String taxCertLocalTaxNo,String taxCertName,String legalCertNo,String agentCertNo,String treasurerName,String treasurerCertNo,
    		String orgCodeCertCode,String orgCodeCertName,String orgCodeCertType,String acctPermitNo,String acctPermitPermitNo,String contactName,String contactMobile,
    		String imgPathA1,String imgPathA2,String imgPath20,Model model){
		try {
			if(memberId==null)return this.DATE_FAILED("获取登录信息失败！", model);
			if(type==null)return this.DATE_FAILED("角色未知！请联系客服", model);
			if(StringUtils.isBlank(email))return this.DATE_FAILED("请输入邮箱地址！", model);
			if(StringUtils.isBlank(name))return this.DATE_FAILED("请输入企业名称！", model);
			if(StringUtils.isBlank(address))return this.DATE_FAILED("请输入联系地址！", model);
			if(StringUtils.isBlank(bankAcctBankNo))return this.DATE_FAILED("请输入开户银行行号！", model);
			if(StringUtils.isBlank(bankAcctCnapsCode))return this.DATE_FAILED("请输入分支行的人行大小额行号！", model);
			if(StringUtils.isBlank(bankAcctBankBranch))return this.DATE_FAILED("请输入分支行完整名称！", model);
			if(StringUtils.isBlank(bankAcctAcctNo))return this.DATE_FAILED("请输入银行账号！", model);
			if(StringUtils.isBlank(bankAcctAcctName))return this.DATE_FAILED("请输入银行账户名！", model);
			
			if(StringUtils.isBlank(bizLicenceRegisteredNo))return this.DATE_FAILED("请输入注册号（企业法人营业执照）！", model);
			if(StringUtils.isBlank(bizLicenceName))return this.DATE_FAILED("请输入名称（企业法人营业执照）！", model);
			if(StringUtils.isBlank(bizLicenceType))return this.DATE_FAILED("请输入公司类型（企业法人营业执照）！", model);
			if(StringUtils.isBlank(bizLicenceAddr))return this.DATE_FAILED("请输入住所（企业法人营业执照）！", model);
			if(StringUtils.isBlank(bizLicenceLegalName))return this.DATE_FAILED("请输入法定代表人姓名（企业法人营业执照）！", model);
			if(StringUtils.isBlank(bizLicenceFoundedDate))return this.DATE_FAILED("请输入成立日期（企业法人营业执照）！", model);
			
			if(StringUtils.isBlank(legalCertNo))return this.DATE_FAILED("请输入法人代表身份证号（法人代表）！", model);
			if(StringUtils.isBlank(agentCertNo))return this.DATE_FAILED("请输入经办人身份证号（经办人）！", model);
			
			if(StringUtils.isBlank(treasurerName))return this.DATE_FAILED("请输入财务人员姓名（财务）！", model);
			if(StringUtils.isBlank(treasurerCertNo))return this.DATE_FAILED("请输入财务人员身份证号（财务）！", model);
			
			if(StringUtils.isBlank(orgCodeCertCode))return this.DATE_FAILED("请输入代码（组织机构代码证）！", model);
			if(StringUtils.isBlank(orgCodeCertName))return this.DATE_FAILED("请输入机构名称（组织机构代码证）！", model);
			if(StringUtils.isBlank(orgCodeCertType))return this.DATE_FAILED("请输入机构类型（组织机构代码证）！", model);
			
			if(StringUtils.isBlank(contactName))return this.DATE_FAILED("请输入联系人姓名（企业联系人）！", model);
			if(StringUtils.isBlank(contactMobile))return this.DATE_FAILED("请输入联系人手机（企业联系人）！", model);
			
			MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
			params.set("memberId",memberId);
			params.set("id",id);
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
			
			params.set("imgPathA1",imgPathA1);
			params.set("imgPathA2",imgPathA2);
			params.set("imgPath20",imgPath20);
			String res = restTemplate.postForObject("http://utiexian-server/cib/save",params, String.class);
			return this.DATE_SUCCESS(res, model);
		} catch (Exception e) {
			return this.DATE_FAILED("操作失败！", model);
		}
    }
	
	/**
	 * 根据主键获取开户信息（必须是用户自己的开户信息）
	 * @author WKX
	 * @param memberId 用户主键
	 * @param id 开户主键
	 * @param model
	 */
	@PostMapping("/get")
    public String get(Integer memberId,Integer id,Model model){
		try {
			if(memberId==null)return this.DATE_FAILED("获取用户信息失败！请重新登录", model);
			if(id==null)return this.DATE_FAILED("操作失败！请联系客服", model);
			MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
			
			params.set("memberId",memberId);
			params.set("id",id);
			String res = restTemplate.postForObject("http://utiexian-server/cib/get",params, String.class);
			return this.DATE_SUCCESS(res, model);
		} catch (Exception e) {
			return this.DATE_FAILED("操作失败！", model);
		}
    }
	
	/**
	 * 查询自己的开户信息
	 * @author WKX
	 * @param memberId 用户主键
	 * @param type 角色：企业0、机构1（非必填）
	 * @return 响应字段说明：
	 * /corp/corp_no 企业编号
	 * /corp/name 企业名称
	 * /corp/status 公司状态
	 * /corp/v_acct/acct_no 虚拟账户
	 * /corp/v_acct/acct_name 虚拟账户名称
	 * /corp/v_acct/balance 可用余额
	 * /corp/v_acct/frozen_balance 冻结余额
	 * /corp/v_acct/trading_amt 交易中金额
	 * /orgInfo 认证信息
	 * /login_url 兴业数金登录地址
	 */
	@PostMapping(value="/corp/query")
    public String query(Integer memberId,Integer type,Model model){
		try {
			if(memberId==null)return this.DATE_FAILED("获取登录信息失败！", model);
			MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
			
			params.set("memberId",memberId);
			params.set("type",type);
			String res = restTemplate.postForObject("http://utiexian-server/cib/corp/query",params, String.class);
			return this.DATE_SUCCESS(res, model);
		} catch (Exception e) {
			return this.DATE_FAILED("操作失败！", model);
		}
    }
	
	/**
	 * 根据订单的Id，获取交易双方的信息
	 * @author MH
	 * @param distId 接单订单主键
	 * @param orderType 订单类型
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value="/contract/info/by/id")
	public String getContractDyadInfoByDiscountId(Integer distId,String orderType,HttpServletRequest request,Model model){
		try {
			if(distId == null)return this.DATE_FAILED("订单的主键不能为空!", model);
			if(StringUtils.isBlank(orderType))return this.DATE_FAILED("订单类型不能为空！", model);
			
			Map<String, Object> member = (Map<String, Object>) request.getSession().getAttribute("member");
			if(member == null)return this.DATE_FAILED("获取用户信息失败！请重新登录", model);
			
			MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
			
			params.set("distId",distId);
			params.set("orderType",orderType);
			String res = restTemplate.postForObject("http://utiexian-server/cib/contract/info/by/id",params, String.class);
			
			Map<String, Object> map = JSON.parseObject(res);
			Map<Object,Object> maps =  (Map<Object,Object>)map.get("data");
			if(maps!=null){
				if(maps.get("memberId") == null || !member.get("id").toString().equals(maps.get("memberId").toString()))return this.DATE_FAILED("获取用户信息失败！请重新登录", model);
			}
			
			return this.DATE_SUCCESS(res, model);
		} catch (Exception e) {
			return this.DATE_FAILED("操作失败！", model);
		}
	}
	
	/**
	 * 合同下载到邮箱
	 * @author WKX
	 * @param no 编号（企业编号corpNo、贴现订单编号ordernumber、no）
	 * @param email 邮箱地址
	 * @param isCib 是否是开户合同（订单类型的不需要填写）
	 * @param model
	 */
	@PostMapping(value="/econtract/email")
	public String downLoadContractToEmail(Integer memberId,String no,String email,boolean isCib,Model model){
		String msg = "该订单无合同或合同正在生成中！";
		if(isCib)msg = "暂无签署电子合同！";
		try {
			if(memberId==null)return this.DATE_FAILED(msg, model);
			if(StringUtils.isBlank(no))return this.DATE_FAILED(msg, model);
			if(StringUtils.isBlank(email))return this.DATE_FAILED("请填写邮箱地址！", model);
				
			MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
			
			params.set("memberId",memberId);
			params.set("no",no);
			params.set("email",email);
			params.set("isCib",isCib);
			String res = restTemplate.postForObject("http://utiexian-server/cib/econtract/email",params, String.class);
			return this.DATE_SUCCESS(res, model);
		} catch (Exception e) {
			return this.DATE_FAILED(msg, model);
		}
    }
	
	
}
