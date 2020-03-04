package pl.example.tmauctionapp.domain.order;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderFacade  {

    private final OrderCreator orderCreator;

    public void createOrder(OrderDto orderDto) {
        orderCreator.createOrderAndUpdateAuction(orderDto);
    }
}
