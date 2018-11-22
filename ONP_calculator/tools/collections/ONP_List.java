package tools.collections;

import java.util.LinkedList;

public class ONP_List {
    private LinkedList<String> list;

    public ONP_List(){
        list = new LinkedList<>();
    }

    public void add(String elem){
        list.add(elem);
    }

    public String remove(){
        return list.remove();
    }
    public void write(){
        for(String x:list){
            System.out.println(x);
        }
    }
    public LinkedList<String> getList(){
        return list;
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
}
