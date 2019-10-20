package com.dongx.accountbook.note.config;

import com.dongx.accountbook.note.model.MultiTenancy;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TenancyDataSource
 * 租户元数据信息
 * @author Dongx
 * Description:
 * Created in: 2019-10-19 22:16
 * Modified by:
 */
public class TenancyMetaData {
	
	/**
	 * 租户map
	 */
	private static Map<Integer, MultiTenancy> tenancyMap = new ConcurrentHashMap<>();

	/**
	 * 租户数据源map
	 */
	private static Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();

	/**
	 * 获取租户信息
	 * @return
	 */
	public static Map<Integer, MultiTenancy> getTenancyInfo() {
		return tenancyMap;
	}
	
	/**
	 * 获取租户datasource
	 * @return
	 */
	public static Map<String, DataSource> getTenancyDataSource() {
		return dataSourceMap;
	}


}
