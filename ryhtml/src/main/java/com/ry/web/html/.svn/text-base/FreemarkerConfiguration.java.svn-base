 package com.ry.web.html;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;


import freemarker.template.Configuration;
public class FreemarkerConfiguration extends Configuration {
	private Logger logger = Logger.getLogger(FreemarkerConfiguration.class);

	/* (non-Javadoc)
	 * @see freemarker.template.Configuration#setDirectoryForTemplateLoading(java.io.File)
	 */
	@Override
	public void setDirectoryForTemplateLoading(File file) throws IOException {
		logger.debug("<<< freemarker DirectoryForTemplateLoading:" + file.getName());
		logger.debug("<<< freemarker real path DirectoryForTemplateLoading:" + file.getAbsolutePath());
		super.setDirectoryForTemplateLoading(file);
	}

}
