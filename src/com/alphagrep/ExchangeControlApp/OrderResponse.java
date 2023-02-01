package com.alphagrep.ExchangeControlApp;

/**
 * Description : This class contains Pojo Classes for Order Response
 * @author : Shikhar
 */
public class OrderResponse {
	private String responseType;
	private Long orderId;
	private String symbol;
	private String side;
	private Double price;
	private Integer quantity;
	private String accountId;
	private Integer errorCode;
	private Long timestamp;
	private Long exchangeOrderId;
	private String childResponseType;
	private String duration;
	private Long exchTs;
	public String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}



	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Long getExchangeOrderId() {
		return exchangeOrderId;
	}

	public void setExchangeOrderId(Long exchangeOrderId) {
		this.exchangeOrderId = exchangeOrderId;
	}

	public String getChildResponseType() {
		return childResponseType;
	}

	public void setChildResponseType(String childResponseType) {
		this.childResponseType = childResponseType;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Long getExchTs() {
		return exchTs;
	}

	public void setExchTs(Long exchTs) {
		this.exchTs = exchTs;
	}


}
