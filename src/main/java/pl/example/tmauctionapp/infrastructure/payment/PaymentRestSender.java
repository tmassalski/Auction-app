package pl.example.tmauctionapp.infrastructure.payment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.example.tmauctionapp.domain.order.Order;
import pl.example.tmauctionapp.domain.payment.Payment;
import pl.example.tmauctionapp.domain.payment.PaymentSenderClient;

@Service
@AllArgsConstructor
public class PaymentRestSender implements PaymentSenderClient {

    @Override
    public Payment sendPayment(Order order) {
        //TODO complete REST statement
        return Payment.generate(order);
    }
}
