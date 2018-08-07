package com.sitric;

/*
* Класс планшета, через который заказ поступает повару
*/

import com.sitric.ad.AdvertisementManager;
import com.sitric.ad.NoVideoAvailableException;
import com.sitric.kitchen.Order;
import com.sitric.kitchen.TestOrder;
import com.sitric.statistic.StatisticManager;
import com.sitric.statistic.event.NoAvailableVideoEventDataRow;


import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet  {
    public final int number; //номер планшета
    public static Logger logger = Logger.getLogger(Tablet.class.getName());
    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }



    public Tablet(int number) {
        this.number = number;
    }

    //заказ из тех блюд, которые выберет пользователь
    public void createOrder() {
        try {
            final Order newOrder = new Order(this);
            insideOrder(newOrder);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            return;
        }
    }
    // заносит заказ в очередь и запускает рекламу
    private void insideOrder(Order newOrder) throws IOException {
        if (newOrder.isEmpty()) return;
        ConsoleHelper.writeMessage(newOrder.toString());
        queue.add(newOrder);
        try {
            new AdvertisementManager(newOrder.getTotalCookingTime() * 60).processVideos();
        } catch (NoVideoAvailableException e) {
            StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(newOrder.getTotalCookingTime()*60));
            logger.log(Level.INFO, "No video is available for the order " + newOrder);
        }
    }
    // случайным образом генерирует заказы
    public void createTestOrder() {
        try {
            final Order newOrder = new TestOrder(this);
            insideOrder(newOrder);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            return;
        }
    }


    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}


