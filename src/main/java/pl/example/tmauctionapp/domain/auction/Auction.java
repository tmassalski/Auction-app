package pl.example.tmauctionapp.domain.auction;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.example.tmauctionapp.shared.Auditable;

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
@Setter(AccessLevel.PRIVATE)
public class Auction extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auction_sequence")
    @SequenceGenerator(name = "auction_sequence", initialValue = 3)
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

    static Auction generateActive(AuctionCommand auctionCommand) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Auction auction = new Auction();
        auction.ownerId = auctionCommand.getOwnerId();
        auction.ownerAccountId = auctionCommand.getOwnerAccountId();
        auction.title = auctionCommand.getTitle();
        auction.description = auctionCommand.getDescription();
        auction.quantity = auctionCommand.getQuantity();
        auction.price = auctionCommand.getPrice();
        auction.startDate = currentDateTime;
        auction.endDate = currentDateTime.plusDays(auctionCommand.getExpirationDays());
        auction.active = true;
        return auction;
    }

    void reduceQuantityAndVerifyStatus(int reduceByQuantity) {
        this.quantity -= reduceByQuantity;
        if (this.quantity == 0) {
            deactivate();
        }
    }

    void deactivate() {
        this.active = false;
    }
}
