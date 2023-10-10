package com.mytelegrambot.feature.mydatabase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytelegrambot.constance.BotConstance;
import com.mytelegrambot.dto.CurrencyItem;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyDataBase {

    private static CopyOnWriteArrayList<CurrencyItem> currencyList;

    public MyDataBase() {
        setCurrencyList();
    }

    public static CopyOnWriteArrayList<CurrencyItem> getCurrencyList() {
        return currencyList;
    }

    public static void setCurrencyList() {
        ObjectMapper objectMapper = new ObjectMapper();
        String url = BotConstance.URL_MONOBANK;
        String json;
        try {
            json = Jsoup.connect(url)
                    .header(BotConstance.CONTENT_TYPE, BotConstance.APPLICATION_JSON_CHARSET_UTF_8)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't connect to Monobank API");
        }


        try {
            currencyList = objectMapper.readValue(json, new TypeReference<CopyOnWriteArrayList<CurrencyItem>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        MyDataBase myDataBase = new MyDataBase();
        System.out.println("myDataBase.getCurrencyList() = " + myDataBase.getCurrencyList());
    }
}
