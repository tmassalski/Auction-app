package pl.tmassalski.auctionapp.infrastructure.auction;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tmassalski.auctionapp.domain.auction.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
}
