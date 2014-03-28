package org.snow.core.handle.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.snow.core.bean.ActionContext;
import org.snow.core.exception.SnowException;
import org.snow.core.handle.IParameterField;
import org.snow.core.util.Common;

/**
 * 上传文件，参数字段
 * @author lyw
 *
 */
public class ParameterFieldMultipart implements IParameterField {
	private List<FileItem> items=null;
	public void setParameterField(ActionContext context)throws Exception {
		this.init(context);
		if(this.items==null){
			return;
		}
	    Field[] fields=context.getActionClass().getFields();
	    for (FileItem item : items) {
          for (Field field : fields) {
        	//字段名与参数相同，设置值
			if(field.getName().equals(item.getFieldName())){
				//信息为普通的格式 
				if(item.isFormField()){
					Object value=Common.convertValue(field.getType(), item.getString());
					if(value!=null){
						field.set(context.getActionObj(), value);
					}
				}else{//信息为文件格式
					//文件类型为FileItem
					if(field.getType().equals(FileItem.class)){
						field.set(context.getActionObj(), item);
					}
				}
				break;
			}
			//参数为类的属性
			if(item.getFieldName().indexOf(".")!=-1){
				String[] arr=item.getFieldName().split("\\.");
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
							//信息为普通的格式 
							if(item.isFormField()){
								Object value=Common.convertValue(types[0], item.getString());
								if(value!=null){
									method.invoke(fieldObj, value);
								}
							}else{//信息为文件格式
								//文件类型为FileItem
								if(types[0].equals(FileItem.class)){
									method.invoke(fieldObj, item);
								}
							}
							break;
						}
					}
				}
			}
          }
		}
	}
	
	@SuppressWarnings("unchecked")
	private void init(ActionContext context) throws Exception{
		if(items==null){
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			this.items=upload.parseRequest(context.getRequest());
		}
	}
	
	public Set<String> getParameterNames(ActionContext context) throws Exception {
		this.init(context);
		if(this.items==null){
			return null;
		}
	    Set<String> set=new HashSet<String>();
	    for (FileItem item : items) {
	          if(!set.contains(item.getName())){
	        	  set.add(item.getFieldName());
	          }
			
		}
		return set;
	}
	
}
