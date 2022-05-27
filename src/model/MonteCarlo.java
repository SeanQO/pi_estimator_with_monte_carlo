package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class MonteCarlo {
	 private int in = 0;
	 private int out = 0;
	 // Pi with 24 digits
	 private BigDecimal pi = new BigDecimal(3.141592653589793238462643);
	 private BigDecimal piCalc = new BigDecimal(0.0);
	 private BigDecimal epsilon = new BigDecimal(0.00001);
	 private int error = 1;
	 // get random value
	 private double getRand() {
	  Random rd = new Random();
	  return rd.nextDouble();
	 }
	 // get amount of points inside the circle
	 private int getIn() {
	  return this.in;
	 }
	 // get amount of points outside the circle
	 private int getOut() {
	  return this.out;
	 }
	 // calculate PI
	 private BigDecimal calcPi() {
	  // multiply the result with 4 because the simulation was only
	  BigDecimal piTmp = BigDecimal.valueOf(4.0 * ((double) this.getIn() / ((double) this.getIn() + (double) this.getOut())));
	  return piTmp;
	 }
	 // get Pi with 24 digits
	 private BigDecimal getPi() {
	  return this.pi;
	 }
	 // reset values to ensure valid results
	 private void resetValues() {
	  this.in = 0;
	  this.out = 0;
	  this.piCalc = new BigDecimal(0.0);
	  this.error = 1;
	 }
	 // print the results to the console
	 private void print(int n) {
	  System.out.println("Calculation with: " + n);
	  System.out.println("Points in: " + this.getIn() + ", points out: " + this.getOut());
	  System.out.println("Pi calculated: " + this.calcPi() + ", In+out: " + (this.getOut() + this.getIn()));
	  System.out.println("Difference: Pi calculated (" + this.calcPi() + ") - Pi (" + this.pi.setScale(10, RoundingMode.CEILING)  + "): " + this.calcPi().subtract(this.getPi()).abs().setScale(10, RoundingMode.CEILING));
	  System.out.println("--------------- " + "\n");
	 }

	 // compare error with epsilon
	 private int getError(BigDecimal currentPi) {
	  // currently calculated PI minus PI with 24 digits
	  BigDecimal diff = currentPi.subtract(this.pi).abs();
	  int error = diff.compareTo(this.epsilon);
	  // error = 1: diff > epsilon
	  // error = 0: diff = epsilon
	  // error = -1: diff < epsilon
	  return error;
	 }
	 
	 // calculate PI for a certain Epsilon
	 public void calcEpsilon() {
	  // initiate values
	  this.resetValues();
	  // Boolean is used to determine whether the calculated value for PI is acceptable
	  Boolean done = false;
	  // compute until done is true
	  while (!done) {
	   double x = this.getRand();
	   double y = this.getRand();
	   if (x * x + y * y <= 1) {
	    this.in++;
	    } else {
	    this.out++;
	   }
	   // calculate current PI
	   this.piCalc = this.calcPi();
	   // compare error with epsilon
	   error = this.getError(this.piCalc);
	   // epsilon is greater than error
	   if (error == -1) {
	    // error is less than epsilon
	    done = true;
	   }
	   // exit if there cannot be determined a Pi with an error less than epsilon
	   if ((this.in + this.out) > 100000000) {
	    break;
	   }
	  }
	   System.out.println("Error (" + this.pi.subtract(this.piCalc).abs().setScale(15, RoundingMode.CEILING)  + ") < epsilon (" + this.epsilon.setScale(10, RoundingMode.CEILING) + ")? " + done);
	  print((int) (this.in + this.out));
	 }
}
