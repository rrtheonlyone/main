package seedu.address.logic.commands.order;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.common.Address;
import seedu.address.model.common.Name;
import seedu.address.model.common.Phone;
import seedu.address.model.deliveryman.Deliveryman;
import seedu.address.model.order.Food;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderDate;
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

        Order completedOrder = createCompletedOrder(orderToBeCompleted);
        Deliveryman deliverymanToRemoveOrder = orderToBeCompleted.getDeliveryman();
        Deliveryman updatedDeliveryman = removeOrderFromDeliveryman(deliverymanToRemoveOrder, orderToBeCompleted);

        model.updateOrder(orderToBeCompleted, completedOrder);
        model.updateDeliveryman(deliverymanToRemoveOrder, updatedDeliveryman);
        model.commitOrderBook();

        return new CommandResult(String.format(MESSAGE_COMPLETED_ORDER_SUCCESS, completedOrder));
    }

    /**
     * Creates and returns a {@code Order} with the details of {@code orderToEdit}
     * edited with OrderStatus as COMPLETED.
     */
    private static Order createCompletedOrder(Order orderToBeCompleted) {
        assert orderToBeCompleted != null;

        Name updatedName = orderToBeCompleted.getName();
        Phone updatedPhone = orderToBeCompleted.getPhone();
        Address updatedAddress = orderToBeCompleted.getAddress();
        OrderDate updatedDate = orderToBeCompleted.getDate();
        Set<Food> updatedFood = orderToBeCompleted.getFood();
        OrderStatus orderStatus = new OrderStatus("COMPLETED");
        Deliveryman deliveryman = orderToBeCompleted.getDeliveryman();

        return new Order(updatedName, updatedPhone, updatedAddress, updatedDate, orderStatus, updatedFood, deliveryman);
    }

    /**
     * Remove order from deliveryman.
     */
    private static Deliveryman removeOrderFromDeliveryman(Deliveryman targetDeliveryman, Order targetOrder) {
        assert targetDeliveryman != null;

        targetDeliveryman.removeOrder(targetOrder);

        return targetDeliveryman;
    }
}
