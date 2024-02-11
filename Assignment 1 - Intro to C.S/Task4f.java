
import java.util.Scanner;

public class Task4f {

    public static void main(String[] args) {
        
		Scanner scanner = new Scanner(System.in);
        boolean ans = true;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int s = scanner.nextInt();
        int d = scanner.nextInt();
        int k = scanner.nextInt();
        int bpowd= 1;//b power d 
        int x = 1;//the options till s-1
        for (int i=0; (i<=k & ans== true) ;i=i+1) {
        	 int b = (int) ((Math.random()*((n-1)-2+1)))+2;
        	 
             for (int z=0;z<d;z=z+1) {
             	bpowd= ((b*bpowd)%n);       	
             }
            
             	for (int j=0;(j<=s-1) & (x !=n-1);j=j+1) {
                 	x= ((x*bpowd) %n);
             }
             
        
        }
        if ((bpowd !=1) & (x !=n-1)){
            ans=false;	
    }
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
		scanner.close();
    }
}