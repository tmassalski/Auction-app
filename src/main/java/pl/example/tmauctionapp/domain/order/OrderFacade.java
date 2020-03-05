package pl.example.tmauctionapp.domain.order;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.example.tmauctionapp.domain.payment.Payment;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderCreator orderCreator;
    private final OrderRetrievalClient orderRetrievalClient;

    public void createOrder(OrderDto orderDto) {
        orderCreator.createOrderAndUpdateAuction(orderDto);
    }

    public List<Payment> getAllPaymentsByOrderStatus(OrderStatus status) {
        return orderRetrievalClient.getAllByStatus(status).stream()
                .map(Payment::generate)
                .collect(Collectors.toList());
    }
}
