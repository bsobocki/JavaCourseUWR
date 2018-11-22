import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leszek on 2018-03-29.
 */
public class Main {

    public static void main(String [] args ) {

        Zmienna x = new Zmienna("x", 2);
        System.out.println(x.ToString2());
        Potega pot = new Potega(x,3);
        Dodawanie dod = new Dodawanie(pot,new Stala(2));
        Potega potegaSumy = new Potega(dod,2);
        Mnozenie mnoz = new Mnozenie(pot,new Potega(new Odejmowanie(x,new Stala(3)),2));

        System.out.println("wyrazenie :");
        System.out.println(potegaSumy.ToString());
        Derivative der = new Derivative(potegaSumy);
        System.out.println("Pochodna : ");
        System.out.println(der.getPrzedstawienie());
        System.out.println("Wartosc: ");
        System.out.println(der.getWartosc());

        System.out.println();

        Dzielenie dziel = new Dzielenie(potegaSumy,mnoz );
        Derivative pochodnaDziel = new Derivative(dziel);
        System.out.println("wyrazenie : \n" + dziel.ToString());
        System.out.println("Pochodna : ");
        System.out.println(pochodnaDziel.getPrzedstawienie());
        System.out.println("Wartosc: ");
        System.out.println(pochodnaDziel.getWartosc());

        System.out.println();
        System.out.println("Wyrazenie: ");
        System.out.println(mnoz.ToString());
        Derivative pochodnaMnoz = new Derivative(mnoz);
        System.out.println("Pochodna: ");
        System.out.println(pochodnaMnoz.getPrzedstawienie());
        System.out.println("Wartosc: ");
        System.out.println(pochodnaMnoz.getWartosc());

    }
}

class Derivative {

    //reprezentacja funkcji
    private double wartosc;
    private String przedstawienie;

    Derivative(Wyrazenie funkcja) {
        wartosc = oblicz(funkcja);
        przedstawienie = ToString(funkcja);
    }

    public String getPrzedstawienie() {
        return przedstawienie;
    }

    public double getWartosc() {
        return wartosc;
    }

    //reprezentacja pochodnej danej funkcji jako wartosc
    public double oblicz(Wyrazenie funkcja) {

        //rozpatrujemy czym dokladnie jest funkcja (jej typ)
        if (funkcja==null) {
            return 0;
        }
        else if (funkcja.getClass().isAssignableFrom(Stala.class)) { //jesli stala
            return 0;
        }
        else if (funkcja.getClass().isAssignableFrom(Zmienna.class)){ //jesli zmienna
            return 1;
        }
        else if (funkcja.getClass().isAssignableFrom(Potega.class)) {

            //pobieramy potege (trzeba w ten sposob poniewaz "power" jest tylko w klasie Potega
            // nie ma jej w klasie abstrakcyjnej przez co nie ma do niej bezposrednio dostepu
            String po = ""; // potega zapisze sie odwrotnie, trzeba ja odwrocic
            String po2 = ""; //odwrocona potega

            //budujemy liczbe reprezentujaca potege
            for(int i =funkcja.ToString().length() - 1 ; funkcja.ToString().charAt(i) != (int)'^' ; i--) {
                po += funkcja.ToString().charAt(i);
            }

            //odwracamy potege
            for(int i = po.length()-1; i>=0; i--)
                po2+=po.charAt(i);

            int pot;
            //pobieramy potege
            pot = Integer.parseInt(po2);

            //kiedy mamy podnosic do potegi 1 to lepiej tego nie zapisywac
            if(pot == 2) {

                if(funkcja.getL().getClass().isAssignableFrom(Zmienna.class))
                    return  pot * funkcja.getL().oblicz();

                if(funkcja.getL().getClass().isAssignableFrom(Stala.class))
                    return 0;

                return pot * funkcja.getL().oblicz()  *  oblicz(funkcja.getL()) ;
            }

            if(funkcja.getL().getClass().isAssignableFrom(Zmienna.class))
                return pot * Math.pow(funkcja.getL().oblicz(),pot-1);

            if(funkcja.getL().getClass().isAssignableFrom(Stala.class))
                return 0;

            return  pot * Math.pow(funkcja.getL().oblicz() ,pot-1) * oblicz(funkcja.getL());
        }

        else if (funkcja.getClass().isAssignableFrom(Dodawanie.class)) {

            if(funkcja.getP().getClass().isAssignableFrom(Stala.class))
                return oblicz(funkcja.getL());

            if(funkcja.getL().getClass().isAssignableFrom(Stala.class))
                return oblicz(funkcja.getP());

            return oblicz(funkcja.getL()) + oblicz(funkcja.getP());
        }

        else if (funkcja.getClass().isAssignableFrom(Odejmowanie.class)) {

            if(funkcja.getP().getClass().isAssignableFrom(Stala.class))
                return oblicz(funkcja.getL());


            return oblicz(funkcja.getL())  - oblicz(funkcja.getP());
        }

        else if (funkcja.getClass().isAssignableFrom(Mnozenie.class)) {

            if(funkcja.getP().getClass().isAssignableFrom(Zmienna.class))
                return oblicz(funkcja.getL());

            if(funkcja.getL().getClass().isAssignableFrom(Zmienna.class))
                return oblicz(funkcja.getP());

            if(funkcja.getL().getClass().isAssignableFrom(Stala.class))
                return 0;

            if(funkcja.getP().getClass().isAssignableFrom(Stala.class))
                return 0;

            return (oblicz(funkcja.getL()) * funkcja.getP().oblicz()) + (oblicz(funkcja.getP())  * funkcja.getL().oblicz()) ;
        }

        else if (funkcja.getClass().isAssignableFrom(Dzielenie.class)) {

            if(funkcja.getP().getClass().isAssignableFrom(Zmienna.class))
                return 0;

            if(funkcja.getL().getClass().isAssignableFrom(Stala.class))
                return 0;

            return ((oblicz(funkcja.getL()) * funkcja.getP().oblicz()) - (oblicz(funkcja.getP()) * funkcja.getL().oblicz()))  / Math.pow(funkcja.getP().oblicz(),2) ;
        }
        return 0;
    }

