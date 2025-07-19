import java.util.*;

public class SumArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nHow many number you want to Enter:");
        int range = sc.nextInt();
        int[] arry = new int[range];
        int sum=0;


        for(int i=0;i<=range-1;i++)
        {
            System.out.print("Enter your number :");
            arry[i] = sc.nextInt();
            sum = sum + arry[i];
        }

        System.out.print("\nyour entered number's are: {");
        for(int i=0;i<=range-2;i++)
        {
            System.out.print(arry[i]+",");
            
        }
        System.out.print(arry[range-1]+"}"+"\nYour sum of this all number is: "+sum+"\n");

        
    }
    
}
