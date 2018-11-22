import java.io.*;
//import java.lang.ArrayIndexOutOfBoundsException;

/**
 * Created by Leszek on 2018-04-12.
 */

public class Program {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        if (args.length == 0) {
            System.out.println("Nie przekazano żadnych parametrów wywołania");
        }
        else {

            File file = new File(args[0]);

            if (file.isFile()) {
                deserializacja(args);
            }
            else {

                if(args[1].compareTo("Samochod")==0) {

                    Samochod car = new Samochod();
                    EdycjaSamochodu edycja = new EdycjaSamochodu(car);
                    edycja.edycja(args[0]);
                }
                else if (args[1].compareTo("Tramwaj")==0) {

                    Tramwaj tram = new Tramwaj();
                    EdycjaTramwaju edycja = new EdycjaTramwaju(tram);
                    edycja.edycja(args[0]);
                }
                else {
                    System.out.println("Podano bledna nazwe klasy!");
                }


            }
        }
    }

    public static void deserializacja(String[] args) throws IOException, ClassNotFoundException {

        ObjectInputStream input = new ObjectInputStream( new BufferedInputStream( new FileInputStream(args[0])));
        if(args[1].compareTo("Samochod")==0) {
            Samochod car = (Samochod)input.readObject();
            EdycjaSamochodu edycja = new EdycjaSamochodu(car);
            edycja.edycja(args[0]);
        }
        else if (args[1].compareTo("Tramwaj")==0) {
            Tramwaj tram = (Tramwaj)input.readObject();
            EdycjaTramwaju edycja = new EdycjaTramwaju(tram);
            edycja.edycja(args[0]);
        }

    }
}
