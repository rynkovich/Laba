package action;
import initialization.LongNum;
import initialization.Operations;
import java.io.*;
import java.util.Scanner;


public class WorkWithConsole {        

     public static int size(){
         Scanner scan = new Scanner(System.in);
         System.out.println("Enter the number of digits");
         int n = 1;
         try{
         n = scan.nextInt();
         }catch(Exception e){
        	 System.out.println("Error: " + e);
         }finally{
             return n;
         }
    }

     public static void print_long_num(LongNum long_num){
         if(long_num.get_sign())
             System.out.print("-");
         for(int i = 0; i < long_num.get_quantity(); i++)
             System.out.print(long_num.get_number(i));
         System.out.print('\n');
     }

     public static void start_dialog(){
         Scanner scan = new Scanner(System.in);
         LongNum ln1;
         LongNum ln2;

         System.out.println("Would you specify the number manually? \n 1 - Yes "
                 + "\n 0 - No");
         int parameter = 0;
         try{
             while(true){
             parameter = scan.nextInt();
             if((parameter != 0) && (parameter != 1))
                 System.out.println("You must correctly answer the question!");
             else
                 break;
             }
             if(parameter == 1){
                 System.out.println("Enter the first number ");
                 ln1 = create_long_num(parameter);
                 System.out.println("Enter the second number ");
                 ln2 = create_long_num(parameter);
                 operations_dialog(ln1, ln2);
             }
             else{
                 System.out.print("Generation of the first number. ");
                 ln1 = create_long_num(parameter);
                 System.out.print("Generation of the second number. ");
                 ln2 = create_long_num(parameter);
                 operations_dialog(ln1, ln2);
             }
         }catch(Exception e){
             System.out.println("Error: " + e);
         }
     }

     public static void operations_dialog(LongNum ln1, LongNum ln2){
         Scanner scan = new Scanner(System.in);
         LongNum result[];
         System.out.print("First number: ");
         print_long_num(ln1);
         System.out.print("Second number: ");
         print_long_num(ln2);
         System.out.println("Operations on numbers: \n 1 - Addition \n 2 - "
                 + "Subtract the second from the first \n 3 - Multiply \n 4 - "
                 + "Divide the first to second \n 5 - Specify the new number"
                 + "\n 6 - Exit \n What choose?");
         int parameter = 0;
         try{
             while(true){
             parameter = scan.nextInt();
             if((parameter != 1) && (parameter != 2)&& (parameter != 3)&& (parameter != 4)&& (parameter != 5)&& (parameter != 6))
                 System.out.println("You must correctly answer the question!");
             else
                 break;
             }
             switch(parameter){
                 case 1:
                     System.out.print("First number: ");
                     print_long_num(ln1);
                     System.out.print("Second number: ");
                     print_long_num(ln2);
                     System.out.print("Sum: ");
                     print_long_num(Operations.sum(ln1,ln2));
                     System.out.println("1 - Next ");
                     while(true){
                         parameter = scan.nextInt();
                         if(parameter != 1)
                             System.out.println("Enter one or not type anything!");
                         else{
                             operations_dialog(ln1, ln2);
                             break;
                         }
                     }
                     break;
                 case 2:
                     System.out.print("First number: ");
                     print_long_num(ln1);
                     System.out.print("Second number: ");
                     print_long_num(ln2);
                     System.out.print("The difference: ");
                     print_long_num(Operations.difference(ln1,ln2));
                     System.out.println("1 - Next");
                     while(true){
                         parameter = scan.nextInt();
                         if(parameter != 1)
                             System.out.println("Enter one or not type anything!");
                         else{
                             operations_dialog(ln1, ln2);
                             break;
                         }
                     }
                     break;
                 case 3:
                     System.out.print("First number: ");
                     print_long_num(ln1);
                     System.out.print("Second number: ");
                     print_long_num(ln2);
                     System.out.print("Product: ");
                     print_long_num(Operations.multiplication(ln1,ln2));
                     System.out.println("1 - Next ");
                     while(true){
                         parameter = scan.nextInt();
                         if(parameter != 1)
                             System.out.println("Enter one or not type anything!");
                         else{
                             operations_dialog(ln1, ln2);
                             break;
                         }
                     }
                     break;
                 case 4:
                     System.out.print("First number: ");
                     print_long_num(ln1);
                     System.out.print("Second number: ");
                     print_long_num(ln2);
                     System.out.println("Division with remainder:");
                     result = Operations.division(ln1,ln2);
                     System.out.println("Quotient: ");
                     print_long_num(result[0]);
                     System.out.println("Remainder: ");
                     print_long_num(result[1]);
                     System.out.println("1 - Next ");
                     while(true){
                         parameter = scan.nextInt();
                         if(parameter != 1)
                             System.out.println("Enter one or not type anything!");
                         else{
                             operations_dialog(ln1, ln2);
                             break;
                         }
                     }
                     break;
                 case 5:
                     start_dialog();
                     break;
                 case 6:
                	 System.out.println("Application quits...");
                	 break;
             }
         }catch(Exception e){
             System.out.println("Error: " + e);
         }finally{
         }
     }

