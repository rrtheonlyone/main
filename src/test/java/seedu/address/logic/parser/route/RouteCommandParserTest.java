package seedu.address.logic.parser.route;

import static org.junit.Assert.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ROUTE_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.route.CreateRouteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.route.Route;
import seedu.address.testutil.route.RouteBuilder;
import seedu.address.testutil.route.RouteUtil;

public class RouteCommandParserTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final RouteCommandParser parser = new RouteCommandParser();

    @Test
    public void parse_create() throws Exception {
        Route route = new RouteBuilder().build();
        CreateRouteCommand command = (CreateRouteCommand) parser.parse(RouteUtil.getCreateRouteCommand(route));
        assertEquals(new CreateRouteCommand(route), command);
    }

    @Test
    public void parse_unrecognisedInput_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(String.format(MESSAGE_INVALID_ROUTE_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        parser.parse("");
        parser.parse("3");
    }

    @Test
    public void parse_unknownCommand_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(MESSAGE_UNKNOWN_COMMAND);
        parser.parse("unknownCommand");
    }

}
