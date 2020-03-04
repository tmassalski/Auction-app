package pl.example.tmauctionapp.domain.payment;
import pl.example.tmauctionapp.domain.order.Order;

public interface PaymentSenderClient {
    Payment sendPayment(Order order);
}
