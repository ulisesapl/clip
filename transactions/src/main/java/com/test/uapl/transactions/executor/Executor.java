package com.test.uapl.transactions.executor;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.test.uapl.transactions.model.Transaction;
import com.test.uapl.transactions.repository.TransactionsRepository;
import com.test.uapl.transactions.util.TransactionUtil;

@Component
public class Executor implements ApplicationRunner {

	private static final Logger LOG = LoggerFactory.getLogger(Executor.class);

	@Autowired
	private TransactionsRepository repository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOG.debug("Running application with args {}", Arrays.toString(args.getSourceArgs()));
		if (args.getSourceArgs().length < 2) {
			this.printHelp();
			return;
		}
		Object object = null;
		switch (args.getSourceArgs()[1]) {
		case "add":
			LOG.debug("Executing add");
			Transaction transaction = TransactionUtil.getObjectFromJson(args.getSourceArgs()[2], Transaction.class);
			transaction.setUserId(Integer.parseInt(args.getSourceArgs()[0]));
			object = this.repository.addTransaction(transaction);
			break;
		case "list":
			LOG.debug("Executing list");
			object = this.repository.list(args.getSourceArgs()[0]);
			break;
		case "sum":
			LOG.debug("Executing sum");
			object = this.repository.sum(args.getSourceArgs()[0]);
			break;
		default:
			LOG.debug("Excuting transaction Id");
			object = this.repository.getTransaction(args.getSourceArgs()[0],args.getSourceArgs()[1]);
			if(object == null) {
				object = "Transaction not found";
			}
			break;
		}
		
		LOG.info(TransactionUtil.getJsonFromObject(object));
	}

	private void printHelp() {
		LOG.info("--For add transaction use:\n\t./application <user_id> add <transaction_json>");
		LOG.info("--For show transaction use:\n\t./application <user_id> <transaction_id>");
		LOG.info("--For list transanctions use:\n\t./application <user_id> list");
		LOG.info("--For sum transactions user:\n\t./application <user_id> sum");
	}

}
