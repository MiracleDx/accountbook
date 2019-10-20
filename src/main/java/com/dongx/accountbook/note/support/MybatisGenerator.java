package com.dongx.accountbook.note.support;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * MybatisGenerator
 *
 * @author Dongx
 * Description:
 * Created in: 2019-10-19 0:28
 * Modified by:
 */
public class MybatisGenerator {
	
	public static void main(String[] args)
			throws InterruptedException, SQLException,
			InvalidConfigurationException, XMLParserException, IOException {
		MybatisGenerator generator = new MybatisGenerator();
		generator.generator();
	}

	public void generator()
			throws InvalidConfigurationException, InterruptedException,
			SQLException, IOException, XMLParserException {
		List<String> warnings = new ArrayList<>();
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(
				this.getClass().getResourceAsStream("/generatorConfig.xml"));
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
}