    //reprezentacja pochodnej danej funkcji jako napis
    public String ToString(Wyrazenie funkcja) {
        if (funkcja==null) {
            return "";
        }
        else if (funkcja.getClass().isAssignableFrom(Stala.class)) { //jesli stala
                return "0";
        }
        else if (funkcja.getClass().isAssignableFrom(Zmienna.class)){ //jesli zmienna
                return "1";
        }
        else if (funkcja.getClass().isAssignableFrom(Potega.class)) {

            String pot = "";

            for(int i =funkcja.ToString().length() - 1 ; funkcja.ToString().charAt(i) != (int)'^' ; i--) {
                pot += funkcja.ToString().charAt(i);
            }

            if((Integer.parseInt(pot)-1) == 1) {

                if(funkcja.getL().getClass().isAssignableFrom(Zmienna.class))
                    return "" + pot + "*" + "(" + funkcja.getL().ToString() + ")";

                if(funkcja.getL().getClass().isAssignableFrom(Stala.class))
                    return "0";

                return "" + pot + "*" + "(" + funkcja.getL().ToString() + ") * (" + ToString(funkcja.getL()) + ")";
            }

            if(funkcja.getL().getClass().isAssignableFrom(Zmienna.class))
                return "" + pot + "*" + "(" + funkcja.getL().ToString() + ")^" + (Integer.parseInt(pot)-1);

            if(funkcja.getL().getClass().isAssignableFrom(Stala.class))
                return "0";

            return "" + pot + "*" + "(" + funkcja.getL().ToString() + ")^" + (Integer.parseInt(pot)-1) + " * (" + ToString(funkcja.getL()) + ")";
        }

        else if (funkcja.getClass().isAssignableFrom(Dodawanie.class)) {

            if(funkcja.getP().getClass().isAssignableFrom(Stala.class))
                return "" + ToString(funkcja.getL());

            if(funkcja.getL().getClass().isAssignableFrom(Stala.class))
                return "" + ToString(funkcja.getP());

            return "" + ToString(funkcja.getL()) + " + " + ToString(funkcja.getP());
        }

        else if (funkcja.getClass().isAssignableFrom(Odejmowanie.class)) {

            if(funkcja.getP().getClass().isAssignableFrom(Stala.class))
                return "" + ToString(funkcja.getL());


            return "" + ToString(funkcja.getL()) + " - " + ToString(funkcja.getP());
        }

        else if (funkcja.getClass().isAssignableFrom(Mnozenie.class)) {

            if(funkcja.getP().getClass().isAssignableFrom(Zmienna.class))
                return "" + ToString(funkcja.getL());

            if(funkcja.getL().getClass().isAssignableFrom(Zmienna.class))
                return "" + ToString(funkcja.getP());

            if(funkcja.getL().getClass().isAssignableFrom(Stala.class))
                return "0";

            if(funkcja.getP().getClass().isAssignableFrom(Stala.class))
                return "0";

            return "(" + ToString(funkcja.getL()) + " * " + funkcja.getP().ToString() + ") + (" + ToString(funkcja.getP()) + " * " + funkcja.getL().ToString() +")";
        }

        else if (funkcja.getClass().isAssignableFrom(Dzielenie.class)) {

            if(funkcja.getP().getClass().isAssignableFrom(Zmienna.class))
                return "NaN";

            if(funkcja.getL().getClass().isAssignableFrom(Stala.class))
                return "0";

            return "(" + ToString(funkcja.getL()) + " * " + funkcja.getP().ToString() + ") - (" + ToString(funkcja.getP()) + " * " + funkcja.getL().ToString() + ") / (" + funkcja.getP().ToString() + ")^2" ;
        }
        return "Niepoprawny typ";
    }
}

abstract class Wyrazenie{

