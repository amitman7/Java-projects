
class Change{
	
	//Task 2.1
	// Assists by aid function that included index
    public static boolean change(int [] coins, int n){
        boolean ans = false;
       
        ans = change (coins,n,0);
        return ans;
    }
    // using recursion by decreasing the value n by checking every index of the array.
    public static boolean change(int [] coins, int n, int index) {
    	boolean ans =false;
    	//Treat the base cases
    	if (n==0) {
    		ans = true;	
    	}
    	else if (index > coins.length-1 | n<0)  {
    		ans = false;
    	}
    	else {
    		
    		ans = change (coins,n-coins[index],index)// decreasing the value of the index and staying in the index till the base case.
    				|| change (coins,n,index+1);// moving on the indexes till the base case.
    	}
    	
    	return ans;
    	}
    
    
    
    
    //Task 2.2
    // Assists by aid function that included index and count of uses. 
    //using the same recursion method as the first function with limitation of number of useses  
    public static boolean changeLimited(int[] coins, int n, int numOfCoinsToUse){
        boolean ans = false;
        
        //Treat the base case
        if (change(coins, n) == false){
        	ans = false;
        }
        else {
        	ans = changeLimited(coins, n,numOfCoinsToUse,0,0);
        	
        }
        return ans;
    }
    // the aid function
    public static boolean changeLimited(int[] coins, int n, int numOfCoinsToUse,int index,int count){
    	 boolean ans =false;
    	//Treat the base cases
    	    if (n==0 & count <= numOfCoinsToUse) {
    	    ans = true;	
    	    }
    	    else if (index > coins.length-1 | n<0)  {
    	    	ans = false;
    	    }
    	    else {
    	    	ans = changeLimited (coins,n-coins[index],numOfCoinsToUse,index,count +1) || changeLimited (coins,n,numOfCoinsToUse,index+1,count);
    	    }
    	    	
    	    return ans;
    	    }
    
    
    
    

    //Task 2.3
    //using the same recursion method as the other functions
    //its goal to print one option of answer with the limit. 
    public static void printChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        
    	boolean ans;
    	ans = printChangeLimited( coins, n, numOfCoinsToUse,0,0,"");
    		
    }
    // the aid function
    public static boolean printChangeLimited(int[] coins, int n, int numOfCoinsToUse,int index,int count,String str){
   	 boolean ans =false;
   //Treat the base cases
   	    if (n==0 & count <= numOfCoinsToUse) {
   	    ans = true;	
   	    String str1 = str.substring(0, str.length() -1);
   	    System.out.println(str1);
   	    }
   	    else if (index > coins.length-1 | n<0)  {
   	    	ans = false;
   	    }
   	    else {
   	    	ans = printChangeLimited (coins,n-coins[index],numOfCoinsToUse,index,count +1,str +  coins[index] + ",") || printChangeLimited (coins,n,numOfCoinsToUse,index+1,count,str);
   	    }
   	    	
   	    return ans;
   	    }
    
    
    
    
    
    //Task 2.4
    //using the same recursion method as the other functions.
    //its goal to find how many answers there are with the limit. 
    public static int countChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        int ans = 0;
      
        //Treat the base case
        if(changeLimited(coins, n, numOfCoinsToUse) == false) {
        	ans =0;	
        }
        else {
        	ans = countChangeLimited(coins, n,numOfCoinsToUse, 0,0);
        	
        }
       
 
        return ans;
    }
    //the aid function
    public static int countChangeLimited(int[] coins, int n, int numOfCoinsToUse,int index,int count){
        int ans = 0;
        
        //Treat the base cases
        if (n==0 & count <= numOfCoinsToUse) {
        	ans = ans+1;}
   
   	    else if (index > coins.length-1 | n<0)  {
   	    	ans = ans +0;
   	    }
   	    else {
   	    	ans = countChangeLimited (coins,n-coins[index],numOfCoinsToUse,index,count +1) + countChangeLimited (coins,n,numOfCoinsToUse,index+1,count);
   	    }
   	    	
   	    return ans;
   	    }
   
    
    
    
    //Task 2.5
    //using the same recursion method as the other functions.
    //its goal to print the whole answers with the limit. 
    public static void printAllChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        
    	int ans =0;
    	ans = printAllChangeLimited(coins, n, numOfCoinsToUse,0,0,"");
    	
    }
    
    //the aid function
    public static int printAllChangeLimited(int[] coins, int n, int numOfCoinsToUse,int index,int count,String str){
    	 int ans = 0;
    	//Treat the base cases
    	 if (n==0 & count <= numOfCoinsToUse) {
    		 ans = ans+1;
    		 String str1 = str.substring(0, str.length() -1);
    		 System.out.println(str1);
    	    	}
    
    	 else if (index > coins.length-1 | n<0)  {
    	     ans = ans +0;
    	    }
    	 
    	 else {
    	     ans = printAllChangeLimited (coins,n-coins[index],numOfCoinsToUse,index,count +1,str +  coins[index] + ",") + printAllChangeLimited (coins,n,numOfCoinsToUse,index+1,count,str);
    	    }
    	    	
    	   return ans;
    }
    
    	   
    	   
}

