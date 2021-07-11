package com.example.codetest.FeedServices;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FeedServiceConsumer1 {

    private final List<String> messages = new ArrayList<>();

    @KafkaListener(topics = "myTopic", groupId = "kafka-feedService1")
    public void listen(String message) {
        synchronized (messages) {
            messages.add(message);
        }
    }


    public List<String> getMessages() {
        return messages;
    }

}