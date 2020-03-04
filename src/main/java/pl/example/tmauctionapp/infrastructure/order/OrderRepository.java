package pl.example.tmauctionapp.infrastructure.order;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.example.tmauctionapp.domain.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
