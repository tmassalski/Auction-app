package pl.tmassalski.auctionapp.infrastructure.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tmassalski.auctionapp.domain.auction.Auction;
import pl.tmassalski.auctionapp.domain.auction.CreateAuctionClient;

@Service
@RequiredArgsConstructor
    class CreateAuctionPostgresClient implements CreateAuctionClient {

    private final AuctionRepository auctionRepository;

    @Override
    public void create(Auction auction) {
        auctionRepository.save(auction);
    }
}
