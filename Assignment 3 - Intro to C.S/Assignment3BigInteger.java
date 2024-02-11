
import java.math.BigInteger;
import java.util.Random;

class Assignment3BigInteger{
   public static void main(String[] args) {
	   BigInteger n =  new BigInteger("101");
	   System.out.println(randomPrime(1));
	  
   }


    public static BigInteger sumSmaller(BigInteger n){
        BigInteger sum =  new BigInteger("0");
        BigInteger cruNum = new BigInteger("1");
        final BigInteger bigOne = BigInteger.ONE;
        
        while (cruNum.compareTo(n) < 0)	{
        	sum = sum.add(cruNum);
        	cruNum = cruNum.add(bigOne);
        }
        return sum;
    }

    public static void printRandoms(int n){
        Random x = new Random();
        for (int i=0;i<n;i=i+1) {
        	 System.out.println(x.nextInt());

        }
        	
        }
    

    public static  boolean isPrime(BigInteger n){
        boolean ans= true;
        //Task 1.3
        BigInteger x =  new BigInteger("2");//starting varible 
        final BigInteger zero = BigInteger.ZERO;
        final BigInteger one = BigInteger.ONE;
        if(n.equals(zero) | n.equals(one)) {
        	ans = false;
        }
        while (x.multiply(x).compareTo(n) <= 0 & ans == true) {
        	
        	if(n.remainder(x).equals(zero)) {
        		ans = false;
        	}
        	else {
        		x = x.add(one);
        	}
        }
	
        return ans;
    }

    public static BigInteger randomPrime(int n){
    	//Task 1.4
        Random rnd = new Random();
        BigInteger randBig = new BigInteger("2");      
        boolean ans =false;
        
        while (ans== false) {
        	BigInteger x = new BigInteger (n,  rnd);
        	if (isPrime(x)) {
        		randBig = x;
        		ans = true;
        			}
        		}
        
       return randBig; 
    }
    
}