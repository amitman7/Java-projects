
import java.util.Scanner;

public class Task4e {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans1 = true;
        int ans2 = 0;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int b = scanner.nextInt();
        int s = scanner.nextInt();
        int d = scanner.nextInt();
        int bpowd= 1;//b power d  
        for (int i=0;i<d;i=i+1) {
        	bpowd= ((b*bpowd)%n);       	
        }
        int x = 1;//the options till s-1
        	for (int j=0;(j<=s-1) & (x !=n-1);j=j+1) {
            	x= ((x*bpowd) %n);
        }
        
        	
        		
        if ((bpowd !=1) & (x !=n-1)){
        	ans1= false;
        	ans2= b;}
        	
        else {
        	ans1=true;
    		ans2 =-1;			
        }
        		
        		
        		
        		
        		





        //---------------write your code ABOVE this line only!--------------
        System.out.println(ans1);
        System.out.println(ans2);
		scanner.close();
    }
}