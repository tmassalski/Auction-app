package pl.example.tmauctionapp.api.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.example.tmauctionapp.domain.order.OrderFacade;
import pl.example.tmauctionapp.domain.order.OrderStatus;
import pl.example.tmauctionapp.domain.payment.Payment;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacade orderFacade;

    @GetMapping()
    public ResponseEntity<List<Payment>> getAllOrdersPendingPayment(@PathParam(value = "status") OrderStatus status) {
        if (!status.equals(OrderStatus.PENDING)) {
            //TODO response for paid orders
        }
        return ResponseEntity.ok(orderFacade.getAllPaymentsByOrderStatus(status));
    }
}
