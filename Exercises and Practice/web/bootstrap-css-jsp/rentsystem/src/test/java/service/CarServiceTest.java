package service;

import domain.Car;
import domain.CarType;
import exception.EntityNotFoundException;
import exception.ValidationException;
import org.junit.Before;
import org.junit.Test;
import repository.CarRepository;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;



public class CarServiceTest {

    private CarRepository carRepository;
    private CarService carService;

    @Before
    public void setUp() {
        carRepository = mock(CarRepository.class);
        carService = new CarService(carRepository);
    }

    private Car buildCar(){
        return new Car(UUID.randomUUID().toString(),
                "hxm123",
                "black",
                2003,
                "Toyota",
                "RAV4",
                CarType.SUV);
    }

    @Test
    public void isDuplicatedPlate_withDuplicatedResult() {
        Car car = buildCar();

        Car dbCar = new Car(UUID.randomUUID().toString(),
                "hxm123",
                "white",
                2005,
                "Honda",
                "CIVIC",
                CarType.SPORT);

        when(carRepository.findByCriteria("plate",car.getPlate())).thenReturn(Optional.of(dbCar));

        boolean result = carService.isDuplicatedPlate(car);

        assertThat(result, is(true));
    }

    @Test
    public void isDuplicatedPlate_withNoDuplicatedResult() {
        Car car = buildCar();


        when(carRepository.findByCriteria("plate", car.getPlate())).thenReturn(Optional.empty());

        boolean result = carService.isDuplicatedPlate(car);

        assertThat(result, is(false));
    }

    @Test(expected = ValidationException.class)
    public void validate_testCarOlderThanCriteria(){
        Car car = new Car(UUID.randomUUID().toString(),
                "hxm123",
                "black",
                1900,
                "Toyota",
                "RAV4",
                CarType.SUV);

        when(carRepository.findByCriteria("plate",car.getPlate())).thenReturn(Optional.empty());

        carService.validate(car);

    }

    @Test
    public void validate_testCarNewerThanCriteria(){
        Car car = new Car(UUID.randomUUID().toString(),
                "hxm123",
                "black",
                2018,
                "Toyota",
                "RAV4",
                CarType.SUV);

        when(carRepository.findByCriteria("plate",car.getPlate())).thenReturn(Optional.empty());

        carService.validate(car);
    }

    @Test
    public void validate_testCarEqualThanCriteria(){
        Car car = new Car(UUID.randomUUID().toString(),
                "hxm123",
                "black",
                2000,
                "Toyota",
                "RAV4",
                CarType.SUV);

        when(carRepository.findByCriteria("plate",car.getPlate())).thenReturn(Optional.empty());

        carService.validate(car);
    }

    @Test
    public void add_addCarCorrect(){
        Car car = buildCar();

        when(carRepository.findByCriteria("plate",car.getPlate())).thenReturn(Optional.empty());

        carService.add(car);
        verify(carRepository,times(1)).add(car);
    }

    @Test
    public void modify_updateCarCorrect(){
        Car car = buildCar();

        when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));
        when(carRepository.findByCriteria("plate",car.getPlate())).thenReturn(Optional.empty());

        carService.modify(car);
        verify(carRepository,times(1)).modify(car);
    }

    @Test(expected = EntityNotFoundException.class)
    public void modify_updateCarDoesntExist(){
        Car car = buildCar();

        when(carRepository.findById(car.getId())).thenReturn(Optional.empty());

        carService.modify(car);
    }

    @Test(expected = EntityNotFoundException.class)
    public void remove_removeCarDoesntExist(){
        Car car = buildCar();

        when(carRepository.findById(car.getId())).thenReturn(Optional.empty());

        carService.remove(car.getId());
    }


}
