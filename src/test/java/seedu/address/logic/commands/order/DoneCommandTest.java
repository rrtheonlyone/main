package seedu.address.logic.commands.order;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDeliverymen.CHIKAO;
import static seedu.address.testutil.TypicalDeliverymen.getTypicalDeliverymenList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalOrders.ALICE;
import static seedu.address.testutil.TypicalOrders.getTypicalOrderBook;
import static seedu.address.testutil.user.TypicalUsers.getTypicalUsersList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.deliveryman.Deliveryman;
import seedu.address.model.order.Order;
import seedu.address.testutil.DeliverymanBuilder;
import seedu.address.testutil.OrderBuilder;

public class DoneCommandTest {

    private Model model = new ModelManager(getTypicalOrderBook(), getTypicalUsersList(),
            getTypicalDeliverymenList(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    private Set<Index> ordersSet = new HashSet<>(Arrays.asList(INDEX_FIRST));

    @Test
    public void execute_validIndexUnfilteredList_success() throws CommandException {
        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST, ordersSet);
        assignCommand.execute(model, commandHistory);

        Order expectedOrder = new OrderBuilder(ALICE).withStatus("COMPLETED").build();

        Model expectedModel = new ModelManager(model.getOrderBook(), model.getUsersList(),
                model.getDeliverymenList(), new UserPrefs());

        expectedModel.commitOrderBook();
        expectedModel.commitDeliverymenList();
        expectedModel.commitOrderBook();
        expectedModel.commitDeliverymenList();

        Order orderTobeCompleted = model.getFilteredOrderList().get(0);
        Deliveryman deliveryman = new DeliverymanBuilder(CHIKAO).build();
        deliveryman.addOrder(orderTobeCompleted);

        Order completedOrder = new OrderBuilder(orderTobeCompleted).build();
        completedOrder.setStatusCompleted();

        Deliveryman updatedDeliveryman = orderTobeCompleted.getDeliveryman();
        updatedDeliveryman.removeOrder(orderTobeCompleted);

        expectedModel.updateOrder(orderTobeCompleted, completedOrder);
        expectedModel.commitOrderBook();
        expectedModel.updateDeliveryman(deliveryman, updatedDeliveryman);
        expectedModel.commitDeliverymenList();
        
        assertExecutionSuccess(INDEX_FIRST, expectedOrder, expectedModel);
    }

    /**
     * Executes a {@code DoneCommand} with the given {@code index}, and checks that {@code JumpToListRequestEvent}
     * is raised with the correct index.
     */
    private void assertExecutionSuccess(Index index, Order expectedOrder, Model expectedModel) {
        DoneCommand doneCommand = new DoneCommand(index);
        String expectedMessage = String.format(DoneCommand.MESSAGE_COMPLETED_ORDER_SUCCESS, expectedOrder);

        assertCommandSuccess(doneCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    /**
     * Executes a {@code SelectCommand} with the given {@code index}, and checks that a {@code CommandException}
     * is thrown with the {@code expectedMessage}.
     */
    private void assertExecutionFailure(Index index, String expectedMessage) {
        SelectCommand selectCommand = new SelectCommand(index);
        assertCommandFailure(selectCommand, model, commandHistory, expectedMessage);
    }
}
