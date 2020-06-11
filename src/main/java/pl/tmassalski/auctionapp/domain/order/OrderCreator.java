package pl.tmassalski.auctionapp.domain.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tmassalski.auctionapp.domain.auction.Auction;
import pl.tmassalski.auctionapp.domain.auction.AuctionRetrievalClient;
import pl.tmassalski.auctionapp.domain.auction.QuantityChanger;
import pl.tmassalski.auctionapp.domain.payment.PaymentSenderClient;

@Service
@RequiredArgsConstructor
class OrderCreator {

    private final AuctionRetrievalClient auctionRetrievalClient;
    private final CreateOrderClient createOrderClient;
    private final QuantityChanger quantityChanger;
    private final PaymentSenderClient paymentSenderClient;

    @Transactional
    public void createOrderAndUpdateAuction(OrderCommand orderCommand) {
        Auction auction = auctionRetrievalClient.getActiveByIdOrThrow(orderCommand.getAuctionId());
        if (auction.getQuantity() >= orderCommand.getQuantity()) {
            Order order = Order.generatePending(orderCommand, auction);
            createOrderClient.create(order);
            quantityChanger.reduceQuantity(auction, order.getQuantity());
            paymentSenderClient.sendPayment(order);
        } else {
            throw new IllegalArgumentException(String
                    .format("Order quantity is higher then auction quantity [Order quantity = %d] [Auction quantity = %d]",
                            orderCommand.getQuantity(),
                            auction.getQuantity()));
        }
    }
}
