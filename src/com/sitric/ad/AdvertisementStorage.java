package com.sitric.ad;

/*
    хранилище рекламных роликов
    Thread-safe Singleton
 */

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
    private static volatile AdvertisementStorage mInstance;

    public final List<Advertisement> videos = new ArrayList<>();

    private AdvertisementStorage() {
        Object someContent = new Object();

        videos.add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60));
        videos.add(new Advertisement(someContent, "second Video", 100, 10, 15 * 60));
        videos.add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60));

//        videos.add(new Advertisement(someContent, "четвертое видео", 0, 100, 3 * 60));
//        videos.add(new Advertisement(someContent, "fifth", 100, 0, 15 * 60));
//        videos.add(new Advertisement(someContent, "super", 100, 0, 15 * 60));
//        videos.add(new Advertisement(someContent, "апрельский розмарин", 0, 2, 10 * 60));

//        videos.add(new Advertisement(someContent, "1", 1523, 3, 3 * 60));
//        videos.add(new Advertisement(someContent, "2", 5, 2, 1 * 60));
//        videos.add(new Advertisement(someContent, "3", 99, 2, 3 * 60));
//        videos.add(new Advertisement(someContent, "4", 99, 10, 2 * 60));
//        videos.add(new Advertisement(someContent, "5", 2506, 3, 3 * 60));
//        videos.add(new Advertisement(someContent, "6", 2506, 3, 3 * 60));
//        videos.add(new Advertisement(someContent, "7", 400, 1, 3 * 60));
//        videos.add(new Advertisement(someContent, "8", 500, 1, 2 * 60));
//        videos.add(new Advertisement(someContent, "10", 400, 2, 3 * 60));
//        videos.add(new Advertisement(someContent, "11", 350, 100, 3 * 60)); // 3 min
//        videos.add(new Advertisement(someContent, "12", 1500, 10, 2 * 60)); //15 min
//        videos.add(new Advertisement(someContent, "13", 4600, 2, 10 * 60));   //10 min

    }

    public static AdvertisementStorage getInstance() {
        if (mInstance == null) {
            synchronized (AdvertisementStorage.class) {
                if (mInstance == null) {
                    mInstance = new AdvertisementStorage();
                }
            }
        }
        return mInstance;
    }

    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }
}
