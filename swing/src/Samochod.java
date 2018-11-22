import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


/**
 * Created by Bartosz Sobocki on 2018-04-12.
 */

public class Samochod extends Pojazd implements Serializable{

    private int liczbaDrzwi;
    private String marka;

    public int getLiczbaDrzwi() { return liczbaDrzwi;}
    public String getMarka()    { return marka;}
    public void setLiczbaDrzwi(int a) { liczbaDrzwi=a;}
    public void setMarka(String a)    { marka=a;}

    public Samochod(int maxsp, int iloscPas, String zasilanie, int drzwi, String marka) {

        super(maxsp, "Samochod", iloscPas, zasilanie);
        liczbaDrzwi = drzwi;
        this.marka = marka;

    }

    public Samochod () {

        super(200, "Samochod", 5, "benzyna");
        liczbaDrzwi = 5;
        marka = "ford";

    }

    @Override
    public String toString () {

        return typ + " marki: " + marka + "\n" + liczbaDrzwi + "-drzwiowy" + "\nmaksymalna predkosc[km/h]: " + maksPredkosc + "\nilosc pasazerow: " +
                liczbaPasazerow + "\nrodzaj zasilania: " + rodzajZasilania;
    }

}
