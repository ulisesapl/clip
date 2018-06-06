package com.test.uapl.transactions.repository.imp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.uapl.transactions.model.Sum;
import com.test.uapl.transactions.model.Transaction;
import com.test.uapl.transactions.repository.TransactionsRepository;

public class FileSystemRepository implements TransactionsRepository {

	private static final Logger LOG = LoggerFactory.getLogger(FileSystemRepository.class);

	@Override
	public Transaction addTransaction(Transaction transaction) throws IOException {
		LOG.debug("FileSystemRepository::addTransaction with {}", transaction);
		File directory = new File(transaction.getUserId().toString());
		if (!directory.exists() && !directory.mkdir()) {
			LOG.error("Cannot create the directory");
			throw new FileSystemException("Cannot create the directory");
		}
		FileOutputStream fileOutputStream = new FileOutputStream(
				transaction.getUserId().toString() + File.separator + transaction.getTransactionId());
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(transaction);
		objectOutputStream.flush();
		objectOutputStream.close();
		fileOutputStream.flush();
		fileOutputStream.close();

		return transaction;
	}

	@Override
	public List<Transaction> list(String userId) throws IOException {
		List<Transaction> list = this.getUserTransactions(userId).sorted((ob1,ob2) -> ob1.getDate().compareTo(ob2.getDate())).collect(Collectors.toList());
		Collections.reverse(list);
		return list;
	}

	@Override
	public Sum sum(String userId) throws IOException {
		return new Sum(Integer.parseInt(userId),
				this.getUserTransactions(userId)
				.map(Transaction::getAmount)
				.reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
	}

	private Stream<Transaction> getUserTransactions(String userId) throws IOException {

		
		if (Files.exists(Paths.get(userId)))
			return Files.list(Paths.get(userId)).map(this.getMapper());
		else {
			return Stream.empty();
		}

	}

	@Override
	public Transaction getTransaction(String userId, String transactionId) throws IOException {

		if (Files.exists(Paths.get(userId)))
			return Files.list(Paths.get(userId)).filter(s -> s.toString().contains(transactionId)).findFirst().map(this.getMapper()).orElse(null);
		else {
			return null;
		}
	}
	
	private Function<Path, Transaction> getMapper() {
		Function<Path, Transaction> mapper = s -> {

			try {
				InputStream is = Files.newInputStream(s);
				ObjectInputStream oi = new ObjectInputStream(is);
				Transaction t = (Transaction) oi.readObject();
				oi.close();
				is.close();
				return t;
			} catch (IOException | ClassNotFoundException e) {
				return new Transaction();
			}
		};
		
		return mapper;
	}

}
