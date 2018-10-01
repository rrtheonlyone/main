package seedu.address.testutil.user;

import seedu.address.model.UsersList;
import seedu.address.model.user.User;

/**
 * A utility class to help with building UsersList objects.
 * Example usage: <br>
 *     {@code UsersList usersList = new UsersListBuilder().withUsers("John", "Doe").build();}
 */
public class UsersListBuilder {

    private UsersList usersList;

    public UsersListBuilder() {
        this.usersList = usersList;
    }

    public UsersListBuilder(UsersList usersList) {
        this.usersList = usersList;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public UsersListBuilder withUser(User user) {
        usersList.addUser(user);
        return this;
    }

    public UsersList build() {
        return usersList;
    }
}
