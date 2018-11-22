package tools.symbol.function.oneArg;

import tools.exceptions.ONP_Exception;

public class Ceil extends OneArg {
    @Override
    public double calc() throws ONP_Exception {
        check();
        return Math.ceil(arg.calc());
    }
}
