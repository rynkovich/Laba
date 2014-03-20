package initialization;


public class LongNum {
    private int[] numbers;
    private boolean sign;
 
    public LongNum(){
        numbers = new int[1];
        numbers[0] = 0;
        sign = false;
    }

    public LongNum(int power){
        numbers = new int[power + 1];
        numbers[0] = 1;
        for(int i = 1; i < power + 1; i++)
            numbers[i] = 0;
    }

    public LongNum(boolean sign, int[] numbers){
        this.sign = sign;
        this.numbers = numbers;
    }

    public LongNum copy(){
        return new LongNum(sign, numbers);
    }

    public boolean is_zero(){
        if(numbers.length == 1 && numbers[0] == 0)
            return true;
        else
            return false;
    }

    public boolean get_sign(){
        return sign;
    }
    public void set_sign(boolean value){
        sign = value;
    }
    public int get_quantity(){
        return numbers.length;
    }
    public int[] get_numbers(){
        return numbers;
    }
    public void set_numbers(int[] value){
        numbers = value;
    }
    public int get_number(int number){
        return numbers[number];
    }
    public void set_number(int number, int value){
        numbers[number] = value;
    }
}