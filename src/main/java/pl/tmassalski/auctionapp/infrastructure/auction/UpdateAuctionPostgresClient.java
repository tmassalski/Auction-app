package pl.tmassalski.auctionapp.infrastructure.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tmassalski.auctionapp.domain.auction.Auction;
import pl.tmassalski.auctionapp.domain.auction.UpdateAuctionClient;

@Service
@RequiredArgsConstructor
class UpdateAuctionPostgresClient implements UpdateAuctionClient {

    private final AuctionRepository auctionRepository;

    public void update(Auction auction) {
        auctionRepository.save(auction);
    }
}
