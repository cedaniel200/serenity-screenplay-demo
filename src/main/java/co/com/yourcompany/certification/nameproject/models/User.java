package co.com.yourcompany.certification.nameproject.model;

import co.com.yourcompany.certification.nameproject.exceptions.UserModelCreationException;
import co.com.yourcompany.certification.nameproject.model.builders.UserBuilder;

import static co.com.yourcompany.certification.nameproject.util.validations.Validations.isEmptyOrNull;

public class User {

    private final String username;
    private final String password;

    public User(UserBuilder builder) {
        this.username = builder.getUsername();
        this.password = builder.getPassword();
        isValid();
    }

    private void isValid(){
        if(isEmptyOrNull(username) || isEmptyOrNull(password)){
            throw new UserModelCreationException(
                    String.format("Invalid username (%s) or password (%s)", username, password));
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}