package seedu.address.logic.parser.route;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.route.CreateRouteCommand;

public class CreateRouteCommandParserTest {
    private CreateRouteCommandParser parser = new CreateRouteCommandParser();

    @Test
    public void parse_validArgs_returnsCreateRouteCommand() {
        Set<Index> orderIds = new HashSet<>();
        orderIds.add(INDEX_FIRST);
        orderIds.add(INDEX_SECOND);
        assertParseSuccess(parser, " o/1 o/2", new CreateRouteCommand(orderIds));
    }

    @Test
    public void parse_invalidIndexNonNumber_failure() {
        // invalid index
        assertParseFailure(parser, " o/a", MESSAGE_INVALID_INDEX);
    }

}
