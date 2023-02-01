package com.alphagrep.ExchangeControlApp;

public class OrderRequest {

	/**
	 * Description : This class contains Pojo Classes for Order Request
	 * @author : Shikhar
	 */

	private String requestType;
	private Long orderId;
	private String token;
	private String symbol;
	private String side;
	private Double price;
	private Integer quantity;
	private Integer quantityFilled;
	private Integer disclosedQuantity;
	private Long timestamp;
	private String duration;
	private String orderType;
	private String account;
	private Integer exchange;
	private Integer numCopies;

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public Integer getQuantityFilled() {
		return quantityFilled;
	}

	public void setQuantityFilled(Integer quantityFilled) {
		this.quantityFilled = quantityFilled;
	}

	public Integer getDisclosedQuantity() {
		return disclosedQuantity;
	}

	public void setDisclosedQuantity(Integer disclosedQuantity) {
		this.disclosedQuantity = disclosedQuantity;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getExchange() {
		return exchange;
	}

	public void setExchange(Integer exchange) {
		this.exchange = exchange;
	}

	public Integer getNumCopies() {
		return numCopies;
	}

	public void setNumCopies(Integer numCopies) {
		this.numCopies = numCopies;
	}





}
