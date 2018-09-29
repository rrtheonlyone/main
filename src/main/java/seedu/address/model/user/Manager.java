package seedu.address.model.user;

import seedu.address.model.person.Name;
import seedu.address.model.person.Password;
import seedu.address.model.person.Username;


public class Manager extends User {

    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param username
     * @param password
     */
    public Manager(Name name, Username username, Password password) {
        super(name, username, password);
    }

    public Manager(Username username, Password password) {
        super(new Name(""), username, password);
    }
}

