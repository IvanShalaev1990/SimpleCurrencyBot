package com.mytelegrambot.feature.telgram.command;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PzlCommand extends BotCommand {
    public PzlCommand() {
        super("pzl", "PZL currency");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String text = "PZL (Polish zloty) is the official currency and legal tender of Poland.";
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
