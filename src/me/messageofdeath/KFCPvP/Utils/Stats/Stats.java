package me.messageofdeath.KFCPvP.Utils.Stats;

public class Stats {

	private double amount = 0;
	
	public Stats() {}
		
	public double getAmount() {
		return this.amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void addAmount(double amount) {
		this.amount += amount;
	}
	
	public void subAmount(double amount) {
		this.amount -= amount;
	}
}
