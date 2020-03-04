package pl.example.tmauctionapp.domain.auction;

public interface AuctionRetrievalClient {
    Auction getActiveByIdOrThrow(long id);
}
