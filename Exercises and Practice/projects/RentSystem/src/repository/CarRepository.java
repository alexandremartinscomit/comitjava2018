package repository;

import domain.Car;

import java.util.ArrayList;
import java.util.List;

public class CarRepository {

    List<Car> cars = new ArrayList<>();

    public void addCar(Car car){
        cars.add(car);
    }

    public void modifyCar(Car car){
        Integer index = null;
        int i = 0;
        for (Car tempCar:cars) {
            if (car.getId().equals(tempCar.getId())){
                index = i;
                break;
            }
            i++;
        }
        cars.set(index, car);
    }

    public void removeCar(Car car){
        cars.remove(car);
    }

    public Car findCarById(String id){
        for (Car tempCar:cars) {
            if (id.equals(tempCar.getId())){
                return tempCar;
            }
        }
        return null;
    }

    public List<Car> findAll(){
        return cars;
    }
}
