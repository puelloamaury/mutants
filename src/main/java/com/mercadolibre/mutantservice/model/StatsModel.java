package com.mercadolibre.mutantservice.model;

public class StatsModel {

	
	
	public StatsModel(long mutantCount, long humanCount) {
		this.mutantCount = mutantCount;
		this.humanCount = humanCount;
		if(humanCount>0) {
			this.ratio = mutantCount/humanCount;
		} else {
			if(mutantCount == 0) {
				this.ratio = 0;
			} else {
				this.ratio = 1;
			}
		}
	}
	private long mutantCount;
	private long humanCount;
	private double ratio;
	
	public long getMutantCount() {
		return mutantCount;
	}
	public void setMutantCount(long mutantCount) {
		this.mutantCount = mutantCount;
	}
	public long getHumanCount() {
		return humanCount;
	}
	public void setHumanCount(long humanCount) {
		this.humanCount = humanCount;
	}
	public double getRatio() {
		return ratio;
	}
	@Override
	public String toString() {
		return "StatsModel [mutantCount=" + mutantCount + ", humanCount=" + humanCount + ", ratio=" + ratio + "]";
	}
	
	
	
}
