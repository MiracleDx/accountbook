package com.dongx.accountbook.note.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * DynamicDataSourceContextHolder
 * 动态数据源上下文
 * @author Dongx
 * Description:
 * Created in: 2019-10-19 22:06
 * Modified by:
 */
public class DynamicDataSourceContextHolder {
	
	private static final String DEFAULT_DB = "tenancy";
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>() {

		/**
		 * 默认数据源上下文
		 * @return
		 */
		@Override
		protected String initialValue() {
			return DEFAULT_DB;
		}	
	};

	/**
	 * 数据源key列表
	 */
	private static List<Object> dataSourceKeys = new ArrayList<>();

	/**
	 * 设置当前数据源
	 * @param key
	 */
	public static void setDataSourceKey(String key) {
		contextHolder.set(key);
	}

	/**
	 * 获取当前数据源
	 * @return
	 */
	public static String getDataSourceKey() {
		return contextHolder.get();
	}

	/**
	 * 判断是否包含数据源
	 * @param key
	 * @return
	 */
	public static boolean containDataSourceKey(String key) {
		return dataSourceKeys.contains(key);
	}

	/**
	 * 添加数据源keys
	 * @param keys
	 * @return
	 */
	public static boolean addDataSourceKeys(Collection<? extends Object> keys) {
		return dataSourceKeys.addAll(keys);
	}
}
