package initialization;


public class Operations {

    private static int compare(LongNum ln1, LongNum ln2){
        if(ln1.get_quantity() == ln2.get_quantity()){
            for(int i = 0; i < ln1.get_quantity(); i ++){
                if(ln1.get_number(i) > ln2.get_number(i))
                    return 1;
                if(ln1.get_number(i) < ln2.get_number(i))
                    return -1;
            }
            return 0;
        }
        if(ln1.get_quantity() > ln2.get_quantity())
            return 1;
        else
            return -1;
    }


    public static LongNum sum(LongNum ln1, LongNum ln2){

        if(ln1.is_zero())
            return ln2;
        if(ln2.is_zero())
            return ln1;

        LongNum temp;
        int[] numbers;
        int[] ans_numbers;
        boolean sign = false;        
        boolean add_ten = false;

        if(ln1.get_sign() && ln2.get_sign())
            sign = true;

        if(ln1.get_quantity() < ln2.get_quantity()){
            temp = ln1;
            ln1 = ln2;
            ln2 = temp;
            temp = null;
        }

        if(ln1.get_sign() && !ln2.get_sign())
            return difference(ln2, new LongNum(false, ln1.get_numbers()));
        if(!ln1.get_sign() && ln2.get_sign())
            return difference(ln1, new LongNum(false, ln2.get_numbers()));

        numbers = new int[ln1.get_quantity()];
        int i_ln1 = ln1.get_quantity() - 1;
        int i_ln2 = ln2.get_quantity() - 1;

        for( ; i_ln2 > -1; i_ln2--, i_ln1--){
            numbers[i_ln1] = ln1.get_number(i_ln1) + ln2.get_number(i_ln2);
            if(add_ten){
                numbers[i_ln1] = numbers[i_ln1] + 1;
                add_ten = false;
            }
            if(numbers[i_ln1] > 9){
                numbers[i_ln1] = numbers[i_ln1]%10;
                add_ten = true;
            }
        }

        for( ; i_ln1 > -1; i_ln1--){
            numbers[i_ln1] = ln1.get_number(i_ln1);
            if(add_ten){
                numbers[i_ln1] = numbers[i_ln1] + 1;
                add_ten = false;
            }
            if(numbers[i_ln1] > 9){
                numbers[i_ln1] = numbers[i_ln1]%10;
                add_ten = true;
            }
        }

        if(add_ten){
            ans_numbers = new int[ln1.get_quantity() + 1];
            ans_numbers[0] = 1;
            for(int k = 0; k < ln1.get_quantity(); k++)
                ans_numbers[k + 1] = numbers[k];
            return new LongNum(sign, ans_numbers);
        }
        else{
            return new LongNum(sign, numbers);
        }
    }
   

    
    public static LongNum difference(LongNum ln1, LongNum ln2){
        LongNum temp;
        int[] numbers;
        int[] ans_numbers;
        boolean sign = false;
        boolean minus_ten = false;
                                 

      
        if(ln1.get_sign() && !ln2.get_sign())
            return sum(ln1, new LongNum(true, ln2.get_numbers()));
        
     
        if(!ln1.get_sign() && ln2.get_sign())
            return sum(ln1, new LongNum(false, ln2.get_numbers()));

        boolean flag = false;

        if(compare(ln1,ln2) == -1){
            temp = ln1;
            ln1 = ln2;
            ln2 = temp;
            temp = null;
            flag = true;
        }        

        if(compare(ln2,ln1) == 0 && !ln1.get_sign() && !ln2.get_sign())
            return new LongNum();

  

         if(ln1.get_sign() && ln2.get_sign() && !flag)
             sign = true;


        if(!ln1.get_sign() && !ln2.get_sign() && flag)
            sign = true; 

        numbers = new int[ln1.get_quantity()];
        int i_ln1 = ln1.get_quantity() - 1;
        int i_ln2 = ln2.get_quantity() - 1;
        
        for( ; i_ln2 > -1; i_ln2--, i_ln1--){
            if(minus_ten){
                if(ln1.get_number(i_ln1) - 1 < ln2.get_number(i_ln2))
                    numbers[i_ln1] = 9 + ln1.get_number(i_ln1) - ln2.get_number(i_ln2);
                else{
                    numbers[i_ln1] = ln1.get_number(i_ln1) - 1 - ln2.get_number(i_ln2);
                    minus_ten = false;
                }
            }
            else{
                if(ln1.get_number(i_ln1) < ln2.get_number(i_ln2)){
                    numbers[i_ln1] = 10 + ln1.get_number(i_ln1) - ln2.get_number(i_ln2);
                    minus_ten = true;
                }
                else{
                    numbers[i_ln1] = ln1.get_number(i_ln1) - ln2.get_number(i_ln2);
                    minus_ten = false;
                }
            }
        }

        for( ; i_ln1 > -1; i_ln1--){
            if(minus_ten){
                if(ln1.get_number(i_ln1) - 1 < 0)
                    numbers[i_ln1] = 9;
                else{
                    numbers[i_ln1] = ln1.get_number(i_ln1) - 1;
                    minus_ten = false;
                }
            }
            else
                numbers[i_ln1] = ln1.get_number(i_ln1);
        }

        int zero_q = 0;
        for(int i = 0; i < ln1.get_quantity(); i++){
            if(numbers[i] == 0)
                zero_q++;
            else
                break;
        }

        if(zero_q == 0)
            return new LongNum(sign, numbers);
        else{
            ans_numbers = new int[ln1.get_quantity() - zero_q];
            for(int k = 0; k < ln1.get_quantity() - zero_q; k++)
                ans_numbers[k] = numbers[k + zero_q];
            return new LongNum(sign, ans_numbers);
        }
    }


