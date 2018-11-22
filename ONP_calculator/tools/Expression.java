package tools;

import tools.collections.*;
import tools.exceptions.*;
import tools.symbol.Symbol;
import tools.symbol.function.twoArgs.*;
import tools.symbol.function.oneArg.*;
import tools.symbol.function.constant.*;
import tools.symbol.operand.*;
import tools.symbol.operand.Number;

import java.util.ArrayList;
import java.util.Collections;

/**a class that storages an expression*/
public class Expression {
    /**collections necessary to calculate*/
    //private ONP_Queue queue;  //need to calculate in difffrent way 
    //private ONP_Stack stack;  //than mine
    public ONP_Set set;
    private ONP_List expr;

    /**constructors*/
    public Expression() throws ONP_Exception{
        //queue = new ONP_Queue();
        //stack = new ONP_Stack();
        set = new ONP_Set();
        expr = new ONP_List();
    }
    public Expression(String onp) throws ONP_Exception{
        //queue = new ONP_Queue();
        //stack = new ONP_Stack();
        set = new ONP_Set();
        expr = new ONP_List();
        setExpression(onp);
    }
    public void setExpression(String onp) throws ONP_Exception {
        expr = onp_symbols_prefix(onp);
    }

    /**method creates ONP_List which storages an prefix expressioin*/
    public static ONP_List onp_symbols_prefix(String str){
        /**to build an single symbol*/
        String temp = "";
        /**to build an postfix expression and reverse it*/
        ArrayList<String> arrayList = new ArrayList<>();
        /**string to char array*/
        char[] charArray = str.toCharArray();
        ONP_List k = new ONP_List();

        /**add symbols to arrayList*/
        for(char x: charArray){
            if(x != ' ' && x != '\n'){
                temp+=x;
            }
            else{
                arrayList.add(temp);
                temp = "";
            }
        }
        /**add last symbol*/
        if(charArray.length!=0)
            if(charArray[str.length()-1]!=' ')
                arrayList.add(temp);
        
        Collections.reverse(arrayList);

        /**make prefix expression to calculate*/
        for(String x: arrayList){
            k.add(x);
        }
        return k;
    }

    /**build a tree-expression from prefix*/
    public Symbol build() throws ONP_Exception{
        if(!expr.isEmpty()) {
            String x = expr.remove();
            /**number*/
            if (x.toCharArray()[0] >= '0' && x.toCharArray()[0] <= '9')
                return new Number(Double.parseDouble(x));
                /**constant*/
            else if (x.equals("e"))
                return new E();
            else if (x.equals("pi"))
                return new Pi();
            else if (x.equals("phi"))
                return new Phi();
                /**function 1-arg*/
            else if (x.equals("abs")) {
                Abs abs = new Abs();
                abs.addArg(build());
                return abs;
            } else if (x.equals("atan")) {
                Atan abs = new Atan();
                abs.addArg(build());
                return abs;
            } else if (x.equals("ceil")) {
                Ceil abs = new Ceil();
                abs.addArg(build());
                return abs;
            } else if (x.equals("cos")) {
                Cos abs = new Cos();
                abs.addArg(build());
                return abs;
            } else if (x.equals("exp")) {
                Exp abs = new Exp();
                abs.addArg(build());
                return abs;
            } else if (x.equals("floor")) {
                Floor abs = new Floor();
                abs.addArg(build());
                return abs;
            } else if (x.equals("ln")) {
                Ln abs = new Ln();
                abs.addArg(build());
                return abs;
            } else if (x.equals("sgn")) {
                Sgn abs = new Sgn();
                abs.addArg(build());
                return abs;
            } else if (x.equals("sin")) {
                Sin abs = new Sin();
                abs.addArg(build());
                return abs;
            }
            /**function 2-args*/
            else if (x.equals("+")) {
                Plus abs = new Plus();
                abs.addArg(build());
                abs.addArg(build());
                return abs;
            } else if (x.equals("-")) {
                Minus abs = new Minus();
                abs.addArg(build());
                abs.addArg(build());
                return abs;
            } else if (x.equals("/")) {
                Divide abs = new Divide();
                abs.addArg(build());
                abs.addArg(build());
                return abs;
            } else if (x.equals("*")) {
                Multiply abs = new Multiply();
                abs.addArg(build());
                abs.addArg(build());
                return abs;
            } else if (x.equals("log")) {
                Log abs = new Log();
                abs.addArg(build());
                abs.addArg(build());
                return abs;
            } else if (x.equals("max")) {
                Max abs = new Max();
                abs.addArg(build());
                abs.addArg(build());
                return abs;
            } else if (x.equals("nim")) {
                Min abs = new Min();
                abs.addArg(build());
                abs.addArg(build());
                return abs;
            } else if (x.equals("pow")) {
                Pow abs = new Pow();
                abs.addArg(build());
                abs.addArg(build());
                return abs;
            }
            /**assign*/
            else if (x.equals("=")) {
                Assign a = new Assign();
                a.addArg1(new Variable(expr.remove()));
                Symbol c = build();
                a.addArg(c);
                set = a.assign(set);
                return c;
            } else
                return new Number(set.get(x));
        }
        else
            /**normal tree ends on leaves (constant or numbers)*/
            throw new ONP_WrongExpression();
    }

    /**calculate expression*/
    public double calculate(){
        Symbol x = build();
        return x.calc();
    }

    public ONP_Set getSet() {
        return set;
    }
}
