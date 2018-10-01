package seedu.address.testutil.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.UsersList;
import seedu.address.model.user.User;

public class TypicalUsers {
    public static final User ALICE = new UserBuilder()
            .withName("Alice Pauline")
            .withUsername("alicepauline")
            .withPassword("alicepauline01")
            .build();

    public static final User BENSON = new UserBuilder()
            .withName("Benson Meier")
            .withUsername("bensonmeier")
            .withPassword("bensonmeier02")
            .build();

    public static final User CARL = new UserBuilder()
            .withName("Carl Kurz")
            .withUsername("carlkurz")
            .withPassword("carlkurz03")
            .build();

    private TypicalUsers() {} // prevents instantiation

    /**
     * Returns an {@code UsersList} with all the typical users.
     */
    public static UsersList getTypicalUsersList() {
        UsersList usersList = new UsersList();
        for (User user : getTypicalUsers()) {
            usersList.addUser(user);
        }
        return usersList;
    }

    public static List<User> getTypicalUsers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL));
    }
}
