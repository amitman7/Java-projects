
import java.util.Scanner;

public class Task4d {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans1 = 0;
        int ans2 = 0;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        ans2 = n-1;
        while(ans2%2==0) {
        	ans2 = (ans2/2);
        	ans1= ans1 +1;}
       

        //---------------write your code ABOVE this line only!--------------
        System.out.println(ans1);
        System.out.println(ans2);
		scanner.close();
    }
}