import com.mytelegrambot.constance.BotConstance;
import com.mytelegrambot.feature.mydatabase.MyDataBase;
import com.mytelegrambot.feature.telgram.TelegramBotService;


public class TelegramBotApp {


    public static void main(String[] args) {

        TelegramBotService telegramBotService = new TelegramBotService();
        Thread threadDataBaseUpdate = new Thread(()->{
            while (true){
                try {
                    Thread.sleep(BotConstance.WAIT_UNTIL_UPDATE_DB);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                MyDataBase.setCurrencyList();
                System.out.println("Data base updated.");
                System.out.println("MyDataBase.getCurrencyList() = " + MyDataBase.getCurrencyList());
            }
        });
        threadDataBaseUpdate.start();
    }
}
