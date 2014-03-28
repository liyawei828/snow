package org.snow.plugin.spring;

import org.snow.core.bean.ActionConfig;
import org.snow.core.bean.ActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringHelper {
	/**
	 * Get bean from spring context.
	 * 
	 * @param context Execute context.
	 * @param beanName Bean name.
	 * @return Spring bean.
	 * @throws Exception
	 */
	public static Object getBean(ActionContext context, String beanName) throws Exception {
		WebApplicationContext ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(ActionConfig.getServletContext());
		return ctx.getBean(beanName);
	}
}
