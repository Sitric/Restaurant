package com.sitric;
/*
* Поток, который имитирует заказ клиента через планшет
* */
import java.util.List;

class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        if (tablets.isEmpty()) {
            return;
        }
        while (!Thread.currentThread().isInterrupted())
        {
            Tablet tablet = tablets.get((int)(Math.random() * tablets.size()));
            tablet.createTestOrder();
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
