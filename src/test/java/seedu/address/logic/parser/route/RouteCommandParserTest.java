package seedu.address.logic.parser.route;

import static org.junit.Assert.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ROUTE_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.route.CreateRouteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class RouteCommandParserTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final RouteCommandParser parser = new RouteCommandParser();

    @Test
    public void parse_create() throws Exception {
        CreateRouteCommand command = (CreateRouteCommand) parser.parse(
                CreateRouteCommand.COMMAND_WORD + " o/" + INDEX_FIRST.getOneBased());
        Set<Index> orderIds = new HashSet<>();
        orderIds.add(INDEX_FIRST);
        assertEquals(new CreateRouteCommand(orderIds), command);
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
