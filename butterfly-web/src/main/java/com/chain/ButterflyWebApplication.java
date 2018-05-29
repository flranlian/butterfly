package com.chain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

@EnableAutoConfiguration
//@SpringBootApplication
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class ButterflyWebApplication {


//	@Bean
//	public Object testBean(PlatformTransactionManager platformTransactionManager){
//		System.out.println(">>>>>>>>>>事物管理:" + platformTransactionManager.getClass().getName());
//		return new Object();
//	}

	public static void main(String[] args) {
		SpringApplication.run(ButterflyWebApplication.class, args);
	}
}
