package com.mytelegrambot.feature.telgram.command;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class StartCommand extends BotCommand {
    public StartCommand() {
        super("start", "Start command");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String text = "What currency rate you want to know: ";
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chat.getId());

        InlineKeyboardButton cadButton = InlineKeyboardButton.builder()
                .text("CAD")
                .callbackData("124")
                .build();

        InlineKeyboardButton usdButton = InlineKeyboardButton.builder()
                .text("USD")
                .callbackData("840")
                .build();

        InlineKeyboardButton eurButton = InlineKeyboardButton.builder()
                .text("EUR")
                .callbackData("978")
                .build();

        InlineKeyboardButton plzButton = InlineKeyboardButton.builder()
                .text("PLZ")
                .callbackData("985")
                .build();
        List<InlineKeyboardButton> buttonsEurop = Stream.of(eurButton, plzButton).toList();
        List<InlineKeyboardButton> buttonsAmerica = Stream.of(usdButton, cadButton).toList();
        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder()
                .keyboard(Collections.singleton(
                        buttonsAmerica))
                .keyboard(Collections.singleton(
                        buttonsEurop))
                .build();
        message.setReplyMarkup(keyboard);
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
