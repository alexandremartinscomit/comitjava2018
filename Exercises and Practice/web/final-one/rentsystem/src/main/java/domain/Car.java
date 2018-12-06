package domain;

import java.util.Objects;

public class Car {

    private String id;
    private String plate;
    private String color;
    private int year;
    private String brand;
    private String model;
    private CarType type;


    public Car(String id, String plate, String color,
               int year, String brand,
               String model,CarType type){
        this.id = id;
        this.plate = plate;
        this.color = color;
        this.year = year;
        this.brand = brand;
        this.model = model;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }

    public String getColor() {
        return color;
    }

    public int getYear() {
        return year;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public CarType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
