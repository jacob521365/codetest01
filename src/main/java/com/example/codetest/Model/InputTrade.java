package com.example.codetest.Model;


import lombok.Data;

@Data
public class InputTrade {
    private String tradeReference;
    private Long accountNumber;
    private String stockCode;
    private Float quantity;
    private String currency;
    private Float price;
    private String broker;
}
