import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Leszek on 2018-04-12.
 */

public class EdycjaTramwaju extends JComponent{

    private Tramwaj tram;

    protected JFrame frame;
    protected Container kontener;
    protected GridLayout layout;
    protected JLabel maksPredkosc_etykieta;
    protected JTextField maksPredkosc_pole;
    protected JLabel zasilanie_etykieta;
    protected JTextField zasilanie_pole;
    protected JLabel liczbaPasazerow_etykieta;
    protected JTextField liczbaPasazerow_pole;
    private JTextField numerLinii_pole;
    private JLabel numerLinii_etykieta;
    private JTextField iloscWagonow_pole;
    private JLabel iloscWagonow_etykieta;

    public EdycjaTramwaju(Tramwaj tram) {

        this.tram = tram;
    }

    public void edycja(String sciezka){

        frame = new JFrame("Edycja Pojazdu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        kontener = frame.getContentPane();
        layout = new GridLayout(6,2);
        kontener.setLayout(layout);

        maksPredkosc_etykieta = new JLabel("Predkosc Maksymalna[km/h]");
        kontener.add(maksPredkosc_etykieta);
        maksPredkosc_pole = new JTextField( "" + tram.getMaksPredkosc() ,40);
        kontener.add(maksPredkosc_pole);

        zasilanie_etykieta = new JLabel("Rodzaj zasilania");
        kontener.add(zasilanie_etykieta);
        zasilanie_pole = new JTextField( "" + tram.getRodzajZasilania() ,40);
        kontener.add(zasilanie_pole);

        liczbaPasazerow_etykieta = new JLabel("Liczba Pasazerow");
        kontener.add(liczbaPasazerow_etykieta);
        liczbaPasazerow_pole = new JTextField( "" + tram.getliczbaPasazerow() ,40);
        kontener.add(liczbaPasazerow_pole);

        iloscWagonow_etykieta = new JLabel("Ilosc Wagonow");
        kontener.add(iloscWagonow_etykieta);
        iloscWagonow_pole = new JTextField( "" + tram.getIloscWagonow() ,40);
        kontener.add(iloscWagonow_pole);

        numerLinii_etykieta = new JLabel("Numer Linii");
        kontener.add(numerLinii_etykieta);
        numerLinii_pole = new JTextField( "" + tram.getNumerLinii() ,40);
        kontener.add(numerLinii_pole);

        JButton zastosuj = new JButton("zastosuj");
        zastosuj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tram.setNumerLinii(numerLinii_pole.getText());
                tram.setLiczbaPasazerow (Integer.parseInt(liczbaPasazerow_pole.getText()));
                tram.setIloscWagonow ( Integer.parseInt(iloscWagonow_pole.getText()));
                tram.setRodzajZasilania (zasilanie_pole.getText());
                tram.setMaksPredkosc (Integer.parseInt(maksPredkosc_pole.getText()));
                System.out.println(tram.toString());
            }
        });
        kontener.add(zastosuj);

        JButton zapisz = new JButton("zapisz");
        zapisz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tram.setNumerLinii(numerLinii_pole.getText());
                tram.setLiczbaPasazerow (Integer.parseInt(liczbaPasazerow_pole.getText()));
                tram.setIloscWagonow ( Integer.parseInt(iloscWagonow_pole.getText()));
                tram.setRodzajZasilania (zasilanie_pole.getText());
                tram.setMaksPredkosc (Integer.parseInt(maksPredkosc_pole.getText()));

                try {
                    serializacja(sciezka);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                System.out.println(tram.toString());
            }
        });
        kontener.add(zapisz);

        frame.pack();
        frame.setVisible(true);
    }

    public void serializacja (String sciezka)  throws IOException {

        ObjectOutputStream output = new ObjectOutputStream( new BufferedOutputStream( new FileOutputStream(sciezka)));
        output.reset();
        output.writeObject(tram);
        output.close();

    }

}
