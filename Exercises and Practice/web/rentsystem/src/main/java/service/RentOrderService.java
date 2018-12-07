package service;

import com.google.common.annotations.VisibleForTesting;
import domain.RentOrder;
import exception.EntityNotFoundException;
import repository.RentOrderRepository;
import repository.Repository;

import java.util.List;

public class RentOrderService implements Service<RentOrder> {

    private static final int MAX_YEAR_ALLOWED = 2000;

    private Repository<RentOrder> rentOrderRepository;

    public RentOrderService() {
        rentOrderRepository = new RentOrderRepository();
    }

    public RentOrderService(Repository<RentOrder> rentOrderRepository) {
        this.rentOrderRepository = rentOrderRepository;
    }

    @VisibleForTesting
    void validate(RentOrder rentOrder) {

    }

    public void add(RentOrder rentOrder) {
        validate(rentOrder);
        rentOrderRepository.add(rentOrder);
    }

    public void modify(RentOrder rentOrder) {
        rentOrderRepository
                .findById(rentOrder.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Rent Order with id " + rentOrder.getId() + " was not found!"));

        validate(rentOrder);
        rentOrderRepository.modify(rentOrder);
    }

    public void remove(String id) {
        rentOrderRepository.remove(rentOrderRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Rent Order with id " + id + " was not found!"))
        );
    }

    public RentOrder findById(String id) {
        return rentOrderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("RentOrder with id " + id + " was not found!"));
    }

    public List<RentOrder> findAll() {
        return rentOrderRepository.findAll();
    }
}
