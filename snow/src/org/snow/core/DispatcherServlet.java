package org.snow.core;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.snow.core.bean.ActionConfig;
import org.snow.core.bean.ActionContext;
import org.snow.core.exception.SnowException;
import org.snow.core.util.StringUtil;
/**
 * 
 * @author lyw
 *
 */
public class DispatcherServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet (HttpServletRequest request, HttpServletResponse response) {
		this.doPost(request, response);
	}
	public void doPost (HttpServletRequest request, HttpServletResponse response) {
		vaildate();
		//实例化上下文
		ActionContext context=new ActionContext(request, response);
		//获取控制器单例
		ActionController con=ActionController.getInstance();
		//执行控制器
		con.doAction(context);
	}

	public void init() {
		//ServletConfig
		ActionConfig.setServletConfig(this.getServletConfig());
		
		//ServletContext
		ActionConfig.setServletContext(this.getServletContext());
	}
	private void vaildate(){
		if(ActionConfig.getServletConfig()==null){
			throw new SnowException("ServletConfig is null");
		}

		if(ActionConfig.getServletContext()==null){
			throw new SnowException("ServletContext is null");
		}
		if(StringUtil.isEmpty(ActionConfig.getBase())){
			throw new SnowException("<init-param><param-name>base</param-name></init-param> is must");
		}
		if(StringUtil.isEmpty(ActionConfig.getCharset())){
			throw new SnowException("<init-param><param-name>charset</param-name></init-param> is must");
		}
	}
}
