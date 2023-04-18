package pizza;

public class BBQ extends PizzaDecorator {

    private int price = 5;

    public BBQ(Pizza decoratorPizza) {
        super(decoratorPizza);
    }

    @Override
    public String getDescription() {
        return decoratorPizza.getDescription() + " + BBQ";
    }

    @Override
    public int getPrice() {
        return decoratorPizza.getPrice() + price;
    }


}
