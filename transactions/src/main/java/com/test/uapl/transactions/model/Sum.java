package com.test.uapl.transactions.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3623441557065165915L;
	
	@JsonProperty("user_id")
	private Integer userId;
	
	@JsonProperty("sum")
	private BigDecimal sum;


	public Sum(Integer userId, BigDecimal sum) {
		super();
		this.userId = userId;
		this.sum = sum;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}
	
	

}
