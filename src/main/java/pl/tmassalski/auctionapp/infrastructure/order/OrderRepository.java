package pl.tmassalski.auctionapp.infrastructure.order;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tmassalski.auctionapp.domain.order.Order;
import pl.tmassalski.auctionapp.domain.order.OrderStatus;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByStatus(OrderStatus orderStatus);
}
