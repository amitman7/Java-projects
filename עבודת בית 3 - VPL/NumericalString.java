public class NumericalString {

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
    
 
	
}
   