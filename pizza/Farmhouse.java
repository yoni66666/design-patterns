package pizza;

public class Farmhouse implements Pizza {
    private int price = 11;

    @Override
    public String getDescription() {
        return "Farmhouse";
    }

    public int getPrice() {
        return price;
    }
}
