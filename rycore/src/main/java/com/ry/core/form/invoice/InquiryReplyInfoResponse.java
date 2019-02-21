/**
 * 
 */
package com.ry.core.form.invoice;

import java.util.List;

import com.ry.core.entity.InquiryReply;
import com.ry.core.entity.Invoice;
import com.ry.util.datatable.BaseResponseData;

/**
 * 名称: InquiryReplyInfpResponse.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月5日 下午5:17:35<br>
 * @since  2016年3月5日
 * @author li.xiaofei 
 */
public class InquiryReplyInfoResponse extends BaseResponseData{
	
	private  InquiryReply reply;
	private  List<Invoice> invoice;

	/**
	 * @return the reply
	 */
	public InquiryReply getReply() {
		return reply;
	}

	/**
	 * @param reply the reply to set
	 */
	public void setReply(InquiryReply reply) {
		this.reply = reply;
	}

	/**
	 * @return the invoice
	 */
	public List<Invoice> getInvoice() {
		return invoice;
	}

	/**
	 * @param invoice the invoice to set
	 */
	public void setInvoice(List<Invoice> invoice) {
		this.invoice = invoice;
	}

}
