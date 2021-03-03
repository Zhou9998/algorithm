package zsc.ArrayStack;

public class TestBracketMatch {
    public static void main(String[] args) {
        System.out.println(bracketMatch("(){[[]}"));
    }

    public static boolean bracketMatch(String expression){
        MyArrayStack stack = new MyArrayStack();
        //遍历整个表达式
        for (int i = 0;i<expression.length();i++){
            char cc = expression.charAt(i);
            switch (cc){
                case '(':
                case '[':
                case '{':
                    stack.push(cc);
                    break;
                case '}':
                    if (!stack.isEmpty()&&stack.pop().equals('{')){
                        break;
                    }else {
                        return false;
                    }
                case ']':
                    if (!stack.isEmpty()&&stack.pop().equals('[')){
                        break;
                    }else {
                        return false;
                    }
                case ')':
                    if (!stack.isEmpty()&&stack.pop().equals('(')){
                        break;
                    }else {
                        return false;
                    }
            }
        }
        if (stack.isEmpty()){
            return true;
        }else {
            return false;
        }
    }
}
