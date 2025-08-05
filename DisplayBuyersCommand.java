package ShaharAndYahli;

public class DisplayBuyersCommand implements Command {
    private MarketInterface market;

    public DisplayBuyersCommand(MarketInterface market) {
        this.market = market;
    }

    @Override
    public void execute() {
        market.displayBuyerDetails();
    }
}
