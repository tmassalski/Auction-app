package pl.tmassalski.auctionapp.domain.payment;
import pl.tmassalski.auctionapp.domain.order.Order;

public interface PaymentSenderClient {

    void sendPayment(Order order);
}
