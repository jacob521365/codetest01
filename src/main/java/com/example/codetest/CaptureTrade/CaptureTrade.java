package com.example.codetest.CaptureTrade;



import java.io.*;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Instant;

import com.example.codetest.Model.InputTrade;
import com.example.codetest.Model.OutputTrade;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class CaptureTrade {
    public static String generateTradeOutput() throws URISyntaxException {
        URL res = CaptureTrade.class.getClassLoader().getResource("data/input.txt");
        File file = Paths.get(res.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();
        //convert txt to string
        String inputJsonString = readTxtFile(absolutePath);
        //convert string to java object array
        InputTrade[] inputTrades = parseJson(inputJsonString);
        //process input to output object array
        OutputTrade[] outputTrades = convertToOutput(inputTrades);
        //for(OutputTrade trade : outputTrades){System.out.println(object2Json(trade));}
        StringBuilder messageBuilder = new StringBuilder();
        for(OutputTrade trade : outputTrades){messageBuilder.append(object2Json(trade));}
        return messageBuilder.toString();
    }

    public static String readTxtFile(String filePath){
        StringBuilder sb = new StringBuilder();
        try {
            String encoding="UTF-8";
            File file = new File(filePath);
            if(file.isFile() && file.exists()){ //if the file exists
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//encoding methods
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    sb.append(lineTxt);
                }
                read.close();
            }else{
                System.out.println("can found the file");
            }
        } catch (Exception e) {
            System.out.println("parse error");
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static InputTrade[] parseJson(String txt){
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputTrade[] trades = mapper.readValue(txt, InputTrade[].class);

            //System.out.println("JSON array to array objects...");
            /*
            for (InputTrade trade:trades){
                System.out.println(trade);
            }
            */
            return trades;
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static OutputTrade[] convertToOutput(InputTrade[] trades){
        OutputTrade[] output = new OutputTrade[trades.length];
        for (int i = 0; i < trades.length; i++) {
            InputTrade trade = trades[i];
            output[i] = new OutputTrade();
            output[i].setTradeReference(trade.getTradeReference());
            output[i].setAccountNumber(trade.getAccountNumber());
            output[i].setStockCode(trade.getStockCode());
            output[i].setQuantity(trade.getQuantity());
            output[i].setCurrency(trade.getCurrency());
            output[i].setPrice(trade.getPrice());
            output[i].setBroker(trade.getBroker());
            BigDecimal amount = new BigDecimal(Float.toString(trade.getPrice()*trade.getQuantity()));
            amount.setScale(2, BigDecimal.ROUND_UP);
            output[i].setAmount(amount.toString());
            Instant instant = Instant.now();
            output[i].setReceivedTimestamp(instant.toString());
            //System.out.println(output[i]);
        }
        return output;
    }

    private static String object2Json(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        try{
            String json = mapper.writeValueAsString(obj);
            //System.out.println("Result = " + json);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

