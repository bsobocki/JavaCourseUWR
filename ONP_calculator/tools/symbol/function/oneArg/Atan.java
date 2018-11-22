package tools.symbol.function.oneArg;

import tools.exceptions.ONP_Exception;

public class Atan extends OneArg {
    @Override
    public double calc() throws ONP_Exception {
        check();
        return Math.atan(arg.calc());
    }
}
