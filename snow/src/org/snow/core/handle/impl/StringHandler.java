package org.snow.core.handle.impl;

import javax.servlet.RequestDispatcher;

import org.snow.core.annotation.Intercept;
import org.snow.core.annotation.Stage;
import org.snow.core.bean.ActionConfig;
import org.snow.core.bean.ActionContext;
import org.snow.core.handle.IActionHandler;
import org.snow.core.util.Common;
import org.snow.core.util.Constant;

/**
 * string视图处理
 * @author lyw
 *
 */
@Intercept(Stage.AFTER)
public class StringHandler implements IActionHandler {
	
	public boolean process(ActionContext context) throws Exception {
		if(context.getReturnObj()==null){
			return true;
		}
		if(context.getReturnObj().getClass().equals(String.class)){
			String returnString=context.getReturnObj().toString();
			//返回jsp|html...文件
			if(returnString.matches(Constant.VIEW_MATCHES)){
				RequestDispatcher dis=ActionConfig.getServletContext().getRequestDispatcher(context.getReturnObj().toString());
				dis.forward(context.getRequest(),context.getResponse());
				return true;
			}
			//输出string
			Common.outPrint(context,returnString);
			return true;
		}
		return false;
	}
	

}