    public static LongNum mult_by_ten_power_n(LongNum long_num, int power){
        if(power <= 0)
            return long_num; 
        int[] ans_numbers = new int[long_num.get_quantity() + power];
        for(int i = 0; i < long_num.get_quantity(); i++)
            ans_numbers[i] = long_num.get_number(i);
        for(int k = long_num.get_quantity(); k < long_num.get_quantity() + power; k++)
            ans_numbers[k] = 0;
        return new LongNum(long_num.get_sign(), ans_numbers);
    }
    
    public static LongNum multiplication(LongNum ln1, LongNum ln2){
        if(ln1.is_zero() || ln2.is_zero())
            return new LongNum();
        LongNum ans = new LongNum();
        LongNum temp;
        LongNum mult1 = ln1.copy(); 
        LongNum mult2 = ln2.copy();
                                   
        mult1.set_sign(false);
        mult2.set_sign(false);
        for(int i = 0; i < mult2.get_quantity(); i++){
            temp = new LongNum();                  
            for(int k = 0; k < mult2.get_number(i); k++)
                temp = sum(temp, mult1);
            if(ln2.get_number(i) != 0)
                ans = sum(ans, mult_by_ten_power_n(temp, mult2.get_quantity() - 1 - i));
        }
        if(ln1.get_sign() != ln2.get_sign())
            ans.set_sign(true);
        return ans;
    }

    public static LongNum[] division(LongNum ln1, LongNum ln2){
        LongNum ans[] = {new LongNum(), new LongNum()};
        
        if(ln2.is_zero()){
            System.out.println("You tried to divide by zero");
            return ans;
        }
        if(compare(ln1, ln2) == -1){
            ans[1] = ln1.copy();
            return ans;
        }
        if(compare(ln1, ln2) == 0){
            ans[0] = ln1.copy();
            ans[0].set_number(0, 1);
            return ans;
        }

        boolean sign = false;
        if(ln1.get_sign() != ln2.get_sign())
            sign = true;

        LongNum temp;
        LongNum temp2;
        LongNum temp3 = new LongNum();
        
        LongNum result = new LongNum();
        LongNum temp_result = new LongNum();
        LongNum temp_result2 = new LongNum();

        LongNum dividend = ln1.copy();
        LongNum divider = ln2.copy();
        dividend.set_sign(false);
        divider.set_sign(false);

        

        for(int i = dividend.get_quantity() - divider.get_quantity(); compare(multiplication(result, divider), dividend) != 1 && i > -1; ){
            temp = new LongNum();
            while(i > -1){
                temp = mult_by_ten_power_n(divider, i);
                temp_result = new LongNum(i);
                if(compare(dividend, sum(temp, multiplication(result, divider))) == 1){
                    i--;
                    break;
                }
                i--;
            }
            temp2 = temp.copy();
            temp_result2 = temp_result.copy();
            boolean flag = false;
            while(true){
                if(compare(dividend, sum(temp2, temp3)) != 1){
                    flag = true;
                    break;
                }
                if(compare(dividend, sum(sum(temp2, temp), temp3)) == -1){
                    break;
                }                
                temp2 = sum(temp2,temp);
                temp_result2 = sum(temp_result2,temp_result);
            }
            if(!flag){
                result = sum(result, temp_result2.copy());
                temp3 = sum(temp3,temp2.copy());
            }
        }
                
        temp = difference(dividend, multiplication(result, divider));
        temp.set_sign(sign);
        ans[1] = temp;
        ans[0] = result;
        result.set_sign(sign);
        return ans;
    }
}