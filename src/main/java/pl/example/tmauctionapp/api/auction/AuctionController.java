package pl.example.tmauctionapp.api.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.example.tmauctionapp.api.order.CreateOrderRequest;
import pl.example.tmauctionapp.domain.auction.AuctionCommand;
import pl.example.tmauctionapp.domain.auction.AuctionFacade;
import pl.example.tmauctionapp.domain.order.OrderCommand;
import pl.example.tmauctionapp.domain.order.OrderFacade;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/auctions")
@RequiredArgsConstructor
class AuctionController {

    private final AuctionFacade auctionFacade;
    private final OrderFacade orderFacade;

    @PostMapping
    void createAuction(@Valid @RequestBody CreateAuctionRequest createAuctionRequest) {
        AuctionCommand auctionCommand = AuctionCommand.builder()
                .ownerId(createAuctionRequest.getOwnerId())
                .ownerAccountId(createAuctionRequest.getOwnerAccountId())
                .title(createAuctionRequest.getTitle())
                .description(createAuctionRequest.getDescription())
                .quantity(createAuctionRequest.getQuantity())
                .price(createAuctionRequest.getPrice())
                .expirationDays(createAuctionRequest.getExpirationDays())
                .build();
        auctionFacade.createAuction(auctionCommand);
    }

    @PostMapping(path = "{auctionId}/orders")
    void createOrder(@Min(1) @PathVariable long auctionId, @Valid @RequestBody CreateOrderRequest createOrderRequest) {
        OrderCommand orderCommand = OrderCommand.builder()
                .auctionId(auctionId)
                .clientId(createOrderRequest.getClientId())
                .clientAccountNumber(createOrderRequest.getClientAccountNumber())
                .quantity(createOrderRequest.getQuantity())
                .build();
        orderFacade.createOrder(orderCommand);
    }
}
