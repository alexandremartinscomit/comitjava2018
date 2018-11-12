package service;

import domain.Car;
import repository.CarRepository;

import java.util.List;

public class CarService {

    private CarRepository carRepository = new CarRepository();

    public void addCar(Car car){
        if(car.getYear() >= 2000) {
            carRepository.addCar(car);
        }
    }

    public void modifyCar(Car car){
        Car carDB = findCarById(car.getId());
        if(carDB != null){
            carRepository.modifyCar(car);
        }
    }

    public void removeCar(Car car){
        Car carDB = findCarById(car.getId());
        if(carDB != null) {
            carRepository.removeCar(car);
        }
    }

    public Car findCarById(String id){
        return carRepository.findCarById(id);
    }

    public List<Car> findAll(){
        return carRepository.findAll();
    }


}
