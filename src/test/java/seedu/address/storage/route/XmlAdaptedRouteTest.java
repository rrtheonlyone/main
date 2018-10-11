package seedu.address.storage.route;

import static org.junit.Assert.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_ANGMOKIO;
import static seedu.address.storage.route.XmlAdaptedRoute.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.TypicalRoutes.ANGMOKIO;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.testutil.Assert;

public class XmlAdaptedRouteTest {
    private static final String INVALID_ADDRESS = " ";

    @Test
    public void toModelType_validRouteDetails_returnsPerson() throws Exception {
        XmlAdaptedRoute route = new XmlAdaptedRoute(ANGMOKIO);
        assertEquals(ANGMOKIO, route.toModelType());
    }

    @Test
    public void toModelType_invalidSource_throwsIllegalValueException() {
        XmlAdaptedRoute route = new XmlAdaptedRoute(INVALID_ADDRESS, VALID_ADDRESS_ANGMOKIO);
        String expectedMessage = Address.MESSAGE_ADDRESS_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, route::toModelType);
    }

    @Test
    public void toModelType_invalidDestination_throwsIllegalValueException() {
        XmlAdaptedRoute route = new XmlAdaptedRoute(VALID_ADDRESS_ANGMOKIO, INVALID_ADDRESS);
        String expectedMessage = Address.MESSAGE_ADDRESS_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, route::toModelType);
    }

    @Test
    public void toModelType_nullSource_throwsIllegalValueException() {
        XmlAdaptedRoute route = new XmlAdaptedRoute((String) null, VALID_ADDRESS_ANGMOKIO);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, route::toModelType);
    }

    @Test
    public void toModelType_nullDestination_throwsIllegalValueException() {
        XmlAdaptedRoute route = new XmlAdaptedRoute(VALID_ADDRESS_ANGMOKIO, (String) null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, route::toModelType);
    }

}
