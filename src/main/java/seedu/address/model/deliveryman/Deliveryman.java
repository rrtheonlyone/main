package seedu.address.model.deliveryman;

import seedu.address.model.person.Name;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Deliveryman {
    private final Name name;

    public Deliveryman(Name name) {
        requireAllNonNull(name);
        this.name = name;
    }

    public Name getName() {
        return name;
    }
}
