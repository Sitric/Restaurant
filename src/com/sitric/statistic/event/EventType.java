package com.sitric.statistic.event;

/*
* Типы событий
*/

public enum EventType {
    COOKED_ORDER,           //*повар приготовил заказ
    SELECTED_VIDEOS,        //*выбрали набор видео-роликов для заказа
    NO_AVAILABLE_VIDEO      //*нет ни одного видео-ролика, который можно показать во время приготовления заказа
}
