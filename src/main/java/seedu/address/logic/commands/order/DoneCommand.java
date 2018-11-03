package seedu.address.logic.commands.order;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deliveryman.Deliveryman;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderStatus;

/**
 * Mark an order as COMPLETED.
 */
public class DoneCommand extends OrderCommand {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = OrderCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": Marks an order as COMPLETED \n"
            + "Parameters: INDEX (must be a positive integer) \n"
            + "Example: " + OrderCommand.COMMAND_WORD + " " + COMMAND_WORD + " 1 ";


    public static final String MESSAGE_COMPLETED_ORDER_SUCCESS = "Order %1$s have been completed.";
    public static final String MESSAGE_ONGOING_ORDER = "Only ONGOING status can be marked as completed.";

    private final Index targetIndex;

    public DoneCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        List<Order> lastShownList = model.getFilteredOrderList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToBeCompleted = lastShownList.get(targetIndex.getZeroBased());

        if (!orderToBeCompleted.getOrderStatus().equals(new OrderStatus("ONGOING"))) {
            throw new CommandException(MESSAGE_ONGOING_ORDER);
        }

        orderToBeCompleted.setStatusCompleted();
        Deliveryman deliverymanToRemoveOrder = orderToBeCompleted.getDeliveryman();
        Deliveryman updatedDeliveryman = removeOrderFromDeliveryman(deliverymanToRemoveOrder, orderToBeCompleted);

        model.updateOrder(orderToBeCompleted, orderToBeCompleted);
        model.updateDeliveryman(deliverymanToRemoveOrder, updatedDeliveryman);
        model.commitOrderBook();
        model.commitDeliverymenList();

        return new CommandResult(String.format(MESSAGE_COMPLETED_ORDER_SUCCESS, orderToBeCompleted));
    }

    /**
     * Remove order from deliveryman.
     */
    private static Deliveryman removeOrderFromDeliveryman(Deliveryman targetDeliveryman, Order targetOrder) {
        assert targetDeliveryman != null;
        assert targetOrder != null;

        targetDeliveryman.removeOrder(targetOrder);

        return targetDeliveryman;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneCommand // instanceof handles nulls
                && targetIndex.equals(((DoneCommand) other).targetIndex)); // state check
    }
}
