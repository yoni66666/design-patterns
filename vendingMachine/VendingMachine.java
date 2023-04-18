/*
Name: Jonathan
Reviewer: Tsach
Description: vendingMachine
*/
package il.co.ilrd.vendingMachine;

import java.util.List;
import java.time.LocalTime;
import java.util.Locale;

import static java.lang.Thread.sleep;

public class VendingMachine {
    private VendingMachineState currentState;
    private List<VendingMachineItem> vendingMachineItems;
    private int balance;
    private static Display displayVm;
    private MyThread timeoutThread;

    private static final int INTERVAL_TIME = 5;

    public VendingMachine(List<VendingMachineItem> vendingMachineItems, Display display) {
        currentState = VendingMachineState.OFF_STATE ;
        balance = 0;
        displayVm = display;
        this.vendingMachineItems = vendingMachineItems;
        timeoutThread = new MyThread();
    }

    public void turnOn() {
        currentState.turnOn(this);
    }

    //move to enum
    public void turnOff() {
        currentState.turnOff(this);
    }

    public void insertMoney(int amount) {
        currentState.insertMoney(this, amount);
    }

    public void selectItem(String itemName) {
        currentState.selectItem(this, itemName);
    }

    public void cancel() {
        currentState.cancel(this);
    }


    /* עדיף לעשות implements מכיוון שאפשר לעשות ככה מימוש לעוד מחלקות, ואחרת אם היינו עושים extend אז היינו תקועים */
    private class MyThread extends Thread{
        private boolean isRunning = true;
        @Override
        public void run(){
            while (isRunning == true){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                currentState.timeOut(VendingMachine.this);
            }
        }
}

    private enum VendingMachineState {

        OFF_STATE {
            @Override
            public void turnOn(VendingMachine machine) {
                machine.currentState = WAITING_FOR_MONEY_STATE;
                displayVm.PrintMessageToUser("got turn on");

                machine.timeoutThread.isRunning = true;
                machine.timeoutThread.start();
                System.out.println("run timeoutThread");
            }
            @Override
            public void insertMoney(VendingMachine machine, int amount) {
                machine.currentState = OFF_STATE;
            }
            @Override
            public void selectItem(VendingMachine machine, String itemName) {
                machine.currentState = OFF_STATE;
            }
            @Override
            public void cancel(VendingMachine machine) {
                machine.currentState = OFF_STATE;
            }

            @Override
            public void timeOut(VendingMachine machine) {
                System.out.println("timeOut in state off");
            }
        },

        WAITING_FOR_MONEY_STATE{
            @Override
            public void turnOn(VendingMachine machine) {
                machine.currentState = WAITING_FOR_MONEY_STATE;
            }

            @Override
            public void insertMoney(VendingMachine machine, int amount) {
                machine.currentState = WAITING_FOR_ITEM_SELECTION_STATE;
                // need to check if amount < 0 ???
                machine.balance = machine.balance + amount;
                displayVm.PrintMessageToUser(amount + " is add to balance. now balance is: " + machine.balance);
            }
            @Override
            public void selectItem(VendingMachine machine, String itemName) {
                displayVm.PrintMessageToUser("need to insert money before selectItem");
                machine.currentState = WAITING_FOR_MONEY_STATE;
            }
            @Override
            public void cancel(VendingMachine machine) {
                machine.currentState = WAITING_FOR_MONEY_STATE;
                System.out.println("machine is waiting for insert money");
            }

            public void timeOut(VendingMachine machine) {
                System.out.println("timeOut in WAITING_FOR_MONEY_STATE");
            }
        },
        WAITING_FOR_ITEM_SELECTION_STATE{
            @Override
            public void turnOn(VendingMachine machine) {
                machine.currentState = WAITING_FOR_MONEY_STATE;
            }

            @Override
            public void insertMoney(VendingMachine machine, int amount) {
                machine.currentState = WAITING_FOR_ITEM_SELECTION_STATE;
                displayVm.PrintMessageToUser("need do select Item");
            }
            @Override
            public void selectItem(VendingMachine machine, String itemName) {
                //to do in func...
                for (VendingMachineItem item : machine.vendingMachineItems){
                    if(itemName.equals(item.getName())){
                        if (item.getPrice() <= machine.balance){
                            machine.balance = machine.balance - item.getPrice();
                            displayVm.PrintMessageToUser("great, you go your Item " + itemName + " with price: " + item.getPrice() + " new balance is " + machine.balance);
                            machine.currentState = WAITING_FOR_MONEY_STATE;
                        }
                        else {
                            displayVm.PrintMessageToUser("need to put more money for this item with price " + item.getPrice());
                            machine.currentState = WAITING_FOR_ITEM_SELECTION_STATE;
                        }
                        return;
                    }
                }
                displayVm.PrintMessageToUser("not found: " + itemName);
            }

            @Override
            public void cancel(VendingMachine machine) {
                machine.balance = 0;
                machine.currentState = WAITING_FOR_MONEY_STATE;
                System.out.println("machine return your money");
            }
            @Override
            public void timeOut(VendingMachine machine) {
                LocalTime dest = LocalTime.now().plusSeconds(INTERVAL_TIME);
                while (0 < dest.compareTo(LocalTime.now()) && machine.currentState == WAITING_FOR_ITEM_SELECTION_STATE){
                }

                if (machine.currentState == WAITING_FOR_ITEM_SELECTION_STATE){
                    machine.cancel();
                }
            }
        };

        abstract public void turnOn(VendingMachine state);

        public abstract void insertMoney(VendingMachine machine, int amount);

        public abstract void selectItem(VendingMachine machine, String itemName);

        public abstract void cancel(VendingMachine machine);

        public abstract void timeOut(VendingMachine machine);

        public void turnOff(VendingMachine machine) {
            machine.currentState = VendingMachineState.OFF_STATE;
            machine.balance = 0;
            machine.timeoutThread.isRunning = false;
        }
    }
}
