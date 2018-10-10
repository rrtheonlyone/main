package seedu.address.testutil.route;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;

import seedu.address.logic.commands.route.CreateRouteCommand;
import seedu.address.model.route.Route;

/**
 * A utility class for Route.
 */
public class RouteUtil {

    /**
     * Returns an add command string for adding the {@code route}.
     */
    public static String getCreateRouteCommand(Route route) {
        return CreateRouteCommand.COMMAND_WORD + " " + getRouteDetails(route);
    }

    /**
     * Returns the part of command string for the given {@code route}'s details.
     */
    public static String getRouteDetails(Route route) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_ADDRESS + route.getDestination().value + " ");
        return sb.toString();
    }

}
