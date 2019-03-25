import java.io.*;

/**
 * Created by Bartosz Sobocki on 2018-04-04.
 */

public class Serialize {
    public static void main(String[]args) throws IOException, ClassNotFoundException {

        System.out.println("Lists :");

        Coll<Integer> kol = new Coll<>();
        Coll<String> kol2 = new Coll<>();

        for(int i=0;i<=5; i++)
            kol.addAtBeginning(i);

        String a = "k";
        for (int i=0; i<=5; i++)
            kol2.addAtEnd(a + Integer.toString(i));

        kol.write();
        System.out.println();
        kol2.write();
        System.out.println();

        System.out.println("Serialize \n");
        ObjectOutputStream output = new ObjectOutputStream( new BufferedOutputStream( new FileOutputStream("plik.ser")));

        output.writeObject("Lists :");
        output.writeObject(kol);
        output.writeObject(kol2);
        output.close();

        System.out.println("DeSerialize \n");
        ObjectInputStream input = new ObjectInputStream( new BufferedInputStream( new FileInputStream("plik.ser")));


        System.out.println(input.readObject());
        System.out.println();

        Coll<Integer> i = (Coll<Integer>)input.readObject();
        i.write();
        System.out.println();
        i = (Coll<Integer>)input.readObject();
        i.write();
    }
}
