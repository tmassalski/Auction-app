package pl.example.tmauctionapp.domain.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

@Service
@RequiredArgsConstructor
public class AuctionScheduler {

    private final AuctionRetrievalClient auctionRetrievalClient;
    private final UpdateAuctionClient updateAuctionClient;

    void scheduleDeactivation(Long auctionId, LocalDateTime auctionEndDate) {
        Timer auctionTimer = new Timer();
        TimerTask deactivateAuction = new TimerTask() {
            @Override
            @Transactional
            public void run() {
                Auction auction = auctionRetrievalClient.getActiveByIdOrThrow(auctionId);
                auction.deactivate();
                updateAuctionClient.update(auction);
            }
        };
        auctionTimer.schedule(deactivateAuction, Timestamp.valueOf(auctionEndDate));
    }
}
