package domain;

public class User {

    private String id;
    private String email;
    private String password;
    private UserType type;

    public User(String id, String email, String password, UserType type) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.type = type;
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

    public UserType getType() {
        return type;
    }

    public enum UserType {
        ADMIN, CUSTOMER;
    }

}
