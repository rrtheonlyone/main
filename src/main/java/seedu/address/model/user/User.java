package seedu.address.model.user;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.Name;
import seedu.address.model.person.Password;
import seedu.address.model.person.Username;

/**
 * Represents a User in the FoodZoom.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class User {

    // Identity fields
    private Name name;
    private final Username username;
    private final Password password;

    /**
     * Every field must be present and not null.
     */
    public User(Name name, Username username, Password password) {
        requireAllNonNull(name, username, password);
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public User(Username username, Password password) {
        requireAllNonNull(username, password);
        this.username = username;
        this.password = password;
    }

    public Name getName() {
        return name;
    }

    public Username getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }

    /**
     * Returns true if both users of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameUser(User otherUser) {
        if (otherUser == this) {
            return true;
        }

        return otherUser != null
                && otherUser.getName().equals(getName())
                && otherUser.getUsername().equals(getUsername());
    }

    /**
     * Returns true if both users of the same username and password.
     */
    public boolean isSameExistingUser(User otherUser) {
        if (otherUser == this) {
            return true;
        }

        return otherUser != null
                && otherUser.getUsername().equals(getUsername())
                && otherUser.getPassword().equals(getPassword());
    }




}
