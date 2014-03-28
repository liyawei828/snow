package org.snow.core.util;

import java.io.PrintWriter;

import org.snow.core.bean.ActionConfig;
import org.snow.core.bean.ActionContext;
import org.snow.core.exception.SnowException;

public class Common {
	/**
	 * 转换value
	 * @param cs
	 * @param value
	 * @return
	 */
	public static Object convertValue(Class<?> cs,String value){
		if(value==null){
			return null;
		}
		//String
		if(cs.equals(String.class)){
			return value;
		}
		//int 
		if(cs.equals(int.class)
				||cs.equals(Integer.class)){
			return Integer.valueOf(value);
		}
		//short
		if(cs.equals(short.class)
				||cs.equals(Short.class)){
			return Short.valueOf(value);
		}
		//long
		if(cs.equals(long.class)
				||cs.equals(Long.class)){
			return Short.valueOf(value);
		}
		//double
		if(cs.equals(double.class)
				||cs.equals(Double.class)){
			return Double.valueOf(value);
		}
		//float
		if(cs.equals(float.class)
				||cs.equals(Float.class)){
			return Float.valueOf(value);
		}
		//char
		if(cs.equals(char.class)){
			return (char)Integer.parseInt(value);
		}
		//boolean
		if(cs.equals(boolean.class)
				||cs.equals(Boolean.class)){
			return Boolean.valueOf(value);
		}
		return null;
	}
	/**
	 * 是否存在指定class
	 * @param classList
	 * @param cs
	 * @return
	 */
	public static boolean contains(Class<?>[] classList,Class<?> cs){
		for (Class<?> class1 : classList) {
			if(class1.equals(cs)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 是否是基本数据类型
	 * @param class1
	 * @return
	 */
	public static boolean isBasicType(Class<?> class1){
		//int 
		if(class1.equals(int.class)
				||class1.equals(Integer.class)){
			return true;
		}
		//short
		if(class1.equals(short.class)
				||class1.equals(Short.class)){
			return true;
		}
		//long
		if(class1.equals(long.class)
				||class1.equals(Long.class)){
			return true;
		}
		//double
		if(class1.equals(double.class)
				||class1.equals(Double.class)){
			return true;
		}
		//float
		if(class1.equals(float.class)
				||class1.equals(Float.class)){
			return true;
		}
		//char
		if(class1.equals(char.class)){
			return true;
		}

		//boolean
		if(class1.equals(boolean.class)
				||class1.equals(Boolean.class)){
			return true;
		}
		return false;
	}
	
	/**
	 * 打印字符串
	 * @param context
	 * @param returnString
	 */
	public static void outPrint(ActionContext context,String returnString){
		PrintWriter out=null;
		try {
			context.getResponse().setContentType("text/html;charset="+ActionConfig.getCharset());
			//输出返回字符串
			out = context.getResponse().getWriter();
			out.print(returnString);
		} catch (Exception e) {
			throw new SnowException(e);
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}
	}
}
