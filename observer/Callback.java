/**
 * Name: yoni
 * Reviewer: tali
 * Exercise: observer
 */

package il.co.ilrd.observer;
import java.util.function.Consumer;

public class Callback<T> {
    private final Consumer<T> notifyConsumer;
    private final Runnable stopRunnable;

    public Dispatcher<T> dispatcher;

    public Callback(Consumer<T> notify, Runnable stop) {
        if(notify == null){
            throw new NullPointerException();
        }

        notifyConsumer = notify;
        stopRunnable = stop;
    }
    public void setDispatcher(Dispatcher<T> dispatcher){
        this.dispatcher = dispatcher;
    }

    public void notify(T t) {
        notifyConsumer.accept(t);
    }
    public void stop() {
        if (stopRunnable != null) {
            stopRunnable.run();
        }
    }
}