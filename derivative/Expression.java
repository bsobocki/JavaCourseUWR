import java.util.HashMap;
import java.util.Map;

abstract class Expression{

    protected Expression left;
    protected Expression right;
    public static Map<String,Integer> Hmap = new HashMap<String, Integer>();

    public Expression getL() { return left;}
    public Expression getR() { return right;}
    public abstract int calc();
    public abstract String ToString();
    public abstract String ToString2();
}