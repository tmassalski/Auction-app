package pl.tmassalski.auctionapp.domain.auction;

public interface AuctionRetrievalClient {
    Auction getActiveByIdOrThrow(long id);
}
