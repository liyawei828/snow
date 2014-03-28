package org.snow.core;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.snow.core.annotation.Intercept;
import org.snow.core.annotation.Stage;
import org.snow.core.bean.ActionConfig;
import org.snow.core.bean.ActionContext;
import org.snow.core.exception.SnowException;
import org.snow.core.handle.IActionHandler;
import org.snow.core.handle.IInvoke;
import org.snow.core.handle.impl.ResponseParamsHandler;
import org.snow.core.handle.impl.StringHandler;
import org.snow.core.handle.impl.Invoke;
import org.snow.core.handle.impl.ParameterFieldMultipart;
import org.snow.core.handle.impl.ParameterFieldRequest;
import org.snow.core.util.StringUtil;
/**
 * action控制器
 * @author lyw
 *
 */
public class ActionController {
	private static ActionController actionController=null;
	/**
	 * 
	 * @param context
	 */
	private ActionController(){
	}
	/**
	 * 单例
	 * @param context
	 * @return
	 */
	public static ActionController getInstance(){
		if(actionController==null){
			actionController=new ActionController();
		}
		return actionController;
	}
	
	public void doAction(ActionContext context){
		
		try {
			//执行方法之前
			this.befor(context);
			//执行过程
			this.process(context);
			//执行之后
			this.after(context);
			//返回视图
			this.view(context);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SnowException(e);
		}
	}
	/**
	 * 执行方法之前
	 * @param context
	 * @throws Exception
	 */
	private void befor(ActionContext context) throws Exception{
		
		//action class path
		String path=context.getRequest().getServletPath();
		path=path.split("\\.")[0].replace("/", ".");
		String actionClassPath=ActionConfig.getBase()+path;
		context.setActionClassPath(actionClassPath);
		
		//action class
		Class<?> actionClass=Class.forName(context.getActionClassPath());
		context.setActionClass(actionClass);
		
		//action obj
		Object actionObj=actionClass.newInstance();
		context.setActionObj(actionObj);
		
		//IParameterField
		boolean isMultipart= ServletFileUpload.isMultipartContent(context.getRequest());
		context.setMultipart(isMultipart);
		if(isMultipart){
			context.setParameterField(new ParameterFieldMultipart());
		}else{
			context.setParameterField(new ParameterFieldRequest());
		}
		
		//执行插件
		this.doHandles(context,Stage.BEFOR);
	}
	private void process(ActionContext context) throws Exception{
		
		//设置参数字段
		context.getParameterField().setParameterField(context);
		
		//调用方法
		IInvoke invoke=new Invoke();
		Object returnObj=invoke.invokeMethod(context);
		context.setReturnObj(returnObj);
	}
	private void after(ActionContext context) throws Exception{
		//响应参数
		IActionHandler handle=new ResponseParamsHandler();
		handle.process(context);
		
		//执行插件
		boolean isHandle=this.doHandles(context,Stage.AFTER);
		if(isHandle){
			return;
		}
	}
	private void view(ActionContext context) throws Exception{
		if(context.getReturnObj()==null){
			return;
		}
		//执行插件
		boolean isHandle=this.doHandles(context,Stage.VIEW);
		if(isHandle){
			return;
		}
		//处理返回string
		IActionHandler handle=new StringHandler();
		isHandle=handle.process(context);
		if(isHandle){
			return;
		}
		
		throw new SnowException("not find "+context.getReturnObj()+" IActionHandler");
	}
	/**
	 * 
	 * 执行插件
	 * @param context
	 * @param stage 运行阶段
	 * @return 是否继续执行
	 * @throws Exception
	 */
	private boolean doHandles(ActionContext context,Stage stage) throws Exception{
		String actionHandles=ActionConfig.getActionHandles();
		if(StringUtil.isNotEmpty(actionHandles)){
			String[] handleList=actionHandles.split(",");
			for (String classPath : handleList) {
				Class<?> cs=Class.forName(classPath);
				//查找Intercept注解
				Intercept it=cs.getAnnotation(Intercept.class);
				if(it==null){
					throw new SnowException(cs+" not statement org.snow.annotation.@Intercept");
				}
				if(it.value()!=stage){
					continue;
				}
				//查找IActionHandler接口
				Class<?>[] interfaceList=cs.getInterfaces();
				boolean isInterface=false;
				for (Class<?> class1 : interfaceList) {
					if(class1.equals(IActionHandler.class)){
						isInterface=true;	
						break;
					}
				}
				if(isInterface==false){
					throw new SnowException(cs+" not implements org.snow.handle.IActionHandler");
				}
				//执行过程
				IActionHandler ih=(IActionHandler) cs.newInstance();
				boolean ret= ih.process(context);
				if(ret){
					return true;
				}
			}
		}
		return false;
	}
}
