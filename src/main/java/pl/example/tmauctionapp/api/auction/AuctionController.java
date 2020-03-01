package pl.example.tmauctionapp.api.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.example.tmauctionapp.domain.auction.AuctionDto;
import pl.example.tmauctionapp.domain.auction.AuctionFacade;

import javax.validation.Valid;

@RestController
@RequestMapping("/auctions")
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionFacade auctionFacade;

    @PostMapping
    public void create(@Valid @RequestBody CreateAuctionRequest createAuctionRequest) {
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
}
