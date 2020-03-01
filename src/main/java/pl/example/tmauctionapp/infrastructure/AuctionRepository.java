package pl.example.tmauctionapp.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.example.tmauctionapp.domain.auction.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
}
