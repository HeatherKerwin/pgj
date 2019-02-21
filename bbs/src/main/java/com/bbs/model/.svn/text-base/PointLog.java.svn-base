package com.bbs.model;

import java.io.Serializable;

import com.blade.jdbc.annotation.Table;

/**
 * 获取积分行为记录
 */
@Table(value = "t_pointlog", PK = "id")
public class PointLog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	//用户主键（得分者）
	private Long uid;
	
	//用户主键（触发者）
	private Long hid;
	
	//分数
	private Integer score;
	
	//事件
	private Fun fun;
	
	//外键（帖子主键）
	private Long fk_id;
	
	//时间
	private Long create_time;
	
	public PointLog(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	/**
	 * 用户主键（触发者）
	 * @author WKX
	 * @since 2016年10月31日 下午4:41:16
	 */
	public Long getHid() {
		return hid;
	}

	public void setHid(Long hid) {
		this.hid = hid;
	}

	/**
	 * 操作
	 * @author WKX
	 * @since 2016年10月31日 下午4:41:35
	 */
	public Fun getFun() {
		return fun;
	}

	public void setFun(Fun fun) {
		this.fun = fun;
	}

	/**
	 * 外键
	 * @author WKX
	 * @since 2016年10月31日 下午4:41:43
	 */
	public Long getFk_id() {
		return fk_id;
	}

	public void setFk_id(Long fk_id) {
		this.fk_id = fk_id;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	/**
	 * 执行的操作
	 * @author WKX
	 */
	public static enum Fun{
		QIANDAO(10,"签到"),WANSHAN(20,"完善资料（仅首次有效）"),FATIE(100,"发帖"),DIANZAN(10,"被点赞"),HUIFU(20,"被回复"),JINGHUA(50,"精华帖");
		private Integer sc;
		private String name;
		Fun(Integer sc,String name){
			this.sc = sc;
			this.name = name;
		}
		public Integer getSc() {
			return sc;
		}
		public void setSc(Integer sc) {
			this.sc = sc;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	
	/**
	 * @author WKX
	 * 扮演的角色
	 */
	public static enum Cosname{
		XIAOSHENG(0,1000,"票小生",1),XIUCAI(1001,2000,"票秀才",2),JUREN(2001,4500,"票举人",3),TANHUA(4501,8000,"票探花",4),BANGYAN(8001,20000,"票榜眼",5),ZHUANGYUAN(20001,null,"票状元",6);
		private Integer min;
		private Integer max;
		private String name;
		private Integer index;
		Cosname(Integer min,Integer max,String name,Integer index){
			this.min = min;
			this.max = max;
			this.name = name;
			this.index = index;
		}
		public Integer getMin() {
			return min;
		}
		public void setMin(Integer min) {
			this.min = min;
		}
		public Integer getMax() {
			return max;
		}
		public void setMax(Integer max) {
			this.max = max;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getIndex() {
			return index;
		}
		public void setIndex(Integer index) {
			this.index = index;
		}
		/**
		 * 获取扮演的角色
		 * @author WKX
		 * @param index
		 * @since 2016年11月4日 下午2:29:03
		 */
		public static Cosname valueOf(Integer index) {
			switch(index){
				case 1:return XIAOSHENG;
				case 2:return XIUCAI;
				case 3:return JUREN;
				case 4:return TANHUA;
				case 5:return BANGYAN;
				case 6:return ZHUANGYUAN;
			}
			return null;
		}
		
		/**
		 * 当前积分获取角色
		 * @author WKX
		 * @param value
		 * @since 2016年11月4日 下午2:29:20
		 */
		public static Cosname getCurrent(Long value) {
			if(value==null)return XIAOSHENG;
			if(value>=XIAOSHENG.min && value<=XIAOSHENG.max){
				return XIAOSHENG;
			}else if(value>=XIUCAI.min && value<=XIUCAI.max){
				return XIUCAI;
			}else if(value>=JUREN.min && value<=JUREN.max){
				return JUREN;
			}else if(value>=TANHUA.min && value<=TANHUA.max){
				return TANHUA;
			}else if(value>=BANGYAN.min && value<=BANGYAN.max){
				return BANGYAN;
			}else if(value>=ZHUANGYUAN.min){
				return ZHUANGYUAN;
			}
			return null;
		}
		public static Cosname getNext(Cosname cos) {
			if(XIAOSHENG==cos){
				return XIUCAI;
			}else if(XIUCAI==cos){
				return JUREN;
			}else if(JUREN==cos){
				return TANHUA;
			}else if(TANHUA==cos){
				return BANGYAN;
			}else if(BANGYAN==cos){
				return ZHUANGYUAN;
			}
			return cos;
		}
	}
	
	public static void main(String[] args) {
		System.err.println(Cosname.getCurrent(null));
		System.err.println(Fun.DIANZAN.toString());
		System.err.println(Cosname.getNext(Cosname.BANGYAN));
	}
}