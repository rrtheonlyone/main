package seedu.address.model.route;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_MAGGIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.TypicalRoutes.ALICE;
import static seedu.address.testutil.TypicalRoutes.BENNY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.route.exceptions.DuplicateRouteException;
import seedu.address.model.route.exceptions.RouteNotFoundException;
import seedu.address.testutil.RouteBuilder;

public class UniqueRouteListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final UniqueRouteList uniqueRouteList = new UniqueRouteList();

    @Test
    public void contains_nullRoute_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueRouteList.contains(null);
    }

    @Test
    public void contains_routeNotInList_returnsFalse() {
        assertFalse(uniqueRouteList.contains(ALICE));
    }

    @Test
    public void contains_routeInList_returnsTrue() {
        uniqueRouteList.add(ALICE);
        assertTrue(uniqueRouteList.contains(ALICE));
    }

    @Test
    public void contains_routeWithSameIdentityFieldsInList_returnsTrue() {
        uniqueRouteList.add(ALICE);
        Route editedAlice = new RouteBuilder(ALICE)
                .withDeliveryman(VALID_NAME_BOB).build();
        assertTrue(uniqueRouteList.contains(editedAlice));
    }

    @Test
    public void add_nullRoute_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueRouteList.add(null);
    }

    @Test
    public void add_duplicateRoute_throwsDuplicateRouteException() {
        uniqueRouteList.add(ALICE);
        thrown.expect(DuplicateRouteException.class);
        uniqueRouteList.add(ALICE);
    }

    @Test
    public void setRoute_nullTargetRoute_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueRouteList.setRoute(null, ALICE);
    }

    @Test
    public void setRoute_nullEditedRoute_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueRouteList.setRoute(ALICE, null);
    }

    @Test
    public void setRoute_targetRouteNotInList_throwsRouteNotFoundException() {
        thrown.expect(RouteNotFoundException.class);
        uniqueRouteList.setRoute(ALICE, ALICE);
    }

    @Test
    public void setRoute_editedRouteIsSameRoute_success() {
        uniqueRouteList.add(ALICE);
        uniqueRouteList.setRoute(ALICE, ALICE);
        UniqueRouteList expectedUniqueRouteList = new UniqueRouteList();
        expectedUniqueRouteList.add(ALICE);
        assertEquals(expectedUniqueRouteList, uniqueRouteList);
    }

    @Test
    public void setRoute_editedRouteHasSameIdentity_success() {
        uniqueRouteList.add(ALICE);
        Route editedAlice = new RouteBuilder(ALICE)
                .withOrder(VALID_NAME_BOB, VALID_PHONE_BOB, VALID_ADDRESS_BOB, VALID_ORDER_MAGGIE).build();
        uniqueRouteList.setRoute(ALICE, editedAlice);
        UniqueRouteList expectedUniqueRouteList = new UniqueRouteList();
        expectedUniqueRouteList.add(editedAlice);
        assertEquals(expectedUniqueRouteList, uniqueRouteList);
    }

    @Test
    public void setRoute_editedRouteHasDifferentIdentity_success() {
        uniqueRouteList.add(ALICE);
        uniqueRouteList.setRoute(ALICE, BENNY);
        UniqueRouteList expectedUniqueRouteList = new UniqueRouteList();
        expectedUniqueRouteList.add(BENNY);
        assertEquals(expectedUniqueRouteList, uniqueRouteList);
    }

    @Test
    public void setRoute_editedRouteHasNonUniqueIdentity_throwsDuplicateRouteException() {
        uniqueRouteList.add(ALICE);
        uniqueRouteList.add(BENNY);
        thrown.expect(DuplicateRouteException.class);
        uniqueRouteList.setRoute(ALICE, BENNY);
    }

    @Test
    public void remove_nullRoute_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueRouteList.remove(null);
    }

    @Test
    public void remove_routeDoesNotExist_throwsRouteNotFoundException() {
        thrown.expect(RouteNotFoundException.class);
        uniqueRouteList.remove(ALICE);
    }

    @Test
    public void remove_existingRoute_removesRoute() {
        uniqueRouteList.add(ALICE);
        uniqueRouteList.remove(ALICE);
        UniqueRouteList expectedUniqueRouteList = new UniqueRouteList();
        assertEquals(expectedUniqueRouteList, uniqueRouteList);
    }

    @Test
    public void setRoutes_nullUniqueRouteList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueRouteList.setRoutes((UniqueRouteList) null);
    }

    @Test
    public void setRoutes_uniqueRouteList_replacesOwnListWithProvidedUniqueRouteList() {
        uniqueRouteList.add(ALICE);
        UniqueRouteList expectedUniqueRouteList = new UniqueRouteList();
        expectedUniqueRouteList.add(BENNY);
        uniqueRouteList.setRoutes(expectedUniqueRouteList);
        assertEquals(expectedUniqueRouteList, uniqueRouteList);
    }

    @Test
    public void setRoutes_nullList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueRouteList.setRoutes((List<Route>) null);
    }

    @Test
    public void setRoutes_list_replacesOwnListWithProvidedList() {
        uniqueRouteList.add(ALICE);
        List<Route> routeList = Collections.singletonList(BENNY);
        uniqueRouteList.setRoutes(routeList);
        UniqueRouteList expectedUniqueRouteList = new UniqueRouteList();
        expectedUniqueRouteList.add(BENNY);
        assertEquals(expectedUniqueRouteList, uniqueRouteList);
    }

    @Test
    public void setRoutes_listWithDuplicateRoutes_throwsDuplicateRouteException() {
        List<Route> listWithDuplicateRoutes = Arrays.asList(ALICE, ALICE);
        thrown.expect(DuplicateRouteException.class);
        uniqueRouteList.setRoutes(listWithDuplicateRoutes);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        uniqueRouteList.asUnmodifiableObservableList().remove(0);
    }
}
