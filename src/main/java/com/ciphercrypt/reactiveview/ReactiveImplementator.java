package com.ciphercrypt.reactiveview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by user on 18/06/2018.
 */

public class ReactiveImplementator {
    protected List<Reactive> listReactive;

    public ReactiveImplementator(){
        listReactive = new ArrayList<>();
    }

    public void register(Collection<? extends Reactive> elements){
        listReactive.addAll(elements);
    }

    public <T extends Reactive> void register(T element){
        listReactive.add(element);
    }

    public void removeAll(){
        listReactive.clear();
    }

    public <T extends Reactive> boolean remove(T element){
        return listReactive.remove(element);
    }

    public void start(){
        for(Reactive reactive : listReactive){
            reactive.react();
        }
    }

    public <T extends Reactive> void startFor(T element){
        boolean isFound = false;
        for(Reactive reactive : listReactive){
            if(reactive.equals(element)){
                reactive.react();
                isFound = true;
                break;
            }
        }
        if(!isFound){
            element.react();
        }
    }
}
