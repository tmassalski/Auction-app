package pl.example.tmauctionapp.domain.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.tmauctionapp.domain.auction.Auction;
import pl.example.tmauctionapp.domain.auction.AuctionRetrievalClient;
import pl.example.tmauctionapp.domain.auction.QuantityChanger;
import pl.example.tmauctionapp.domain.payment.PaymentSenderClient;

@Service
@RequiredArgsConstructor
class OrderCreator {

    private final AuctionRetrievalClient auctionRetrievalClient;
    private final CreateOrderClient createOrderClient;
    private final QuantityChanger quantityChanger;
    private final PaymentSenderClient paymentSenderClient;

    @Transactional
    public void createOrderAndUpdateAuction(OrderDto orderDto) {
        Auction auction = auctionRetrievalClient.getActiveByIdOrThrow(orderDto.getAuctionId());
        if (auction.getQuantity() >= orderDto.getQuantity()) {
            Order order = Order.generatePending(orderDto, auction);
            createOrderClient.create(order);
            quantityChanger.reduceQuantity(auction, order.getQuantity());
            paymentSenderClient.sendPayment(order);
        } else {
            throw new IllegalArgumentException(String
                    .format("Order quantity is higher then auction quantity [Order quantity = %d] [Auction quantity = %d]",
                            orderDto.getQuantity(),
                            auction.getQuantity()));
        }
    }
}
