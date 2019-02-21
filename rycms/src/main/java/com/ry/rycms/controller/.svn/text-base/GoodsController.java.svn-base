package com.ry.rycms.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Goods;
import com.ry.core.form.integral.GoodsRequest;
import com.ry.core.service.GoodsService;
import com.ry.core.util.Constant;
import com.ry.core.util.FileUtil;
import com.ry.util.page.PageResults;

@Controller
@RequestMapping("/goods/")
public class GoodsController {
	
	@Resource
	private GoodsService goodsService;
	
	/**
	 * 页面初始化
	 */
	@RequestMapping("/list")
	public String list(PageResults<Goods> pr, Model model,GoodsRequest goodsReq ) {
		GoodsRequest req = new GoodsRequest();
		if (pr.getCurrentPage() == null) {
			req.setStart(1L);
		} else {
			req.setStart((pr.getCurrentPage().longValue() - 1) * req.getLength());
		}
		req.setGoodsNo(goodsReq.getGoodsNo());
		req.setGoodsName(goodsReq.getGoodsName());
		req.setState(goodsReq.getState());
		req.setMinPrice(goodsReq.getMinPrice());
		req.setMaxPrice(goodsReq.getMaxPrice());
		req.setMinStock(goodsReq.getMinStock());
		req.setMaxStock(goodsReq.getMaxStock());
		try {
			pr = goodsService.getPage(req);
			model.addAttribute("pr", pr);
			model.addAttribute("goodsNo", goodsReq.getGoodsNo());
			model.addAttribute("goodsName", goodsReq.getGoodsName());
			model.addAttribute("state", goodsReq.getState());
			model.addAttribute("minPrice", goodsReq.getMinPrice());
			model.addAttribute("maxPrice", goodsReq.getMaxPrice());
			model.addAttribute("minStock", goodsReq.getMinStock());
			model.addAttribute("maxStock", goodsReq.getMaxStock());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "integral/goodsList";
	}
	
	@RequestMapping("/toAddOrEdit")
	public String toAddOrEidt(Integer id,Model model){
		if(id != null){
			Goods goods = goodsService.getModelById(id);
			if(goods!=null){
				String showUrl = Constant.properties.getProperty("preimgUrl");
				goods.setBanner1(showUrl+goods.getBanner1());
				if(goods.getBanner2() != null){
					goods.setBanner2(showUrl+goods.getBanner2());
				}
				if(goods.getBanner3() != null){
					goods.setBanner3(showUrl+goods.getBanner3());
				}
				goods.setExplainBanner1(showUrl+goods.getExplainBanner1());
				if(goods.getExplainBanner2() != null){
					goods.setExplainBanner2(showUrl+goods.getExplainBanner2());
				}
				if(goods.getExplainBanner3() != null){
					goods.setExplainBanner3(showUrl+goods.getExplainBanner3());
				}
			}
			model.addAttribute("goods", goods);
		}
		return "integral/goodsAdd";
	}
	
	@RequestMapping("/save")
	public String save(Goods goods , Model model , HttpServletRequest request , HttpServletResponse response){
		try {	
			List<String> p = new ArrayList<String>();
			List<String> p2 = new ArrayList<String>();
			for (int i = 3; i > 0; i--) {
				String path = request.getParameter("banner"+i+"path");
				String explainBannerpath = request.getParameter("explainBanner"+i+"path");
				//将临时目录文件转移到目录文件中									
				//临时文件夹 的文件 转到 保存目录中
				String temp = Constant.properties.getProperty("uploadpath") + Constant.TEMP_PATH;//保存目录包名的绝对路径
				String temp2 = Constant.properties.getProperty("uploadpath") + Constant.TEMP_PATH;//保存目录包名的绝对路径
				String uploadPath = Constant.properties.getProperty("uploadpath") + Constant.UPLOAD_SRC;//保存目录包名的绝对路径			
				
				//检查目录
				File uploadDir = new File(uploadPath);
				if(!uploadDir.isDirectory()){			
					uploadDir.mkdirs();			
				}
				
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				String extpath = df.format(new Date());
				uploadPath += extpath;		
				temp += "image" + File.separator + extpath;
				temp2 += "image" + File.separator + extpath;
				if (path != null && !"".equals(path)) {
					temp += path.substring(path.lastIndexOf("/"), path.length());
					path = path.replaceFirst("temp", "upload");
					int idx = path.indexOf("upload");
					p.add(path.substring(idx));
				}	
				if(explainBannerpath != null && !"".equals(explainBannerpath)){
					temp2 += explainBannerpath.substring(explainBannerpath.lastIndexOf("/"), explainBannerpath.length());
					explainBannerpath = explainBannerpath.replaceFirst("temp", "upload");
					int idx1 = explainBannerpath.indexOf("upload");
					p2.add(explainBannerpath.substring(idx1));
				}
				FileUtil.moveFile(temp, uploadPath);
				FileUtil.moveFile(temp2, uploadPath);
			}
			goods.setBanner1(p.get(0));
			//判空
			if(p.size() >= 2){
				goods.setBanner2(p.get(1));
			}
			if(p.size() >= 3){
				goods.setBanner3(p.get(2));
			}
			goods.setExplainBanner1(p2.get(0));
			if(p2.size() >= 2){
				goods.setExplainBanner2(p2.get(1));
			}
			if(p2.size() >= 3){
				goods.setExplainBanner3(p2.get(2));
			}
			if(goods.getId()!=null){	//修改
				if(goods.getStock() == 0){//假如库存修改为0
					goods.setState(2);//状态改为已售罄
				}
				if(goods.getStock() != 0 && goods.getState() == 2){//库存不为0，状态为已售罄的话
					goods.setState(0);//状态改为待上架
				} 
				goodsService.updateGoods(goods);
			}else{	//新增
				//设置状态默认待上架
				goods.setState(0);
				//自动生成商品编号
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				StringBuffer no = new StringBuffer("SP");
				no.append(df.format(new Date()));
				no.append(new Random().nextInt(1000000)+100000);
				goods.setNo(no.toString());
				goodsService.addGoods(goods);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/goods/list";
	}
	
	@RequestMapping("/putaway")
	public String putaway(Integer id,Model model){
		goodsService.putaway(id);
		return "redirect:/goods/list";
	}
	
	/**
	 * 上热门
	 * @param id
	 * @author ZWD
	 * @return
	 */
	@RequestMapping("/gohot")
	public String gohot(Integer id){
		Goods goods = goodsService.getModelById(id);
		goods.setHotGoods(0);
		goodsService.updateGoods(goods);
		return "redirect:/goods/list";
	}
	
	/**
	 * 下热门
	 * @param id
	 * @author ZWD
	 * @return
	 */
	@RequestMapping("/downhot")
	public String downhot(Integer id){
		Goods goods = goodsService.getModelById(id);
		goods.setHotGoods(1);
		goodsService.updateGoods(goods);
		return "redirect:/goods/list";
	}
	
	/**
	 * 删除
	 * @param id
	 * @author ZWD
	 * @return
	 */
	@RequestMapping("/remove")
	public String remove(Integer id){
		Goods goods = goodsService.getModelById(id);
		goods.setState(3);
		goods.setHotGoods(1);
		goods.setSort(9999999);
		goodsService.updateGoods(goods);
		return "redirect:/goods/list";
	}
	
}
