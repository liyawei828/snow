package org.snow.core.bean;

import java.io.File;

/**
 * 文件封装类
 * @author lyw
 *
 */
public class FileBean {
	public FileBean(File file){
		this.file=file;
	}
	public FileBean(File file,String name){
		this.file=file;
		this.name=name;
	}
	private File file;
	private String name;
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
