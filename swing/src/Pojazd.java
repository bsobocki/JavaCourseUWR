import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Bartosz Sobocki on 2018-04-12.
 */
public abstract class Pojazd implements Serializable {

    protected int maksPredkosc;
    protected String typ;
    protected int liczbaPasazerow;
    protected String rodzajZasilania;


    public Pojazd (int maxspeed, String t, int ilosc, String zasilanie) {

        maksPredkosc = maxspeed;
        typ = t;
        liczbaPasazerow = ilosc;
        rodzajZasilania = zasilanie;

    }

    public int getMaksPredkosc()                 { return maksPredkosc;}
    public int getliczbaPasazerow()              { return liczbaPasazerow;}
    public String getTyp()                       { return typ;}
    public String getRodzajZasilania()           { return rodzajZasilania;}
    public void setMaksPredkosc(int a)           { maksPredkosc=a;}
    public void setLiczbaPasazerow(int a)        { liczbaPasazerow=a;}
    public void setTyp(String a)                 { typ=a;}
    public void setRodzajZasilania(String a)     { rodzajZasilania=a;}

    public abstract String toString();

}
