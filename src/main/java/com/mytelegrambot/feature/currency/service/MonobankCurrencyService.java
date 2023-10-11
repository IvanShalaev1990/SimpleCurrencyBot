package com.mytelegrambot.feature.currency.service;

import com.mytelegrambot.dto.CurrencyItem;
import com.mytelegrambot.feature.currency.repositoriy.CurrencyRepository;

import java.util.concurrent.CopyOnWriteArrayList;

public class MonobankCurrencyService implements CurrencyService {
    private CopyOnWriteArrayList<CurrencyItem> currencyItems;
    @Override
    public double getRate(Integer currencyCode) {

        currencyItems = CurrencyRepository.getCurrencyList();

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
