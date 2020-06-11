package pl.tmassalski.auctionapp.api.order;

import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Value
public class CreateOrderRequest {

    @Min(1)
    long clientId;
    @NotBlank
    String clientAccountNumber;
    @Min(1)
    int quantity;
}
