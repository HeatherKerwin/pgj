package com.utiexian.third.controller.yonyou;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.utiexian.utils.BaseController;

import freemarker.template.Configuration;

/**
 * 多账户（兴业数金）
 * @author MH
 */
@Controller
@RequestMapping("/yonyou/cibbank")
public class CibBankController extends BaseController{
	
	@Autowired
    RestTemplate restTemplate;
	
	@Resource
	private Configuration configuration;
	
	/**
	 * 添加多账户（第一步）
	 * @author MH
	 * @param memberId 用户主键
	 * @param type 角色：企业0、机构1
	 * @param bankAcctBankNo 开户银行行号（见兴业数金附录）
	 * @param bankAcctCnapsCode 分支行的人行大小额行号
	 * @param bankAcctBankBranch 分支行完整名称
	 * @param bankAcctAcctNo 银行账号
	 * @param bankAcctAcctName 银行账户名
	 * @param model
	 */
	@PostMapping("/save")
    public String corp(Integer memberId,Integer type,String bankAcctBankNo,String bankAcctCnapsCode,String bankAcctBankBranch,String bankAcctAcctNo,String bankAcctAcctName,
    		Model model){
		try {
			if(memberId==null)return this.DATE_FAILED("获取用户信息失败！请重新登录", model);
			if(type==null)return this.DATE_FAILED("角色未知！请联系客服", model);
			if(StringUtils.isBlank(bankAcctBankNo))return this.DATE_FAILED("请输入开户银行行号！", model);
			if(StringUtils.isBlank(bankAcctCnapsCode))return this.DATE_FAILED("请输入分支行的人行大小额行号！", model);
			if(StringUtils.isBlank(bankAcctBankBranch))return this.DATE_FAILED("请输入分支行完整名称！", model);
			if(StringUtils.isBlank(bankAcctAcctNo))return this.DATE_FAILED("请输入银行账号！", model);
			if(StringUtils.isBlank(bankAcctAcctName))return this.DATE_FAILED("请输入银行账户名！", model);
			
			MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
			params.set("memberId",memberId);
			params.set("type",type);
			
			params.set("bankAcctBankNo",bankAcctBankNo);
			params.set("bankAcctCnapsCode",bankAcctCnapsCode);
			params.set("bankAcctBankBranch",bankAcctBankBranch);
			params.set("bankAcctAcctNo",bankAcctAcctNo);
			params.set("bankAcctAcctName",bankAcctAcctName);
			String res = restTemplate.postForObject("http://utiexian-server/cibbank/save",params, String.class);
			return this.DATE_SUCCESS(res, model);
		} catch (Exception e) {
			return this.DATE_FAILED("操作失败！", model);
		}
    }

	/**
	 * 小额鉴定（第二步）
	 * @author MH
	 * @param memberId 用户主键（后台不应用）
	 * @param cibBankId 多账户主键
	 * @param amt 金额
	 * @return 响应字段说明：
	 * /corp/corp_no 企业编号
	 * /corp/name 企业名称
	 * /corp/status 公司状态(0：待审核 1：审核失败 2：正常 3：锁定 4：无效 5：审核通过待鉴定 6：鉴定失败)
	 * /authenticate_order/left_count 剩余次数（总共6次）
	 */
	@PostMapping(value="/authenticate")
    public String authenticate(Integer memberId,Integer cibBankId,String amt,Model model){
		try {
			if(memberId==null)return this.DATE_FAILED("获取用户信息失败！请重新登录", model);
			if(cibBankId==null)return this.DATE_FAILED("数据异常！请联系客服", model);
			if(StringUtils.isBlank(amt))return this.DATE_FAILED("请输入金额！", model);
			MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
			
			params.set("cibBankId",cibBankId);
			params.set("memberId",memberId);
			params.set("amt",amt);
			String res = restTemplate.postForObject("http://utiexian-server/cibbank/authenticate",params, String.class);
			return this.DATE_SUCCESS(res, model);
		} catch (Exception e) {
			return this.DATE_FAILED("操作失败！", model);
		}
    }
	
	/**
	 * 根据主键获取开户信息（必须是用户自己的开户信息）
	 * @author MH
	 * @param memberId 用户主键
	 * @param id 多账户主键
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
			String res = restTemplate.postForObject("http://utiexian-server/cibbank/get",params, String.class);
			return this.DATE_SUCCESS(res, model);
		} catch (Exception e) {
			return this.DATE_FAILED("操作失败！", model);
		}
    }
	
	/**
	 * 根据主键获取开户信息（必须是用户自己的开户信息）
	 * @author MH
	 * @param memberId 用户主键
	 * @param type 角色：企业0、机构1
	 * @param status 虚拟户状态（1：正常 2：锁定 0：无效 5：待鉴定 6：鉴定失败）
	 * @param model
	 */
	@PostMapping("/list")
    public String list(Integer memberId,Integer type,Integer status,Model model){
		try {
			if(memberId==null)return this.DATE_FAILED("获取用户信息失败！请重新登录", model);
			if(type==null)return this.DATE_FAILED("角色未知！请联系客服", model);
			MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
			
			params.set("memberId",memberId);
			params.set("type",type);
			params.set("status",status);//如果需要过滤则传递（不传值，则获取所有多账户）
			String res = restTemplate.postForObject("http://utiexian-server/cibbank/list",params, String.class);
			return this.DATE_SUCCESS(res, model);
		} catch (Exception e) {
			return this.DATE_FAILED("操作失败！", model);
		}
    }
}