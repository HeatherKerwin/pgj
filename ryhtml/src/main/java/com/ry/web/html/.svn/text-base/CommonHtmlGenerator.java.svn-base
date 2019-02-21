package com.ry.web.html;

import java.io.File;
import java.util.Map;

import org.springframework.beans.BeanUtils;

public class CommonHtmlGenerator {
	
	public static final String DEFAULT_SUFFIX = ".html";
	
	private String templateFileName;
	private String outputFileName;
	
	
	private String outputPath="";
	
	private String contextRealPath;
	
	
	public String getContextRealPath() {
		return contextRealPath;
	}

	public void setContextRealPath(String contextRealPath) {
		this.contextRealPath = contextRealPath;
	}

	private String suffix;
	
	private HtmlTemplate template;


	public void generate(Map root) throws Exception {
		template.generateHtml(templateFileName, root, outputFile());
	}
	public String analysis(Map root) throws Exception{
		return template.analysis(templateFileName, root);
	}
	
	private String outputFile(){
		File file = new File(getOutputRealPath(), getOutputPath());
	    if (!file.exists()) file.mkdirs();
	    return new File(file.getAbsolutePath(), this.outputFileName).getAbsolutePath();
	}

	/**
	 * @return the templateFileName
	 */
	public String getTemplateFileName() {
		return templateFileName;
	}

	/**
	 * @param templateFileName the templateFileName to set
	 */
	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}

	/**
	 * @return the outputFileName
	 */
	public String getOutputFileName() {
		return outputFileName;
	}

	/**
	 * @param outputFileName the outputFileName to set
	 */
	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}
	
	
	
	/**
	 * @return the outputPath
	 */
	public String getOutputPath() {
		return outputPath;
	}

	/**
	 * @param outputPath the outputPath to set
	 */
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public String getOutputRealPath(){
		return contextRealPath;
	}
	
	/**
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * @param suffix the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * @return the template
	 */
	public HtmlTemplate getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(HtmlTemplate template) {
		this.template = template;
	}

	public static CommonHtmlGenerator clone(CommonHtmlGenerator htmlGenerator){
		CommonHtmlGenerator commonHtmlGenerator = new CommonHtmlGenerator();
		BeanUtils.copyProperties(htmlGenerator, commonHtmlGenerator);
		return commonHtmlGenerator;
	}
}
