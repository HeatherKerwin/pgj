package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.RefundRecordDao;
import com.ry.core.entity.RefundRecord;
import com.ry.core.form.RefundRecordForm;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

@Repository
public class RefundRecordDaoImpl extends BaseDao<RefundRecord,Integer > implements RefundRecordDao{

	/* (non-Javadoc)
	 * @see com.ry.core.dao.RefundRecordDao#saveModel(com.ry.core.entity.RefundRecord)
	 */
	public void saveModel(RefundRecord refundRecord) {
		if(refundRecord!=null && refundRecord.getId()!=null){
			update(refundRecord);
		}else{
			save(refundRecord);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.RefundRecordDao#getById(java.lang.Integer)
	 */
	public RefundRecord getById(Integer id){
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.RefundRecordDao#getList(java.lang.Integer, java.lang.Integer, com.ry.core.form.RefundRecordForm)
	 */
	public PageResults<Map<String,Object>> getList(Integer pageIndex,Integer pageSize,RefundRecordForm from) {
		StringBuffer sql = new StringBuffer("FROM (");
		List<Object> param = new ArrayList<Object>();
		
		sql.append("SELECT res.*,o.member_id distMemberId FROM (");
		sql.append("SELECT rr.*,disc.ordernumber,disc.ordertime,disc.memberid discMemberId,disc.membername,disc.deposit discDeposit,disc.pay_way discPayway,disc.card discCard,dist.no,dist.create_time distTime,dist.org_id distOrgId,dist.deposit distDeposit,dist.pay_way distPayway,dist.card distCard FROM refund_record rr LEFT JOIN discountrecord disc ON rr.dcrd_id=disc.id LEFT JOIN distribute_order dist ON rr.dtbo_id=dist.id");
		sql.append(")res LEFT JOIN org o ON res.distOrgId=o.id");
		sql.append(") result LEFT JOIN member m ON result.member_id=m.id where 1=1");
		if(from!=null){
			if(from.getState()!=null){
				sql.append(" AND result.state=?");
				param.add(from.getState());
			}
			if(from.getType()!=null){
				sql.append(" AND result.type=?");
				param.add(from.getType());
			}
			if(from.getCreateTime()!=null){
				sql.append(" AND result.create_time<=?  and result.create_time >=?");
				param.add(DateUtil.addDays(from.getCreateTime(),1));
				param.add(from.getCreateTime());
			}
			if(StringUtils.hasText(from.getUserName())){//根据用户名查询
				sql.append(" AND m.username LIKE ?");
				param.add("%"+from.getUserName()+"%");
			}
		}
		
		String count = "select count(*) "+ sql.toString();
		return findPageMapByFetchedSql("SELECT result.*,m.mobile,m.username "+sql.toString(),count,pageIndex,pageSize, param.toArray());
	}
}