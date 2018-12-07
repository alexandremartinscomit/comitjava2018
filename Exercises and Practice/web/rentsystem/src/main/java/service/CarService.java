package service;

import com.google.common.annotations.VisibleForTesting;
import domain.Car;
import exception.EntityNotFoundException;
import exception.ValidationException;
import repository.CarRepository;
import repository.Repository;

import java.util.List;

public class CarService implements Service<Car> {

    private static final int MAX_YEAR_ALLOWED = 2000;

    private Repository<Car> carRepository;

    public CarService() {
        carRepository = new CarRepository();
    }

    public CarService(Repository<Car> carRepository) {
        this.carRepository = carRepository;
    }

    @VisibleForTesting
    void validate(Car car) {
        if (car.getYear() < MAX_YEAR_ALLOWED) {
            throw new ValidationException("The car cannot be older than year 2000");
        }

        if (isDuplicatedPlate(car)) {
            throw new ValidationException("There is another car with the same plate, please, choose another one");
        }
    }

    @VisibleForTesting
    boolean isDuplicatedPlate(Car car) {
        return carRepository
                .findByCriteria("plate", car.getPlate())
                .filter(c -> !c.getId().equals(car.getId()))
                .isPresent();
    }

    public void add(Car car) {
        validate(car);
        carRepository.add(car);
    }

    public void modify(Car car) {
        carRepository
                .findById(car.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Car with id " + car.getId() + " was not found!"));

        validate(car);
        carRepository.modify(car);
    }

    public void remove(String id) {
        carRepository.remove(carRepository
                                     .findById(id)
                                     .orElseThrow(
                                             () -> new EntityNotFoundException("Car with id " + id + " was not found!"))
        );
    }

    public Car findById(String id) {
        return carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Car with id " + id + " was not found!"));
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }
}
