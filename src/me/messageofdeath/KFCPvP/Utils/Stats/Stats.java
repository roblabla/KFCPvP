package me.messageofdeath.KFCPvP.Utils.Stats;

import java.util.HashMap;

public class Stats {

	private double amount = 0;
	private String name;
	
	public Stats(String name) {
		this.name = name;
	}
	
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
	
	//******************** Static ************************
	
	private static HashMap<String, Stats> stats = null;
	
	
}
