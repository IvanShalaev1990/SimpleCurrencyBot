package com.mytelegrambot.feature.telgram;

import com.mytelegrambot.constance.BotConstance;
import com.mytelegrambot.feature.currency.rate.GetCurrencyRate;
import com.mytelegrambot.feature.telgram.command.*;
import com.mytelegrambot.feature.ui.PrintResponse;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.helpCommand.HelpCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class CurrencyTelegramBot extends TelegramLongPollingCommandBot {
    private GetCurrencyRate getCurrencyRate;
    private PrintResponse printResponse;
    public CurrencyTelegramBot(){
        getCurrencyRate = new GetCurrencyRate();
        printResponse = new PrintResponse();
        register(new StartCommand());
        register(new HelpCommand());
        register(new PzlCommand());
        register(new CadCommand());
        register(new UsdCommand());
        register(new EurCommand());
    }
    @Override
    public void processNonCommandUpdate(Update update) {
        String callbackQuery = update.getCallbackQuery().getData();
        double currencyRate =getCurrencyRate.getRate(Integer.parseInt(callbackQuery));
        String responseText =printResponse.response(currencyRate, Integer.parseInt(callbackQuery));
        SendMessage responseMessage = new SendMessage();
        responseMessage.setText(responseText);
        long chatID = update.getCallbackQuery().getMessage().getChatId();
        responseMessage.setChatId(chatID);
        try {
            execute(responseMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return BotConstance.BOT_NAME;
    }
    @Override
    public String getBotToken() {
        return BotConstance.BOT_TOKEN;
    }


}
