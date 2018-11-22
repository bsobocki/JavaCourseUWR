import tools.Expression;
import tools.exceptions.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Expression expr = new Expression();
        String line = scan.nextLine();
        String command = read(line)[0];
        String arg = read(line)[1];
        try {

            while (!command.equals("exit")) {

                if (command.equals("calc")) {
                    expr.setExpression(arg);
                    System.out.println(expr.calculate());
                } else if (command.equals("clear")) {
                    expr.getSet().clear(arg);
                } else if (command.equals("args")) {
                    expr.getSet().write();
                } else break;

                line = scan.nextLine();
                command = read(line)[0];
                arg = read(line)[1];
            }
        } catch (ONP_Exception exc) {
            System.out.println("Error: " + exc.getMessage());
        }


    }

    static String[] read(String str){
        //when user enters command without space, method may have a problem
        char[] tab = (str+' ').toCharArray();
        String[] strings = {"",""};
        int i = 0;
        while (tab[i]==' '){
            i++;
        }
        while(tab[i]!=' '){
            strings[0] += tab[i];
            i++;
        }
        i++;
        for(; i<tab.length; i++){
            strings[1]+=tab[i];
        }

        //return command
        return strings;
    }
}