package me.messageofdeath.KFCPvP.Utils.Stats;

public class Stats {

	private Object obj;
	private double tempobj;
	
	public Stats() {}
		
	public Object getStat() {
		return this.obj;
	}
	
	public void setStat(Object obj) {
		this.obj = obj;
	}
	
	public void addAmount(double amount) {
		this.obj = (Double)this.obj + amount;
		this.tempobj += amount;
	}
	
	public void subAmount(double amount) {
		this.obj = (Double)this.obj - amount;
		this.tempobj -= amount;
	}
	
	public double getAmountChanged() {
		return this.tempobj;
	}
}
