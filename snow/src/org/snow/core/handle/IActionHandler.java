package org.snow.core.handle;

import org.snow.core.bean.ActionContext;

/**
 * action 处理接口
 * @author lyw
 *
 */
public interface IActionHandler {
	/**
	 * action过程链。
	 * @param bean 
	 * @return 如果返回true，然后返回阶段将停止办理（当阶段支持被停止）。
	 */
	public boolean process(ActionContext context) throws Exception;
}
