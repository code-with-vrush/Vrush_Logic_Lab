import java.util.Scanner;

public class Palindrome {
    public String  str_rev(String str_r){

         String str = str_r;
         char ch;
         String rev = "";
         for(int i=str.length()-1;i>=0;i--)
         {
            ch = str.charAt(i);

            rev = rev + ch;

         }
         return rev;

         
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        Palindrome obj1 = new Palindrome();

         System.out.print("\nEnter your string here: ");
         String str = sc.nextLine();
         String str_2 = obj1.str_rev(str);

         System.out.println(str+" "+str_2);

         System.out.println("\nYour entered string '"+str+(str.equals(str_2)?"' is Palidrom\n":"' is Not palidrom\n"));
       
    }
}
