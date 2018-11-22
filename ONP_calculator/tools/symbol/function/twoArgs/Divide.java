package tools.symbol.function.twoArgs;

import tools.exceptions.*;
import tools.symbol.function.twoArgs.TwoArgs;

public class Divide extends TwoArgs {
    @Override
    public double calc() throws ONP_Exception {
        check();
        if (arg2.calc()==0)
            throw new ONP_DivideByZero();
        return arg1.calc()/arg2.calc();
    }
}
