package pl.example.tmauctionapp.domain.order;

import java.util.List;

public interface OrderRetrievalClient {

   List<Order> getAllByStatus(OrderStatus status);
}
