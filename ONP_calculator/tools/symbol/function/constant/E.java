package tools.symbol.function.constant;

import tools.exceptions.ONP_Exception;

public class E extends Constant {
    @Override
    public double calc() throws ONP_Exception {
        return Math.E;
    }
}
