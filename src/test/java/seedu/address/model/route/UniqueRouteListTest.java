package seedu.address.model.route;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.TypicalRoutes.ROUTE_ALICE_BENSON;
import static seedu.address.testutil.TypicalRoutes.ROUTE_CARL;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.route.exceptions.DuplicateRouteException;
import seedu.address.model.route.exceptions.RouteNotFoundException;
import seedu.address.testutil.route.RouteBuilder;

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
        assertFalse(uniqueRouteList.contains(ROUTE_ALICE_BENSON));
    }

    @Test
    public void contains_routeInList_returnsTrue() {
        uniqueRouteList.add(ROUTE_ALICE_BENSON);
        assertTrue(uniqueRouteList.contains(ROUTE_ALICE_BENSON));
    }

    @Test
    public void add_nullRoute_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueRouteList.add(null);
    }

    @Test
    public void add_duplicateRoute_throwsDuplicateRouteException() {
        uniqueRouteList.add(ROUTE_ALICE_BENSON);
        thrown.expect(DuplicateRouteException.class);
        uniqueRouteList.add(ROUTE_ALICE_BENSON);
    }

    @Test
    public void setRoute_nullTargetRoute_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueRouteList.setRoute(null, ROUTE_ALICE_BENSON);
    }

    @Test
    public void setRoute_nullEditedRoute_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueRouteList.setRoute(ROUTE_ALICE_BENSON, null);
    }

    @Test
    public void setRoute_targetRouteNotInList_throwsRouteNotFoundException() {
        thrown.expect(RouteNotFoundException.class);
        uniqueRouteList.setRoute(ROUTE_ALICE_BENSON, ROUTE_ALICE_BENSON);
    }

    @Test
    public void setRoute_editedRouteIsSameRoute_success() {
        uniqueRouteList.add(ROUTE_ALICE_BENSON);
        uniqueRouteList.setRoute(ROUTE_ALICE_BENSON, ROUTE_ALICE_BENSON);
        UniqueRouteList expectedUniqueRouteList = new UniqueRouteList();
        expectedUniqueRouteList.add(ROUTE_ALICE_BENSON);
        assertEquals(expectedUniqueRouteList, uniqueRouteList);
    }

    @Test
    public void setRoute_editedRouteHasSameIdentity_success() {
        uniqueRouteList.add(ROUTE_ALICE_BENSON);
        Route editedRouteAliceBenson = new RouteBuilder(ROUTE_ALICE_BENSON)
                .withSource(VALID_ADDRESS_BOB).build();
        uniqueRouteList.setRoute(ROUTE_ALICE_BENSON, editedRouteAliceBenson);
        UniqueRouteList expectedUniqueRouteList = new UniqueRouteList();
        expectedUniqueRouteList.add(editedRouteAliceBenson);
        assertEquals(expectedUniqueRouteList, uniqueRouteList);
    }

    @Test
    public void setRoute_editedRouteHasDifferentIdentity_success() {
        uniqueRouteList.add(ROUTE_ALICE_BENSON);
        uniqueRouteList.setRoute(ROUTE_ALICE_BENSON, ROUTE_CARL);
        UniqueRouteList expectedUniqueRouteList = new UniqueRouteList();
        expectedUniqueRouteList.add(ROUTE_CARL);
        assertEquals(expectedUniqueRouteList, uniqueRouteList);
    }

    @Test
    public void setRoute_editedRouteHasNonUniqueIdentity_throwsDuplicateRouteException() {
        uniqueRouteList.add(ROUTE_ALICE_BENSON);
        uniqueRouteList.add(ROUTE_CARL);
        thrown.expect(DuplicateRouteException.class);
        uniqueRouteList.setRoute(ROUTE_ALICE_BENSON, ROUTE_CARL);
    }

    @Test
    public void remove_nullRoute_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueRouteList.remove(null);
    }

    @Test
    public void remove_routeDoesNotExist_throwsRouteNotFoundException() {
        thrown.expect(RouteNotFoundException.class);
        uniqueRouteList.remove(ROUTE_ALICE_BENSON);
    }

    @Test
    public void remove_existingRoute_removesRoute() {
        uniqueRouteList.add(ROUTE_ALICE_BENSON);
        uniqueRouteList.remove(ROUTE_ALICE_BENSON);
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
        uniqueRouteList.add(ROUTE_ALICE_BENSON);
        UniqueRouteList expectedUniqueRouteList = new UniqueRouteList();
        expectedUniqueRouteList.add(ROUTE_CARL);
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
        uniqueRouteList.add(ROUTE_ALICE_BENSON);
        List<Route> routeList = Collections.singletonList(ROUTE_CARL);
        uniqueRouteList.setRoutes(routeList);
        UniqueRouteList expectedUniqueRouteList = new UniqueRouteList();
        expectedUniqueRouteList.add(ROUTE_CARL);
        assertEquals(expectedUniqueRouteList, uniqueRouteList);
    }

    @Test
    public void setRoutes_listWithDuplicateRoutes_throwsDuplicateRouteException() {
        List<Route> listWithDuplicateRoutes = Arrays.asList(ROUTE_ALICE_BENSON, ROUTE_ALICE_BENSON);
        thrown.expect(DuplicateRouteException.class);
        uniqueRouteList.setRoutes(listWithDuplicateRoutes);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        uniqueRouteList.asUnmodifiableObservableList().remove(0);
    }
}
