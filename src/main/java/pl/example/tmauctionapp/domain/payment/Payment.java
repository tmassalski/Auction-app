package pl.example.tmauctionapp.domain.payment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.example.tmauctionapp.domain.order.Order;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private long ownerId;
    private long ownerAccountId;
    private String clientAccountNumber;
    private BigDecimal totalPrice;
    private String title;

    public static Payment generate(Order order) {
        Payment payment = new Payment();
        payment.ownerId = order.getOwnerId();
        payment.ownerAccountId = order.getOwnerAccountId();
        payment.clientAccountNumber = order.getClientAccountNumber();
        payment.totalPrice = order.getUnitPrice().multiply(new BigDecimal(order.getQuantity()));
        payment.title = String.format("%d, %d, %d", order.getId(), order.getOwnerId(), order.getClientId());
        return payment;
    }
}
