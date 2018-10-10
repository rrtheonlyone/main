package seedu.address.logic.parser.route;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_ANGMOKIO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_ANGMOKIO;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalRoutes.ANGMOKIO;

import org.junit.Test;

import seedu.address.logic.commands.route.CreateRouteCommand;
import seedu.address.model.person.Address;
import seedu.address.model.route.Route;
import seedu.address.testutil.route.RouteBuilder;

public class CreateRouteCommandParserTest {
    private CreateRouteCommandParser parser = new CreateRouteCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Route expectedRoute = new RouteBuilder(ANGMOKIO).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ADDRESS_DESC_ANGMOKIO,
                new CreateRouteCommand(expectedRoute));

        assertParseSuccess(parser, ADDRESS_DESC_ANGMOKIO, new CreateRouteCommand(expectedRoute));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateRouteCommand.MESSAGE_USAGE);

        // missing address prefix
        assertParseFailure(parser, VALID_ADDRESS_ANGMOKIO, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_ADDRESS_DESC, Address.MESSAGE_ADDRESS_CONSTRAINTS);
    }

}
