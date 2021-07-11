package com.example.codetest.Model;

import lombok.Data;

@Data
public class OutputTrade {
    private String tradeReference;
    private Long accountNumber;
    private String stockCode;
    private Float quantity;
    private String currency;
    private Float price;
    private String broker;
    private String amount;
    private String receivedTimestamp;
}
