import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


/**
 * Created by Leszek on 2018-04-12.
 */

public class Tramwaj extends Pojazd implements Serializable{

    private int iloscWagonow;
    private String numerLinii;

    public Tramwaj (int maxsp, int iloscPas, String zasilanie, int wagony, String linia) {

        super(maxsp, "tramwaj", iloscPas, zasilanie);
        iloscWagonow = wagony;
        numerLinii = linia;

    }

    public Tramwaj () {

        super(70, "tramwaj", 100, "prąd");
        iloscWagonow = 2;
        numerLinii = "1";
    }

    public int getIloscWagonow() { return iloscWagonow;}
    public String getNumerLinii()   { return numerLinii;}
    public void setIloscWagonow(int a) { iloscWagonow=a;}
    public void setNumerLinii(String a)   { numerLinii=a;}

    @Override
    public String toString () {
        return typ + "\nliczba wagonow: " + iloscWagonow + "\npomiesci " + liczbaPasazerow + " pasazerow" +
                "\nrodzaj zasilania: " + rodzajZasilania + "\nmaksymalna predkosc[km/h]: " + maksPredkosc + "\nnumer linii: " + numerLinii;
    }

}
