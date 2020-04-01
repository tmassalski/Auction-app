package pl.example.tmauctionapp.infrastructure.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.example.tmauctionapp.domain.auction.Auction;
import pl.example.tmauctionapp.domain.auction.UpdateAuctionClient;

@Service
@RequiredArgsConstructor
class UpdateAuctionPostgresClient implements UpdateAuctionClient {

    private final AuctionRepository auctionRepository;

    public void update(Auction auction) {
        auctionRepository.save(auction);
    }
}
