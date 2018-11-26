package com.hb.HiberNateFirst.config;

import java.util.Properties;

import javax.management.MXBean;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hb.HiberNateFirst.dao.impl.EmployeeDaoImpl;
import com.hb.HiberNateFirst.pojo.Employee;

@Configuration
@ComponentScan
@EnableTransactionManagement
public class DBConnect {

	
	private final static String database_URL = "jdbc:h2:tcp://localhost/~/demo";
	private final static String database_driver = "org.h2.Driver";
	private final static String database_dialect = "org.hibernate.dialect.H2Dialect";
	private final static String database_username = "sa";
	private final static String database_password = "";



	@Bean(name="dataSource")
	public DataSource getData() {
	
	BasicDataSource db2=new BasicDataSource();
		db2.setDriverClassName(database_driver);
		db2.setUrl(database_URL);
		db2.setUsername(database_username);
		db2.setPassword(database_password);
	return db2;
	}
	
	
	// All the hibernate properties will be returned in this method
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", database_dialect);
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		System.out.println("Hibernate properties created");
		return properties;
	}

	
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder  builder=new LocalSessionFactoryBuilder(dataSource);
		System.out.println("Session Object Is created");
	builder.addProperties(getHibernateProperties());
	
	builder.addAnnotatedClass(Employee.class);
		System.out.println("All classes added");
	return builder.buildSessionFactory();

	}
	
	@Bean(name="empconnect")
	@Autowired
	public EmployeeDaoImpl addEmplConfig(SessionFactory sessionFactory) {
		
		return new EmployeeDaoImpl(sessionFactory);
	}
	
	@Bean(name = ("transactionManager"))
	public HibernateTransactionManager getTransactionManager(SessionFactory sf) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sf);
		return transactionManager;

	}
}
