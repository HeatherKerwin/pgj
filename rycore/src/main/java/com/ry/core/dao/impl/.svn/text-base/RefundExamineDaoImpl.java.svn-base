package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.RefundExamineDao;
import com.ry.core.entity.RefundExamine;
import com.ry.core.form.RefundExamineForm;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

@Repository
public class RefundExamineDaoImpl extends BaseDao<RefundExamine,Integer > implements RefundExamineDao{


	/* (non-Javadoc)
	 * @see com.ry.core.dao.RefundExamineDao#getExamineInfoById(java.lang.Integer, java.lang.Integer, com.ry.core.form.RefundExamineForm)
	 */
	@Override
	public PageResults<Map<String, Object>> getExamineInfoById(Integer pageIndex,Integer pageSize,RefundExamineForm from) {
		StringBuffer sql = new StringBuffer(" FROM refund_examine re, discountrecord disc , distribute_order dist  WHERE re.dcrd_id = disc.id AND re.dtbo_id = dist.id and 1=1 ");
		List<Object> params = new ArrayList<Object>();
		String count;
		if(from.getMembername()!=null || from.getPayWay()!=null || from.getOperatorTime()!=null){
			if(from.getMembername()!=null && from.getMembername() != ""){
				sql.append(" and disc.membername = ? ");
				params.add(from.getMembername());
			}
			if(from.getPayWay()!=null){
				sql.append(" and disc.pay_way = ? ");
				params.add(from.getPayWay());
			}
			if(from.getCard() != null && from.getCard() != ""){
				sql.append(" and disc.card = ? ");
				params.add(from.getCard());
			}
			if(from.getOperatorTime()!=null){
				sql.append(" and re.operator_time <= ?  and re.operator_time >=?");
				params.add(DateUtil.addDays(from.getOperatorTime(),1));
				params.add(from.getOperatorTime());
			}
			count ="SELECT COUNT(*) " +sql.toString();
			sql.append(" and re.id NOT IN (SELECT id FROM refund_examine WHERE cancel_result=1 OR cancel_result=2)");
		}else{
			sql.append(" and re.id NOT IN (SELECT id FROM refund_examine WHERE cancel_result=1 OR cancel_result=2)");
			count ="SELECT COUNT(*)"+sql.toString();
		}
		return findPageMapByFetchedSql("SELECT re.id as reid, disc.ordernumber , dist.`no` , disc.ordertime ,dist.create_time ,disc.membername,disc.card AS disccard,dist.card AS distcard,disc.pay_way AS discpay,"+
											"dist.pay_way AS distpay ,disc.`deposit` AS discdeposit , dist.deposit AS distdeposit ,re.cancel_role ,re.cause AS recaues ,"+
											"re.operator_time "+sql.toString(), count,pageIndex,pageSize,params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.RefundExamineDao#updateExamine(com.ry.core.entity.RefundExamine)
	 */
	public void updateExamine(RefundExamine examine){
		String sql="UPDATE refund_examine SET cancel_result=? WHERE id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(examine.getCancelResult());
		params.add(examine.getId());
		executeSql(sql,params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.RefundExamineDao#getById(java.lang.Integer)
	 */
	public RefundExamine getById(Integer id){
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.RefundExamineDao#saveModel(com.ry.core.entity.RefundExamine)
	 */
	public void saveModel(RefundExamine refundExamine) {
		save(refundExamine);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.RefundExamineDao#updateModel(com.ry.core.entity.RefundExamine)
	 */
	public void updateModel(RefundExamine refundExamine) {
		update(refundExamine);
	}
}