package com.mytelegrambot.feature.currency.updateschedule;

import com.mytelegrambot.feature.currency.repositoriy.CurrencyRepository;

public class CurrencyUpdateSchedule {
    private final int timeToWait;

    public CurrencyUpdateSchedule(int timeToWait) {
        this.timeToWait = timeToWait;
    }

    public void run(){
        Thread threadDataBaseUpdate = new Thread(()->{
            while (true){
                CurrencyRepository.setCurrencyList();
                System.out.println("Data base updated.");
                System.out.println("MyDataBase.getCurrencyList() = " + CurrencyRepository.getCurrencyList());
                try {
                    Thread.sleep(timeToWait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threadDataBaseUpdate.start();
    }
}
