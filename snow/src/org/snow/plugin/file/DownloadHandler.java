package org.snow.plugin.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import org.snow.core.annotation.Intercept;
import org.snow.core.annotation.Stage;
import org.snow.core.bean.ActionContext;
import org.snow.core.bean.FileBean;
import org.snow.core.exception.SnowException;
import org.snow.core.handle.IActionHandler;
import org.snow.core.util.StringUtil;

/**
 * 下载文件处理
 * @author lyw
 *
 */
@Intercept(Stage.VIEW)
public class DownloadHandler implements IActionHandler {
	public boolean process(ActionContext context) throws Exception {
		if(context.getReturnObj().getClass().equals(File.class)){
			File file=(File)context.getReturnObj();
			this.downLoad(context, file, file.getName());
			return true;
		}
		if(context.getReturnObj().getClass().equals(FileBean.class)){
			FileBean fileBean=(FileBean)context.getReturnObj();
			this.downLoad(context, fileBean.getFile(), fileBean.getName());
			return true;
		}
		return false;
	}
	
	public void downLoad(ActionContext context,File file,String filename) throws Exception {
        if (file==null
        		||!file.exists()) {
        	context.getResponse().sendError(404, "File not found!");
            return;
        }
        if(StringUtil.isEmpty(filename)){
        	filename=file.getName();
        }
        BufferedInputStream br=null;
        OutputStream out=null;
        try {
			br = new BufferedInputStream(new FileInputStream(file));
			byte[] buf = new byte[1024];
			int len = 0;
			context.getResponse().reset(); // 非常重要
			context.getResponse().setContentType("application/x-msdownload");
			context.getResponse().setHeader("Content-Disposition",
					"attachment; filename=" + filename);
			out = context.getResponse().getOutputStream();
			while ((len = br.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} catch (Exception e) {
			throw new SnowException(e);
		}finally{
			if(br!=null){
				try {
					br.close();
				} catch (Exception e2) {
				}
			}
			if(out!=null){
				try {
					out.close();
				} catch (Exception e2) {
				}
			}
		}
    }

}
