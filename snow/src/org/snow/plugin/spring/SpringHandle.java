package org.snow.plugin.spring;

import java.lang.reflect.Field;

import org.snow.core.annotation.Intercept;
import org.snow.core.annotation.Stage;
import org.snow.core.bean.ActionContext;
import org.snow.core.handle.IActionHandler;
/**
 * spring注入
 * @author lyw
 *
 */
@Intercept(Stage.BEFOR)
public class SpringHandle implements IActionHandler {

	public boolean process(ActionContext context) throws Exception {
		Class<?> class1=context.getActionClass();
		Field[] fieldList=class1.getDeclaredFields();
		for (Field field : fieldList) {
			Spring spring=field.getAnnotation(Spring.class);
			if(spring!=null){
				Object bean=SpringHelper.getBean(context, "bean");
				field.setAccessible(true);
				field.set(context.getActionObj(), bean);
			}
		}
		return false;
	}

}
