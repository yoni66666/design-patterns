/**
 * Name: yoni
 * Reviewer: Sasha
 * Exercise: observer
 */
package il.co.ilrd.observer;

public class Subject {
    Dispatcher<String> dispatcher;

    public Subject() {
        this.dispatcher = new Dispatcher<>();
    }

    public Dispatcher<String> getDispatcher(){
        return dispatcher;
    }

    public void register(Callback<String> func) {
        dispatcher.registerCallback(func);
    }

    public void unregister(Callback<String> func) {
        dispatcher.unregisterCallback(func);
    }

}
