package service;

import domain.User;
import exception.EntityNotFoundException;
import exception.ValidationException;
import repository.Repository;
import repository.UserRepository;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService implements Service<User> {

    private Repository<User> userRepository;

    private AuthenticationService authenticationService;

    private Pattern pattern;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public UserService(){
        userRepository = new UserRepository();
        authenticationService = new AuthenticationService();
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public UserService(Repository<User> userRepository, AuthenticationService authenticationService){
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    private void validate(User user){
        if(isEmailInvalid(user.getEmail())){
            throw new ValidationException("The email is invalid!");
        }
        if(isDuplicatedEmail(user)){
            throw new ValidationException("There is another user with the same email!");
        }
    }

    public boolean isEmailInvalid(final String email){
        return !pattern.matcher(email).matches();
    }

    private boolean isDuplicatedEmail(User user){
        return userRepository.findByCriteria("email", user.getEmail())
                .filter(u -> !u.getId().equals(user.getId()))
                .isPresent();
    }

    @Override
    public void add(User user) {
        validate(user);
        user.setPassword(authenticationService.generateSecurePassword(user.getPassword()));
        userRepository.add(user);
    }

    @Override
    public void modify(User user) {
        User userDB = findById(user.getId());
        validate(user);
        user.setPassword(userDB.getPassword());
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

    public User findByEmail(String email){
        return userRepository.findByCriteria("email", email)
                .orElseThrow(() -> new EntityNotFoundException("The user doesn't exist"));

    }
}
