package tools.collections;

import java.util.ArrayDeque;

public class ONP_Stack {
    private ArrayDeque<Double> stack;

    public ONP_Stack(){
        stack = new ArrayDeque<>();
    }
    public void push(Double val){
        stack.push(val);
    }
    public Double pop(){
        return stack.pop();
    }
}
