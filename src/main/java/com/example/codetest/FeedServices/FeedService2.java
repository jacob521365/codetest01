package com.example.codetest.FeedServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FeedService2 {
    private static final Logger logger = LoggerFactory.getLogger(FeedService2.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void feedService2(List<String> message) {
        logger.info("feedService1 :: Execution Time - {}",
                dateTimeFormatter.format(LocalDateTime.now()));
        if(message != null && message.size() != 0) {
            StringBuilder res = new StringBuilder();
            for (String s : message) {
                res.append(s + "\n");
            }
            try {
                Instant instant = Instant.now();
                File file = new File("feedServiceOutput2/feedService2"+ instant.toString() + ".txt");
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("File already exists.");
                }
                FileWriter myWriter = new FileWriter("feedServiceOutput2/"+ file.getName());
                myWriter.write(res.toString());
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}
