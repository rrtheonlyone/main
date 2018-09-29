package seedu.address.model.deliveryman;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of unique deliverymen
 */
public class UniqueDeliverymenList implements Iterable<Deliveryman>{
    private final ObservableList<Deliveryman> internalList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Deliveryman toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds deliveryman to the list.
     * The deliveryman must not already exist in the list.
     */
    public void add(Deliveryman toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            //TODO: add appropriate exception for this
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the deliveryman {@code target} in the list with {@code edited}.
     * {@code target} must exist in the list.
     * The deliveryman identity of {@code edited} must not be the same as another deliveryman in the list.
     */
    public void setDeliverymen(Deliveryman target, Deliveryman edited) {
        requireAllNonNull(target, edited);

        int index = internalList.indexOf(target);
        if (index == -1) {
            //TODO: add appropriate exception
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(edited) && contains(edited)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, edited);
    }

    @Override
    public Iterator<Deliveryman> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof UniqueDeliverymenList
                    && internalList.equals(((UniqueDeliverymenList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    private boolean deliverymenAreUnique(List<Deliveryman> deliverymen) {
        for (int i = 0; i < deliverymen.size() - 1; i++) {
            for (int j = i + 1; j < deliverymen.size(); j++) {
                if (deliverymen.get(i).isSamePerson(deliverymen.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
