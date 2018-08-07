package com.sitric.ad;

/*
    Рекламное объявление
 */

public class Advertisement {
    private Object content;
    private String name;

    //Используем long, чтобы избежать проблем с округлением
    private long initialAmount; // начальная сумма, стоимость рекламы в копейках.
    private long amountPerOneDisplaying; //стоимость одного показа рекламного объявления в копейках

    private int hits; //количество оплаченных показов
    private int duration;

    public String getName() {
        return name;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public int getDuration() {
        return duration;
    }

    public int getHits() {
        return hits;
    }

    public double getAmountPerSecond() {
        return (double)amountPerOneDisplaying / duration;
    }

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        this.amountPerOneDisplaying = (hits > 0) ? initialAmount/hits : 0;

    }

    public void revalidate() {
        if (hits <= 0)
            throw new UnsupportedOperationException();
        hits--;
    }

}
