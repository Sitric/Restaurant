package com.sitric.statistic.event;

import com.sitric.ad.Advertisement;

import java.util.Date;
import java.util.List;

public class VideoSelectedEventDataRow implements EventDataRow{
    private List<Advertisement> optimalVideoSet;    //список видео-роликов, отобранных для показа
    private long amount;                            // сумма денег в копейках
    private int totalDuration;                      // общая продолжительность показа отобранных рекламных роликов
    private Date currentDate;


    public long getAmount() {
        return amount;
    }

    public VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration) {
        this.optimalVideoSet = optimalVideoSet;
        this.amount = amount;
        this.totalDuration = totalDuration;
        currentDate = new Date();
    }

    //test constructor

//    public VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration, Date date) {
//        this.optimalVideoSet = optimalVideoSet;
//        this.amount = amount;
//        this.totalDuration = totalDuration;
//        currentDate = date;
//    }

    @Override
    public EventType getType() {
        return EventType.SELECTED_VIDEOS;
    }

    @Override
    public Date getDate() {

        return currentDate;
    }

    @Override
    public int getTime() {
        return totalDuration;
    }
}
