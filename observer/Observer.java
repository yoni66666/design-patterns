/**
 * Name: yoni
 * Reviewer: Sasha
 * Exercise: observer
 */
package il.co.ilrd.observer;

import il.co.ilrd.observer.Subject;
import il.co.ilrd.observer.Callback;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class Observer {

    Runnable runnableStop = new Runnable() {
        @Override
        public void run() {
            System.out.println("Stop the paper service");
        }
    };

    Consumer<String> notifyJonathan = (msg) -> System.out.println("notify for jonathan: " + msg);

    private final Callback<String> callback = new Callback<>(notifyJonathan, runnableStop);

    public Observer(){};


    public void subscribe(Subject sub) {
        sub.register(callback);
        //callback.setDispatcher(sub.getDispatcher());
    }

    public void unsubscribe() {
        callback.dispatcher.unregisterCallback(callback);
        //subjectList.get(0).unregister(callback);
    }
}