    public static Map<String,Integer> Hmap = new HashMap<String, Integer>();
    public abstract Wyrazenie getP();
    public abstract Wyrazenie getL();
    public abstract int oblicz();
    public abstract String ToString();
    public abstract String ToString2();
}

class Dodawanie extends Wyrazenie{

    private Wyrazenie Lewy;
    private Wyrazenie Prawy;

    public Wyrazenie getL() { return Lewy;}
    public Wyrazenie getP() { return Prawy;}

    public Dodawanie(Wyrazenie L, Wyrazenie P) {
        Lewy = L;
        Prawy = P;
    }

    public int oblicz() {
        return Lewy.oblicz()+Prawy.oblicz();
    }

    public String ToString() {
        return Lewy.ToString() + " + " + Prawy.ToString();
    }

    public String ToString2() {
        String k;
        try{
            k = "" + oblicz();
        }
        catch (ArithmeticException a)
        {
            k = "NaN";
        }
        return Lewy.ToString() + " + " + Prawy.ToString() + " = " + oblicz();
    }
}

class Odejmowanie extends Wyrazenie{

    private Wyrazenie Lewy;
    private Wyrazenie Prawy;

    public Wyrazenie getL() { return Lewy;}
    public Wyrazenie getP() { return Prawy;}

    public Odejmowanie(Wyrazenie L, Wyrazenie P) {
        Lewy = L;
        Prawy = P;
    }

    public int oblicz() {
        return Lewy.oblicz()-Prawy.oblicz();
    }

    public String ToString() {
        return Lewy.ToString() + " - " + Prawy.ToString();
    }

    public String ToString2() {
        return Lewy.ToString() + " - " + Prawy.ToString() + " = " + oblicz();
    }
}

class Mnozenie extends Wyrazenie{

    private Wyrazenie Lewy;
    private Wyrazenie Prawy;

    public Wyrazenie getL() { return Lewy;}
    public Wyrazenie getP() { return Prawy;}

    public Mnozenie(Wyrazenie L, Wyrazenie P)
    {
        Lewy = L;
        Prawy = P;
    }

    public int oblicz() {
        return Lewy.oblicz()*Prawy.oblicz();
    }

    public String ToString() {
        return Lewy.ToString() + " * " + Prawy.ToString();
    }

    public String ToString2() {
        return "(" + Lewy.ToString() + ")" + " * " + "(" +Prawy.ToString() + ")" + " = " + oblicz();
    }
}

class Dzielenie extends Wyrazenie{

    private Wyrazenie Lewy;
    private Wyrazenie Prawy;

    public Wyrazenie getL() { return Lewy;}
    public Wyrazenie getP() { return Prawy;}

    public Dzielenie(Wyrazenie L, Wyrazenie P) {
        Lewy = L;
        Prawy = P;
    }

    public int oblicz() {
        try{
            return Lewy.oblicz() / Prawy.oblicz();
        }
        catch (ArithmeticException a) {
            System.out.println("Nie mozna dzielic przez 0!");
            // ArithmeticException b = null;
            // throw b;
        }
        return Lewy.oblicz() / Prawy.oblicz();
    }

    public String ToString() {
        return "(" + Lewy.ToString() + ")" + " / " + "(" + Prawy.ToString() + ")";
    }

    public String ToString2() {
        String k;
        try{
            k = "" + oblicz();
        }
        catch (ArithmeticException a)
        {
            k = "NaN";
        }
        return "(" + Lewy.ToString() + ")" + " / " + "(" + Prawy.ToString() + ")" + " = " + k;
    }
}

class Stala extends Wyrazenie{

    public Wyrazenie getL() { return null;}
    public Wyrazenie getP() { return null;}

    public Integer wartosc;

    Stala(int val) {
        wartosc = val;
    }

    public int oblicz() {
        return wartosc;
    }

    public String ToString() {
        return "" + wartosc;
    }

    public String ToString2() {
        return "" + wartosc;
    }
}

class Zmienna extends Wyrazenie{

    public Wyrazenie getL() { return null;}
    public Wyrazenie getP() { return null;}

    public String variable;

    Zmienna(String var) {
        variable = var;
    }

    Zmienna(String var, Integer val) {
        variable = var;
        Hmap.put(var,val);
    }

    public int oblicz() {
        return Hmap.get(variable);
    }

    public String ToString() {
        return variable;
    }

    public String ToString2() {
        return variable + " = " + Wyrazenie.Hmap.get(variable);
    }
}

class Potega extends Wyrazenie{

    public Wyrazenie getP(){ return null;}
    public Wyrazenie getL(){ return Lewy;}

    private Wyrazenie Lewy;
    public Integer power;

    public Potega(Wyrazenie f,Integer pow)
    {
        Lewy = f;
        power = pow;
    }

    public int oblicz(){
        return (int)Math.pow(Lewy.oblicz(),power);
    }

    public String ToString(){
        return "(" + Lewy.ToString() + ")^" + power;
    }

    public String ToString2(){
        return "" + Lewy.ToString() + "^" + power + " = " + oblicz();
    }
}