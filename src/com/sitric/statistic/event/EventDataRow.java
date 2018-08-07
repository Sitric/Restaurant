package com.sitric.statistic.event;

import java.util.Date;

public interface EventDataRow {
    EventType getType();
    Date getDate(); // вернет дату создания записи
    int getTime(); // вернет время и продолжительность
}
