package tools.symbol.function.twoArgs;

import tools.collections.ONP_Set;
import tools.exceptions.ONP_ArgumentDoesNotExist;
import tools.exceptions.ONP_Exception;
import tools.exceptions.ONP_TooManyArguments;
import tools.symbol.Symbol;
import tools.symbol.function.Function;
import tools.symbol.operand.Variable;

public class Assign extends Function {
    Variable arg1;
    Symbol arg2;
    int numberOfArgs;

    public ONP_Set assign(ONP_Set set){
        check();
        set.add(arg1.getName(),arg2.calc());
        return  set;
    }

    private void check() throws ONP_Exception {
        if (arg2==null || arg1==null)
            throw new ONP_ArgumentDoesNotExist();
    }

    public void addArg1(Variable arg) throws ONP_Exception{
        if(arg1==null)
            this.arg1 = arg;
        else
            throw new ONP_TooManyArguments();
    }

    @Override
    public void addArg(Symbol arg) throws ONP_Exception{
        if(arg2==null)
            this.arg2 = arg;
        else
            throw new ONP_TooManyArguments();
    }

    @Override
    public double calc() throws ONP_Exception {
        return 0;
    }

    @Override
    public int numberOfArgs() {
        return 2;
    }

    @Override
    public int missingArgs() {
        return 2 - numberOfArgs;
    }
}
