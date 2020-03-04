package pl.example.tmauctionapp.infrastructure.order;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.example.tmauctionapp.domain.order.Order;
import pl.example.tmauctionapp.domain.order.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByStatus(OrderStatus orderStatus);
}
