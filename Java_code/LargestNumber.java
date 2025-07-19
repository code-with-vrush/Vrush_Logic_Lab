import  java.util.*;

public class LargestNumber {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enetr your 3 number here...\n");
        System.out.print("Enter your 1st Number:");
        int a = sc.nextInt();
        System.out.print("Enter your 2nd Number:");
        int b = sc.nextInt();
        System.out.print("Enter your 3rd Number:");
        int c = sc.nextInt();

        System.out.print("\nyour Enterd are\nyour 1st number is:"+a+"\n" +"your 2nd number is:"+b+"\n"+"your 3rd number is:"+c);

        System.out.println("\nYour largest number from this 3 number is:"+((a>b && a>c)?a:(b>a && b>c)?b:c)+"\n");



        
    }
}
