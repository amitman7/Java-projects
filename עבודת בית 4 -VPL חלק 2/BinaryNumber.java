/*
I, <Amit Man> (<206961120>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.Iterator;

public class BinaryNumber implements Comparable<BinaryNumber>{
    
    private static final BinaryNumber ZERO = new BinaryNumber(0);
    private static final BinaryNumber ONE  = new BinaryNumber(1);
    private BitList bits;

    // Copy constructor
    //Do not chainge this constructor
    public BinaryNumber(BinaryNumber number) {
        bits = new BitList(number.bits);
    }

    //Do not chainge this constructor
    private BinaryNumber(int i) {
        bits = new BitList();
        bits.addFirst(Bit.ZERO);
        if (i == 1)
            bits.addFirst(Bit.ONE);
        else if (i != 0)
            throw new IllegalArgumentException("This Constructor may only get either zero or one.");
    }

    //Do not chainge this method
    public int length() {
        return bits.size();
    }

    //Do not change this method
    public boolean isLegal() {
        return bits.isNumber() & bits.isReduced();
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.1 ================================================
    public BinaryNumber(char c) {
    	if (c <'0' | c > '9') {
    		throw new IllegalArgumentException(c + "is not valid input");
    	}
    	bits = new BitList();
    	int cValue = c - '0';// value of char c.
    	while (cValue > 0) {
    		int digit = cValue % 2;
    		bits.addLast(new Bit(digit));
    		cValue = cValue / 2;
    	}
    	
    	bits.addLast(Bit.ZERO);
    }
    

  //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.2 ================================================
    public String toString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
       
       String bitLiString = bits.toString();
       String BinNumString = bitLiString.substring(1, bitLiString.length() -1);
       
       return BinNumString;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.3 ================================================
    public boolean equals(Object other) {
    	 Boolean ans = true;
    	 String firstBN = bits.toString();
    	 firstBN = firstBN.substring(1, firstBN.length() -1);
    	 String secondtBN =other.toString();
    	 if (isLegal() == false || firstBN.equals(secondtBN) == false ) {
    		 ans = false;
    	 }
    	
    	 return ans;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.4 ================================================
    public BinaryNumber add(BinaryNumber addMe) {
    	 if (!addMe.bits.isNumber()) { 
             throw new RuntimeException("I am illegal.");
         }
    	//building empty BinaryNumber that will hold the two BinaryNumber together.
    	 BinaryNumber bnAdd = new BinaryNumber('0');
    	 bnAdd.bits.removeLast();
    	
    	//checking which length is bigger and doing padding.
    	if (length() < addMe.length()) {
    		bits.padding(addMe.length());
    	}
    	if (length() > addMe.length()) {
    		addMe.bits.padding(length());	
    	}
    	 // building iterator
    	 BitList first = new BitList();// the first will hold the first BinaryNumber
    	 Iterator<Bit> iter = bits.iterator() ;// iterator for the first BinaryNumber
    	 Iterator<Bit> iterAddMe = addMe.bits.iterator() ;//iterator the second BinaryNumber
    	
    	 Bit sum = Bit.ZERO;
    	 Bit carry = Bit.ZERO;
    	 while(iter.hasNext() ) {// the loop
    		 Bit firstBit = iter.next();// the bit from the first BinaryNumber that the iterator will go over
    		 Bit secondBit = iterAddMe.next();// the bit from the second BinaryNumber that the iterator will go over
    		 
    		 sum = Bit.fullAdderSum(firstBit,secondBit,carry);
    		 carry = Bit.fullAdderCarry(firstBit,secondBit,carry);
    		 first.addLast(sum); 
    		 
    	 }
    	 // checking if the "Add" made Positive or negative number.
    	Bit lastAddMe = addMe.bits.getLast();// the last bit of addMe to see if its Positive or negative. 
     	Bit lastFirst = bits.getLast();// the last bit of first to see if its Positive or negative. 
    	if (lastFirst.toInt()  == 0 & lastAddMe.toInt() == 0) {
    		first.addLast(Bit.ZERO); 
    	}
    	if (lastFirst.toInt()  == 1 & lastAddMe.toInt() == 1) {
    		first.addLast(Bit.ONE);
    	}
    	
    	
    	//make the answer minimal
    	first.reduce();
    	this.bits.reduce();
    	addMe.bits.reduce();
    	bnAdd.bits = first;
    	
    
    	return bnAdd;
    	}

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.5 ================================================
    public BinaryNumber negate() {
    	if (bits.isNumber() == false) {
    		throw new RuntimeException("I am illegal.");
    	}
    	
    	 BinaryNumber bnNeg = new BinaryNumber('0');
    	 bnNeg.bits.removeLast();
    	 
    	 BitList comp =  bits.complement();
    	 bnNeg.bits = comp;
    	
    	
    	 return bnNeg.add(ONE);
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.6 ================================================
    public BinaryNumber subtract(BinaryNumber subtractMe) {
    	if (!subtractMe.bits.isNumber()) { 
            throw new RuntimeException("I am illegal.");
         }
    	//building empty BinaryNumber that will hold the two BinaryNumbers together.
    	BinaryNumber subtract = new BinaryNumber('0');
    	subtract.bits.removeLast();
    	
    	int firstValue = this.bits.getLast().toInt(); 
    	int secondValue = subtractMe.bits.getLast().toInt();
    	
        //if the first number is positive and the second is negative or both of the number are negative.
        if (firstValue == 0 & secondValue == 1 | firstValue == 1 & secondValue == 1 ) {
    	    subtract = this.add(subtractMe.negate()); 
        }
        //if the first number is negative the second is positive.
        if (firstValue == 1 & secondValue == 0)  {
    	    subtract = (this.negate().add(subtractMe)).negate(); 
        }
        // if both of the numbers positive.
        else if (firstValue == 0 & secondValue == 0){
    	    	 subtract = this.add(subtractMe.negate()); 
    	    	 if (subtract.length() > subtractMe.negate().length() & subtract.length() > this.length()) {
    	    		subtract.bits.removeLast();
    	    	 }
    	     subtract.bits.reduce();
        }
       
        return subtract;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.7 ================================================
    public int signum() {
    	int ans;
        if (bits.getLast().toInt() == 1) {
        	ans = -1;
        }
        
        else {
        	if (bits.size() > 1) {
        		ans = 1;
        	}
        	else {
        		ans = 0;
        	}
        }
        return ans;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.8 ================================================
    public int compareTo(BinaryNumber other) {
    	if (other.bits.isNumber() == false) {
    		throw new IllegalArgumentException("i am illgeal");
    	}
    	other.bits.reduce();
    	BinaryNumber compare = this.subtract(other);
    	
    	return compare.signum();
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.9 ================================================
    public int toInt() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        Iterator<Bit> iter;
        int sum = 0;
        int multiplyBy2 = 1;
        int isMinus = 1; // variable to check if the BinaryNumber is positive or negative.
        int multiplyCount = 0;// the variable is to check if we passed the int max value.
        
        // checking isMinus? 
        if (bits.getLast().toInt() == 1) {
        	BinaryNumber negate = this.negate();
        	isMinus = -1;
        	iter = negate.bits.iterator() ;
        }
        else {
        	iter = this.bits.iterator() ;
        	 
        }
        // the loop 
    	while (iter.hasNext()) {
    		sum = sum + (multiplyBy2 * iter.next().toInt());
    		multiplyBy2 = multiplyBy2 *2;
    		multiplyCount = multiplyCount +1;
    		
    		// if we passed the max value	
    		if (multiplyCount >30) {
    			throw new RuntimeException("the number is either bigger or smaller to be int");
    		}
    	
    	}
    	return sum * isMinus;
    	
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.10 ================================================
    // Do not change this method
    public BinaryNumber multiply(BinaryNumber multiplyMe) {
    	
    	if(this.isLegal() == false | multiplyMe.isLegal() == false ) {
    		throw new IllegalArgumentException("one of the BinaryNumber is not legal");
    	}
    	
    	BinaryNumber res = new BinaryNumber('0');// initial BinaryNumber.
    	
    	//if non of the Binary Numbers value is 0.
    	if ((this.compareTo(ZERO) != 0) & (multiplyMe.compareTo(ZERO) != 0)){
    		
    		// if multiplyMe is positive.
    		if (multiplyMe.signum() == 1) {
    			res = multiplyPositive(multiplyMe);
            }
    		
    		// if multiplyMe is negative.
    		else {
                multiplyMe = multiplyMe.negate();
                res = multiplyPositive(multiplyMe);
                
             // if both of the Binary Numbers are negative.
                if (this.signum() == -1) {
                	res = res.negate();
                }	
            }
    		
    	
         }
    	return res;
    }
    
    // using long multiplication and its Binary quality.
    private BinaryNumber multiplyPositive(BinaryNumber multiplyMe) {
    	BinaryNumber res = new BinaryNumber('0');
    	
    	BinaryNumber toAdd = this; // initializing.  
    	Iterator<Bit> iter = multiplyMe.bits.iterator();
    	int timesToMulti = 0;
    	
    	// moving over multiplyMe and adding to "res" when the index value is one.
    	while (iter.hasNext()) {
    		if (iter.next().toInt() == 0){ // when the next bit value is 0, the multiplication value is 0.
    			timesToMulti = timesToMulti +1; 
    		}
    		
    		else {
    			int multiplyCount=0;
    			while (timesToMulti > multiplyCount)  { // using loop to multiplyBy2 "toAdd" "timesToMulti" times.
    				toAdd = toAdd.multiplyBy2();
    				multiplyCount=multiplyCount+1;
    			}
    			timesToMulti = 1;
    			res = res.add(toAdd);
    		}
    		    		
    	}
    	
    	res.bits.reduce();
    	return res;
    }
    
    	
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.11 ================================================
    // Do not change this method
    public BinaryNumber divide(BinaryNumber divisor) {
    	// Do not remove or change the next two lines
    	if (divisor.equals(ZERO)) { // Do not change this line
            throw new RuntimeException("Cannot divide by zero."); // Do not change this line
    	}
    	if (this.isLegal() == false | divisor.isLegal() == false ) {
    		throw new IllegalArgumentException("one of the BinaryNumber is not legal");
    	}
    	BinaryNumber res = new BinaryNumber('0');// initial BinaryNumber.
    		if (divisor.equals(ONE)) {
    			res =this;
    		}
    	
    		else {
        		// checking if the numbers are positive or negative
        		if (divisor.signum() == 1 & this.signum() == 1) {
        			res = dividePositive(divisor);
                }
        		if (divisor.signum() == -1 & this.signum() == 1) {
        			divisor = divisor.negate();
        			res = dividePositive(divisor);
        			res = res.negate();
        		}
        		if (divisor.signum() == 1 & this.signum() == -1) {
        			
        			res = this.negate().dividePositive(divisor);
        			res = res.negate();
        		}
        		if (divisor.signum() == -1 & this.signum() == -1) {
        			divisor = divisor.negate();
        			res = this.negate().dividePositive(divisor);
                }
            }
        		
    			return res ;
    			
    		}
    	
    
    private BinaryNumber dividePositive(BinaryNumber divisor) {
    		BinaryNumber divider = this.divideBy2();
    		BinaryNumber res = new BinaryNumber('0');
    		
    		// base case
    		if (divider.compareTo(divisor) == -1) {
    			res = res.add(ZERO);
    		}
    		// the recursion - using "multiplyBy2".  
    		else {
    			res = res.add(divider.dividePositive(divisor).multiplyBy2());
    		}
    		
    		
    		// check if there is carry.
    		BinaryNumber carryCheck = res.add(ONE).multiplyBy2();
    		if ((carryCheck.compareTo(this) == 0) | (carryCheck.compareTo(this) == -1)){
    			res = res.add(ONE); 
    			if (this.compareTo(res.multiply(divisor)) == -1) {
        			res = res.subtract(ONE);
        		}
    			else {
    				res = res.add(ZERO);	
    			}
    		}
    		
    	res.bits.reduce();
    	return res;
    }
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.12 ================================================
    public BinaryNumber(String s) {
    
    	 BinaryNumber sum = new BinaryNumber(ZERO);
    	 BinaryNumber power = new BinaryNumber(ONE);
    	 BinaryNumber nine = new BinaryNumber('9');
    	 BinaryNumber decimal = nine.add(ONE);
    	 bits= new BitList();
    	  
    	 
    	 if (s.charAt(0) == '-') {
    		 for (int i =1; i < s.length()  ; i=i+1) {
    	    	   BinaryNumber chAsBinary = new BinaryNumber(s.charAt(s.length() -i));
    	    	   chAsBinary=chAsBinary.multiply(power);
    	    	   sum=sum.add(chAsBinary);
    	    	   power = power.multiply(decimal);
    		 }
    		 bits = sum.negate().bits;
    	 }
    	 else {
    		 for (int i =1; i < s.length() +1 ; i=i+1) {
    			 BinaryNumber chAsBinary = new BinaryNumber(s.charAt(s.length() -i));
    			 chAsBinary=chAsBinary.multiply(power);
    			 sum=sum.add(chAsBinary);
    			 power = power.multiply(decimal);
    			   
    		 }
    		 bits = sum.bits;
    	 } 
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.13 ================================================
    public String toIntString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        
        String deciS = ""; // variable which will hold the "lbsf" of the decimal number . 
        String res = ""; // the "deciS" after make it "mbsf" of the decimal number.
        
        // when the number is negative.
        if (this.signum() == -1) {
        	BitList negate = (this.negate()).bits;
        	String sNegate = negate.toString();
        	sNegate= sNegate.substring(2, sNegate.length() -1);
        	  
        	
            String  sRepla= ""; //variable which will hold the "lsbf" of the binary number 
            
            // first loop to change the order to: "lsbf".
            for (int i=1;i<sNegate.length() +1;i=i+1) {
            	sRepla = sRepla + sNegate.charAt(sNegate.length() -i);
            }
            // using the function from work "3" to make it decimal.
            deciS = binary2Decimal(sRepla);
            
            // second loop to change the order back to: "msbf"
            for (int i=1;i<deciS.length() +1 ;i=i+1) {
            	 res =  res + deciS.charAt(deciS.length() -i) ;
            }
            
            res = '-' + res;
        }
        // when the number is positive.
        else {
        	BitList pos = this.bits;
        	String sPos = pos.toString();
        	sPos = sPos.substring(2, sPos.length() -1);
            String sRepla = "";//variable which will hold the "lsbf" of the binary number 
            
            // first loop to change the order to:"lsbf"
            for (int i=1;i<sPos.length() +1;i=i+1) {
            	sRepla = sRepla + sPos.charAt(sPos.length() -i);
            }
            // second loop to change the order back to: "msbf".
            deciS = binary2Decimal(sRepla);
            for (int i=1;i<deciS.length() +1;i=i+1) {
            	res =  res + deciS.charAt(deciS.length() -i) ;
             }
        }
     
        return res;
    }


    // using work 3 to make binary String to decimal.
    
    public static int toInt(char c) {
		return "0123456789".indexOf(c);
		}
	
	
	//Task 3.1
    public static boolean legalNumericString(String s, int b) {
        boolean ans = true;
        int n = s.length();
        if ( (b>10) | (b<2) )  {
        	throw new IllegalArgumentException("the string is not legal Numeric String");
        }
        for (int i=0; i<s.length(); i=i+1){
        	if (s.charAt(i)>57 | s.charAt(i)<48)
        	throw new IllegalArgumentException("the string is not legal Numeric String");	
        }
        if ((s.equals(null)) || (s.equals("")) || s.charAt(0) == '-' | (s.length() > 1 & (toInt(s.charAt(n-1)) == 0))) {
        	ans = false;
        }
        for (int i=0; i<n & ans == true ;i=i+1) {
        	int x = toInt(s.charAt(i));//varible to move over the value of each char at the string.
        	if(x >= b) {
        		ans = false;
        	}
        }
        return ans;
    }
    
    
    //Task 3.2
    //using recursion method by increase the first char by 1 and then concatenate the whole String 
    public static String decimalIncrement(String s) {
        String ans = "";
        
        if (legalNumericString(s,10) == false) {
        	throw new IllegalArgumentException("the string is not legal Numeric String in 10 base");
        }
        ans= decimalIncrement (s,"");
        return ans;
    }

    // aid function 
    public static String decimalIncrement(String s,String ans) {
    	int x = toInt(s.charAt(0));// the value of the first char
    	//Treat the base cases
        if (x < 9 ) {
        	x=x+1;
        	String st = ans+ x;// making the first char String again after the increase.
        	ans = st + s.substring(1, s.length());
        } 
        else {
        	// dealing with the situation that the first char value is 9 (and the chars after it).
        	x=0;
        	if (s.length() == 1) {
        		ans = ans + x + '1';// if the whole String made by '9'.
        	}
        	else {
        		ans = decimalIncrement(s.substring(1, s.length()), ans + x);
        	}
        	 
        }
        
        return ans;	
    }
      
    
    
    //Task 3.3
    //using recursion method by powering every by 2 and then concatenate the whole String 
    public static String decimalDouble(String s) {
        String ans = "";
        if (legalNumericString(s,10) == false) {
        	throw new IllegalArgumentException("the string is not legal Numeric String in 10 base");
        }
        
        ans = decimalDouble(s,"",0);
        
        return ans;
    }
    
    // aid function 
    public static String decimalDouble(String s,String ans,int left) {
    	//Treat the base cases
    	if(s.length() == 0) {
    		if (left == 1) {//dealing with the situation that the last char > 9.
    			ans = ans + "1";
    		}
    		else {
    			ans = ans + "";
    		}
    	}
    	
    	else {
    		int firstCh = toInt(s.charAt(0)) * 2; // name for the value of the first char.
    		if (left == 1) {
    			firstCh = firstCh + 1;
        	}
    		
    			if (firstCh > 9) {// dealing with the situation that char > 9.
    				ans = decimalDouble(s.substring(1, s.length()),ans + (firstCh -10),1);
    			}
    		
    			else {
    				ans = decimalDouble(s.substring(1, s.length()),ans + firstCh ,0);
    		
    			}
    		}
    	
    	return ans;
    		
    }
    	
   
	
    
    
    
    //Task 3.4
    //using recursion method of the two previous function. 
    public static String binary2Decimal(String s) {
        String ans = "0";
        if (legalNumericString(s,2) == false) {
        	throw new IllegalArgumentException("the string is not legal Numeric binary String");
        }
        
        ans = binary2Decimal(s,ans);
        
        return ans;
    }
    
    
    
    
    //aid function
    public static String binary2Decimal(String s,String ans) {
    	//Treat the base case.
    	if(s.length() == 0) {
    		ans = ans + "";
    	}
    	
    	
    	else {
    		int lastCh = toInt(s.charAt(s.length() -1));// the value of the last char at the binary string, using its reduction behavior. 
    		String sub = s.substring(0,s.length() -1);// the sub string of the recursion.
    		
    		// if the last char value is 0, then make it Decimal by "decimalDouble".
    		if (lastCh == 0) {
    			ans = binary2Decimal(sub,decimalDouble(ans));	
    		}
    		
    		//if the last char value is 1, then make it Decimal by "decimalDouble", and then add 1 by "decimalIncrement".
    		if (lastCh == 1) { 
    			ans = binary2Decimal(sub,decimalIncrement(decimalDouble(ans)));
    		}
    		
    
    	}
    	
    	return ans;

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // Returns this * 2
    public BinaryNumber multiplyBy2() {
        BinaryNumber output = new BinaryNumber(this);
        output.bits.shiftLeft();
        output.bits.reduce();
        return output;
    }

    // Returens this / 2;
    public BinaryNumber divideBy2() {
        BinaryNumber output = new BinaryNumber(this);
        if (!equals(ZERO)) {
            if (signum() == -1) {
                output.negate();
                output.bits.shiftRight();
                output.negate();
            } else output.bits.shiftRight();
        }
        return output;
    }

}
