//Check if number is even or odd
package Vrush_Logic_Lab.Java_code;

import java.util.*;

public class EvenOdd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Your number for chake your number is EVEN or ODD: ");
        int num = sc.nextInt();

        System.out.println("Your Ented number '"+num+"' is "+(num%2==0?"Eeven":"Odd"));

    }
}
