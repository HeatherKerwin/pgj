package com.ry.ryapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.City;
import com.ry.core.entity.Provice;
import com.ry.core.entity.Region;
import com.ry.core.service.CityService;
import com.ry.core.service.ProviceService;
import com.ry.core.service.RegionService;
import com.ry.util.JacksonUtil;


@Controller
public class ProvicecityController {
	
	@Resource
	ProviceService proviceService;
	
	@Resource
	CityService cityService;
	
	@Resource
	RegionService regionService;
	
	@RequestMapping("/app/json/test")
	public String test(Model model){
//		List<Region> p = regionService.getByType("P");//省
		List<Region> p = new ArrayList<Region>();
		Region pp = new Region();
		pp.setId(17);
		pp.setName("河南省");
		p.add(pp);
		List<Map<String,Object>> maps  = new ArrayList<Map<String,Object>>();
		for (Region region : p) {
			Map<String,Object> map = new HashMap<String,Object>(); 
			List<Region> c = regionService.getByParent(region.id);
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				for (Region region2 : c) {
					Map<String,Object> result = new HashMap<String,Object>();
					List<Map<String, Object>> dsub = regionService.getByName(region2.id);
					Object[] o  ;
					o = new Object[dsub.size()];
					for (int j = 0; j < dsub.size(); j++) {
						Object obj =  (Object) dsub.get(j).get("sub");
						o[j] = obj;
					}
					result.put("name",region2.getName() );
					result.put("sub", o);
					list.add(result);
				}
			map.put("name",region.getName());
			map.put("sub",new Object[]{list});
			maps.add(map);
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(maps));
		return "ajax";
	}
	
