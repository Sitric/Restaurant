package com.sitric;

import com.sitric.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString(){
        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static List<Dish> getAllDishesForOrder() {
        List<Dish> dishes = new ArrayList<>();
        writeMessage("Выберите блюдо из списка:");
        writeMessage(Dish.allDishesToString());
        String dish = readString();
        while (!dish.equals("exit")){
            try {
                dishes.add(Dish.valueOf(dish));
                writeMessage("Блюдо " + dish + " добавлено к заказу");
            } catch (IllegalArgumentException iae) {
                writeMessage("Блюда " + dish + " нет в продаже. Выберите другое");
            }
            dish = readString();
        }
        return dishes;
    }
}
