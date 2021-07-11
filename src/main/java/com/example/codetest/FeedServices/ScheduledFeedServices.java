package com.example.codetest.FeedServices;


import com.example.codetest.CaptureTrade.CaptureTradeConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ScheduledFeedServices {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledFeedServices.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private FeedServiceConsumer1 feedServiceConsumer1;
    private FeedServiceConsumer2 feedServiceConsumer2;

    public ScheduledFeedServices(FeedServiceConsumer1 feedServiceConsumer1, FeedServiceConsumer2 feedServiceConsumer2) {
        this.feedServiceConsumer1 = feedServiceConsumer1;
        this.feedServiceConsumer2 = feedServiceConsumer2;
    }

    @Scheduled(fixedRate = 300000)
    public void scheduleFeedService1(){
        logger.info("Fixed Rate Task - scheduleFeedService1 :: Execution Time - {}",
                dateTimeFormatter.format(LocalDateTime.now()));
        List<String> message = feedServiceConsumer1.getMessages();
        FeedService1.feedService1(message);
    }

    @Scheduled(fixedRate = 60000)
    public void scheduleFeedService2(){
        logger.info("Fixed Rate Task - scheduleFeedService2 :: Execution Time - {}",
                dateTimeFormatter.format(LocalDateTime.now()));
        List<String> message = feedServiceConsumer2.getMessages();
        FeedService2.feedService2(message);
    }
}
