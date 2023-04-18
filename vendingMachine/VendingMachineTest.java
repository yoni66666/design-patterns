/*
Name: Jonathan
Reviewer: Tsach
Description: vendingMachine
*/

package il.co.ilrd.vendingMachine;

import il.co.ilrd.linkedlist.LinkedListIterator;
import org.junit.BeforeClass;
import org.junit.Test;
import static java.lang.Thread.sleep;
import java.util.Scanner;


import java.util.ArrayList;
import java.util.List;

public class VendingMachineTest {

    static class item implements VendingMachineItem {

        private int price;
        private String name;

        public item (int price, String name){
            this.price = price;
            this.name = name;
        }

        @Override
        public int getPrice(){
            return price;
        };
        @Override
        public String getName(){
            return name;
        };
    }

    public static void main(String[] args) throws InterruptedException {
        VendingMachine vm;
        Display display;
        int[] price = {10, 8, 12, 4};
        String[] name = {"Gum", "Apple", "Water", "Banana"};
        List<VendingMachineItem> listOfItem = new ArrayList<VendingMachineItem>();

        for (int i = 0; i < price.length; ++i) {
            listOfItem.add(new item(price[i], name[i]));
        }

        display = new Display() {};
        vm = new VendingMachine(listOfItem, display);

        vm.turnOn();
        vm.insertMoney(30);
        vm.selectItem("Apple");
        vm.insertMoney(2);
        vm.selectItem("Banana");
        vm.insertMoney(2);
/*
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter selectItem");
        String userName = myObj.nextLine();  // Read user input
        vm.selectItem(userName);*/
        /*
        try {
            sleep(10000);
        }
        catch (Exception error){}
*/
        vm.turnOff();
    }

}