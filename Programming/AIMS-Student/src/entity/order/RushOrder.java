package entity.order;

import java.time.LocalDate;

public class RushOrder extends Order {
    private LocalDate deliveryTime;

    public LocalDate getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDate deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}