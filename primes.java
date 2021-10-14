import java.util.Arrays;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
	   
		
		
		
		//change to 2000000000
        final int MAX = 20000000;
        boolean primes[];
        primes = new boolean[MAX];
        
        long start = System.currentTimeMillis(); 
        compute(primes);
        System.out.println(System.currentTimeMillis() - start); 
       // System.out.println((Arrays.toString(primes))); //for testing only 
        
        
        
        display(primes);
	}

	/* Use the algorithm described in the lab description so that the array, nums, contains all the
	 * valid primes. All others should be false.
	 */
	public static void compute(boolean[] nums) {
		//at the end- locations that are set to TRUE are the primes 
		

		
		// assume each element is a prime- set everything to true
		
		//added "=" and changed i = 0
		for(int i = 1; i < nums.length; i++) {
			
			nums[i] = true; 
		}
		
		
		//set up the loop to check for multiples of 2s and set them to false 
		// "multiple" deals w/ indexes NOT booleans themselves 
		
		int multiple = 2; 
		
		for(int i = multiple; i < nums.length; i++) {
			
			if(nums[i-1]) {
				
				nums[i-1] = true; 
				
			}
			
			
			//all multiples of prime number being crossed off
			for(int j = i*2; j < nums.length; j += i) {
				
				nums[j-1] = false; 
				
			}
			
		}
		
		
		
		
		}
		
		
		
		
		
		
		
	  // old method: for(multiple = 2; multiple < nums.length/2; multiple ++) {
	
		
		
		/*for(multiple = 2; multiple * multiple <= nums.length; multiple ++) { 
				
			if(nums[multiple] == true) {
			
			for(int i = multiple*multiple; i <= nums.length; i++) {
			

			
			}
				
			}
			
		
			}
		}
			
		*/
		
		/*for(int i = multiple; i <= nums.length; i++) {
			
			int num = i + 1; 
			
			if(num % multiple == 0) {
				
				nums[i-1] = false; 
				
			}
			
			for(int h = i * i; h <= nums.length; h += i) {
				
				int num2 = h - 1; 
				
				if(num2 % multiple == 0) {
					nums[h-1] = false; 
				
				}
			}
		}
	}
	*/

			
		
			
				

			
		
			
		
		
		
		

	
	  
	
	
		/*
		for(multiple = 2; multiple * multiple < nums.length; multiple++) {
			
			if(multiple % 2 == 0) {
				
				for(int i = multiple * multiple; i < nums.length; i +=multiple) {
					
					nums[i] = false; 
					
				}
				
			}
			
		}
	}
		*/	
		
	
		
	/*
	 * Complete the method below so that it prints all the prime numbers in the array 
	 * Assume that the compute method has been called that sets the array to contain the prime numbers as
	 * described in the lab document.
	 */
	public static void display(boolean[] nums) {
		//display the values of the prime numbers marked in nums
	
	}
	
}
