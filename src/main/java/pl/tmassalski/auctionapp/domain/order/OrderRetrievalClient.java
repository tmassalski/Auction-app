package pl.tmassalski.auctionapp.domain.order;

import java.util.List;

public interface OrderRetrievalClient {

    List<Order> getPendingOrders();
}
