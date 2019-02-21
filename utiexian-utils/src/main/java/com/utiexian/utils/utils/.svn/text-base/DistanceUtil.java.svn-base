package com.utiexian.utils.utils;

/**
 * 经纬度坐标距离计算
 */
public class DistanceUtil {
	
	/**
	 * 计算两个坐标之间的距离
	 * @param lat_a A点纬度
	 * @param lng_a A点经度
	 * @param lat_b B点纬度
	 * @param lng_b B点经度
	 * @return Double A，B之间的距离
	 */
	public static Double getDistance(double lat_a, double lng_a, double lat_b, double lng_b) {
		double pk = 180 / 3.1415926;
		double a1 = lat_a / pk;
		double a2 = lng_a / pk;
		double b1 = lat_b / pk;
		double b2 = lng_b / pk;
		double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
		double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
		double t3 = Math.sin(a1) * Math.sin(b1);
		double tt = Math.acos(t1 + t2 + t3);
		return 6366000 * tt / 1000;
	}
}