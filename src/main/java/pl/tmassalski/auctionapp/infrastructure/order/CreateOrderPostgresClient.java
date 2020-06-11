package pl.tmassalski.auctionapp.infrastructure.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tmassalski.auctionapp.domain.order.CreateOrderClient;
import pl.tmassalski.auctionapp.domain.order.Order;

@Service
@RequiredArgsConstructor
class CreateOrderPostgresClient implements CreateOrderClient {

    private final OrderRepository orderRepository;

    @Override
    public void create(Order order) {
        orderRepository.save(order);
    }
}
