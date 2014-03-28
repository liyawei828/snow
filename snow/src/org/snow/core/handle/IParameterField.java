package org.snow.core.handle;

import java.util.Set;

import org.snow.core.bean.ActionContext;

/**
 * 参数字段映射接口
 * @author lyw
 *
 */
public interface IParameterField {
	/**
	 * 设置参数字段
	 * @param obj
	 * @param bean
	 */
	public void setParameterField(ActionContext bean)throws Exception;
	/**
	 * 获取参数名称列表
	 * @param bean
	 */
	public Set<String> getParameterNames(ActionContext bean)throws Exception;
}
