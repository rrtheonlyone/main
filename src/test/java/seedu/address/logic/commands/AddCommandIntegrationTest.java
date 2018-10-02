package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalOrders.getTypicalOrderBook;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;
import seedu.address.testutil.OrderBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalOrderBook(), new UserPrefs());
    }

    @Test
    public void execute_newOrder_success() {
        Order validOrder = new OrderBuilder().build();

        Model expectedModel = new ModelManager(model.getOrderBook(), new UserPrefs());
        expectedModel.addOrder(validOrder);
        expectedModel.commitOrderBook();

        assertCommandSuccess(new AddCommand(validOrder), model, commandHistory,
                String.format(AddCommand.MESSAGE_SUCCESS, validOrder), expectedModel);
    }

    @Test
    public void execute_duplicateOrder_throwsCommandException() {
        Order orderInList = model.getOrderBook().getOrderList().get(0);
        assertCommandFailure(new AddCommand(orderInList), model, commandHistory,
                AddCommand.MESSAGE_DUPLICATE_ORDER);
    }

}
