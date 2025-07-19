import java.util.*;
public class Table {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enetr your table that's you want: ");
        int num = sc.nextInt();
        
        System.out.println("here is you table for "+num);

        for(int i=1;i<=10;i++){System.out.println(num+" x "+i+" = "+num*i);}
    }
    
}
