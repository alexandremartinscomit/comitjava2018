package domain;

import java.time.Instant;

public class RentOrder {

    private Car car;
    private User user;
    private Instant rentDate;
    private Instant expectedDeliveryDate;
    private Float price;
    private Boolean isFreeUpgrade;

    public RentOrder(Car car, User user, Instant rentDate, Instant expectedDeliveryDate, Float price, Boolean isFreeUpgrade) {
        this.car = car;
        this.user = user;
        this.rentDate = rentDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.price = price;
        this.isFreeUpgrade = isFreeUpgrade;
    }

    public Car getCar() {
        return car;
    }

    public User getUser() {
        return user;
    }

    public Instant getRentDate() {
        return rentDate;
    }

    public Instant getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public Float getPrice() {
        return price;
    }

    public Boolean getFreeUpgrade() {
        return isFreeUpgrade;
    }
}
