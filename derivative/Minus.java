class Minus extends Expression{

    public Minus(Expression L, Expression P) {
        left = L;
        right = P;
    }
    @Override
    public int calc() {
        return left.calc()-right.calc();
    }
    @Override
    public String ToString() {
        return left.ToString() + " - " + right.ToString();
    }
    @Override
    public String ToString2() {
        return left.ToString() + " - " + right.ToString() + " = " + calc();
    }
}
