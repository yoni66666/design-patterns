package pizza;

public class FreshTomato extends PizzaDecorator {

    private int price = 1;

    public FreshTomato(Pizza decoratorPizza) {
        super(decoratorPizza);
    }

    @Override
    public String getDescription() {
        return decoratorPizza.getDescription() + "FreshTomato";
    }

    @Override
    public int getPrice() {
        return decoratorPizza.getPrice() + price;
    }
}
