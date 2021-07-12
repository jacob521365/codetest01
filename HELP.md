# Getting Started

## Enviroment setup

To run the application, a Kafka cluster need to be setup for the message delivery

```bash
zookeeper-server-start -daemon /usr/local/etc/kafka/zookeeper.properties \
& kafka-server-start /usr/local/etc/kafka/server.properties
```

Configure the kafka topic:
```bash
kafka-console-consumer --bootstrap-server localhost:9092 --topic myTopic
```

Run spring-boot application:
```bash
./mvnw spring-boot:run
```

Interact with application:
```bash
curl http://localhost:8080/kafka/captureTrade
```
This will send a request to application, then it will read JSON trade from resource file, then send them to kafka topic.

Check the sent message by:
```bash
curl http://localhost:8080/kafka/captureTradeMessages
```

The sent messages will also be recorded to text file by periodic tasks:
Two tasks will read the kafka topic and record the data in 5 min and 1 min lifecycle.
Files are stored at *feedServiceOutput1* and *feedServiceOutput2*.
