package co.com.yourcompany.certification.nameproject.model.builders;

import co.com.yourcompany.certification.nameproject.exceptions.UserModelCreationException;
import co.com.yourcompany.certification.nameproject.model.User;
import co.com.yourcompany.certification.nameproject.util.builder.Builder;

public class UserBuilder implements Builder<User> {

    private String username;
    private String password;

    private UserBuilder(String username) {
        this.username = username;
    }

    public User withPassword(String password) throws UserModelCreationException {
        this.password = password;
        return this.build();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public User build() throws UserModelCreationException {
        return new User(this);
    }

    public static UserBuilder theUser(String username){
        return new UserBuilder(username);
    }

}
