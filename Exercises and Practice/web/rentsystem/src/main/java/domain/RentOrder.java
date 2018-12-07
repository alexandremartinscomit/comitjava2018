package domain;

import java.time.Instant;

public class RentOrder {

    private String id;
    private Car car;
    private User user;
    private Instant rentDate;
    private Instant expectedDeliveryDate;
    private float price;
    private boolean freeUpgrade;
    private String observations;

    public RentOrder(String id, Car car, User user, Instant rentDate, Instant expectedDeliveryDate, float price, boolean freeUpgrade, String observations) {
        this.id = id;
        this.car = car;
        this.user = user;
        this.rentDate = rentDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.price = price;
        this.freeUpgrade = freeUpgrade;
        this.observations = observations;
    }

    public String getId(){
        return id;
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

    public float getPrice() {
        return price;
    }

    public boolean getFreeUpgrade() {
        return freeUpgrade;
    }

    public String getObservations() {
        return observations;
    }
}
