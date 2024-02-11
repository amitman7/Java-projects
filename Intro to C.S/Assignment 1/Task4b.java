
import java.util.Scanner;

public class Task4b {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;
        

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int num = 2;
        while (num <= n) {
        	boolean isPrime = true;
        	int p = 2;
        	while (p*p <= num & isPrime) {
        		if (num%p == 0) {
        			isPrime = false;
        		}
        		p = p + 1;
        	}
        	if(isPrime) {
        		ans = ans +1;
        	}
        	num = num + 1;



        }
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);    
		scanner.close();    
    }
}