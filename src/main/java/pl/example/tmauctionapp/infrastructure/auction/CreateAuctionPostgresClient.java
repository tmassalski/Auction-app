package pl.example.tmauctionapp.infrastructure.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.example.tmauctionapp.domain.auction.Auction;
import pl.example.tmauctionapp.domain.auction.CreateAuctionClient;

@Service
@RequiredArgsConstructor
public class CreateAuctionPostgresClient implements CreateAuctionClient {

    private final AuctionRepository auctionRepository;

    @Override
    public void create(Auction auction) {
        auctionRepository.save(auction);
    }
}
