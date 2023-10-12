package com.mytelegrambot.feature.currency.repositoriy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytelegrambot.dto.CurrencyItem;
import com.mytelegrambot.feature.currency.service.CurrencyService;
import com.mytelegrambot.feature.currency.service.MonobankCurrencyService;

import java.util.concurrent.CopyOnWriteArrayList;

public class CurrencyRepository {

    private static CopyOnWriteArrayList<CurrencyItem> currencyList;

    public CurrencyRepository() {
        setCurrencyList();
    }

    public static CopyOnWriteArrayList<CurrencyItem> getCurrencyList() {
        return currencyList;
    }

    public static void setCurrencyList() {
        ObjectMapper objectMapper = new ObjectMapper();
        CurrencyService currencyService =new MonobankCurrencyService();
        String json = currencyService.getCurrenciesInfo();
        try {
            currencyList = objectMapper.readValue(json, new TypeReference<CopyOnWriteArrayList<CurrencyItem>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
