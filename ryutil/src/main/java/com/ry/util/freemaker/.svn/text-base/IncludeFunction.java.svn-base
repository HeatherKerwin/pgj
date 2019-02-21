package com.ry.util.freemaker;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * freemarker包含页面，文件系统绝对路径，如：D:/logs/logs.log
 * @author  wangkai
 *
 */
@SuppressWarnings("deprecation")
@Component
public class IncludeFunction implements TemplateMethodModel {

	public Object exec(List arguments) throws TemplateModelException {
		String content="";
		if(arguments.size()==1){
			String filepath=arguments.get(0).toString();
			if(StringUtils.hasLength(filepath)){
				try {
					content = FileUtil.readAsString(new File(filepath));
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
		return content;
	}
	

}
