package com.sitric.kitchen;

/*
* Класс-поток "Повар"
*/

import com.sitric.statistic.StatisticManager;
import com.sitric.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
// Observable — интерфейс, определяющий методы для добавления, удаления и оповещения наблюдателей
public class Cook extends Observable implements Runnable{
    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    private boolean busy;
    private String name;

    public boolean isBusy() {
        return busy;
    }

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    /*
    - observable - объект, который отправил нам значение
    - arg - само значение, в нашем случае - это объект Order
     */

    public void startCookingOrder(Order order) {
        busy = true;
        try {
            // регистрация события "заказ готов"
            CookedOrderEventDataRow event =
                    new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime()*60, order.getDishes());
            StatisticManager.getInstance().register(event);
            setChanged();
            notifyObservers(order);
            Thread.currentThread().sleep(order.getTotalCookingTime() * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        busy = false;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (queue.peek() != null) {
                if (!isBusy()) {
                    startCookingOrder(queue.poll());
                }
            }
            else {
                try {
                    // смотрим каждые 10мс освободился ли какой-нибудь из поваров
                    Thread.sleep(10);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
