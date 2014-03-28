package org.snow.core.handle.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.snow.core.annotation.DefaultMethod;
import org.snow.core.bean.ActionConfig;
import org.snow.core.bean.ActionContext;
import org.snow.core.handle.IInvoke;
import org.snow.core.util.Common;

public class Invoke implements IInvoke {

	public Object invokeMethod(ActionContext context) throws Exception {
		//参数名称列表
		Set<String> nameSet=context.getParameterField().getParameterNames(context);
		/*
		 * 执行指定方法
		 */
		Method[] ms=context.getActionClass().getDeclaredMethods();
		Method defaultMethod=null;
		for (Method method : ms) {
			//查找默认action方法
			DefaultMethod default1=method.getAnnotation(DefaultMethod.class);
			if(default1!=null){
				if(defaultMethod!=null){
					throw new RuntimeException("@DefaultAction>1");
				}
				defaultMethod=method;
			}
			//执行参数指定action方法
			if(nameSet!=null
					&&nameSet.contains(method.getName())){
				return this.invokeMethod(context, method);
			}
		}
		//无指定action方法，执行默认action方法
		if(defaultMethod==null){
			throw new RuntimeException("not find @DefaultAction");
		}
		return this.invokeMethod(context, defaultMethod);
	}
	/**
	 * 调用方法
	 * @param context
	 * @param method
	 * @return
	 * @throws Exception
	 */
	private Object invokeMethod(ActionContext context,Method method) throws Exception{
		Class<?>[] types=method.getParameterTypes();
		if(types==null
				||types.length==0){
			return method.invoke(context.getActionObj());
		}
		//参数列表
		List<Object> list=new ArrayList<Object>();
		for (Class<?> type : types) {
			//HttpServletRequest
			if(type.equals(HttpServletRequest.class)){
				list.add(context.getRequest());
				continue;
			}
			//HttpServletResponse
			if(type.equals(HttpServletResponse.class)){
				list.add(context.getResponse());
				continue;
			}
			//HttpSession
			if(type.equals(HttpSession.class)){
				list.add(context.getRequest().getSession());
				continue;
			}
			//ActionContext
			if(type.equals(ActionContext.class)){
				list.add(context);
				continue;
			}
			//ServletContext
			if(type.equals(ServletContext.class)){
				list.add(ActionConfig.getServletContext());
				continue;
			}
			//ServletConfig
			if(type.equals(ServletConfig.class)){
				list.add(ActionConfig.getServletConfig());
				continue;
			}
			//basic type
			if(Common.isBasicType(type)){
				list.add(0);
				continue;
			}
			//default
			list.add(null);
		}
		
		return method.invoke(context.getActionObj(), list.toArray());
	}
	
}
