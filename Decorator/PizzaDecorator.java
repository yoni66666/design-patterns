package pizza;

public abstract class PizzaDecorator implements Pizza{
    protected Pizza decoratorPizza;

    public PizzaDecorator(Pizza decoratorPizza) {
        this.decoratorPizza = decoratorPizza;
    }

    public PizzaDecorator(){};

}
