package org.snow.core.handle.impl;

import java.lang.reflect.Field;

import org.apache.commons.fileupload.FileItem;
import org.snow.core.annotation.Intercept;
import org.snow.core.annotation.Stage;
import org.snow.core.bean.ActionContext;
import org.snow.core.handle.IActionHandler;

/**
 * 响应参数处理
 * @author lyw
 *
 */
@Intercept(Stage.AFTER)
public class ResponseParamsHandler implements IActionHandler {
	public boolean process(ActionContext context) throws Exception {
		Field[] fields=context.getActionClass().getFields();
		for (Field field : fields) {
			if(field.getClass().equals(FileItem.class)){
				continue;
			}
			context.getRequest().setAttribute(field.getName(), field.get(context.getActionObj()));
		}
		return false;
	}

}
