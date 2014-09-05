package com.appteam.exchange.model;

public class ExchangeItemModel {

	private long id;
	private String to;
	private String rate;
	private String from;
	private long currenttime;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	
	public long getCurrenttime() {
		return currenttime;
	}
	public void setCurrenttime(long currenttime) {
		this.currenttime = currenttime;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
