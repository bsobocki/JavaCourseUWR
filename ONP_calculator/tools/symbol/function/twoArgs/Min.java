package tools.symbol.function.twoArgs;

import tools.exceptions.ONP_Exception;
import tools.symbol.function.twoArgs.TwoArgs;

public class Min extends TwoArgs {
    @Override
    public double calc() throws ONP_Exception {
        check();
        return Math.min(arg2.calc(), arg1.calc());
    }
}
