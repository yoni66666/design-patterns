/**
 * Name: yoni
 * Reviewer: Sasha
 * Exercise: observer
 */

package il.co.ilrd.observer;

import java.util.LinkedList;
import java.util.List;

public class Dispatcher<T> {
    private List<Callback<T>> callbacksList = new LinkedList<>();

    public void notifyALL(T message) {
        for(Callback<T> call : callbacksList){
            call.notify(message);
        }
       // callbacksList.clear();
    }
    public void stopService() {
        for (Callback<T> call : callbacksList){
            call.stop();
        }
    }

    public void registerCallback(Callback<T> func) {
        if (func == null){
            return;
        }
        callbacksList.add(func);
        func.setDispatcher(this);
    }
    public void unregisterCallback(Callback<T> func) {
        func.setDispatcher(null);
        callbacksList.remove(func);
    }
}