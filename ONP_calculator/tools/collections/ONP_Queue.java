package tools.collections;

import tools.symbol.Symbol;
import java.util.ArrayDeque;

public class ONP_Queue {
    private ArrayDeque<Symbol> queue;

    public ONP_Queue(){
        queue = new ArrayDeque<>();
    }
    public void add(Symbol sym){
        queue.addLast(sym);
    }
    public Symbol remove(){
        return queue.removeFirst();
    }
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
