package org.snow.core.bean;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
/**
 * action配置
 * @author lyw
 *
 */
public class ActionConfig {
	private ActionConfig(){}
	
	private static ServletConfig servletConfig;
	private static ServletContext servletContext;
	/**
	 * action包
	 */
	private static String base;
	/**
	 * 插件class
	 */
	private static String actionHandles;
	/**
	 * 编码
	 */
	private static String charset;
	public static ServletConfig getServletConfig() {
		return servletConfig;
	}
	public static void setServletConfig(ServletConfig servletConfig1) {
		if(ActionConfig.servletConfig==null
				&&servletConfig1!=null){
			ActionConfig.servletConfig = servletConfig1;
			//base
			String base=ActionConfig.servletConfig.getInitParameter("base");
			ActionConfig.base=base;
			
			//actionHandles
			String actionHandles=ActionConfig.servletConfig.getInitParameter("actionHandles");
			ActionConfig.actionHandles=actionHandles;

			//charset
			String charset=ActionConfig.servletConfig.getInitParameter("charset");
			ActionConfig.charset=charset;
		}
	}
	public static ServletContext getServletContext() {
		return servletContext;
	}
	public static void setServletContext(ServletContext servletContext) {
		ActionConfig.servletContext = servletContext;
	}
	public static String getBase() {
		return base;
	}
	public static String getActionHandles() {
		return actionHandles;
	}
	public static String getCharset() {
		return charset;
	}
	
	
	
}
