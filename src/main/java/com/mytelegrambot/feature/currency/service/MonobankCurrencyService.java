package com.mytelegrambot.feature.currency.service;

import com.mytelegrambot.constance.BotConstance;
import com.mytelegrambot.dto.CurrencyItem;
import com.mytelegrambot.feature.currency.repositoriy.CurrencyRepository;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class MonobankCurrencyService implements CurrencyService {
    @Override
    public String getCurrenciesInfo() {
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
        return json;
    }
}
