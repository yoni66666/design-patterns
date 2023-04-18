/*
Name: Jonathan
Reviewer: Tsach
Description: vendingMachine
*/

package il.co.ilrd.vendingMachine;

public interface Display {
    default void PrintMessageToUser(String message){
        System.out.println(message);
    }
}
