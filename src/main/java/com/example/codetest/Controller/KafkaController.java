package com.example.codetest.Controller;

import com.example.codetest.CaptureTrade.CaptureTradeConsumer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.codetest.CaptureTrade.CaptureTrade;

import java.net.URISyntaxException;
import java.util.List;

@RestController
public class KafkaController {

    private KafkaTemplate<String, String> template;
    private CaptureTradeConsumer captureTradeConsumer;

    public KafkaController(KafkaTemplate<String, String> template, CaptureTradeConsumer captureTradeConsumer) {
        this.template = template;
        this.captureTradeConsumer = captureTradeConsumer;
    }

    @GetMapping("/kafka/captureTrade")
    public String produce() throws URISyntaxException {
        String message = CaptureTrade.generateTradeOutput();
        template.send("myTopic", message);
        return "Capture Trade!";
    }

    @GetMapping("/kafka/captureTradeMessages")
    public List<String> getMessages() {
        return captureTradeConsumer.getMessages();
    }

}