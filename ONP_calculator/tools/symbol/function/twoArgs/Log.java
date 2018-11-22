package tools.symbol.function.twoArgs;

import tools.exceptions.ONP_Exception;
import tools.symbol.function.twoArgs.TwoArgs;

public class Log extends TwoArgs {
    @Override
    public double calc() throws ONP_Exception {
        check();
        return Math.log(arg2.calc())/Math.log(arg1.calc());
    }
}
