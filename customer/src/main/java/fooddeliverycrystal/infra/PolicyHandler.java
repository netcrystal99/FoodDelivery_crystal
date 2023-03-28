package fooddeliverycrystal.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fooddeliverycrystal.config.kafka.KafkaProcessor;
import fooddeliverycrystal.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PayRepository payRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Paid'"
    )
    public void wheneverPaid_OrderForMeal(@Payload Paid paid) {
        Paid event = paid;
        System.out.println(
            "\n\n##### listener OrderForMeal : " + paid + "\n\n"
        );

        // Sample Logic //
        Order.orderForMeal(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='StatusUpdated'"
    )
    public void wheneverStatusUpdated_UpdateStatus(
        @Payload StatusUpdated statusUpdated
    ) {
        StatusUpdated event = statusUpdated;
        System.out.println(
            "\n\n##### listener UpdateStatus : " + statusUpdated + "\n\n"
        );

        // Sample Logic //
        Order.updateStatus(event);
    }
}
