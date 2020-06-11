package pl.tmassalski.auctionapp.infrastructure.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tmassalski.auctionapp.domain.order.Order;
import pl.tmassalski.auctionapp.domain.order.OrderRetrievalClient;
import pl.tmassalski.auctionapp.domain.order.OrderStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
class OrderRetrievalPostgresClient implements OrderRetrievalClient {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getPendingOrders() {
       return orderRepository.findAllByStatus(OrderStatus.PENDING);
    }
}
