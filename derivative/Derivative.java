 class Derivative {
    /**function representation*/
    private double value;
    private String representation;

    Derivative(Expression function) {
        value = calc(function);
        representation = ToString(function);
    }
    public String getRepresentation() {
        return representation;
    }
    public double getValue() {
        return value;
    }
    /**derivative reprezentation of the function as value*/
    public double calc(Expression function) {

        /**check what is function (its type)*/
        if (function==null) {
            return 0;
        }
        else if (function.getClass().isAssignableFrom(Constant.class)) { //if Constant
            return 0;
        }
        else if (function.getClass().isAssignableFrom(Variable.class)){ //if Variable
            return 1;
        }
        else if (function.getClass().isAssignableFrom(Power.class)) {
            /**get power (in this way because "power" is only in the Power class
              *it isn't in an abstract class so there isn't direct access to it*/
            String po = ""; // Power will be safed not like we need, so have to reverse it
            String po2 = ""; //reverse Power

            /**build the number representing power*/
            for(int i =function.ToString().length() - 1 ; function.ToString().charAt(i) != (int)'^' ; i--) {
                po += function.ToString().charAt(i);
            }

            /**oreverse power*/
            for(int i = po.length()-1; i>=0; i--)
                po2+=po.charAt(i);

            int pow;
            /**get power*/
            pow = Integer.parseInt(po2);

            /**start at power equals 2*/
            if(pow == 2) {

                if(function.getL().getClass().isAssignableFrom(Variable.class))
                    return  pow * function.getL().calc();

                if(function.getL().getClass().isAssignableFrom(Constant.class))
                    return 0;

                return pow * function.getL().calc()  *  calc(function.getL()) ;
            }

            if(function.getL().getClass().isAssignableFrom(Variable.class))
                return pow * Math.pow(function.getL().calc(),pow-1);

            if(function.getL().getClass().isAssignableFrom(Constant.class))
                return 0;

            return  pow * Math.pow(function.getL().calc() ,pow-1) * calc(function.getL());
        }
        else if (function.getClass().isAssignableFrom(Plus.class)) {

            if(function.getR().getClass().isAssignableFrom(Constant.class))
                return calc(function.getL());

            if(function.getL().getClass().isAssignableFrom(Constant.class))
                return calc(function.getR());

            return calc(function.getL()) + calc(function.getR());
        }
        else if (function.getClass().isAssignableFrom(Minus.class)) {

            if(function.getR().getClass().isAssignableFrom(Constant.class))
                return calc(function.getL());

            return calc(function.getL())  - calc(function.getR());
        }
        else if (function.getClass().isAssignableFrom(Multiply.class)) {

            if(function.getR().getClass().isAssignableFrom(Variable.class))
                return calc(function.getL());

            if(function.getL().getClass().isAssignableFrom(Variable.class))
                return calc(function.getR());

            if(function.getL().getClass().isAssignableFrom(Constant.class))
                return 0;

            if(function.getR().getClass().isAssignableFrom(Constant.class))
                return 0;

            return (calc(function.getL()) * function.getR().calc()) + (calc(function.getR())  * function.getL().calc()) ;
        }
        else if (function.getClass().isAssignableFrom(Divide.class)) {

            if(function.getR().getClass().isAssignableFrom(Variable.class))
                return 0;

            if(function.getL().getClass().isAssignableFrom(Constant.class))
                return 0;

            return ((calc(function.getL()) * function.getR().calc()) - (calc(function.getR()) * function.getL().calc()))  / Math.pow(function.getR().calc(),2) ;
        }
        return 0;
    }
    /**derivative representation of the funkction as string*/
    public String ToString(Expression function) {
        if (function==null) {
            return "";
        }
        else if (function.getClass().isAssignableFrom(Constant.class)) { //if Constant
            return "0";
        }
        else if (function.getClass().isAssignableFrom(Variable.class)){ //if Variable
            return "1";
        }
        else if (function.getClass().isAssignableFrom(Power.class)) {

            String pow = "";

            for(int i =function.ToString().length() - 1 ; function.ToString().charAt(i) != (int)'^' ; i--) {
                pow += function.ToString().charAt(i);
            }

            if((Integer.parseInt(pow)-1) == 1) {

                if(function.getL().getClass().isAssignableFrom(Variable.class))
                    return "" + pow + "*" + "(" + function.getL().ToString() + ")";

                if(function.getL().getClass().isAssignableFrom(Constant.class))
                    return "0";

                return "" + pow + "*" + "(" + function.getL().ToString() + ") * (" + ToString(function.getL()) + ")";
            }

            if(function.getL().getClass().isAssignableFrom(Variable.class))
                return "" + pow + "*" + "(" + function.getL().ToString() + ")^" + (Integer.parseInt(pow)-1);

            if(function.getL().getClass().isAssignableFrom(Constant.class))
                return "0";

            return "" + pow + "*" + "(" + function.getL().ToString() + ")^" + (Integer.parseInt(pow)-1) + " * (" + ToString(function.getL()) + ")";
        }
        else if (function.getClass().isAssignableFrom(Plus.class)) {

            if(function.getR().getClass().isAssignableFrom(Constant.class))
                return "" + ToString(function.getL());

            if(function.getL().getClass().isAssignableFrom(Constant.class))
                return "" + ToString(function.getR());

            return "" + ToString(function.getL()) + " + " + ToString(function.getR());
        }

        else if (function.getClass().isAssignableFrom(Minus.class)) {

            if(function.getR().getClass().isAssignableFrom(Constant.class))
                return "" + ToString(function.getL());


            return "" + ToString(function.getL()) + " - " + ToString(function.getR());
        }
        else if (function.getClass().isAssignableFrom(Multiply.class)) {

            if(function.getR().getClass().isAssignableFrom(Variable.class))
                return "" + ToString(function.getL());

            if(function.getL().getClass().isAssignableFrom(Variable.class))
                return "" + ToString(function.getR());

            if(function.getL().getClass().isAssignableFrom(Constant.class))
                return "0";

            if(function.getR().getClass().isAssignableFrom(Constant.class))
                return "0";

            return "(" + ToString(function.getL()) + " * " + function.getR().ToString() + ") + (" + ToString(function.getR()) + " * " + function.getL().ToString() +")";
        }
        else if (function.getClass().isAssignableFrom(Divide.class)) {

            if(function.getR().getClass().isAssignableFrom(Variable.class))
                return "NaN";

            if(function.getL().getClass().isAssignableFrom(Constant.class))
                return "0";

            return "(" + ToString(function.getL()) + " * " + function.getR().ToString() + ") - (" + ToString(function.getR()) + " * " + function.getL().ToString() + ") / (" + function.getR().ToString() + ")^2" ;
        }
        return "Incorrect type";
    }
}

