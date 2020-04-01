package pl.example.tmauctionapp.domain.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class AuctionCreator {

    private final CreateAuctionClient createAuctionClient;
    private final AuctionScheduler auctionScheduler;

    @Transactional
    public void create(AuctionCommand auctionCommand) {
        Auction auction = Auction.generateActive(auctionCommand);
        createAuctionClient.create(auction);
        auctionScheduler.scheduleDeactivation(auction);
    }
}
