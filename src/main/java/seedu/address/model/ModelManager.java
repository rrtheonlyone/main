package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.AddressBookChangedEvent;
import seedu.address.commons.events.model.RouteListChangedEvent;
import seedu.address.model.person.Person;
import seedu.address.model.route.ReadOnlyRouteList;
import seedu.address.model.route.Route;
import seedu.address.model.route.RouteList;
import seedu.address.model.route.VersionedRouteList;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedAddressBook versionedAddressBook;
    private final VersionedRouteList versionedRouteList;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Route> filteredRoute;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyRouteList routeList, UserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, routeList, userPrefs);

        logger.fine("Initializing with address book: " + addressBook
                + " and route list " + routeList
                + " and user prefs " + userPrefs);

        versionedAddressBook = new VersionedAddressBook(addressBook);
        versionedRouteList = new VersionedRouteList(routeList);
        filteredPersons = new FilteredList<>(versionedAddressBook.getPersonList());
        filteredRoute = new FilteredList<>(versionedRouteList.getRouteList());
    }

    public ModelManager() {
        this(new AddressBook(), new RouteList(), new UserPrefs());
    }

    @Override
    public void resetData(ReadOnlyAddressBook newData) {
        versionedAddressBook.resetData(newData);
        indicateAddressBookChanged();
    }

    @Override
    public void resetRouteData(ReadOnlyRouteList newData) {
        versionedRouteList.resetData(newData);
        indicateRouteListChanged();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return versionedAddressBook;
    }

    @Override
    public ReadOnlyRouteList getRouteList() {
        return versionedRouteList;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateAddressBookChanged() {
        raise(new AddressBookChangedEvent(versionedAddressBook));
    }

    /** Raises an event to indicate the route model has changed */
    private void indicateRouteListChanged() {
        raise(new RouteListChangedEvent(versionedRouteList));
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return versionedAddressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        versionedAddressBook.removePerson(target);
        indicateAddressBookChanged();
    }

    @Override
    public void addPerson(Person person) {
        versionedAddressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        indicateAddressBookChanged();
    }

    @Override
    public void updatePerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        versionedAddressBook.updatePerson(target, editedPerson);
        indicateAddressBookChanged();
    }

    // ======================== Route related methods =========================

    @Override
    public boolean hasRoute(Route route) {
        requireNonNull(route);
        return versionedRouteList.hasRoute(route);
    }

    @Override
    public void deleteRoute(Route target) {
        versionedRouteList.removeRoute(target);
        indicateRouteListChanged();
    }

    @Override
    public void addRoute(Route route) {
        versionedRouteList.addRoute(route);
        indicateRouteListChanged();
    }

    @Override
    public void updateRoute(Route target, Route editedRoute) {
        versionedRouteList.updateRoute(target, editedRoute);
        indicateRouteListChanged();
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return FXCollections.unmodifiableObservableList(filteredPersons);
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Route List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Route} backed by the internal list of
     * {@code versionedRouteList}
     */
    @Override
    public ObservableList<Route> getFilteredRouteList() {
        return FXCollections.unmodifiableObservableList(filteredRoute);
    }

    @Override
    public void updateFilteredRouteList(Predicate<Route> predicate) {
        requireNonNull(predicate);
        filteredRoute.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoAddressBook() {
        return versionedAddressBook.canUndo();
    }

    @Override
    public boolean canRedoAddressBook() {
        return versionedAddressBook.canRedo();
    }

    @Override
    public void undoAddressBook() {
        versionedAddressBook.undo();
        indicateAddressBookChanged();
    }

    @Override
    public void redoAddressBook() {
        versionedAddressBook.redo();
        indicateAddressBookChanged();
    }

    @Override
    public void commitAddressBook() {
        versionedAddressBook.commit();
    }

    @Override
    public boolean canUndoRouteList() {
        return versionedRouteList.canUndo();
    }

    @Override
    public boolean canRedoRouteList() {
        return versionedRouteList.canRedo();
    }

    @Override
    public void undoRouteList() {
        versionedRouteList.undo();
        indicateRouteListChanged();
    }

    @Override
    public void redoRouteList() {
        versionedRouteList.redo();
        indicateRouteListChanged();
    }

    @Override
    public void commitRouteList() {
        versionedRouteList.commit();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedAddressBook.equals(other.versionedAddressBook)
                && filteredPersons.equals(other.filteredPersons)
                && versionedRouteList.equals(other.versionedRouteList)
                && filteredRoute.equals(other.filteredRoute);
    }

}
