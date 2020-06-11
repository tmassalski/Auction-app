package pl.tmassalski.auctionapp.infrastructure.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import pl.tmassalski.auctionapp.domain.order.Order;
import pl.tmassalski.auctionapp.domain.payment.Payment;
import pl.tmassalski.auctionapp.domain.payment.PaymentSenderClient;
import pl.tmassalski.auctionapp.infrastructure.order.OrderRepository;

@Service
public class PaymentKafkaSender implements PaymentSenderClient {

    private final KafkaTemplate<String, Payment> kafkaTemplate;
    private final OrderRepository orderRepository;
    private final String topic;

    @Autowired
    public PaymentKafkaSender(KafkaTemplate<String, Payment> kafkaTemplate,
                              OrderRepository orderRepository,
                              @Value("${message.topic.name}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderRepository = orderRepository;
        this.topic = topic;
    }

    @Override
    public void sendPayment(Order order) {
        Payment payment = Payment.generate(order);
        ListenableFuture<SendResult<String, Payment>> future =  kafkaTemplate.send(topic, payment);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Payment>>() {
            @Override
            public void onSuccess(SendResult<String, Payment> stringObjectSendResult) {
                order.setPaidStatus();
                orderRepository.save(order);
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Message cannot be published" + ex.getMessage());
            }
        });
    }
}
