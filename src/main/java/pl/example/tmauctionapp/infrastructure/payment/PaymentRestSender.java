package pl.example.tmauctionapp.infrastructure.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;
import pl.example.tmauctionapp.domain.order.Order;
import pl.example.tmauctionapp.domain.payment.Payment;
import pl.example.tmauctionapp.domain.payment.PaymentSenderClient;
import pl.example.tmauctionapp.infrastructure.order.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
class PaymentRestSender implements PaymentSenderClient {

    private final RestTemplate restTemplate;
    private final String transferBankAppUrl;
    private final String pendingTransfersBankAppUrl;
    private final OrderRepository orderRepository;

    @Autowired
    public PaymentRestSender(RestTemplate restTemplate,
                             @Value("${send.payment.bankapp.url}") String transferBankAppUrl,
                             @Value("${send.all.pending.payments.bankapp.url}")String pendingTransfersBankAppUrl,
                             OrderRepository orderRepository) {
        this.restTemplate = restTemplate;
        this.transferBankAppUrl = transferBankAppUrl;
        this.pendingTransfersBankAppUrl = pendingTransfersBankAppUrl;
        this.orderRepository = orderRepository;
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendPayment(Order order) {
        HttpEntity<Payment> requestEntity = new HttpEntity<>(Payment.generate(order));
        restTemplate.exchange(transferBankAppUrl,
                HttpMethod.POST,
                requestEntity,
                Void.class);
        order.setPaidStatus();
        orderRepository.save(order);
    }

    @Override
    @Transactional
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendPendingPayments(List<Order> pendingOrders) {
        List<Payment> pendingPayments = mapPendingOrdersToPayments(pendingOrders);
        HttpEntity<List<Payment>> requestEntity = new HttpEntity<>(pendingPayments);
        restTemplate.exchange(pendingTransfersBankAppUrl, HttpMethod.POST, requestEntity, Void.class);
        pendingOrders.forEach(Order::setPaidStatus);
        orderRepository.saveAll(pendingOrders);
    }

    List<Payment> mapPendingOrdersToPayments(List<Order> pendingOrders) {
        return pendingOrders.stream()
                .map(Payment::generate)
                .collect(Collectors.toList());
    }
}