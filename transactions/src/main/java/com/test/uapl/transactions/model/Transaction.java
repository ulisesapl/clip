package com.test.uapl.transactions.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7825617558192333312L;

	@JsonProperty("transaction_id")
	private String transactionId = UUID.randomUUID().toString();
	
	@JsonProperty("amount")
	private BigDecimal amount;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("date")
	@JsonFormat(
		      shape = JsonFormat.Shape.STRING,
		      pattern = "yyyy-MM-dd")
	private Date date;
	
	@JsonProperty("user_id")
	private Integer userId;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", amount=" + amount + ", description=" + description
				+ ", date=" + date + ", userId=" + userId + "]";
	}
	
	
}
