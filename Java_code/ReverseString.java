package Vrush_Logic_Lab.Java_code;

import java.util.*;

import javax.print.DocFlavor.STRING;

public class ReverseString {
    
    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);

         System.out.print("Enter your string here: ");
         String str = sc.nextLine();
         char ch;
         String rev = "";
         for(int i=str.length()-1;i>=0;i--)
         {
            ch = str.charAt(i);

            rev = rev + ch;
         }

         System.out.println("\nYour entered string is: "+str);
         System.out.println("Your reversed string is: "+rev);
    }
}
