import java.io.*;

/**
 * Created by Bartosz Sobocki on 2018-04-04.
 */

public class Serializacja {
    public static void main(String[]args) throws IOException, ClassNotFoundException {

        System.out.println("Listy :");

        Kolekcja<Integer> kol = new Kolekcja<>();
        Kolekcja<String> kol2 = new Kolekcja<>();

        for(int i=0;i<=5; i++)
            kol.DodajNaPoczatek(i);

        String a = "k";
        for (int i=0; i<=5; i++)
            kol2.DodajNaKoniec(a + Integer.toString(i));

        kol.wypisz();
        System.out.println();
        kol2.wypisz();
        System.out.println();

        System.out.println("Serializacja \n");
        ObjectOutputStream output = new ObjectOutputStream( new BufferedOutputStream( new FileOutputStream("plik.ser")));

        output.writeObject("Listy :");
        output.writeObject(kol);
        output.writeObject(kol2);
        output.close();

        System.out.println("Deserializacja \n");
        ObjectInputStream input = new ObjectInputStream( new BufferedInputStream( new FileInputStream("plik.ser")));


        System.out.println(input.readObject());
        System.out.println();

        Kolekcja<Integer> i = (Kolekcja<Integer>)input.readObject();
        i.wypisz();
        System.out.println();
        i = (Kolekcja<Integer>)input.readObject();
        i.wypisz();
    }
}
