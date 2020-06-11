package pl.example.tmauctionapp.domain.payment;
import pl.example.tmauctionapp.domain.order.Order;

public interface PaymentSenderClient {

    void sendPayment(Order order);
}
