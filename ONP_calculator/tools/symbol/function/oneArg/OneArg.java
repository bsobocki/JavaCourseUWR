package tools.symbol.function.oneArg;

import tools.exceptions.*;
import tools.symbol.*;
import tools.symbol.function.*;
import tools.symbol.operand.Number;

public abstract class OneArg extends Function {
    Symbol arg;
    int numOfArgs = 0;

    void check() throws ONP_Exception {
        if (arg==null)
            throw new ONP_ArgumentDoesNotExist();
    }
    @Override
    public int numberOfArgs() {
        return numOfArgs;
    }
    @Override
    public int missingArgs() {
        return 1 - numOfArgs;
    }
    @Override
    public void addArg(Symbol arg) throws ONP_Exception {
        if(this.arg==null)
            this.arg = arg;
        else
            throw new ONP_TooManyArguments();
    }
}
