package com.dongx.accountbook.note.config;

import com.dongx.accountbook.note.model.MultiTenancy;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DataSourceConfig
 * 数据源配置
 * @author Dongx
 * Description:
 * Created in: 2019-10-20 11:28
 * Modified by:
 */
@Configuration
public class DataSourceConfig {
	
	/** 默认数据库 */
	@Value("${spring.datasource.default-db}")
	private String defaultDb;

	/** 数据源驱动 */
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	/** 数据库url */
	@Value("${spring.datasource.url}")
	private String dbUrl;

	/** 数据库用户名 */
	@Value("${spring.datasource.username}")
	private String dbUsername;

	/** 数据库密码 */
	@Value("${spring.datasource.password}")
	private String dbPassword;
	
	@Bean("dynamicDataSource")
	public DataSource dynamicDataSource() {
		// 从数据库初始化数据源
		initDataSource();
		
		// 设置动态数据源
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		Map<String, DataSource> tenancyDataSource = TenancyMetaData.getTenancyDataSource();
		Map<Object, Object> dataSourceMap = new HashMap<>(16);
		tenancyDataSource.forEach((k, v) -> dataSourceMap.put(k, v));
		dynamicDataSource.setDefaultDataSource(dataSourceMap.get(defaultDb));
		dynamicDataSource.setDataSources(dataSourceMap);
		return dynamicDataSource;
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		// 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource作为数据源则不能实现切换
		sessionFactory.setDataSource(dynamicDataSource());
		// 扫描Model
		sessionFactory.setTypeAliasesPackage("con.dongx.accountbook.not.model");    
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		// 扫描映射文件
		sessionFactory.setMapperLocations(resolver.getResources("classpath*:mapper/**.xml"));   
		return sessionFactory;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		// 配置事务管理, 使用事务时在方法头部添加@Transactional注解即可
		return new DataSourceTransactionManager(dynamicDataSource());
	}

	/**
	 * 初始化租户信息
	 */
	private void initDataSource() {
		// 加载jdbcTemplate
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		// 设置jdbc数据源
		DataSource defaultDataSource = dataSource(defaultDb);
		jdbcTemplate.setDataSource(defaultDataSource);

		// 获取所有租户信息
		final String sql = "select id, tenancy_name, tenancy_db, tenancy_account, description, create_time, update_time, flag from multi_tenancy";
		List<MultiTenancy> multiTenancies = jdbcTemplate.query(sql, (rs, num) -> {
			MultiTenancy multiTenancy = new MultiTenancy()
					.withId(rs.getInt("id"))
					.withTenancyName(rs.getString("tenancy_name"))
					.withTenancyDb(rs.getString("tenancy_db"))
					.withTenancyAccount(rs.getInt("tenancy_account"))
					.withDescription(rs.getString("description"))
					.withCreateTime(rs.getDate("create_time"))
					.withUpdateTime(rs.getDate("update_time"))
					.withFlag(rs.getByte("flag"));
			return multiTenancy;
		});

		// 设置租户数据源
		Map<String, DataSource> tenancyDataSourceMap = TenancyMetaData.getTenancyDataSource();
		tenancyDataSourceMap.put(defaultDb, defaultDataSource);
		
		// 租户信息
		Map<Integer, MultiTenancy> tenancyInfoMap = TenancyMetaData.getTenancyInfo();

		for (MultiTenancy multiTenancy : multiTenancies) {
			String tenancy = multiTenancy.getTenancyDb();
			// 设置租户数据源
			tenancyDataSourceMap.put(tenancy, dataSource(tenancy));
			// 租户id对应关系
			tenancyInfoMap.put(multiTenancy.getTenancyAccount(), multiTenancy);
		}
	}

	/**
	 * 生成数据源
	 * @param tenancyDb 数据库名称
	 */
	private DataSource dataSource(String tenancyDb) {
		String url = dbUrl.replace(defaultDb, tenancyDb);
		// todo 配置hikariDataSource
		HikariConfig configuration = new HikariConfig();
		configuration.setPoolName("HikariPool-" + tenancyDb);
		configuration.setDriverClassName(driverClassName);
		configuration.setJdbcUrl(url);
		configuration.setUsername(dbUsername);
		configuration.setPassword(dbPassword);
		return new HikariDataSource(configuration);
	}
}
