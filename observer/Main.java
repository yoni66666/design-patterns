package il.co.ilrd.observer;


public class Main {

    public static void main(String[] args) {

        Observer jonathan = new Observer();

        Subject paper = new Subject();

        jonathan.subscribe(paper);

        paper.dispatcher.notifyALL("got a new paper today");

        jonathan.unsubscribe();

        paper.dispatcher.notifyALL("without yoni ");

        paper.dispatcher.stopService();

    }
}
