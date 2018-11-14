package service;

import domain.Car;
import domain.CarType;
import repository.CarRepository;

import java.util.List;

public class CarService {

    private CarRepository carRepository = new CarRepository();

    private void validateCar(Car car) throws Exception {
        if (car.getYear() < 2000) {
            throw new Exception("The car cannot be older than year 2000");
        }

        if(car.getType() == CarType.TRUCK){
            throw new Exception("Truck type is not allowed!");
        }


    }

    public void addCar(Car car) throws Exception {
        validateCar(car);
        carRepository.addCar(car);
    }

    public void modifyCar(Car car) throws Exception {
        validateCar(car);
        Car carDB = findCarById(car.getId());
        if (carDB != null) {
            carRepository.modifyCar(car);
        }
    }

    public void removeCar(Car car) throws Exception {
        Car carDB = findCarById(car.getId());
        if (carDB != null) {
            carRepository.removeCar(car);
        }
    }

    public Car findCarById(String id) {
        return carRepository.findCarById(id);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }


}
