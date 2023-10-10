package com.mytelegrambot.constance;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringJoiner;

public class BotConstance {
    public static final String URL_MONOBANK = "https://api.monobank.ua/bank/currency";
    public static final String CONTENT_TYPE = "content-type";
    public static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset=utf-8";
    public static final String BOT_NAME = readPropertiesFromFile()[0];
    public static final String BOT_TOKEN = readPropertiesFromFile()[1];
    public static final int WAIT_UNTIL_UPDATE_DB = 300_000;
    private static String[] readPropertiesFromFile(){
        StringJoiner stringJoiner = new StringJoiner("\s");
        try(Scanner scanner = new Scanner(new File("File/NameAndToken.txt"))) {
            while (scanner.hasNext()){
                stringJoiner.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(stringJoiner).split("\\s");
    }
}
