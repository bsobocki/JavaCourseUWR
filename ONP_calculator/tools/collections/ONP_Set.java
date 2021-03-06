package tools.collections;

import tools.Expression;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class ONP_Set {
    private TreeMap<String, Double> set;

    public ONP_Set(){
        set = new TreeMap<>();
    }
    public void add(String name, Double val){
        set.put(name,val);
    }
    public Double get(String name){
        return set.get(name);
    }
    public void clear(String name){
        ONP_List list = Expression.onp_symbols_prefix(name);
        if(list.isEmpty())
            set.clear();
        else
            for(String n: list.getList())
                set.remove(n);
    }
    public void write() {
        for(String key:set.keySet()){
            System.out.println(key+" -> "+set.get(key));
        }
    }
}
