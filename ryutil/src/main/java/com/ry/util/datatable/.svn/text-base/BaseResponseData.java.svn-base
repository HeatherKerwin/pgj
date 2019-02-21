/**
 * 
 */
package com.ry.util.datatable;

import java.util.List;

import com.ry.util.datatable.ResponseCode;

/**
 * 名称: BaseResponseData.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年2月29日 下午3:58:10<br>
 * @since  2016年2月29日
 * @author li.xiaofei 
 */
public class BaseResponseData {
	
    private Long recordsTotal;
    private Long recordsFiltered;
	private String checkCode;
	private String message;
	
	public Long getRecordsTotal(){
		if(this.recordsTotal==null){
			return 0l;
		}
        return recordsTotal;
    }
    public void setRecordsTotal(Long recordsTotal){
        this.recordsTotal = recordsTotal;
    }
    public Long getRecordsFiltered(){
    	if(this.recordsFiltered==null){
    		return 0l;
    	}
        return recordsFiltered;
    }
    public void setRecordsFiltered(Long recordsFiltered)
    {
        this.recordsFiltered = recordsFiltered;
    }
    public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public BaseResponseData() {
		super();
	}
	public static BaseResponseData createFailureResp()
    {
        return createFailureResp(null);
    }
	 public static BaseResponseData createFailureResp(String msg)
	    {
		 BaseResponseData dto = new BaseResponseData(ResponseCode.DEFAULT_EXCEPTION,msg);
         return dto;
	    }
   public static BaseResponseData createSuccessResp(String msg)
    {
	   BaseResponseData dto = new BaseResponseData(ResponseCode.SUCCESS,msg);
        return dto;
    }

    public static BaseResponseData createSuccessResp()
    {
        return createSuccessResp(null);
    }
	public BaseResponseData(String checkCode, String message) {
		super();
		this.checkCode = checkCode;
		this.message = message;
	}
}
