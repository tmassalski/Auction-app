package pl.tmassalski.auctionapp.infrastructure.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tmassalski.auctionapp.domain.auction.Auction;

import pl.tmassalski.auctionapp.domain.auction.AuctionException;
import pl.tmassalski.auctionapp.domain.auction.AuctionRetrievalClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuctionRetrievalPostgresClient implements AuctionRetrievalClient {

    private final AuctionRepository auctionRepository;

    @Override
    public Auction getActiveByIdOrThrow(long id) {
        Optional<Auction> optionalAuction = auctionRepository.findById(id);
        Auction auction = optionalAuction.orElseThrow(
                () -> AuctionException.auctionIdNotFound(id));
        if (!auction.isActive()) {
            throw AuctionException.auctionIsInactive(id);
        }
        return auction;
    }
}
