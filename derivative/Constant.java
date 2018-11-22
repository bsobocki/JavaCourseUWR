class Constant extends Expression{

    public Integer value;

    Constant(int val) {
        value = val;
    }
    @Override
    public int calc() {
        return value;
    }
    @Override
    public String ToString() {
        return "" + value;
    }
    @Override
    public String ToString2() {
        return "" + value;
    }
}