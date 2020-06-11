package pl.tmassalski.auctionapp.api.auction;

import lombok.Value;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Value
class CreateAuctionRequest {

    @Min(1)
    long ownerId;
    @Min(1)
    long ownerAccountId;
    @NotBlank
    String title;
    @NotBlank
    String description;
    @Min(1)
    int quantity;
    @DecimalMin("0.01")
    BigDecimal price;
    @Min(1)
    int expirationDays;
}
