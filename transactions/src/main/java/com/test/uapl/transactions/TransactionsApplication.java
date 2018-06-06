package com.test.uapl.transactions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.test.uapl.transactions.repository.TransactionsRepository;
import com.test.uapl.transactions.repository.imp.FileSystemRepository;

@SpringBootApplication
public class TransactionsApplication {
	
	private static final Logger LOG = LoggerFactory.getLogger(TransactionsApplication.class);
	
	@Autowired
	private TransactionsProperties transactionsProperties;

	public static void main(String[] args) {
		SpringApplication.run(TransactionsApplication.class, args);
	}
	
	@Bean
	TransactionsRepository repository() {
		String repository = this.transactionsProperties.getRepository();
		LOG.debug("Selecting repository {}", repository);
		switch(repository.toLowerCase()){
			case "filesystem":
			default:
				return new FileSystemRepository();
		}
	}
}
