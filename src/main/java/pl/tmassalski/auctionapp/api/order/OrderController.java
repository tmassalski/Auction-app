package pl.tmassalski.auctionapp.api.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tmassalski.auctionapp.domain.order.OrderFacade;
import pl.tmassalski.auctionapp.domain.payment.Payment;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
class OrderController {

    private final OrderFacade orderFacade;

    @GetMapping(path = "/pending")
    ResponseEntity<List<Payment>> getPendingOrders() {
        return ResponseEntity.ok(orderFacade.getAllPaymentsByOrderStatus());
    }
}
