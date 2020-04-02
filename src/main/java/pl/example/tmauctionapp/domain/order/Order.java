package pl.example.tmauctionapp.domain.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import pl.example.tmauctionapp.domain.auction.Auction;
import pl.example.tmauctionapp.shared.Auditable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@Table(name = "ORDERS")
public class Order extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "order_sequence")
    @SequenceGenerator(name = "order_sequence", initialValue = 3)
    private long id;
    private long auctionId;
    private long ownerId;
    private long ownerAccountId;
    private BigDecimal unitPrice;
    private long clientId;
    private String clientAccountNumber;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    static Order generatePending(OrderCommand orderCommand, Auction auction) {
        return Order.builder().auctionId(orderCommand.getAuctionId())
                .ownerId(auction.getOwnerId())
                .ownerAccountId(auction.getOwnerAccountId())
                .unitPrice(auction.getPrice())
                .clientId(orderCommand.getClientId())
                .clientAccountNumber(orderCommand.getClientAccountNumber())
                .quantity(orderCommand.getQuantity())
                .status(OrderStatus.PENDING)
                .build();
    }

    public void setPaidStatus() {
        this.status = OrderStatus.PAID;
    }
}
