package com.sitric.ad;

import java.util.*;

public class StatisticAdvertisementManager {

    private static StatisticAdvertisementManager mInstance;
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {
    }

    public static StatisticAdvertisementManager getInstance() {
        if (mInstance == null) {
            mInstance = new StatisticAdvertisementManager();
        }
        return mInstance;
    }

    public List<Advertisement> getActiveAdvertisements (){
        List<Advertisement> result = new ArrayList<>();
        for (Advertisement advertisement : advertisementStorage.list()) {
            if (advertisement.getHits() > 0)
                result.add(advertisement);
        }
        return result;
    }

    public List<Advertisement> getArchievedAdvertisements() {
        List<Advertisement> result = new ArrayList<>();
        for (Advertisement advertisement : advertisementStorage.list()) {
            if (advertisement.getHits() == 0)
                result.add(advertisement);
        }
        return result;
    }
}
