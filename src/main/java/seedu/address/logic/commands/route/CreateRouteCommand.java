package seedu.address.logic.commands.route;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;
import seedu.address.model.route.Route;

/**
 * Create a route with the given destination.
 */
public class CreateRouteCommand extends RouteCommand {

    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a route with a set of orders. "
            + "Parameters: "
            + PREFIX_ORDER + "ORDER_ID\n"
            + "Example: " + RouteCommand.COMMAND_WORD + " " + COMMAND_WORD + " "
            + PREFIX_ORDER + "1 " + PREFIX_ORDER + "3";

    public static final String MESSAGE_SUCCESS_ALL_VALID = "Route created with orders: %1$s";
    public static final String MESSAGE_SUCCESS_SOME_INVALID = "Route created with orders: %1$s,"
            + " failed to add orders: %2$s";
    public static final String MESSAGE_DUPLICATE_ROUTE = "This route already exists in the address book";

    private final Set<Index> orderIds;

    /**
     * Creates an AddCommand to add the specified {@code Route}
     */
    public CreateRouteCommand(Set<Index> orderIds) {
        requireNonNull(orderIds);
        this.orderIds = orderIds;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();
        Set<Order> ordersToAdd = new HashSet<>();
        Set<Index> validIndex = new HashSet<>();
        Set<Index> invalidIndex = new HashSet<>();

        for (Index id : orderIds) {
            if (id.getZeroBased() >= lastShownList.size()) {
                invalidIndex.add(id);
                continue;
            }
            validIndex.add(id);
            Order order = lastShownList.get(id.getZeroBased());
            ordersToAdd.add(order);
        }

        if (ordersToAdd.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        StringJoiner validSj = new StringJoiner(", ");
        for (Index i : validIndex) {
            validSj.add(Integer.toString(i.getOneBased()));
        }

        if (invalidIndex.isEmpty()) {
            return new CommandResult(String.format(MESSAGE_SUCCESS_ALL_VALID, validSj.toString()));
        }

        StringJoiner invalidSj = new StringJoiner(", ");
        for (Index i : invalidIndex) {
            invalidSj.add(Integer.toString(i.getOneBased()));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS_SOME_INVALID, validSj.toString(), invalidSj.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateRouteCommand // instanceof handles nulls
                && orderIds.equals(((CreateRouteCommand) other).orderIds));
    }

}
