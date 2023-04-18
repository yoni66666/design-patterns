package pizza;

public class PeppyPaneer implements Pizza {
    private int price = 10;

    @Override
    public String getDescription() {
        return "PeppyPaneer";
    }

    public int getPrice() {
        return price;
    }
}
