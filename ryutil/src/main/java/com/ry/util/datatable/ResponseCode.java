/**
 * 
 */
package com.ry.util.datatable;

/**
 * 名称: ResponseCode.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年2月29日 下午3:54:23<br>
 * @since  2016年2月29日
 * @author li.xiaofei 
 */
public interface ResponseCode {
	/**
	 * 成功
	 */
	 final String SUCCESS = "000000";
	 
	 
	 final String NO_RIGHT = "000001";
	 /**
	  * 请求参数非法
	  */
	 final String PARAM_ERROR= "100001";
	 /**
	  * 请求url method非法
	  */
	 final String URL_ERROR = "100002";
	 
	 /**
	  * 数据没有找到
	  */
	 final String DATA_NOT_FOUND = "200001";
	 
	 /**
	  * 数据异常
	  */
	 final String DATA_EXCEPTION = "200002";
     /**
      * 默认异常
      */
     final String DEFAULT_EXCEPTION = "500000";

}
