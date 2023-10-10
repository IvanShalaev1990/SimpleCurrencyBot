package com.mytelegrambot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyItem {
    private int currencyCodeA;
    private int currencyCodeB;
    private long date;
    private double rateBuy;
    private double rateCross;
    private double rateSell;


}
