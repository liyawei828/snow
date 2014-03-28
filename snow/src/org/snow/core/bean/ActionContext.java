package org.snow.core.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.snow.core.exception.SnowException;
import org.snow.core.handle.IParameterField;
/**
 * 上下文
 * @author lyw
 *
 */
public class ActionContext {
	public ActionContext(HttpServletRequest request, HttpServletResponse response){
		if(request==null
				||response==null){
			throw new SnowException("servletContext||request||response not null");
		}
		this.request=request;
		this.response=response;
	}
	private HttpServletRequest request;
	private HttpServletResponse response;
	/**
	 * action class path
	 */
	private String actionClassPath;
	/**
	 * action class
	 */
	private Class<?> actionClass;
	/**
	 * action对象
	 */
	private Object actionObj;
	/**
	 * 返回值对象
	 */
	private Object returnObj;
	/**
	 * 参数字段映射接口
	 */
	private IParameterField parameterField;
	/**
	 * 是否上传文件
	 */
	private boolean isMultipart;
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public String getActionClassPath() {
		return actionClassPath;
	}
	public void setActionClassPath(String actionClassPath) {
		this.actionClassPath = actionClassPath;
	}
	public Object getActionObj() {
		return actionObj;
	}
	public void setActionObj(Object actionObj) {
		this.actionObj = actionObj;
	}
	public Object getReturnObj() {
		return returnObj;
	}
	public void setReturnObj(Object returnObj) {
		this.returnObj = returnObj;
	}
	public Class<?> getActionClass() {
		return actionClass;
	}
	public void setActionClass(Class<?> actionClass) {
		this.actionClass = actionClass;
	}
	public IParameterField getParameterField() {
		return parameterField;
	}
	public void setParameterField(IParameterField parameterField) {
		this.parameterField = parameterField;
	}
	public boolean isMultipart() {
		return isMultipart;
	}
	public void setMultipart(boolean isMultipart) {
		this.isMultipart = isMultipart;
	}
	

	
	
}
