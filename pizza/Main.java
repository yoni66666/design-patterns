package pizza;

public class Main {
    public static void main(String[] args) {
        Pizza myPizza = new PeppyPaneer();
        System.out.println("price of  "+ myPizza.getDescription() + "is: " + myPizza.getPrice());
        //Pizza myBBqPizza = new BBQ(myPizza);
        //Pizza myBBqPizza = new BBQ(myPizza);
        myPizza = new BBQ(myPizza);
        myPizza = new FreshTomato(myPizza);
        System.out.println("price of "+ myPizza.getDescription() + "is: " + myPizza.getPrice());
    }
}
