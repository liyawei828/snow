package org.snow.action.lyw;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.snow.core.annotation.DefaultMethod;
import org.snow.core.bean.ActionContext;
import org.snow.core.bean.FileBean;
import org.snow.plugin.spring.Spring;

/**
 * 
 * @author lyw
 *
 */
public class lyw {
	public String a;
	public int b;
	public short c;
	public long d;
	public float e;
	public double f;
	public FileItem upload_file;
	public Bean bean_param=new Bean();
	public List<String> list;
	@Spring
	private Bean bean=new Bean();
	@DefaultMethod
	public Object defaultMethod(){
		System.out.println("defaultMethod");
		return "defaultMethod";
	}
	public Object targetMethod(){
		System.out.println("targetMethod");
		return "targetMethod";
	}
	public Object upload(){
		try {
			if(upload_file!=null
					&&upload_file.isFormField()){
				System.out.println(upload_file);
				return "upload success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "upload failure";
	}
	
	public Object download(){
		File file=new File("d:/123456789.jpg");
		FileBean fb=new FileBean(file,"bot.jpg");
		return fb;
	}
	
	public Object params(HttpServletRequest req,HttpServletResponse rep
			,HttpSession session,ActionContext context
			,ServletContext sc,ServletConfig scf,int it,String str){
		StringBuilder sb=new StringBuilder();
		sb.append(context.isMultipart()?"是":"否");
		sb.append("<br/>");
		sb.append("传入参数-------------");
		sb.append("<br/>");
		sb.append("a:"+a);
		sb.append("<br/>");
		sb.append("b:"+b);
		sb.append("<br/>");
		sb.append("c:"+c);
		sb.append("<br/>");
		sb.append("d:"+d);
		sb.append("<br/>");
		sb.append("e:"+e);
		sb.append("<br/>");
		sb.append("f:"+f);
		sb.append("<br/>");
		sb.append("bean_param.g:"+this.bean_param.getG());
		sb.append("<br/>");
		
		sb.append("spring注入-------------");
		sb.append("<br/>");
		sb.append(bean);
		sb.append("<br/>");

		sb.append("方法参数-------------");
		sb.append("<br/>");
		sb.append("req:"+req);
		sb.append("<br/>");
		sb.append("rep:"+rep);
		sb.append("<br/>");
		sb.append("session:"+session);
		sb.append("<br/>");
		sb.append("context:"+context);
		sb.append("<br/>");
		sb.append("sc:"+sc);
		sb.append("<br/>");
		sb.append("scf:"+scf);
		sb.append("<br/>");
		sb.append("it:"+it);
		sb.append("<br/>");
		sb.append("str:"+str);
		sb.append("<br/>");
		
		return sb.toString();
	}
	
	public Object returnJsp(){
		list=new ArrayList<String>();
		list.add("list1");
		list.add("list2");
		list.add("list3");
		list.add("list4");
		return "/return.jsp";
	}
	
	
}
