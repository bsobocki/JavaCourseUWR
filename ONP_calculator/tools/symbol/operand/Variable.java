package tools.symbol.operand;

import tools.exceptions.ONP_Exception;
import tools.exceptions.ONP_WrongVarableName;
import tools.symbol.Symbol;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Variable extends Symbol {
    private String name;

    public Variable(String name){
        Pattern pat = Pattern.compile("\\p{Alpha}\\p{Alnum}*");
        Matcher mat = pat.matcher(name);
        if(mat.matches())
            this.name = name;
        else
            throw new ONP_WrongVarableName();
    }

    public String getName() {
        return this.name;
    }

    @Override
    public double calc() throws ONP_Exception {
        return 0;
    }
}
