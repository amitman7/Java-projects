
import java.util.Scanner;

public class Task4a {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans = true;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int p = 2;
        while (p*p <=n & ans) {
        	if (n%p==0) {
        		ans = false;
        	}
        	else {
        		p= p+1;	
        	}
        }


        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
		scanner.close();
    }
}