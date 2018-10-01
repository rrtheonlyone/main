package seedu.address.model.route;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalRoutes.ALICE;
import static seedu.address.testutil.TypicalRoutes.BENNY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_MAGGIE;

import org.junit.Test;

import seedu.address.testutil.RouteBuilder;

public class RouteTest {

    @Test
    public void isSameRoute() {
        // same object -> returns true
        assertTrue(ALICE.isSameRoute(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameRoute(null));

        // different order -> returns false
        Route editedAlice = new RouteBuilder(ALICE)
                .withOrder(VALID_NAME_BOB, VALID_PHONE_BOB, VALID_ADDRESS_BOB, VALID_ORDER_MAGGIE).build();
        assertFalse(ALICE.isSameRoute(editedAlice));

        // different source -> returns false
        editedAlice = new RouteBuilder(ALICE).withSource(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.isSameRoute(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Route aliceCopy = new RouteBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different route -> returns false
        assertFalse(ALICE.equals(BENNY));

        // different source -> returns false
        Route editedAlice = new RouteBuilder(ALICE).withSource(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different deliveryman -> returns false
        editedAlice = new RouteBuilder(ALICE).withDeliveryman(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different order -> returns false
        editedAlice = new RouteBuilder(ALICE)
                .withOrder(VALID_NAME_BOB, VALID_PHONE_BOB, VALID_ADDRESS_BOB, VALID_ORDER_MAGGIE).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
