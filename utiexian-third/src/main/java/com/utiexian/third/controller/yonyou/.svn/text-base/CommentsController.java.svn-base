package com.utiexian.third.controller.yonyou;

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
 * 评价
 * @author MH
 */
@Controller
@RequestMapping("/yonyou/comments")
public class CommentsController extends BaseController{
	
	@Autowired
    RestTemplate restTemplate;

    /**
     * 获取机构、企业评价
     * @author MH
     * @param memberId 用户Id
     * @param role 评价的角色（0企业，1机构）
     * 	(获取企业的评分（传1，机构评论的评分），获取机构的评分（传0，企业评论的评分）)
     *  (数据：0都是评论进行的评分，1都是机构评论的评分)
     */
    @PostMapping("/get")
    public String get(Model model,Integer memberId,Integer role){
    	MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("memberId",memberId);
		params.set("role",role);
		String res = restTemplate.postForObject("http://utiexian-server/comments/get",params, String.class);
		return this.DATE_SUCCESS(res, model);
    }
    
    /**
     * 保存评价
     * @author MH
     * @param model
     * @param dcrdId 贴现订单Id
     * @param dtboId 接单订单Id
     * @param role 角色（0：企业，1：机构，谁评论传谁）
     * @param type （0银1商2批）
     * @param memberId 用户Id（表里operatorId字段）
     * @param content 评价内容
     * @param price	价格（票据）真实（评分）
     * @param service 服务态度（评分）
     * @param speed 确认速度（评分）
     * @return
     */
    @PostMapping("/save")
    public String save(Model model,Integer dcrdId,Integer dtboId,Integer role,Integer type,Integer memberId,
    		String content,Float price, Float service,Float speed){
    	MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("dcrdId",dcrdId);
		params.set("dtboId",dtboId);
		params.set("role",role);
		params.set("type",type);
		params.set("operatorId",memberId);
		params.set("content",content);
		params.set("price",price);
		params.set("service",service);
		params.set("speed",speed);
		String res = restTemplate.postForObject("http://utiexian-server/comments/save",params, String.class);
		return this.DATE_SUCCESS(res, model);
    }
    
    /**
     * 获取评论内容和订单信息
     * @author MH
     * @param pageIndex 页
     * @param pageSize 数量
     * @param memberId 用户Id
     * @param role 评价人的角色（0企业1机构）
     * @return
     * member_id: 80491,评价用户Id
     * membermobile: "18964721269",手机号
     * createtime: "2016-11-17 16:57:15", 评论时间
     * role: 0, 角色
     * t: 2, 类型
     * org_id: 1, 机构Id
     * c_id: 39, 评价Id
     * id: 13, 订单Id
     * place: "上海市", 交易城市
     * allmoney: 1235, 金额
     * content: "这家机构可以啊" 内容
     */
    @PostMapping("/get/discinfo")
    public String getCommentsAndDiscInfo(Integer pageIndex,Integer pageSize,Integer memberId, Integer role,Model model){
    	MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("memberId",memberId);
		params.set("role",role);
		params.set("pageIndex",pageIndex);
		params.set("pageSize",pageSize);
		String res = restTemplate.postForObject("http://utiexian-server/comments/get/discinfo",params, String.class);
		return this.DATE_SUCCESS(res, model);
    }
}
