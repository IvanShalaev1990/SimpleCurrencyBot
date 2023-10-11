import com.mytelegrambot.constance.BotConstance;
import com.mytelegrambot.feature.currency.repositoriy.CurrencyRepository;
import com.mytelegrambot.feature.currency.updateschedule.CurrencyUpdateSchedule;
import com.mytelegrambot.feature.telgram.TelegramBotService;


public class TelegramBotApp {


    public static void main(String[] args) {
        CurrencyUpdateSchedule currencyUpdateSchedule= new CurrencyUpdateSchedule(BotConstance.WAIT_UNTIL_UPDATE_DB);
        currencyUpdateSchedule.run();
        TelegramBotService telegramBotService = new TelegramBotService();

    }
}
