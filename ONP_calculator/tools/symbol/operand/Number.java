package tools.symbol.operand;

import tools.exceptions.ONP_Exception;

public class Number extends Operand {
    protected double val;

    public Number(double val){
        this.val = val;
    }
    public double getVal() {
        return val;
    }
    @Override
    public double calc() throws ONP_Exception {
        return val;
    }
}
