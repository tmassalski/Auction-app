package pl.example.tmauctionapp.infrastructure.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.example.tmauctionapp.domain.order.Order;
import pl.example.tmauctionapp.domain.order.OrderRetrievalClient;
import pl.example.tmauctionapp.domain.order.OrderStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderRetrievalPostgresClient implements OrderRetrievalClient {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAllByStatus(OrderStatus status) {
       return orderRepository.findAllByStatus(status);
    }
}
