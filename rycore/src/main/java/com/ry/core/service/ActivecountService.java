/**
* @Title: ServicememberService.java
* @Package com.ry.core.service
* @Description: TODO
* @author Ry-wk
* @date 2015年10月16日
* @version V1.0
*/
package com.ry.core.service;


import java.util.List;

import com.ry.core.entity.Activecount;
import com.ry.core.form.ActivecountForm;

/**
 * @ClassName: ActivecountService
 * @Description: TODO
 * @author Ry-wk
 * @date 2015年10月16日
 *
 */
public interface ActivecountService {
	
	public Long getAllCount();
	
	/**
	 * 
	* @Title: getCountByRpAndTime
	* @Description: 按时间段和推荐人查询日活
	* @param @param rp 推按人
	* @param @param beginTime
	* @param @param endTime
	* @param @return    参数
	* @return Long    返回类型
	* @throws
	 */
	public Long getCountByRpAndTime(String rp, Long beginTime, Long endTime);
	
	
	public void addActivecount(Activecount activecount);
	
	public Long getActivecount(String begintimed, String endtimed, Integer memberid);
	
	public Long countActive(Long begintimed, Long endtimed, Integer memberid);
	
	public List<Activecount> getList(ActivecountForm activecountForm);
}
