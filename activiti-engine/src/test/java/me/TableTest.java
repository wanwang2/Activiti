package me;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * 	db.driverClassName=com.mysql.jdbc.Driver
	db.url=jdbc:mysql://localhost:3306/ifast
	db.username=root
	db.password=root
 * @author Administrator
 *
 */
public class TableTest {

	@Test
	public void createTable() {
		ProcessEngineConfiguration config = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/activiti");
		config.setJdbcDriver("com.mysql.jdbc.Driver");
		config.setJdbcUsername("root");
		config.setJdbcPassword("root");
		config.setDatabaseSchemaUpdate("true");
		ProcessEngine pe = config.buildProcessEngine();
		System.out.println("create end, pe:" + pe);
	}
}