	@RequestMapping("/app/provicecity/")
	public void provicecity(String gongcuinumber, Integer memberid, HttpServletRequest request, HttpServletResponse response) throws IOException {		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			List<Provice> baseEntityList1 = proviceService.getList();
			List<City> baseEntityList2 = cityService.getList();
			Map<Integer,Provice> pmap = new HashMap<Integer,Provice>();
			for(Provice baseEntity : baseEntityList1){				
				pmap.put(baseEntity.getId(), baseEntity);
			}
			List<City> cityList = new ArrayList<City>();
			for(City baseEntity : baseEntityList2){
				City city = baseEntity;
				Provice provice = pmap.get(city.getPid());
				city.setPname(provice.getName());
				cityList.add(city);
			}
			map.put("response", "success");
			map.put("msg", new Object[]{baseEntityList1,cityList});
			out.print(JSONArray.fromObject(map));
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "获取失败");
			out.print(JSONArray.fromObject(map));
		}
	}	
	
	/**
	 * 获取省市区
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("/app/region")
	public String region(Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			List<Region> p = regionService.getByType("P");//省
			List<Region> c = regionService.getByType("C");//市
			List<Region> d = regionService.getByType("D");//区
			result.put("p", p);
			result.put("c", c);
			result.put("d", d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据首字母获取城市
	 * @author WKX
	 * @param model
	 * @since 2016年3月23日 下午4:48:08
	 */
	@RequestMapping("/app/region/city")
	public String regionCity(Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			List<Region> city = regionService.getByType("C");//市
			List<Region> a = new ArrayList<Region>();
			List<Region> b = new ArrayList<Region>();
			List<Region> c = new ArrayList<Region>();
			List<Region> d = new ArrayList<Region>();
			List<Region> e = new ArrayList<Region>();
			List<Region> f = new ArrayList<Region>();
			List<Region> g = new ArrayList<Region>();
			List<Region> h = new ArrayList<Region>();
			List<Region> i = new ArrayList<Region>();
			List<Region> j = new ArrayList<Region>();
			List<Region> k = new ArrayList<Region>();
			List<Region> l = new ArrayList<Region>();
			List<Region> m = new ArrayList<Region>();
			List<Region> n = new ArrayList<Region>();
			List<Region> o = new ArrayList<Region>();
			List<Region> p = new ArrayList<Region>();
			List<Region> q = new ArrayList<Region>();
			List<Region> r = new ArrayList<Region>();
			List<Region> s = new ArrayList<Region>();
			List<Region> t = new ArrayList<Region>();
			List<Region> u = new ArrayList<Region>();
			List<Region> v = new ArrayList<Region>();
			List<Region> w = new ArrayList<Region>();
			List<Region> x = new ArrayList<Region>();
			List<Region> y = new ArrayList<Region>();
			List<Region> z = new ArrayList<Region>();
			if(city!=null && city.size()>0){
				for(Region temp:city){
					if(temp!=null && StringUtils.isNotBlank(temp.getNameEn()) && temp.getNameEn().length()>0){
						if("A".equals(temp.getNameEn().substring(0, 1)))a.add(temp);
						else if("B".equals(temp.getNameEn().substring(0, 1)))b.add(temp);
						else if("C".equals(temp.getNameEn().substring(0, 1)))c.add(temp);
						else if("D".equals(temp.getNameEn().substring(0, 1)))d.add(temp);
						else if("E".equals(temp.getNameEn().substring(0, 1)))e.add(temp);
						else if("F".equals(temp.getNameEn().substring(0, 1)))f.add(temp);
						else if("G".equals(temp.getNameEn().substring(0, 1)))g.add(temp);
						else if("H".equals(temp.getNameEn().substring(0, 1)))h.add(temp);
						else if("I".equals(temp.getNameEn().substring(0, 1)))i.add(temp);
						else if("J".equals(temp.getNameEn().substring(0, 1)))j.add(temp);
						else if("K".equals(temp.getNameEn().substring(0, 1)))k.add(temp);
						else if("L".equals(temp.getNameEn().substring(0, 1)))l.add(temp);
						else if("M".equals(temp.getNameEn().substring(0, 1)))m.add(temp);
						else if("N".equals(temp.getNameEn().substring(0, 1)))n.add(temp);
						else if("O".equals(temp.getNameEn().substring(0, 1)))o.add(temp);
						else if("P".equals(temp.getNameEn().substring(0, 1)))p.add(temp);
						else if("Q".equals(temp.getNameEn().substring(0, 1)))q.add(temp);
						else if("R".equals(temp.getNameEn().substring(0, 1)))r.add(temp);
						else if("S".equals(temp.getNameEn().substring(0, 1)))s.add(temp);
						else if("T".equals(temp.getNameEn().substring(0, 1)))t.add(temp);
						else if("U".equals(temp.getNameEn().substring(0, 1)))u.add(temp);
						else if("V".equals(temp.getNameEn().substring(0, 1)))v.add(temp);
						else if("W".equals(temp.getNameEn().substring(0, 1)))w.add(temp);
						else if("X".equals(temp.getNameEn().substring(0, 1)))x.add(temp);
						else if("Y".equals(temp.getNameEn().substring(0, 1)))y.add(temp);
						else if("Z".equals(temp.getNameEn().substring(0, 1)))z.add(temp);
					}
				}
			}
			result.put("a", a);
			result.put("b", b);
			result.put("c", c);
			result.put("d", d);
			result.put("e", e);
			result.put("f", f);
			result.put("g", g);
			result.put("h", h);
			result.put("i", i);
			result.put("j", j);
			result.put("k", k);
			result.put("l", l);
			result.put("m", m);
			result.put("n", n);
			result.put("o", o);
			result.put("p", p);
			result.put("q", q);
			result.put("r", r);
			result.put("s", s);
			result.put("t", t);
			result.put("u", u);
			result.put("v", v);
			result.put("w", w);
			result.put("x", x);
			result.put("y", y);
			result.put("z", z);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 查询（关键字查询）汉字、拼音
	 * @author WKX
	 * @param model
	 * @param keywords
	 * @since 2016年4月6日 下午6:43:56
	 */
	@RequestMapping("/app/region/city/search")
	public String search(Model model,String keywords){
		Map<String,Object> result = new HashMap<String, Object>();
		keywords = keywords.replace(" ", "");//这里不是空格
		List<Region> city = regionService.getByNameAndType(keywords,"C");//市
		result.put("citys",city);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
}