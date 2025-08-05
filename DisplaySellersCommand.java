package ShaharAndYahli;

public class DisplaySellersCommand implements Command {
    private MarketInterface market;

    public DisplaySellersCommand(MarketInterface market) {
        this.market = market;
    }

    @Override
    public void execute() {
        market.displaySellerDetails();
    }
}
