package org.snow.core.annotation;

public enum Stage {
	/**
	 * action执行之前
	 */
	BEFOR
	,
	/**
	 * action执行之后，返回视图之前
	 */
	AFTER
	,
	/**
	 * 返回试图
	 */
	VIEW
}
