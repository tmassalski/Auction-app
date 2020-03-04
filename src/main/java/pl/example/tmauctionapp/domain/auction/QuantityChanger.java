package pl.example.tmauctionapp.domain.auction;

public interface QuantityChanger {
    void reduceQuantity (Auction auction, int reduceByQty);
}
