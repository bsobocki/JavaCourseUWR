package tools;

import tools.exceptions.*;

/**objects from class implementing Calculable will be calculating in the interpreter*/
public interface Calculable {
    double calc () throws ONP_Exception;
}
