/**
 * 
 */
package com.ry.util.datatable;

/**
 * 名称: BaseRequestData.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年2月29日 下午3:58:52<br>
 * @since  2016年2月29日
 * @author li.xiaofei 
 */
public class BasePageRequestData {
    private String sort;
    private String order;
    private Long start;
    private Long length = 10L;
    
	public Long getLength()
    {
        return length;
    }
    public void setLength(Long length)
    {
        this.length = length;
    }
    public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	} 
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	public Long currentPage(){
	    if(this.start == null)return 1L;
	    return this.start/this.length +1;
	}
}
