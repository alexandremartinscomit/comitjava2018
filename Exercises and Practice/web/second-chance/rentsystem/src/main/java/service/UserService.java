package service;

import domain.User;
import exception.EntityNotFoundException;
import exception.ValidationException;
import repository.Repository;
import repository.UserRepository;

import java.util.List;

public class UserService implements Service<User> {

    private Repository<User> userRepository;

    public UserService(){
        userRepository = new UserRepository();
    }

    public UserService(Repository<User> userRepository){
        this.userRepository = userRepository;
    }

    private void validate(User user){
        if(isDuplicatedEmail(user)){
            throw new ValidationException("There is another user with the same email!");
        }
    }

    private boolean isDuplicatedEmail(User user){
        return userRepository.findByCriteria("email", user.getEmail())
                .filter(u -> !u.getId().equals(user.getId()))
                .isPresent();
    }

    @Override
    public void add(User user) {
        validate(user);
        userRepository.add(user);
    }

    @Override
    public void modify(User user) {
        findById(user.getId());
        validate(user);
        userRepository.modify(user);
    }

    @Override
    public void remove(String id) {
        userRepository.remove(findById(id));
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User with id " + id + " was not found!"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
