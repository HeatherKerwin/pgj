package com.utiexian.third.controller;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.utiexian.third.utils.MyProperties;
import com.utiexian.utils.BaseController;
import com.utiexian.utils.utils.FileUtil;
import com.utiexian.utils.utils.OCRUtil;

/**
 * 文件上传
 * @author MH
 */
@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController{
	
	@Autowired
    RestTemplate restTemplate;
	
	@Autowired
	private MyProperties myProperties;

	/**
	 * 图片上传（BASE64）
	 * @author MH
	 * @param base64Image BASE64格式图片
	 * @param ocrGenre 图片类型 DRAFT票面  BIZLICENCE营业执照 IDCARD身份证
	 * @param suffix (jpge\jpg\png)
	 * @param response
	 */
	@PostMapping("/image")
    public String image(String base64Image,String ocrGenre,String suffix,Model model){
		try {
			if(StringUtils.isBlank(base64Image))return this.DATE_FAILED("上传失败，请重新选择图片！", model);
			String path = myProperties.getUploadPath();
			Map<String, Object> result = FileUtil.image(base64Image, suffix, path);
			String filePath = result.get("base64Image").toString();
			if(StringUtils.isNotBlank(ocrGenre)){
				Map<String, Object> res = OCRUtil.runOCR(path+filePath, ocrGenre);
				result.put("ocrInfo", res.get("json_final"));
			}
			
			return this.DATE_SUCCESS(this.SUCCESS("上传成功！", result), model);
		} catch (Exception e) {
			return this.DATE_FAILED("上传失败，请重新选择图片！", model);
		}
    }
}