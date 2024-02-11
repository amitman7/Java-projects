

public class BitVector {
    private Bit[] bits;
    
  //Task 4.4
    public BitVector(String s) {
    	if (NumericalString.legalNumericString(s,2) == false) {
    		throw new IllegalArgumentException("the string is not legal BitVector String");
    	}
    	else {
    		bits = new Bit [s.length()];
    		for(int i=0;i<s.length();i=i+1) {
    			if (toInt(s.charAt(i)) == 1) {
    				bits[i] = new Bit(true);
    			}
    		
    			else {
    				bits[i] = new Bit(false);
    			}
    		
    		}
    		
    	
        
    }
    }
    

    
    public static int toInt(char c) {
    	return "0123456789".indexOf(c);
    	}
    
   
  //Task 4.5
    public String toString() {
    	 String ans = "";
    	 for (int i=0;i<bits.length;i=i+1) {
    		
    			 ans =  ans + bits[i].toString();
    		
    	 }
    	 String x = NumericalString.binary2Decimal(ans);
    	 String y = "";
    	 for (int i=0;i<x.length();i=i+1) {
    		 y = y+ x.charAt(x.length()-1 -i)  ;
    		 
    	 }
        
      
       
        
        return  y ;
    }

}
