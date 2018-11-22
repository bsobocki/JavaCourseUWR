import java.io.Serializable;

/**
 * Created by Bartosz Sobocki on 2018-04-04.
 */

public class Kolekcja<T> implements Serializable {

    public Element<T> ostatni;
    public Element<T> pierwszy;

    public Kolekcja() {
        this.pierwszy = null;
        this.ostatni  = null;
    }

    public void DodajNaKoniec (T nowy) {
        Element<T> element = new Element<T>(nowy);
        if(this.pierwszy == null) {
            this.pierwszy = element;
            this.ostatni  = element;
        }
        else {
            this.ostatni.nastepny = element;
            element.poprzedni = this.ostatni;
            this.ostatni = element;
        }
    }

    public void DodajNaPoczatek(T nowy){
        Element<T> element = new Element<T>(nowy);
        if(this.pierwszy == null) {
            this.pierwszy = element;
            this.ostatni  = element;
        }
        else {
            this.pierwszy.poprzedni = element;
            element.nastepny = this.pierwszy;
            this.pierwszy = element;
        }
    }

    public Element<T> UsunZKonca(){
        if(pierwszy!=null){
            if(pierwszy.nastepny!=null){
                Element<T> tmp = this.ostatni;
                this.ostatni = this.ostatni.poprzedni;
                this.ostatni.nastepny = null;
                return tmp;
            }
            else{
                Element<T> tmp = this.ostatni;
                this.pierwszy = null;
                this.ostatni  = null;
                return tmp;
            }
        }
        System.out.println("Lista pusta!");
        return null;
    }

    public Element<T> UsunZPoczatku(){
        if(pierwszy!=null){
            if(this.pierwszy.nastepny!=null){
                Element<T> tmp = this.pierwszy;
                this.pierwszy = this.pierwszy.nastepny;
                this.pierwszy.poprzedni = null;
                return tmp;
            }
            else {
                Element<T> tmp = this.pierwszy;
                this.pierwszy = null;
                this.ostatni  = null;
                return tmp;
            }
        }
        System.out.println("Lista pusta!");
        return null;
    }

    public boolean CzyPusta(){
        if(pierwszy == null)
            return true;
        return false;
    }

    public void wypisz()
    {
        if(!CzyPusta()){
            Element<T> tmp = this.pierwszy;
            while(tmp.nastepny!=null){
                System.out.println(tmp.obiekt);
                tmp = tmp.nastepny;
            }
            System.out.println(this.ostatni.obiekt);
        }
        else
            System.out.println("Lista pusta!");
    }

}

class Element<T> implements Serializable{

    public T obiekt;
    public Element<T> nastepny;
    public Element<T> poprzedni;

    public Element (T obiekt){
        this.obiekt = obiekt;
        this.nastepny = null;
        this.poprzedni = null;
    }
}