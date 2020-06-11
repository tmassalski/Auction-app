package pl.tmassalski.auctionapp.domain.auction;

public class AuctionException extends RuntimeException {

    public AuctionException(String message) {
        super(message);
    }

    public static AuctionException auctionIdNotFound(long id) {
        return new AuctionException(String.format("Auction not found [id=%s]", id));
    }
    public static AuctionException auctionIsInactive(long id) {
        return new AuctionException(String.format("Auction is inactive [id=%s]", id));
    }
}
