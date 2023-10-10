package com.mytelegrambot.feature.currency;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytelegrambot.constance.BotConstance;
import com.mytelegrambot.dto.CurrencyItem;
import com.mytelegrambot.feature.mydatabase.MyDataBase;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MonobankCurrencyService implements CurrencyService {
    private CopyOnWriteArrayList<CurrencyItem> currencyItems;

    public MyDataBase getMyDataBase() {
        return myDataBase;
    }

    private MyDataBase myDataBase = new MyDataBase();

    @Override
    public double getRate(Integer currencyCode) {

        currencyItems = myDataBase.getCurrencyList();

        return currencyItems.stream()
                .filter(it -> it.getCurrencyCodeA() == currencyCode)
                .map(it -> {
                    if (it.getRateSell() == 0.0 && it.getRateBuy() == 0.0) {
                        return it.getRateCross();
                    } else {
                        return (it.getRateBuy() + it.getRateSell()) / 2;
                    }
                })
                .findFirst()
                .orElseThrow();
    }
}
