package tools;

import tools.exceptions.*;
import tools.symbol.Symbol;

/**for functions 0-, 1- and 2-args*/
public interface Functional extends Calculable {
    /** function arity*/
    int numberOfArgs (); 
    int missingArgs ();
    void addArg (Symbol arg) throws ONP_Exception;
}
