package com.sitric;

import com.sitric.kitchen.Cook;
import com.sitric.kitchen.Order;
import com.sitric.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;


public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();


    public static void main(String[] args) throws Exception{

        Cook cook = new Cook("Amigo");
        Cook cook2 = new Cook("Gevorg");

        cook.setQueue(orderQueue);
        cook2.setQueue(orderQueue);

        List<Tablet> tablets = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(orderQueue);
            tablets.add(tablet);
        }

        Waiter waiter = new Waiter();
        cook.addObserver(waiter);
        cook2.addObserver(waiter);

        new Thread(cook).start();
        new Thread(cook2).start();


        Thread t = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        t.start();
        try
        {

            Thread.sleep(2000);

        }
        catch (InterruptedException e){}
        t.interrupt();

        //Statistics for director
        DirectorTablet directorTablet = new DirectorTablet();

        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
