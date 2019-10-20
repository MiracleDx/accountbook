package com.dongx.accountbook.note.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * DynamicDataSource
 * 动态数据源
 * @author Dongx
 * Description:
 * Created in: 2019-10-19 21:51
 * Modified by:
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	/**
	 * 设置启动数据源
	 */
	@Override
	protected DataSource determineTargetDataSource() {
		return super.determineTargetDataSource();
	}

	/**
	 * 通过修改key值修改数据源
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceContextHolder.getDataSourceKey();
	}

	/**
	 * 设置默认数据源
	 * @param defaultDataSource
	 */
	public void setDefaultDataSource(Object defaultDataSource) {
		super.setDefaultTargetDataSource(defaultDataSource);
	}

	/**
	 * 设置数据源
	 * @param dataSources
	 */
	public void setDataSources(Map<Object, Object> dataSources) {
		super.setTargetDataSources(dataSources);
		// 将数据源的 key 放到数据源上下文的 key 集合中，用于切换时判断数据源是否有效
		DynamicDataSourceContextHolder.addDataSourceKeys(TenancyMetaData.getTenancyDataSource().keySet());
	}
}
