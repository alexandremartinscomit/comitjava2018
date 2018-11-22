package domain;

public class User {

    private String id;
    private String email;
    private String password;
    private UserType userType;

    public User(String id, String email, String password, UserType userType) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    enum UserType {
        ADMIN, CUSTOMER;
    }

}
