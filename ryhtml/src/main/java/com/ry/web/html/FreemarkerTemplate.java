package com.ry.web.html;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;

public class FreemarkerTemplate implements HtmlTemplate {
	private Logger logger = Logger.getLogger(FreemarkerTemplate.class);
	private Configuration configuration;
	
	private String charset="UTF-8";
	
	Map<String,Object> freemarkerVariables = new HashMap<String,Object>();
	
	
	public Map<String,Object> getFreemarkerVariables() {
		return freemarkerVariables;
	}
	public void setFreemarkerVariables(Map<String,Object> freemarkerVariables) {
		this.freemarkerVariables = freemarkerVariables;
	}
	
	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration() {
		try {
			configuration.setAllSharedVariables(new SimpleHash(this.freemarkerVariables,
					configuration.getObjectWrapper()));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return configuration;
		
	}

	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * @return the charset
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * @param charset the charset to set
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}


	/* (non-Javadoc)
	 * @see com.wokejia.web.html.HtmlTemplate#generateHtml(java.lang.String, java.util.Map, java.lang.String)
	 */
	public void generateHtml(String templateFileName, Map root,
			String outputFile) throws Exception {
		Template newsTemplate = getConfiguration().getTemplate(templateFileName);
		newsTemplate.setEncoding(getCharset());
		File file = new File(outputFile);
		logger.info(file.getAbsolutePath());
//		Writer out = new OutputStreamWriter(new FileOutputStream(outputFile));
		Writer out = new OutputStreamWriter(new FileOutputStream(file),getCharset());
		newsTemplate.process(root, out);
		out.flush();
		out.close();
	}
	public String analysis(String templateFileName, Map root)
			throws Exception {
		Template newsTemplate = getConfiguration().getTemplate(templateFileName);
		newsTemplate.setEncoding(getCharset());
		StringWriter stringWriter=new StringWriter();
		newsTemplate.process(root, stringWriter);
		return stringWriter.toString();
	}
	
	
	
	
}
