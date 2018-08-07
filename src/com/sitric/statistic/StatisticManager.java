package com.sitric.statistic;
/*
* С его помощью будем регистрировать события в хранилище.
* Singleton
* */
import com.sitric.kitchen.Cook;
import com.sitric.statistic.event.CookedOrderEventDataRow;
import com.sitric.statistic.event.EventDataRow;
import com.sitric.statistic.event.EventType;
import com.sitric.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticManager {
    private static StatisticManager instance;
    private StatisticStorage statisticStorage = new StatisticStorage();

    private StatisticManager () {}

    public static StatisticManager getInstance() {
        if (instance == null) {
            instance = new StatisticManager();
        }
        return instance;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }
    // статистика загруженности поваров по дням
    public TreeMap<Date, TreeMap<String, Integer>> getCookWorkload() {
        List<EventDataRow> cookWorkList = statisticStorage.getStorage().get(EventType.COOKED_ORDER);
        TreeMap<Date, TreeMap<String, Integer>> workPerDay = new TreeMap<>(Collections.reverseOrder());
        for (EventDataRow row : cookWorkList) {
            CookedOrderEventDataRow cookedOrderEventDataRow = (CookedOrderEventDataRow) row;

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(cookedOrderEventDataRow.getDate());
            GregorianCalendar gregorianCalendar = new GregorianCalendar(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            Date date = gregorianCalendar.getTime();

            String name = cookedOrderEventDataRow.getCookName();
            Integer workTime = cookedOrderEventDataRow.getTime();
            if (workTime > 0) {
                if (!workPerDay.containsKey(date)) {
                    TreeMap<String, Integer> workPerCookByDay = new TreeMap<>();
                    workPerCookByDay.put(name, workTime);
                    workPerDay.put(date, workPerCookByDay);
                } else {
                    TreeMap<String, Integer> workPerCookByDay = workPerDay.get(date);
                    if (!workPerCookByDay.containsKey(name)) {
                        workPerCookByDay.put(name, workTime);
                    } else {
                        workPerCookByDay.put(name, workPerCookByDay.get(name) + workTime);
                    }
                }
            }
        }
        return workPerDay;
    }

    // метод, который формирует статистику показа рекламы по дням
    private static final int[] TIME_FIELDS =
            {
                    Calendar.HOUR_OF_DAY,
                    Calendar.HOUR,
                    Calendar.AM_PM,
                    Calendar.MINUTE,
                    Calendar.SECOND,
                    Calendar.MILLISECOND
            };

    public TreeMap<Date, Long> getAdvertisementRevenueAgregatedByDay() {
        TreeMap<Date, Long> result = new TreeMap<>();
        for (EventDataRow eventDataRow : statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS)) {
            VideoSelectedEventDataRow vEventDataRow = (VideoSelectedEventDataRow) eventDataRow;
            GregorianCalendar gDate = new GregorianCalendar();
            gDate.setTime(vEventDataRow.getDate());
            for(int i : TIME_FIELDS)
                gDate.clear(i);
            Date date = gDate.getTime();
            Long dayRevenue = result.get(date) ;
            if (dayRevenue == null) dayRevenue = Long.valueOf(0);
            result.put(date, dayRevenue + vEventDataRow.getAmount());
        }
        return result;
    }


    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }

        public StatisticStorage() {
            EventType[] types = EventType.values();
            for(EventType type : types){
                storage.put(type, new ArrayList<>());
            }
        }

        private void put(EventDataRow data) {
            List<EventDataRow> eventList = statisticStorage.storage.get(data.getType());
            eventList.add(data);
        }

    }
}

