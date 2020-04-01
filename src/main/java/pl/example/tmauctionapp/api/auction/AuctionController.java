package pl.example.tmauctionapp.api.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.example.tmauctionapp.api.order.CreateOrderRequest;
import pl.example.tmauctionapp.domain.auction.AuctionDto;
import pl.example.tmauctionapp.domain.auction.AuctionFacade;
import pl.example.tmauctionapp.domain.order.OrderDto;
import pl.example.tmauctionapp.domain.order.OrderFacade;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/auctions")
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionFacade auctionFacade;
    private final OrderFacade orderFacade;

    @PostMapping
    public void createAuction(@Valid @RequestBody CreateAuctionRequest createAuctionRequest) {
        AuctionDto auctionDto = AuctionDto.builder()
                .ownerId(createAuctionRequest.getOwnerId())
                .ownerAccountId(createAuctionRequest.getOwnerAccountId())
                .title(createAuctionRequest.getTitle())
                .description(createAuctionRequest.getDescription())
                .quantity(createAuctionRequest.getQuantity())
                .price(createAuctionRequest.getPrice())
                .expirationDays(createAuctionRequest.getExpirationDays())
                .build();
        auctionFacade.createAuction(auctionDto);
    }

    @PostMapping(path = "{auctionId}/orders")
    public void createOrder(@Min(1) @PathVariable long auctionId, @Valid @RequestBody CreateOrderRequest createOrderRequest) {
        OrderDto orderDto = OrderDto.builder()
                .auctionId(auctionId)
                .clientId(createOrderRequest.getClientId())
                .clientAccountNumber(createOrderRequest.getClientAccountNumber())
                .quantity(createOrderRequest.getQuantity())
                .build();
        orderFacade.createOrder(orderDto);
    }
}
