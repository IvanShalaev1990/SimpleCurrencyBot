package com.mytelegrambot.feature.ui;


public class PrintResponse {
    public String response(double rate, Integer currencyItem) {
        String currency = null;
        switch (currencyItem) {
            case 124 -> currency = "CAD";
            case 840 -> currency = "USD";
            case 985 -> currency = "PLZ";
            case 978 -> currency = "EUR";
        }
        return String.format("Rate %s => UAH = %3.3f", currency, rate);
    }
}