     public static LongNum create_long_num(int parameter) throws Exception{
         if(parameter == 0){
             int quantity = size();
             int[] numbers = new int[quantity];
             boolean sign;

             if(Math.random() < 0.5)
                 sign = true;
             else
                 sign = false;

             while(true){
                 numbers[0] = (int)(Math.random()*10);
                 if(numbers[0] != 0)
                     break;
             }

             for(int i = 1; i < quantity; i++){
                 while(true){
                     numbers[i] = (int)(Math.random()*10);
                     if(numbers[i] != 10)
                         break;
                 }
             }
             return new LongNum(sign, numbers);
         }
         else{
             BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
             String line;
             char[] char_line;
             int quantity;
             int[] numbers;
             boolean sign = false;
             try{
                 line = bis.readLine();
                 quantity = line.length();
                 numbers = new int[quantity];
                 char_line = line.toCharArray();
                 if(char_line.length == 0){
                     System.out.println("The information entered does not match required."
                     		 + " Because the user "
                             + "can not perform even a simple action,"
                             + "the program will create uncomplicated long integer number");
                     return new LongNum();
                 }
                 int i;
                 int k = 0;
                 if(char_line[0] == '-'){
                     if(char_line.length == 1){
                         System.out.println("Permission is not a number. Because the user"
                                 + "can not perform even a simple action,"
                                 + "the program will create uncomplicated long integer number");
                         return new LongNum(quantity);
                     }
                     i = 1;
                     k = 1;
                     sign = true;
                     quantity--;
                 }
                 else
                     i = 0;
                 numbers = new int[quantity];
                 for( ; i < line.length(); i++){                     
                     if((int)(char_line[i] - '0') < 0 || (int)(char_line[i] - '0') >9){
                         System.out.println("Permission is not a number. Because the user "
                                 + "can not perform even a simple action,"
                                 + "the program will create uncomplicated long integer number");
                         return new LongNum(quantity);
                     }
                     numbers[i - k] = (int)(char_line[i] - '0');
                 }
                 
                 int zero_q = 0;
                 for(int j = 0; j < quantity; j++){
                     if(numbers[j] == 0)
                         zero_q++;
                     else
                         break;
                 }
                 if(zero_q == 0)
                     return new LongNum(sign, numbers);
                 if(zero_q == quantity)
                     return new LongNum();
                 else{
                     int[] ans_numbers = new int[quantity - zero_q];
                     for(int m = 0; m < quantity - zero_q; m++)
                         ans_numbers[m] = numbers[m + zero_q];
                     return new LongNum(sign, ans_numbers);
                 }
             }catch(Exception e){
                 System.out.println("Error: " + e);
             }
         }
         return null;
    }
}