package com.ry.rycms.task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ry.core.entity.Member;
import com.ry.core.service.MemberService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;

/**
 * 关于客户平均分配归属
 * @author MH
 */
public class CustomerServiceTask {
	
	@Resource
	MemberService memberService;

	public void execute(){
		String date = DateUtil.formart(new Date(), DateUtil.FORMART3);
		String front = Constant.properties.getProperty("FRONTCUSTOMSERVICE");
		String after = Constant.properties.getProperty("AFTERCUSTOMSERVICE");
		String [] frontArray = front.split(",");
		String [] afterArray = after.split(",");
		int b = 0;
		List<Map<String,Object>> listhezuo =  memberService.getNoDistributionMember(date,"sina");
		if(listhezuo != null &&listhezuo.size()>0){
			for (Map<String, Object> map : listhezuo) {
				Member member = memberService.getModelById(Integer.valueOf(map.get("id").toString()));
				member.setRecommendpeople(frontArray[0]);//分配给孙亚丽
				memberService.updateMember(member);
			}
		}
		List<Map<String,Object>> listFront =  memberService.getNoDistributionMember(date,null);
		if(listFront != null && listFront.size()>0){
			List<Map<String,Object>> listLastFront =  memberService.getLastDistributionMember("FRONT");
			if(listLastFront != null && listLastFront.size()>0){
				Map<String,Object> mapServicenumberId = listLastFront.get(0);
				for (int i = 0; i < frontArray.length; i++) {
					if(frontArray[i].equals(mapServicenumberId.get("recommendpeople").toString())){
						b = i+1;
						break ;
					}
				}
			}
			for (Map<String, Object> map : listFront) {
				if(b == frontArray.length)b = 0;
				Member member = memberService.getModelById(Integer.valueOf(map.get("id").toString()));
				member.setRecommendpeople(frontArray[b]);
				memberService.updateMember(member);
				b += 1;
			}
		}
		
		List<Map<String,Object>> listAfter =  memberService.getAuthenticationNoDistributionMember("AFTER");
		b = 0;
		if(listAfter != null && listAfter.size()>0){
			List<Map<String,Object>> listLastAfter =  memberService.getLastDistributionMember("AFTER");
			if(listLastAfter != null && listLastAfter.size()>0){
				Map<String,Object> mapServicenumberId = listLastAfter.get(0);
				for (int i = 0; i < afterArray.length; i++) {
					if(afterArray[i].equals(mapServicenumberId.get("recommendpeople").toString())){
						b = i+1;
						break ;
					}
				}
			}
			for (Map<String, Object> map : listAfter) {
				if(b == afterArray.length)b = 0;
				Member member = memberService.getModelById(Integer.valueOf(map.get("member_id").toString()));
				member.setRecommendpeople(afterArray[b]);
				memberService.updateMember(member);
				b += 1;
			}
		}
	}
}
