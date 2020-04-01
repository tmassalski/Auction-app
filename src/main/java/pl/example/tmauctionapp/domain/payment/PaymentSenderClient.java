package pl.example.tmauctionapp.domain.payment;
import pl.example.tmauctionapp.domain.order.Order;

import java.util.List;

public interface PaymentSenderClient {

    void sendPayment(Order order);
    void sendPendingPayments(List<Order> pendingOrders);
}
