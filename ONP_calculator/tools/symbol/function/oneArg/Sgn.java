package tools.symbol.function.oneArg;

import tools.exceptions.ONP_Exception;

public class Sgn extends OneArg {
    @Override
    public double calc() throws ONP_Exception {
        check();
        if(arg.calc()>0)
            return 1;
        else if(arg.calc()==0)
            return 0;
        else
            return -1;
    }
}
