package com.utiexian.third.controller.yonyou;

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

/**
 * 兴业数金-申请取消订单Controller类
 * @author WKX
 * @date 2017-11-24
 */
@Controller
@RequestMapping("/yonyou/cibcancel")
public class CibCancelController extends BaseController{
	
	@Autowired
    RestTemplate restTemplate;
	
    /**
     * 发起取消申请（含另一方取消）
     * @author MH
     * @param id 主键
     * @param memberId 用户主键（申请人）
     * @param orderId 订单主键
     * @param orderType 订单类型（DISCOUNTRECORD、DISCOUNTRECORDSP）
     * @param type 哪一方发起取消：企业0、机构1
     * @param cancel 拒绝理由（0票面信息有误、1只为熟悉流程和询问价格、2价格不合适、3已提前出票、4其他）
     * @param cancelCause 取消原因
     * @param model
     */
    @PostMapping("/save")
    public String save(Integer id,Integer memberId,Integer orderId,String orderType,Integer type,Integer cancel,String cancelCause,Model model){
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		String res = "";
		try {
			if(id!=null){//另一方确认
				params.set("id",id);
				res = restTemplate.postForObject("http://utiexian-server/cibcancel/update",params, String.class);
			}else{//发起取消
				if(memberId==null)return this.DATE_FAILED("获取用户信息失败！请重新登录", model);
				if(orderId==null)return this.DATE_FAILED("操作失败！请联系客服", model);
				if(StringUtils.isBlank(orderType))return this.DATE_FAILED("操作失败！请联系客服", model);
				if(type==null)return this.DATE_FAILED("操作失败！请联系客服", model);
				
				params.set("memberId",memberId);
				params.set("orderId",orderId);
				params.set("orderType",orderType);
				params.set("type",type);
				params.set("cancel",cancel);
				params.set("cancelCause",cancelCause);
				res = restTemplate.postForObject("http://utiexian-server/cibcancel/save",params, String.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return this.DATE_FAILED("操作失败！请联系客服", model);
		}
		return this.DATE_SUCCESS(res, model);
	}
}