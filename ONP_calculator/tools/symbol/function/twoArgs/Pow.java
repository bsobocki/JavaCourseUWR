package tools.symbol.function.twoArgs;

import tools.exceptions.ONP_Exception;
import tools.symbol.function.twoArgs.TwoArgs;

public class Pow extends TwoArgs {
    @Override
    public double calc() throws ONP_Exception {
        check();
        return Math.pow(arg1.calc(),arg2.calc());
    }
}
