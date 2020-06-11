package pl.tmassalski.auctionapp.domain.order;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.tmassalski.auctionapp.domain.payment.Payment;
import pl.tmassalski.auctionapp.domain.payment.PaymentSenderClient;

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

    @Scheduled(cron = "0 */15 * ? * *")
    public void processPendingOrders() {
        List<Order> pendingOrders = orderRetrievalClient.getPendingOrders();
        if (!pendingOrders.isEmpty()) {
            pendingOrders.forEach(paymentSenderClient::sendPayment);
        }
    }

    public List<Payment> getAllPaymentsByOrderStatus() {
        return orderRetrievalClient.getPendingOrders().stream()
                .map(Payment::generate)
                .collect(Collectors.toList());
    }
}
