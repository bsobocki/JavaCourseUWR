package tools;

import tools.exceptions.*;
import tools.symbol.Symbol;

public interface Functional extends Calculable {
    int numberOfArgs (); // function arity
    int missingArgs ();
    void addArg (Symbol arg) throws ONP_Exception;
}