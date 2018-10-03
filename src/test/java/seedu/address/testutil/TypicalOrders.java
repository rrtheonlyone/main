package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FOOD_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FOOD_RICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.OrderBook;
import seedu.address.model.order.Order;

/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrders {
    public static final Order ALICE = new OrderBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withPhone("94351253")
            .withFood("Roti Prata").build();

    public static final Order BENSON = new OrderBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withPhone("98765432")
            .withFood("Egg Fried Rice", "Milo").build();

    public static final Order CARL = new OrderBuilder().withName("Carl Kurz").withPhone("95352563")
            .withAddress("wall street").withFood("Milkshake").build();
    public static final Order DANIEL = new OrderBuilder().withName("Daniel Meier").withPhone("87652533")
            .withAddress("10th street").withFood("Fish and Chips").build();
    public static final Order ELLE = new OrderBuilder().withName("Elle Meyer").withPhone("9482224")
            .withAddress("michegan ave").withFood("Chicken Chop").build();
    public static final Order FIONA = new OrderBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withAddress("little tokyo").withFood("Tuna Sandwich").build();
    public static final Order GEORGE = new OrderBuilder().withName("George Best").withPhone("9482442")
            .withAddress("4th street").withFood("Ice cream").build();

    //Manually added
    public static final Order HOON = new OrderBuilder().withName("Hoon Meier").withPhone("8482424")
            .withAddress("little india").withFood("Chocolate Milkshake").build();
    public static final Order IDA = new OrderBuilder().withName("Ida Mueller").withPhone("8482131")
            .withAddress("chicago ave").withFood("Bubble Tea").build();

    // Manually added - Order's details found in {@code CommandTestUtil}
    public static final Order AMY = new OrderBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withAddress(VALID_ADDRESS_AMY).withFood(VALID_FOOD_BURGER).build();
    public static final Order BOB = new OrderBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withAddress(VALID_ADDRESS_BOB).withFood(VALID_FOOD_RICE)
            .build();


    private TypicalOrders() {
    } // prevents instantiation

    /**
     * Returns an {@code OrderBook} with all the typical orders.
     */
    public static OrderBook getTypicalOrderBook() {
        OrderBook ab = new OrderBook();
        for (Order order : getTypicalOrders()) {
            ab.addOrder(order);
        }
        return ab;
    }

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
