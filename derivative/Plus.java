class Plus extends Expression{

    public Plus(Expression L, Expression P) {
        left = L;
        right = P;
    }
    @Override
    public int calc() {
        return left.calc()+right.calc();
    }
    @Override
    public String ToString() {
        return left.ToString() + " + " + right.ToString();
    }
    @Override
    public String ToString2() {
        String k;
        try{
            k = "" + calc();
        }
        catch (ArithmeticException a)
        {
            k = "NaN";
        }
        return left.ToString() + " + " + right.ToString() + " = " + calc();
    }
}