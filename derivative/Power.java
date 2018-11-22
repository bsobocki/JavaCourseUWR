class Power extends Expression{

    public Integer power;

    public Power(Expression f,Integer pow)
    {
        left = f;
        power = pow;
    }
    @Override
    public int calc(){
        return (int)Math.pow(left.calc(),power);
    }
    @Override
    public String ToString(){
        return "(" + left.ToString() + ")^" + power;
    }
    @Override
    public String ToString2(){
        return "" + left.ToString() + "^" + power + " = " + calc();
    }
}