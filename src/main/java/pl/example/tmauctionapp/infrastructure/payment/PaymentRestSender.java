package pl.example.tmauctionapp.infrastructure.payment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.example.tmauctionapp.domain.order.Order;
import pl.example.tmauctionapp.domain.payment.Payment;
import pl.example.tmauctionapp.domain.payment.PaymentSenderClient;

@Service
public class PaymentRestSender implements PaymentSenderClient {

    private final RestTemplate restTemplate;
    private final String transferBankAppUrl;

    @Autowired //TODO sprawdź
    public PaymentRestSender(RestTemplate restTemplate,
                             @Value("${send.payment.bankapp.url") String transferBankAppUrl) {
        this.restTemplate = restTemplate;
        this.transferBankAppUrl = transferBankAppUrl;
    }

    @Override
    public void sendPayment(Order order) {
        HttpEntity<Payment> requestEntity = new HttpEntity<>(Payment.generate(order));
        restTemplate.exchange(transferBankAppUrl,
                HttpMethod.POST,
                requestEntity,
                Void.class);
    }
}
