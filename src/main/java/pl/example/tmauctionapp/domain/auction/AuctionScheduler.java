package pl.example.tmauctionapp.domain.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

@Service
@RequiredArgsConstructor
public class AuctionScheduler {

    private final UpdateAuctionClient updateAuctionClient;

    void scheduleDeactivation(Auction auction) {
        Timer auctionTimer = new Timer();
        TimerTask auctionTimerTask = new TimerTask() {
            @Override
            public void run() {
                auction.deactivate();
                updateAuctionClient.update(auction);
            }
        };
        auctionTimer.schedule(auctionTimerTask, Timestamp.valueOf(auction.getEndDate()));
    }
}
