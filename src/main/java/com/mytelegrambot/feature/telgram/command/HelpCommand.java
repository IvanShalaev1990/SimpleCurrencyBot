package com.mytelegrambot.feature.telgram.command;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelpCommand extends BotCommand {
    public HelpCommand() {
        super("help", "Help command");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String text = "This bot represent currency rate for USD, EUR, CAD, PZL. \n " +
                "To know more about currency type: \n" +
                "/usd\n" +
                "/eur\n" +
                "/cad\n" +
                "/pzl\n";
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chat.getId());
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
