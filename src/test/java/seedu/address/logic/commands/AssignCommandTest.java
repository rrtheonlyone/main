package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.AssignCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDeliverymen.CHIKAO;
import static seedu.address.testutil.TypicalDeliverymen.getTypicalDeliverymenList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalOrders.getTypicalOrderBook;
import static seedu.address.testutil.TypicalRoutes.getTypicalRouteList;
import static seedu.address.testutil.user.TypicalUsers.getTypicalUsersList;

import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.deliveryman.Deliveryman;
import seedu.address.model.order.Order;

public class AssignCommandTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager(getTypicalOrderBook(), getTypicalRouteList(), getTypicalUsersList(),
            getTypicalDeliverymenList(), new UserPrefs());
    private Order validOrder = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
    private Index validOrderIndex = Index.fromOneBased(model.getFilteredOrderList().size());
    private Index validDeliverymanIndex = Index.fromOneBased(model.getFilteredDeliverymenList().size());
    private Index outOfBoundOrderIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
    private Index outOfBoundDeliverymanIndex = Index.fromOneBased(model.getFilteredDeliverymenList().size() + 1);
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullDeliveryman_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        Set<Index> validOrderIds = new HashSet<>();
        validOrderIds.add(validOrderIndex);
        new AssignCommand(null, validOrderIds);
    }

    @Test
    public void constructor_nullOrderIds_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AssignCommand(validDeliverymanIndex, null);
    }

    @Test
    public void execute_orderAssignedToDeliveryman_successful() {
        Set<Order> ordersToAdd = new HashSet<>();
        Order toAssign = new Order(validOrder);
        ordersToAdd.add(toAssign);
        Deliveryman deliverymanToAssign = model.getFilteredDeliverymenList().get(validDeliverymanIndex.getZeroBased());
        Deliveryman assignedDeliveryman = new Deliveryman(deliverymanToAssign);
        toAssign.setDeliveryman(assignedDeliveryman);

        Set<Index> orderIds = new HashSet<>();
        orderIds.add(INDEX_FIRST);
        AssignCommand assignCommand = new AssignCommand(validDeliverymanIndex, orderIds);
        String expectedMessage = String.format(MESSAGE_SUCCESS, INDEX_FIRST.getOneBased(), assignedDeliveryman);

        Model expectedModel = new ModelManager(model.getOrderBook(), model.getRouteList(), model.getUsersList(),
                model.getDeliverymenList(), new UserPrefs());
        expectedModel.updateDeliveryman(deliverymanToAssign, assignedDeliveryman);
        expectedModel.updateOrder(validOrder, toAssign);

        assertTrue(toAssign.getDeliveryman().equals(assignedDeliveryman));
        assertTrue(assignedDeliveryman.getOrders().contains(toAssign));
        assertCommandSuccess(assignCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_orderAlreadyAssignedToDeliveryman_failure() {
        Order order = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
        order.setDeliveryman(CHIKAO);

        Set<Order> ordersToAdd = new HashSet<>();
        Order toAssign = new Order(order);
        ordersToAdd.add(toAssign);

        Set<Index> orderIds = new HashSet<>();
        orderIds.add(INDEX_FIRST);
        Deliveryman newDeliveryman = model.getFilteredDeliverymenList().get(validDeliverymanIndex.getZeroBased());
        AssignCommand assignCommand = new AssignCommand(validDeliverymanIndex, orderIds);
        String expectedMessage = String.format(String.format(Messages.MESSAGE_ORDER_ALREADY_ASSIGNED_TO_DELIVERYMAN,
                INDEX_FIRST.getOneBased(), CHIKAO));

        assertCommandFailure(assignCommand, model, commandHistory, expectedMessage);
        assertFalse(toAssign.getDeliveryman().equals(newDeliveryman));
        assertTrue(CHIKAO.getOrders().contains(toAssign));
        assertFalse(newDeliveryman.getOrders().contains(toAssign));
    }

    @Test
    public void execute_orderIndexTooLarge_throwsCommandException() throws Exception {
        Set<Index> orderIds = new HashSet<>();
        orderIds.add(outOfBoundOrderIndex);
        AssignCommand assignCommand = new AssignCommand(validDeliverymanIndex, orderIds);

        thrown.expect(CommandException.class);
        thrown.expectMessage(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        assignCommand.execute(model, commandHistory);
    }

    @Test
    public void execute_deliverymanIndexTooLarge_throwsCommandException() throws Exception {
        Set<Index> orderIds = new HashSet<>();
        orderIds.add(validOrderIndex);
        AssignCommand assignCommand = new AssignCommand(outOfBoundDeliverymanIndex, orderIds);

        thrown.expect(CommandException.class);
        thrown.expectMessage(Messages.MESSAGE_INVALID_DELIVERYMAN_DISPLAYED_INDEX);
        assignCommand.execute(model, commandHistory);
    }

    @Test
    public void equals() {
        Set<Index> indexOne = new HashSet<>();
        indexOne.add(INDEX_FIRST);
        Set<Index> indexTwo = new HashSet<>();
        indexOne.add(INDEX_SECOND);
        AssignCommand assignFirstCommand = new AssignCommand(validDeliverymanIndex, indexOne);
        AssignCommand assignSecondCommand = new AssignCommand(validDeliverymanIndex, indexTwo);

        // same object -> returns true
        assertTrue(assignFirstCommand.equals(assignFirstCommand));

        // same values -> returns true
        AssignCommand addFirstCommandCopy = new AssignCommand(validDeliverymanIndex, indexOne);
        assertTrue(assignFirstCommand.equals(addFirstCommandCopy));

        // different types -> returns false
        assertFalse(assignFirstCommand.equals(1));

        // null -> returns false
        assertFalse(assignFirstCommand.equals(null));

        // different destination -> returns false
        assertFalse(assignFirstCommand.equals(assignSecondCommand));
    }
}
