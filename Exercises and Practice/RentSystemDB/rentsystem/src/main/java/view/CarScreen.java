package view;

import domain.Car;
import service.CarService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CarScreen {

    static CarService carService = new CarService();

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        String id;
        String plate;
        String color;
        int year;
        String brand;
        String model;

        System.out.print("Plate: ");
        plate = scanner.next();

        System.out.println();

        System.out.print("Color: ");
        color = scanner.next();

        System.out.println();

        System.out.print("Year: ");
        year = scanner.nextInt();

        System.out.println();

        System.out.print("Brand: ");
        brand = scanner.next();

        System.out.println();

        System.out.print("Model: ");
        model = scanner.next();

        System.out.println();

        Car car = new Car(UUID.randomUUID().toString(),
                          plate, color, year, brand, model);

        try {
            carService.addCar(car);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        List<Car> carList = carService.findAll();

        System.out.println("Listing cars");

        for (Car tempCar:carList) {

            System.out.println("Car Id: "+ tempCar.getId());
            System.out.println(tempCar.getPlate());
            System.out.println(tempCar.getYear());
            System.out.println(tempCar.getColor());
            System.out.println(tempCar.getBrand());
            System.out.println(tempCar.getModel());
        }

        Car car2 = new Car(car.getId(),
                           car.getPlate(), car.getColor(), 2030, car.getBrand(), car.getModel());

        try {
            carService.modifyCar(car2);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("Listing cars");

        for (Car tempCar:carList) {

            System.out.println("Car Id: "+ tempCar.getId());
            System.out.println(tempCar.getPlate());
            System.out.println(tempCar.getYear());
            System.out.println(tempCar.getColor());
            System.out.println(tempCar.getBrand());
            System.out.println(tempCar.getModel());
        }

        try {
            carService.removeCar(car);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("Listing cars");

        for (Car tempCar:carList) {

            System.out.println("Car Id: "+ tempCar.getId());
            System.out.println(tempCar.getPlate());
            System.out.println(tempCar.getYear());
            System.out.println(tempCar.getColor());
            System.out.println(tempCar.getBrand());
            System.out.println(tempCar.getModel());
        }

    }
}
