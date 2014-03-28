package org.snow.core.handle.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

import org.snow.core.bean.ActionContext;
import org.snow.core.exception.SnowException;
import org.snow.core.handle.IParameterField;
import org.snow.core.util.Common;

/**
 * request参数字段
 * @author lyw
 *
 */
public class ParameterFieldRequest implements IParameterField{

	public void setParameterField(ActionContext context)
			throws Exception {
		Set<String> nameSet=this.getParameterNames(context);
		/*
		 * 对属性字段进行赋值
		 */
		Field[] fields=context.getActionClass().getFields();
		for (Field field : fields) {
			for (String name : nameSet) {
				//字段名与参数相同，设置值
				if(name.equals(field.getName())){
					String fieldValue= context.getRequest().getParameter(field.getName());
					Object value=Common.convertValue(field.getType(), fieldValue);
					if(value==null){
						continue;
					}
					field.set(context.getActionObj(), value);
				}
				//参数为类的属性
				if(name.indexOf(".")!=-1){
					String[] arr=name.split("\\.");
					String className=arr[0];
					String fieldName=arr[1];
					//找到类
					if(field.getName().equals(className)){
						Object fieldObj=field.get(context.getActionObj());
						Method[] methods=fieldObj.getClass().getMethods();
						for (Method method : methods) {
							//调用set方法
							if(method.getName().equalsIgnoreCase("set"+fieldName)){
								Class<?>[] types=method.getParameterTypes();
								if(types==null||types.length!=1){
									throw new SnowException(fieldObj.getClass()+" method["+method.getName()+"] The parameter num must be 1");
								}

								String fieldValue= context.getRequest().getParameter(name);
								Object value=Common.convertValue(types[0], fieldValue);
								if(value==null){
									continue;
								}
								method.invoke(fieldObj, value);
								break;
							}
						}
					}
				}
			}

		}
	}
	@SuppressWarnings("unchecked")
	public Set<String> getParameterNames(ActionContext context) throws Exception {
		return context.getRequest().getParameterMap().keySet();
	}


}
