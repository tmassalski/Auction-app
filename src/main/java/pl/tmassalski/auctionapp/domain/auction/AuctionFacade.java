package pl.tmassalski.auctionapp.domain.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuctionFacade {

    private final AuctionCreator auctionCreator;

    public void createAuction(AuctionCommand auctionCommand) {
        auctionCreator.create(auctionCommand);
    }
}
