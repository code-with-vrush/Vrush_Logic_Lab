//Swap 2 numbers using temp variable
package Vrush_Logic_Lab.Java_code;

import java.util.*;

public class SwapNumbers {

public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    
        System.out.print("\nEnter your number for A:");
        int a = sc.nextInt();
        System.out.print("\nEnter your number for B:");
        int b = sc.nextInt();


        System.out.println("-------------------------");
        System.out.println("|\tBefor Swap\t|");
        System.out.println("-------------------------");
        System.out.println("Your enterd A = "+a);
        System.out.println("Your enterd B = "+b);

        int tamp = b;
             b = a;

        System.out.println("\n-------------------------");
        System.out.println("|\tAfter Swap\t|");
        System.out.println("-------------------------");
        System.out.println("Your enterd A = "+tamp);
        System.out.println("Your enterd B = "+a);



}
    
}
