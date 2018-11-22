
class Divide extends Expression{

    public Divide(Expression L, Expression P) {
        left = L;
        right = P;
    }
    @Override
    public int calc() {
        try{
            return left.calc() / right.calc();
        }
        catch (ArithmeticException a) {
            System.out.println("Nie mozna divic przez 0!");
            // ArithmeticException b = null;
            // throw b;
        }
        return left.calc() / right.calc();
    }
    @Override
    public String ToString() {
        return "(" + left.ToString() + ")" + " / " + "(" + right.ToString() + ")";
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
        return "(" + left.ToString() + ")" + " / " + "(" + right.ToString() + ")" + " = " + k;
    }
}
