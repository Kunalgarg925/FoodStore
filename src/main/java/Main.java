import items.Brownies;
import items.Burger;
import items.Coke;
import items.Item;
import items.Pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {


       Order orderForKunal = new Order();
       TakeOrder takeOrderforkunal = new TakeOrder();
       fillOrderDetails(orderForKunal,takeOrderforkunal);

       System.out.println(orderForKunal.finalPrice);
    }

    private static void fillOrderDetails(Order order, TakeOrder input) {
        String specialDay = input.specialDays;
        String items = input.items;
        String peakHour = input.peakHour;
        String nightOrder = input.nightOrder;

        if(specialDay.toLowerCase(Locale.ROOT).equals("yes")){
            order.isPlacedOnSpecialDays = true;
        }
        if(peakHour.toLowerCase(Locale.ROOT).equals("yes")){
            order.isPlacedAtPeakHours = true;
        }
        if(nightOrder.toLowerCase(Locale.ROOT).equals("yes")){
            order.isPlacedAtNight = true;
        }

        order.items = getOrderItems(items);

        setFinalPrice(order);
    }

    private static double setFinalPrice(Order order) {
        double finalPrice = 0.0;

        List<Item> orderItems = order.items;
        int itemPriceTotal = 0;
        for(Item item: orderItems){
            itemPriceTotal += item.price;
        }

        finalPrice += itemPriceTotal;
        finalPrice += getGST(itemPriceTotal);

        boolean isNormalChargesApplicable = true;
        if(order.isPlacedAtNight){
            finalPrice += 20;
            isNormalChargesApplicable = false;
        }
        if(order.isPlacedAtPeakHours){
            finalPrice += 30;
            isNormalChargesApplicable = false;
        }
        if(order.isPlacedOnSpecialDays){
            finalPrice += 50;
            isNormalChargesApplicable = false;
        }

        if(itemPriceTotal >= 500){
            isNormalChargesApplicable = false;
        }

        if(isNormalChargesApplicable){
            finalPrice += 20;
        }
        order.finalPrice = finalPrice;
        return finalPrice;
    }

    private static double getGST(int itemPriceTotal) {
        return itemPriceTotal*0.05;
    }

    private static List<Item> getOrderItems(String items) {
        List<String> rawItems = List.of(items.split(","));
        List<Item> orderItems = new ArrayList<>();

        for(String item: rawItems){
            if(item.toLowerCase(Locale.ROOT).trim().equals("burger")){
                orderItems.add(new Burger(100));
            }
            if(item.toLowerCase(Locale.ROOT).equals("coke")){
                orderItems.add(new Coke(50));
            }
            if(item.toLowerCase(Locale.ROOT).equals("pizza")){
                orderItems.add(new Pizza(150));
            }
            if(item.toLowerCase(Locale.ROOT).equals("brownie")){
                orderItems.add(new Brownies(60));
            }
        }
        return orderItems;
    }
}
