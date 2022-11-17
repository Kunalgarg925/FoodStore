import items.Item;

import java.util.List;

public class Order {
    double finalPrice;
    List<Item> items;
    boolean isPlacedAtPeakHours;
    boolean isPlacedOnSpecialDays;
    boolean isPlacedAtNight;
    boolean isNormalDeliveryChargesApplicable;
}
