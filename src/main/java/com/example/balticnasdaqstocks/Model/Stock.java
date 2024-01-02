package com.example.balticnasdaqstocks.Model;

public class Stock {
	private String company;
	private String ticker;
	private Double lastPrice = 0.0;
	private Double change = 0.0;
	private Double percentage = 0.0;
	private Double bid = 0.0;
	private Double ask = 0.0;
	private int trades = 0;
	private int volume = 0;
	private Double turnover = 0.0;

	public Stock() {
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public Double getChange() {
		return change;
	}

	public void setChange(Double change) {
		this.change = change;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public Double getBid() {
		return bid;
	}

	public void setBid(Double bid) {
		this.bid = bid;
	}

	public Double getAsk() {
		return ask;
	}

	public void setAsk(Double ask) {
		this.ask = ask;
	}

	public int getTrades() {
		return trades;
	}

	public void setTrades(int trades) {
		this.trades = trades;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public Double getTurnover() {
		return turnover;
	}

	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}

	@Override
	public String toString() {
		return "Stock{" +
				"company='" + company + '\'' +
				", ticker='" + ticker + '\'' +
				", lastPrice=" + lastPrice +
				", change=" + change +
				", percentage=" + percentage +
				", bid=" + bid +
				", ask=" + ask +
				", trades=" + trades +
				", volume=" + volume +
				", turnover=" + turnover +
				'}';
	}
}
