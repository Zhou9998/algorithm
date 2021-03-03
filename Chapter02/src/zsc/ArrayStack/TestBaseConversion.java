package zsc.ArrayStack;


public class TestBaseConversion {
    public static void main(String[] args) {
        System.out.println(convert(100,16));
        System.out.println(convert(100,2));
        System.out.println(convert(100,8));
    }

    private static int convert(int num, int decimal) {
        MyArrayStack stack = new MyArrayStack();
        int remainder = num % decimal;//余数
        while (num != 0){
            stack.push(remainder);
            num /= decimal;
            remainder = num % decimal;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return Integer.parseInt(sb.toString());
    }
}
