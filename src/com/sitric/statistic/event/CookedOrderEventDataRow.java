package com.sitric.statistic.event;

import com.sitric.kitchen.Dish;

import java.util.Date;
import java.util.List;

/*
    Заказ готов
 */

public class CookedOrderEventDataRow implements EventDataRow{
    private String tabletName;
    private String cookName;
    private int cookingTimeSeconds;
    private List<Dish> cookingDishs;    // список блюд для приготовления
    private Date currentDate;

    public String getCookName() {
        return cookName;
    }

    public CookedOrderEventDataRow(String tabletName, String cookName, int cookingTimeSeconds, List<Dish> cookingDishs) {
        this.tabletName = tabletName;
        this.cookName = cookName;
        this.cookingTimeSeconds = cookingTimeSeconds;
        this.cookingDishs = cookingDishs;
        currentDate = new Date();
    }

    @Override
    public EventType getType() {
        return EventType.COOKED_ORDER;
    }

    @Override
    public Date getDate() {

        return currentDate;
    }

    @Override
    public int getTime() {
        return cookingTimeSeconds;
    }
}
