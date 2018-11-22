package tools.symbol.function.constant;

import tools.exceptions.ONP_Exception;

public class Pi extends Constant {
    @Override
    public double calc() throws ONP_Exception {
        return Math.PI;
    }
}
