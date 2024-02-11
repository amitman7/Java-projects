
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        ans = (int) ((Math.random()*(b-a+1)))+a;

        //---------------write your code ABOVE this line only!--------------
        System.out.println(ans);
		scanner.close();
    }
}