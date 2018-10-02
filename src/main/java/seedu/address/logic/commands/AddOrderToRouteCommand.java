package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROUTE_ID;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;
import seedu.address.model.route.Route;

public class AddOrderToRouteCommand extends RouteCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an order to the route. "
            + "Parameters: "
            + PREFIX_ROUTE_ID + "ROUTE_ID "
            + PREFIX_ORDER_ID + "ORDER_ID\n"
            + "Example: /route " + COMMAND_WORD + " "
            + PREFIX_ROUTE_ID + "3 "
            + PREFIX_ORDER_ID + "5 ";

    public static final String MESSAGE_SUCCESS = "Added order %d to route %d.";

    private final Index routeId;
    private final Index orderId;

    /**
     * Creates an AddOrderToRouteCommand to add the specified {@code orderId} to {@code routeId}
     */
    public AddOrderToRouteCommand(Index routeId, Index orderId) {
        this.routeId = routeId;
        this.orderId = orderId;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        List<Route> lastShownRouteList = model.getFilteredRouteList();
        List<Order> lastShownOrderList = model.getFilteredOrderList();

        if (routeId.getZeroBased() >= lastShownRouteList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ROUTE_DISPLAYED_INDEX);
        }

        if (orderId.getZeroBased() >= lastShownOrderList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Route initialRoute = lastShownRouteList.get(routeId.getZeroBased());
        Order order = lastShownOrderList.get(orderId.getZeroBased());
        Route editedRoute = new Route(initialRoute.getSource(), initialRoute.getDeliveryman(), order);
        model.updateRoute(initialRoute, editedRoute);
        model.commitRouteList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, orderId.getOneBased(), routeId.getOneBased()));
    }
}
