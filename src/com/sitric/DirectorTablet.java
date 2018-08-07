package com.sitric;

import com.sitric.ad.Advertisement;
import com.sitric.ad.StatisticAdvertisementManager;
import com.sitric.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {
    //какую сумму заработали на рекламе с группировкой по дням
    public void printAdvertisementProfit(){
        TreeMap<Date, Long> advertisementRevenueAgregatedByDay = StatisticManager.getInstance().getAdvertisementRevenueAgregatedByDay();
        if (advertisementRevenueAgregatedByDay.isEmpty()) return;
        NavigableSet<Date> datesRow = advertisementRevenueAgregatedByDay.descendingKeySet();
        Long totalAmmout = Long.valueOf(0);
        for (Date date : datesRow) {
            Long dayAmount = advertisementRevenueAgregatedByDay.get(date);
            totalAmmout += dayAmount;
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.2f",
                    new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(date),
                    0.01d * dayAmount)
            );
        }
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", 0.01d * totalAmmout));
    }


    public void printCookWorkloading() {
        TreeMap<Date, TreeMap<String, Integer>> cooksWorkload = StatisticManager.getInstance().getCookWorkload();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for (Map.Entry<Date, TreeMap<String, Integer>> pair : cooksWorkload.entrySet()) {
            String date = dateFormat.format(pair.getKey());
            TreeMap<String, Integer> cookNameAndWorktime = pair.getValue();
            ConsoleHelper.writeMessage(date);
            for (Map.Entry<String, Integer> cookPair : cookNameAndWorktime.entrySet()) {
                String name = cookPair.getKey();
                int workTime = (int) Math.ceil(cookPair.getValue()/60.0);
                ConsoleHelper.writeMessage(String.format("%s - %d min", name, workTime));
            }
        }
    }


    //список активных роликов и оставшееся количество показов по каждому;
    public void printActiveVideoSet() {
        List<Advertisement> videoSet = StatisticAdvertisementManager.getInstance().getActiveAdvertisements();
        Collections.sort(videoSet, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        for (Advertisement advertisement : videoSet) {
            ConsoleHelper.writeMessage(String.format("%s - %d",
                    advertisement.getName(),
                    advertisement.getHits()));
        }
    }

    public void printArchivedVideoSet() {
        List<Advertisement> videoSet = StatisticAdvertisementManager.getInstance().getArchievedAdvertisements();
        Collections.sort(videoSet, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        for (Advertisement advertisement : videoSet) {
            ConsoleHelper.writeMessage(advertisement.getName());
        }
    }
}