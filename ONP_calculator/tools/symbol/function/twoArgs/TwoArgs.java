package tools.symbol.function.twoArgs;

import tools.exceptions.*;
import tools.symbol.Symbol;
import tools.symbol.function.Function;

public abstract class TwoArgs extends Function {
    Symbol arg1;
    Symbol arg2;
    int numOfArgs = 0;

    void check() throws ONP_Exception {
        if (arg2==null || arg1==null) {
            throw new ONP_ArgumentDoesNotExist();
        }
    }
    @Override
    public int numberOfArgs(){
        return numOfArgs;
    }
    @Override
    public int missingArgs(){
        return 2 - numOfArgs;
    }
    @Override
    public void addArg(Symbol arg){
        if(arg2==null)
            arg2 = arg;
        else if (arg1==null)
            arg1 = arg;
        else
            throw new ONP_TooManyArguments();
    }
}
