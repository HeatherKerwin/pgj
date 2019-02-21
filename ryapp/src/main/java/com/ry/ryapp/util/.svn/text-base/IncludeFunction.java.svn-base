/**
* <p>Title: IncludeFunction.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: wokejia</p>
* @author ����
* @date 2014��5��21��
* @version 1.0
*/
package com.ry.ryapp.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**freemarke包含文件（绝对路径）
 * @author 
 *
 */
@Component
public class IncludeFunction implements TemplateMethodModel {
	

	/* (non-Javadoc)
	 * @see freemarker.template.TemplateMethodModel#exec(java.util.List)
	 */
	@Override
	public Object exec(List arg0) throws TemplateModelException {
		String content="";
		if(arg0.size()==1){
			//�ļ�·��
			String filepath=arg0.get(0).toString();
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
