package model;

public class MonteCarloEstimator {
	
	public static void main(String[] args) {
		  MonteCarlo mc = new MonteCarlo(5, (long) 0.00001);
		  mc.calculatePi();
     }
}
