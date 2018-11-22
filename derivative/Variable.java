class Variable extends Expression{

    @Override
    public Expression getL() { return null;}
    @Override
    public Expression getR() { return null;}

    public String variable;

    Variable(String var) {
        variable = var;
    }
    Variable(String var, Integer val) {
        variable = var;
        Hmap.put(var,val);
    }
    @Override
    public int calc() {
        return Hmap.get(variable);
    }
    @Override
    public String ToString() {
        return variable;
    }
    @Override
    public String ToString2() {
        return variable + " = " + Expression.Hmap.get(variable);
    }
}