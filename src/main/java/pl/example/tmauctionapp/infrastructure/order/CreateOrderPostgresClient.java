package pl.example.tmauctionapp.infrastructure.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.example.tmauctionapp.domain.order.CreateOrderClient;
import pl.example.tmauctionapp.domain.order.Order;

@Service
@RequiredArgsConstructor
public class CreateOrderPostgresClient implements CreateOrderClient {

    private final OrderRepository orderRepository;

    @Override
    public void create(Order order) {
        orderRepository.save(order);
    }
}
