package com.sitric.kitchen;
/*
* Класс "официант"
* */
import com.sitric.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

//Observer — интерфейс, с помощью которого наблюдатель получает оповещение;
public class Waiter implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        ConsoleHelper.writeMessage(arg + " was cooked by " + o);
    }
}
