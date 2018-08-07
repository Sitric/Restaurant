package com.sitric.kitchen;

import com.sitric.ConsoleHelper;
import com.sitric.Tablet;

import java.io.IOException;
import java.util.List;

public class Order{
    private final Tablet tablet;

    public Tablet getTablet() {
        return tablet;
    }

    protected List<Dish> dishes;

    public List<Dish> getDishes() {
        return dishes;
    }

    protected void initDishes() throws IOException {
        ConsoleHelper.writeMessage(Dish.allDishesToString());
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    public boolean isEmpty() {
        return dishes == null || dishes.isEmpty();
    }

    public int getTotalCookingTime() {
        int cookingTime = 0;
        for(Dish dish: dishes) {
            cookingTime += dish.getDuration();
        }

        return cookingTime;
    }

    @Override
    public String toString() {
        return dishes.size() > 0 ?
                "Your order: " + dishes + " of " + tablet + ", cooking time " + getTotalCookingTime() + "min":
                "";
    }
}

