package pl.example.tmauctionapp.domain.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuctionCreator {

    private final CreateAuctionClient createAuctionClient;

    @Transactional
    public void create(AuctionDto auctionDto) {
        Auction auction = Auction.generateActive(auctionDto);
        createAuctionClient.create(auction);
    }
}
