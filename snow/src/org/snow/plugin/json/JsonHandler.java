package org.snow.plugin.json;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import org.snow.core.annotation.Intercept;
import org.snow.core.annotation.Stage;
import org.snow.core.bean.ActionContext;
import org.snow.core.handle.IActionHandler;
import org.snow.core.util.Common;

/**
 * json视图处理
 * @author lyw
 *
 */
@Intercept(Stage.VIEW)
public class JsonHandler implements IActionHandler {
	public boolean process(ActionContext context) throws Exception {
		Class<?>[] interfaceTypeList=context.getReturnObj().getClass().getInterfaces();
		for (Class<?> type : interfaceTypeList) {
			if(type.equals(List.class)
					||type.equals(Set.class)
					||type.equals(Map.class)){
				this.outJson(context);
				return true;
			}
		}
		return false;
	}
	/**
	 * 打印json
	 * @param context
	 */
	private void outJson(ActionContext context){
		JSONArray json=JSONArray.fromObject(context.getReturnObj());
		Common.outPrint(context, json.toString());
	}

}
