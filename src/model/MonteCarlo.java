package model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Random;

public class MonteCarlo {
	/*
	 * random: base java random.
	 * pInCircle: points inside circle. 
	 * pOutCircle: points outside circle.
	 */
	private Random random;
	private BigInteger pInCircle;
	private BigInteger pOutCircle;
	
	/*Values for calculations:
	 *PNum: number of points to calculate.
	 *Epsilon: epsilon :).
	 */
	private static final Integer RADIOUS = 1;
	private BigInteger PNum;
	private BigDecimal epsilon;
	
	/*
	 * pi: pi final result.
	 */
	private BigDecimal pi;
	
//***************************** Builder & Tools *****************************
	
	//Builder
	public MonteCarlo(int exp, Long epsilon) {
		this.random = new Random();
		
		//init number of points as base 10 to the given power.
		this.PNum = BigInteger.valueOf(10);
		this.PNum = PNum.pow(exp);
		this.pOutCircle = BigInteger.valueOf(0);
		this.pInCircle = BigInteger.valueOf(0);
		this.epsilon = new BigDecimal(0.00001);
	}
	
	//Get next random
	private Long getRand() {	  
		  return random.nextLong();
	}
	
	//Get In circle
	private BigInteger getInCircle() {
		return this.pInCircle;
	}
	
	//Get Out circle
	private BigInteger getOutCircle() {
		return this.pOutCircle;
	}
	
	private int getError(BigDecimal currentPi) {
		  //currently calculated PI minus math.PI
		  BigDecimal diff = currentPi.subtract(BigDecimal.valueOf(Math.PI)).abs();
		  int error = diff.compareTo(this.epsilon);
		  // error = 1: diff > epsilon
		  // error = 0: diff = epsilon
		  // error = -1: diff < epsilon
		  return error;
		 }
	
//***************************** Calculations *****************************
	
	
	// calculate PI
	private BigDecimal getFinalPi() {
	 // multiply the result with 4 because the simulation was only in one quadrant.
	 BigDecimal piTmp = BigDecimal.valueOf(4.0 * (this.getInCircle().doubleValue() / (this.getInCircle().doubleValue() + this.getOutCircle().doubleValue())));
	 return piTmp;
	}
	
	// calculate PI for a certain Epsilon
	 public void calculatePi() {
	  // Boolean is used to determine whether the calculated value for PI is acceptable
	  Boolean done = false;
	  // compute until done is true
	  while (!done) {
	   double x = this.getRand();
	   double y = this.getRand();
	   if (x * x + y * y <= RADIOUS) {
		 //piInCircle++
	    this.pInCircle = pInCircle.add(BigInteger.valueOf(1));
	    } else {
	    	//piOutCircle++
	    	this.pOutCircle = pOutCircle.add(BigInteger.valueOf(1));
	   }
	   // calculate current PI
	   this.pi = this.getFinalPi();
	   // compare error with epsilon
	   int error = this.getError(this.pi);
	   // epsilon is greater than error
	   if (error == -1) {
	    // error is less than epsilon
	    done = true;
	   }
	   // exit if there cannot be determined a Pi with an error less than epsilon
	   if ((this.pInCircle.add(this.pOutCircle)).compareTo(BigInteger.valueOf(100000000))  > 0) {
	    break;
	   }
	  }
	   System.out.println("Error (" + this.pi.subtract(this.pi).abs().setScale(15, RoundingMode.CEILING)  + ") < epsilon (" + this.epsilon.setScale(10, RoundingMode.CEILING) + ")? " + done);
	  print((this.pInCircle.add(this.pOutCircle)));
	 }
	 
	 // print the results to the console
	 private void print(BigInteger n) {
	  System.out.println("Calculation with: " + n);
	  System.out.println("Points in: " + this.getInCircle() + ", points out: " + this.getOutCircle());
	  System.out.println("Pi calculated: " + this.getFinalPi() + ", In+out: " + (this.getOutCircle().add(this.getInCircle())));
	  System.out.println("Difference: Pi calculated (" + this.getFinalPi() + ") - Pi (" + this.pi.setScale(10, RoundingMode.CEILING)  + "): " + this.getFinalPi().subtract(this.getFinalPi()).abs().setScale(10, RoundingMode.CEILING));
	  System.out.println("--------------- " + "\n");
	 }
}
