package com.test.uapl.transactions.repository;

import java.io.IOException;
import java.util.List;

import com.test.uapl.transactions.model.Sum;
import com.test.uapl.transactions.model.Transaction;

public interface TransactionsRepository {
	/**
	 * Add a transaction
	 * @param transaction 
	 * @return The added transaction in json format
	 * @throws IOException 
	 */
	Transaction addTransaction(Transaction transaction) throws  IOException;

	/**
	 * List all the user transactions
	 * @param userId  The user id
	 * @return a List of transactions
	 * @throws IOException 
	 */
	List<Transaction> list(String userId) throws IOException;

	/**
	 * Sum all the transactions for the given userId
	 * @param userId The user id
	 * @return An sum object
	 * @throws IOException 
	 */
	Sum sum(String userId) throws IOException;

	/**
	 * Get the given transaction related with the userId
	 * @param userId The user id
	 * @param transactionId The transaction id
	 * @return The transaction or null if the transaction does not exist.
	 * @throws IOException
	 */
	Transaction getTransaction(String userId, String transactionId) throws IOException;
	
}
