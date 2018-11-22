/**
 * Created by Leszek on 2018-03-29.
 */
public class Main {

    public static void main(String [] args ) {

        Variable x = new Variable("x", 2);
        System.out.println(x.ToString2());
        Power pot = new Power(x,3);
        Plus dod = new Plus(pot,new Constant(2));
        Power sumPower = new Power(dod,2);
        Multiply mult = new Multiply(pot,new Power(new Minus(x,new Constant(3)),2));

        System.out.println("expression :");
        System.out.println(sumPower.ToString());
        Derivative der = new Derivative(sumPower);
        System.out.println("Derivative : ");
        System.out.println(der.getRepresentation());
        System.out.println("Value: ");
        System.out.println(der.getValue());

        System.out.println();

        Divide div = new Divide(sumPower,mult );
        Derivative Derivativediv = new Derivative(div);
        System.out.println("expression : \n" + div.ToString());
        System.out.println("Derivative : ");
        System.out.println(Derivativediv.getRepresentation());
        System.out.println("Value: ");
        System.out.println(Derivativediv.getValue());

        System.out.println();
        System.out.println("expression: ");
        System.out.println(mult.ToString());
        Derivative derMult = new Derivative(mult);
        System.out.println("Derivative: ");
        System.out.println(derMult.getRepresentation());
        System.out.println("Value: ");
        System.out.println(derMult.getValue());

    }
}