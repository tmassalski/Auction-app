package pl.tmassalski.auctionapp.domain.auction;

public interface QuantityChanger {
    void reduceQuantity (Auction auction, int reduceByQty);
}
