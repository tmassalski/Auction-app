package pl.example.tmauctionapp.infrastructure.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.example.tmauctionapp.domain.auction.Auction;

import pl.example.tmauctionapp.domain.auction.AuctionException;
import pl.example.tmauctionapp.domain.auction.AuctionRetrievalClient;

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
