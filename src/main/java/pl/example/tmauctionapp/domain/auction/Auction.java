package pl.example.tmauctionapp.domain.auction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Auction extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auction_sequence")
    @SequenceGenerator(name = "auction_sequence")
    private long id;
    private long ownerId;
    private long ownerAccountId;
    private String title;
    private String description;
    private int quantity;
    private BigDecimal price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean active;

    public static Auction generateActive(AuctionDto auctionDto) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Auction auction = new Auction();
        auction.ownerId = auctionDto.getOwnerId();
        auction.ownerAccountId = auctionDto.getOwnerAccountId();
        auction.title = auctionDto.getTitle();
        auction.description = auctionDto.getDescription();
        auction.quantity = auctionDto.getQuantity();
        auction.price = auctionDto.getPrice();
        auction.startDate = currentDateTime;
        auction.endDate = currentDateTime.plusDays(auctionDto.getExpirationDays());
        auction.active = true;
        return auction;
    }
}
