package org.snow.core.handle;

import org.snow.core.bean.ActionContext;

public interface IInvoke {
	/**
	 * 调用方法
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public Object invokeMethod(ActionContext bean)throws Exception;
}
