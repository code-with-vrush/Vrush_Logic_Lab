import java.util.*;

public class Factorial {
    
 public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    System.out.print("\nEnter your number for factorial:");
    int num = sc.nextInt();
    int factorial = 1;


    System.out.print(num<0?"we cant't find factorial for Nagtive number":num==0?"you Enterd 0 , For zero(0) factorial  is 0 or 1":"here is factorial of your number is:");

    for(int i = 1;i<=num;i++){ factorial = factorial*i;}
    System.out.print((num >0 && num == 0)?"":factorial+"\n");

    
 }
    
}
