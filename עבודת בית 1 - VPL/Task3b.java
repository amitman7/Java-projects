
import java.util.Scanner;


public class Task3b {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int k = scanner.nextInt();	
        if (n<31) {//if "ans" isn't bigger then the MAX_VALUE
        	if (n==0)
            	ans = 1;
            else 
            	ans = 2;
            	
            for (int i=1;i<n;i=i+1) {
            	
            	ans = 2*ans;
            ans = ans % k;}	}
        
        if (n>30) {//if "ans" isn't bigger then the MAX_VALUE
        	int maxx= 1073741824;//the max 2 power n can still be int
        	ans = maxx%k;
        	int x=n/30;//the times you should power the maxx
        	int y=n%30;// the rest of the division of whole numbers
        	int ans2=2;// the times you should power "2" with y 
        	if (y==0) {
        		ans=1;
        	}
        		for (int i=1;i<y;i=i+1) {
        			ans2=ans2 *2;
        		}
        	for (int i=1;i<x;i=i+1) {
        		ans = ans * (maxx%k); 
        		
        	}
        	ans= ((ans) *(ans2%k))%k;
        	
        }
            
        
        		  
        		 
        	 
        	 
         
        
       	

        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
		scanner.close();
} 
}