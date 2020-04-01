package pl.example.tmauctionapp.domain.order;


import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.example.tmauctionapp.domain.payment.Payment;
import pl.example.tmauctionapp.domain.payment.PaymentSenderClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderCreator orderCreator;
    private final OrderRetrievalClient orderRetrievalClient;
    private final PaymentSenderClient paymentSenderClient;

    public void createOrder(OrderCommand orderCommand) {
        orderCreator.createOrderAndUpdateAuction(orderCommand);
    }

    @Scheduled(fixedDelay = 10000)
    public void processPendingPayments() {
        List<Order> pendingOrders = orderRetrievalClient.getPendingOrders();
        if (!pendingOrders.isEmpty()) {
            paymentSenderClient.sendPendingPayments(pendingOrders);
        }
    }

    public List<Payment> getAllPaymentsByOrderStatus() {
        return orderRetrievalClient.getPendingOrders().stream()
                .map(Payment::generate)
                .collect(Collectors.toList());
    }
}
