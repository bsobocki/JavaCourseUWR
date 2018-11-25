import java.io.Serializable;

/**
 * Created by Bartosz Sobocki on 2018-04-04.
 */

public class Coll<T> implements Serializable {

    public Element<T> last;
    public Element<T> first;

    public Coll() {
        this.first = null;
        this.last  = null;
    }

    public void addAtEnd (T arg) {
        Element<T> element = new Element<T>(arg);
        if(this.first == null) {
            this.first = element;
            this.last  = element;
        }
        else {
            this.last.next = element;
            element.prev = this.last;
            this.last = element;
        }
    }

    public void addAtBeginning(T arg){
        Element<T> element = new Element<T>(arg);
        if(this.first == null) {
            this.first = element;
            this.last  = element;
        }
        else {
            this.first.prev = element;
            element.next = this.first;
            this.first = element;
        }
    }

    public Element<T> deeleteFromEnd(){
        if(first!=null){
            if(first.next!=null){
                Element<T> tmp = this.last;
                this.last = this.last.prev;
                this.last.next = null;
                return tmp;
            }
            else{
                Element<T> tmp = this.last;
                this.first = null;
                this.last  = null;
                return tmp;
            }
        }
        System.out.println("Empty list!");
        return null;
    }

    public Element<T> deleteFromBeginning(){
        if(first!=null){
            if(this.first.next!=null){
                Element<T> tmp = this.first;
                this.first = this.first.next;
                this.first.prev = null;
                return tmp;
            }
            else {
                Element<T> tmp = this.first;
                this.first = null;
                this.last  = null;
                return tmp;
            }
        }
        System.out.println("Empty list!");
        return null;
    }

    public boolean isEmpty(){
        if(first == null)
            return true;
        return false;
    }

    public void write()
    {
        if(!isEmpty()){
            Element<T> tmp = this.first;
            while(tmp.next!=null){
                System.out.println(tmp.object);
                tmp = tmp.next;
            }
            System.out.println(this.last.object);
        }
        else
            System.out.println("Empty list!");
    }

    /** ------ */

    class Element<T> implements Serializable{

        public T object;
        public Element<T> next;
        public Element<T> prev;

        public Element (T object){
            this.object = object;
            this.next = null;
            this.prev = null;
        }
    }
}

